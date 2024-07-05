package model;

import java.io.File;

import javax.servlet.http.Part;

import utils.StringUtils;

public class PropertyModel {
 private int seller_id;
 private String property_brief;
 private String property_description;
 private String property_address;
 private int price;
 private int property_size;
 private int no_of_room;
 private String propertyimageUrlFromPart;
 
 public PropertyModel() {
 
 }
 
public PropertyModel(int seller_id, String property_brief,String property_description, String property_address, int price, int property_size,
		int no_of_room, Part property_photo) {
	
	this.seller_id = seller_id;
	this.property_brief=property_brief;
	this.property_description = property_description;
	this.property_address = property_address;
	this.price = price;
	this.property_size = property_size;
	this.no_of_room = no_of_room;
	this.propertyimageUrlFromPart = getImageUrl(property_photo);
}

public int getSeller_id() {
	return seller_id;
}
public void setSeller_id(int seller_id) {
	this.seller_id = seller_id;
}

public String getProperty_brief() {
	return property_brief;
}
public void setProperty_brief(String property_brief) {
	this.property_brief = property_brief;
}
public String getProperty_description() {
	return property_description;
}
public void setProperty_description(String property_description) {
	this.property_description = property_description;
}
public String getProperty_address() {
	return property_address;
}
public void setProperty_address(String property_address) {
	this.property_address = property_address;
}
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}
public int getProperty_size() {
	return property_size;
}
public void setProperty_size(int property_size) {
	this.property_size = property_size;
}
public int getNo_of_room() {
	return no_of_room;
}
public void setNo_of_room(int no_of_room) {
	this.no_of_room = no_of_room;
}
public String getPropertyimageUrlFromPart() {
	return propertyimageUrlFromPart;
}
public void setPropertyPhotoUrlFromPart(Part part) {
	this.propertyimageUrlFromPart =getImageUrl(part);
}
public void setPropertyImageUrlFromDB(String imageUrl) {
	this.propertyimageUrlFromPart = imageUrl;
}



private String getImageUrl(Part part) {
	String savePath = StringUtils.IMAGE_DIR_USER;
	File fileSaveDir = new File(savePath);
	String propertyimageUrlFromPart = null;
	if (!fileSaveDir.exists()) {
		fileSaveDir.mkdir();
	}
	
	String contentDisp = part.getHeader("content-disposition");
	String[] items = contentDisp.split(";");
	for (String s : items) {
		if (s.trim().startsWith("filename")) {
			propertyimageUrlFromPart = s.substring(s.indexOf("=") + 2, s.length() - 1);
		}
	}
	
	if (propertyimageUrlFromPart == null || propertyimageUrlFromPart.isEmpty()) {
		propertyimageUrlFromPart = "download.jpg";
	}
	return propertyimageUrlFromPart;
}
}
