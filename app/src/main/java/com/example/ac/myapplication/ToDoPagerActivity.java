package com.example.ac.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

/**
 * Created by AC on 11/7/2016.
 */

public class ToDoPagerActivity extends AppCompatActivity {

    private static final String EXTRA_TODO_ID = "com.example.ac.myapplication.crime_id";
    private ViewPager mViewPager;
    private List<ToDoItem> mToDoItemList;

    public static Intent newIntent(Context context, Integer i){
        Intent intent = new Intent(context,ToDoPagerActivity.class);
        intent.putExtra(EXTRA_TODO_ID,i);
        return  intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_pager);

        mViewPager = (ViewPager)findViewById(R.id.activity_crime_pager_view_pager);

        mToDoItemList = MyDB.getDB(this).getToDoItemList();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                ToDoItem mToDoItem = mToDoItemList.get(position);
                return ToDoFragment.newInstance(mToDoItem.getItemNo());
            }

            @Override
            public int getCount() {
                return mToDoItemList.size();
            }
        });
        Integer id = getIntent().getIntExtra(EXTRA_TODO_ID,0);
       /* for(int i = 0;i < mToDoItemList.size();i++){
            if(mToDoItemList.get(i).getItemNo().equals(id)){
                mViewPager.setCurrentItem(id);
                break;
            }
        }
*/
        mViewPager.setCurrentItem(id-1);

    }
}
