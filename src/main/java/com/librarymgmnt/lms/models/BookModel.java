package com.librarymgmnt.lms.models;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("books")
public class BookModel {

	@Id
	private String id;
	
	private String name;
	private String author;
	private String price;
	private String genre;
	private int pages;
	private String description;
	private Binary image;
	private int copiesAvailable = 0;

	public BookModel(String id, String name, String author, String price, String genre, int pages, String description,
			Binary image, int copiesAvailable) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.price = price;
		this.genre = genre;
		this.pages = pages;
		this.description = description;
		this.image = image;
		this.copiesAvailable = copiesAvailable;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public String getGenre() {
		return genre;
	}


	public void setGenre(String genre) {
		this.genre = genre;
	}


	public int getPages() {
		return pages;
	}


	public void setPages(int pages) {
		this.pages = pages;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Binary getImage() {
		return image;
	}


	public void setImage(Binary image) {
		this.image = image;
	}


	public int getCopiesAvailable() {
		return copiesAvailable;
	}


	public void setCopiesAvailable(int copiesAvailable) {
		this.copiesAvailable = copiesAvailable;
	}
	
	

}
