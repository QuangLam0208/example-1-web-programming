package ltweb.model;

import java.io.Serializable;

public class Category implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String images;
	public Category(int id, String name, String images) {
		super();
		this.id = id;
		this.name = name;
		this.images = images;
	}
	public Category() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", images=" + images + "]";
	}
	
	
	

}
