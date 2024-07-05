package model;

import java.time.LocalDate;

import javax.servlet.http.Part;

import  java.io.File;

import utils.StringUtils;

public class UserModel {
private String fullName;
private String userName;
private String email;
private String address;
private String password;
private String rePassword;
private String nationality;
private LocalDate DoB;
private String gender;
private String role;
private String phoneNumber;
private String imageUrlFromPart;

public UserModel() {
	 
}

public UserModel(String fullName, String userName, String email, String address, String password, String rePassword,
		String nationality, LocalDate doB, String gender, String role, String phoneNumber, Part imagePart) {
	
	this.fullName = fullName;
	this.userName = userName;
	this.email = email;
	this.address = address;
	this.password = password;
	this.rePassword = rePassword;
	this.nationality = nationality;
	this.DoB = doB;
	this.gender = gender;
	this.role = role;
	this.phoneNumber = phoneNumber;
	this.imageUrlFromPart = getImageUrl(imagePart);
}
public String getFullName() {
	return fullName;
}
public void setFullName(String fullName) {
	this.fullName = fullName;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getRePassword() {
	return rePassword;
}
public void setRePassword(String rePassword) {
	this.rePassword = rePassword;
}
public String getNationality() {
	return nationality;
}
public void setNationality(String nationality) {
	this.nationality = nationality;
}
public LocalDate getDoB() {
	return DoB;
}
public void setDoB(LocalDate doB) {
	DoB = doB;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
public String getPhoneNumber() {
	return phoneNumber;
}
public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}

public String getImageUrlFromPart() {
	return imageUrlFromPart;
}


public void setImageUrlFromPart(Part part) {
	this.imageUrlFromPart = getImageUrl(part);
}



public void setImageUrlFromDB(String imageUrl) {
	this.imageUrlFromPart = imageUrl;
}

public static String getImageUrl(Part part) {
	String savePath = StringUtils.IMAGE_DIR_USER;
	File fileSaveDir = new File(savePath);
	String imageUrlFromPart = null;
	if (!fileSaveDir.exists()) {
		fileSaveDir.mkdir();
	}
	
	String contentDisp = part.getHeader("content-disposition");
	String[] items = contentDisp.split(";");
	for (String s : items) {
		if (s.trim().startsWith("filename")) {
			imageUrlFromPart = s.substring(s.indexOf("=") + 2, s.length() - 1);
		}
	}
	
	if (imageUrlFromPart == null || imageUrlFromPart.isEmpty()) {
		imageUrlFromPart = "download.jpg";
	}
	return imageUrlFromPart;
}


}


