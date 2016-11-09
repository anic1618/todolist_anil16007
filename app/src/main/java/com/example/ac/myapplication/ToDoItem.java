package com.example.ac.myapplication;

import java.util.Date;

/**
 * Created by AC on 11/2/2016.
 */

public class ToDoItem {
    private String title;
    private String details;
    private Date date;
    private Integer itemNo;
    private boolean done;



    public ToDoItem(){
        super();
    }

    public ToDoItem(String title,String details,Integer itemNo,boolean done){//,Date date
        this.title = title;
        this.itemNo = itemNo;
        this.details = details;
        this.done = done;
        //this.date = date;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public Integer getItemNo() {
        return itemNo;
    }


    public void setItemNo(Integer itemNo) {
        this.itemNo = itemNo;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
