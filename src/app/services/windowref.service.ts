import { isPlatformBrowser } from '@angular/common';
import { Inject, Injectable, PLATFORM_ID } from '@angular/core';

function _window(){
  return window;
}

@Injectable({
  providedIn: 'root'
})
export class WindowrefService {

  constructor(@Inject(PLATFORM_ID) private platformId: object) { }

  get nativeWindow(): any{
    if(isPlatformBrowser(this.platformId)){
      return _window()
    }
  }

}
