package com.example.studygroup;

public class Groups {

  private  String crs_code, crs_name, crs_id, location, description;
   private int max_mems; 
     
    // constructors
    public Groups() {
    }
 
    
    public Groups(String crs_code, String crs_name, String location, String description, int max_mems) {
        this.crs_code = crs_code;
        this.crs_name = crs_name;
        this.crs_id = "" + crs_code + crs_name;
        this.location = location;
        this.description = description;
        this.max_mems = max_mems;
    }
 
    // setters
    public void setCode(String code) {
        this.crs_id = code;
    }
 
    public void setName(String name) {
        this.crs_name = name;
    }
 
    public void setLocation(String location) {
        this.location = location;
    }
     
    public void setDescription(String des){
        this.description = des;
    }
    
    public void setMaximumNumber(int max){
    	this.max_mems = max;
    }
 
    // getters
    public String getCode() {
        return this.crs_code;
    }
 
    public String getName() {
        return this.crs_name;
    }
 
    public String getLocation() {
        return this.location;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public int getMaximumNumbers() {
    	return this.max_mems;
    }
    
    public String getCourseID() {
    	return this.crs_id;
    }
    
    public String toString() {
        return "" + crs_id + ". Location: " + this.getLocation() + "Description" + this.getDescription() + "People Allowed: " + this.max_mems;
    }   
}