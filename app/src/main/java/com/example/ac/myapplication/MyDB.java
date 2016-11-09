package com.example.ac.myapplication;

import android.content.Context;
import java.util.List;

/**
 * Created by AC on 11/5/2016.
 */


public class MyDB {
    private static MyDB sMyDB;
    private List<ToDoItem> mToDoItemList;
    private Integer size;
    private static DBManager dbManager;
    private  MyDB(Context context){
        dbManager  = new DBManager(context);
        mToDoItemList = dbManager.getToDoItems();
        size = dbManager.getSize();
        ToDoItem mToDoItem = null;

    }

    public static MyDB getDB(Context context){
        if(sMyDB == null){

            sMyDB  = new MyDB(context);
        }
        return  sMyDB;
    }

    public static void saveItToDB(){
        dbManager.drop();
    }
    public List<ToDoItem> getToDoItemList() {
        return dbManager.getToDoItems();
    }

    /*public void setToDoItemList(List<ToDoItem> mToDoItemList) {
        this.mToDoItemList = mToDoItemList;

    }*/

    public ToDoItem getToDoItem(Integer ItemNo){
        /*for(ToDoItem mTodoDoItem:mToDoItemList){
            if(mTodoDoItem.getItemNo().equals(ItemNo)){
                return mTodoDoItem;
            }

        }
        return  null;*/
        ToDoItem toDoItem = new ToDoItem();
        toDoItem.setItemNo(ItemNo);
        return dbManager.getToDoItem(toDoItem);

    }

    public void updateToDoItem(ToDoItem toDoItem){
        dbManager.updateToDoItem(toDoItem);
    }

    public void deleteToDoItem(ToDoItem toDoItem){
        dbManager.deleteToDoItem(toDoItem);
        //mToDoItemList.add(toDoItem);
        size--;
    }
    public void addToDoItem(ToDoItem toDoItem){
        dbManager.addToDoItem(toDoItem);
        //mToDoItemList.add(toDoItem);
        size++;
    }


    public Integer getSize() {
        return size;
    }
}

