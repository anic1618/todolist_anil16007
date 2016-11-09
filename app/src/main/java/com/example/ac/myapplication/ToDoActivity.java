package com.example.ac.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ToDoActivity extends SingleFragmentActivity {

    private static final String EXTRA_TODO_ID = "com.example.ac.myapplication.crime_id";

    public static Intent newIntent(Context context,Integer i){
        Intent intent = new Intent(context,ToDoActivity.class);
        intent.putExtra(EXTRA_TODO_ID,i);
        return  intent;
    }

    @Override
    protected Fragment createFragment() {
        //return new ToDoFragment();
        Integer i = getIntent().getIntExtra(EXTRA_TODO_ID,0);
        return ToDoFragment.newInstance(i);
    }
}
