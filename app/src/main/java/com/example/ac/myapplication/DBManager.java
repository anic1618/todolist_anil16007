package com.example.ac.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by AC on 11/8/2016.
 */

public class DBManager  extends SQLiteOpenHelper {


    private static final String DB_NAME = "TODO_DB";
    private static final String TABLE_NAME = "TODO";
    private static final String COL_TITLE = "TITLE";
    private static final String COL_ID = "ID";
    private static final String COL_DONE="DONE";
    private static final String COL_DETAILS = "DETAILS ";


    public DBManager(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTbl = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY  , %s TEXT, %s TEXT,%s INTEGER)",TABLE_NAME,COL_ID,COL_TITLE,COL_DETAILS,COL_DONE);
        db.execSQL(createTbl);//autoincrement
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public void drop() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM table_name");
        //onCreate(db);
    }


    public void addToDoItem(ToDoItem toDoItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_TITLE, toDoItem.getTitle());
        values.put(COL_DETAILS, toDoItem.getDetails());
        //values.put(COL_DONE, toDoItem.isDone());
        db.insert(TABLE_NAME, null, values);
        //db.close();

    }


    public void addToDoItems(ArrayList<ToDoItem> toDoItems) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        for(ToDoItem toDoItem:toDoItems) {
            //values.put(COL_ID, toDoItem.getItemNo());
            values.put(COL_TITLE, toDoItem.getTitle());
            values.put(COL_DETAILS, toDoItem.getDetails());
            //values.put(COL_DONE, toDoItem.isDone());
            db.insert(TABLE_NAME, null, values);
        }
        //db.close();

    }


    public int updateToDoItem(ToDoItem toDoItem) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_ID , toDoItem.getItemNo());
        values.put(COL_TITLE, toDoItem.getTitle());
        values.put(COL_DETAILS, toDoItem.getDetails());
        //values.put(COL_DONE , toDoItem.isDone());
        // updating row
         int c = db.update(TABLE_NAME, values, COL_ID + " = ?",
                new String[]{String.valueOf(toDoItem.getItemNo())});
        //db.close();
        return c;
    }

    public void deleteToDoItem(ToDoItem toDoItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COL_ID + " = ?",
                new String[]{String.valueOf(toDoItem.getItemNo())});

        //db.close();
    }

    public int getSize(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select count(*) from "+TABLE_NAME,null);
        if (cursor.moveToFirst()) {
            return cursor.getInt(0);
        }
        return 0;
    }

    public ToDoItem getToDoItem(ToDoItem toDoItem) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COL_ID,
                        COL_TITLE,COL_DETAILS,COL_DONE}, COL_ID + "=?",
                new String[]{String.valueOf(toDoItem.getItemNo())}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        String no = cursor.getString(0);
        if (no == null) {
            return null;
        }


        ToDoItem toDoItem1 = new ToDoItem(cursor.getString(1),cursor.getString(2),Integer.parseInt(cursor.getString(0)),true);
        //db.close();
        // return contact
        return toDoItem1;
    }

    public ArrayList<ToDoItem> getToDoItems() {
        ArrayList<ToDoItem> toDoItems = new ArrayList<>();
        String selectQuery = "Select * from " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        ToDoItem toDoItem = null;
        if (cursor.moveToFirst()) {
            do {
                toDoItem = new ToDoItem();
                toDoItem.setItemNo(Integer.parseInt(cursor.getString(0)));
                toDoItem.setTitle(cursor.getString(1));
                toDoItem.setDetails(cursor.getString(2));
                toDoItems.add(toDoItem);
            } while (cursor.moveToNext());
        }
        //db.close();
        return toDoItems;
    }

}
