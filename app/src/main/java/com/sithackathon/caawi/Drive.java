package com.sithackathon.caawi;

public class Drive {
    private String driveID;
    private String driveName;
    private String driveDescription;
    private String driveImage;
    private String driveType;
    private String driveCategory;
    private String driveGoal;

    public Drive(String id){
        this.driveID = id;
        //image can be retrieved with id + ".jpg"
    }
    public String getDriveID() {return driveID;}

    public String getDriveName() {
        return driveName;
    }

    public String getDriveDescription(){
        return driveDescription;
    }

    public String getDriveType(){
        return driveType;
    }

    public String getDriveCategory(){
        return driveCategory;
    }

    public String getDriveGoal(){
        return driveGoal;
    }

    public void setDriveName(String n){
        this.driveName = n;
    }

    public void setDriveDescription(String d){
        this.driveDescription = d;
    }

    public void setDriveImage(String id){
        this.driveImage = id + ".jpg";
    }

    public void setDriveType(String t){
        this.driveType = t;
    }

    public void setDriveGoal(String g){
        this.driveGoal = g;
    }

    public void setDriveCategory(String c){
        this.driveCategory = c;
    }


}
