package com.example.testdata;

public class Muppet {

	private int		id;
	private String	name;
	
	public void setMuppetId(int muppetId){
		id	= muppetId;
	}
	
	public void setMuppetName(String muppetName){
		name	= muppetName;
	}
	
	public int getMuppetId(){
		return id;
	}
	
	public String getMuppetName(){
		return name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
