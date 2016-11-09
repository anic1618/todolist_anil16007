package com.example.ac.myapplication;

import android.support.v4.app.Fragment;
import android.util.Log;

public class ToDoListActivity extends SingleFragmentActivity {

    private String TAG = "ToDoListActivity";
    @Override
    protected Fragment createFragment() {
        Log.d(TAG, "createFragment: ");
        return new ToDoListFragment();
    }

    @Override
    protected void onStop() {
        super.onStop();
       // MyDB.saveItToDB();
    }
}
