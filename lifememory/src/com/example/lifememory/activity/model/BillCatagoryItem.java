package com.example.lifememory.activity.model;


public class BillCatagoryItem {
	
	private int idx;
	private String name;
	private int imageId;
	private int parentId; 
	
	public BillCatagoryItem() {};
	
	public BillCatagoryItem(String name, int imageId, int parentId) {
		super();
		this.name = name;
		this.imageId = imageId;
		this.parentId = parentId;
	}
	
	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getImageId() {
		return imageId;
	}
	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
}
