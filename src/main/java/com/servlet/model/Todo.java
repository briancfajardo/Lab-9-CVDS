package com.servlet.model;

public class Todo {


    private int userId;
    private int id;
    private String title;
    private Boolean completed;
    
    public Todo(){

    } 

    public void setUserId(int userId){
        this.userId = userId;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setCompleted(Boolean completed){
        this.completed = completed;
    }
    
    public int getUserId(){
        return this.userId;
    }
    public int getId(){
        return this.id;
    }
    public String getTitle(){
        return this.title;
    }
    public Boolean getCompleted(){
        return this.completed;
    }
}
