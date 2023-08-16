import { Component, OnInit } from '@angular/core';
import { UserModel } from 'src/app/models/UserModel';
import { FormBuilder, FormGroup, Validators } from '@angular/forms'
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;

  firstName: String;
  lastName: String;
  email: String;
  password: String;
  cpassword: String;

  constructor(private fb: FormBuilder, private userService: UserService) { }

  ngOnInit(): void {
    this.registerForm = this.fb.group({
      firstName: ['', [Validators.required]],
      lastName:['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]],
      cpassword: ['', [Validators.required]]
    },
    {
      validators: this.confirmChecker.bind(this)
    }
    )
  }

  confirmChecker(formGroup: FormGroup)
  {
    let password = formGroup.get('password')?.value
    let cpassword = formGroup.get('cpassword')?.value

    // console.log(password + " " + cpassword);
    
    return password === cpassword ? null : {passwordNotMatched: true}
  }

  handleSubmit(form: FormGroup){
    // console.log(this.registerForm.value);
    // console.log(this.registerForm.valid);
    // console.log(this.registerForm.errors);
    // console.log(this.registerForm.get("email")?.errors);

    this.userService.register({
      firstName: this.registerForm.value.firstName,
      lastName: this.registerForm.value.lastName,
      username: this.registerForm.value.email,
      password: this.registerForm.value.password
    }).subscribe((res: any) => {
      console.log(res);
      // console.log(res.status);
    }, (res) => {
      if(res.status === 200)
      {
        alert('Signup Success!');
        this.registerForm.reset();
      }
      else if(res.status === 400)
      {
        alert(res.error)
      }
      else
        alert('Something Went Wrong!')
      // console.log(res);
    })

  }

}
