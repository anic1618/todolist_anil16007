package com.example.ac.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by AC on 11/5/2016.
 */

public class ToDoListFragment extends Fragment {
    private RecyclerView mTodoListRecyclerView;
    private ItemAdapter mItemAdapter;
    private String TAG = "ToDoListFragment";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo_list,container,false);
        mTodoListRecyclerView =(RecyclerView) view.findViewById(R.id.todo_recycler_view);
        mTodoListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Log.d(TAG, "onCreateView: ");
        updateUI();
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_crime:
                ToDoItem mToDoItem = new ToDoItem();
                mToDoItem.setItemNo(MyDB.getDB(getActivity()).getSize()+1);
                MyDB.getDB(getActivity()).addToDoItem(mToDoItem);
                Log.d(TAG, "onOptionsItemSelected: "+mToDoItem.getItemNo());
                Intent intent = ToDoPagerActivity
                        .newIntent(getActivity(), mToDoItem.getItemNo());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        Log.d(TAG, "updateUI: ");
        MyDB myDB = MyDB.getDB(getActivity());
        if(mItemAdapter == null) {
            List<ToDoItem> mToDoItems = myDB.getToDoItemList();
            mItemAdapter = new ItemAdapter(mToDoItems);
        }
        else{
            mItemAdapter.notifyDataSetChanged();
        }
        mTodoListRecyclerView.setAdapter(mItemAdapter);
    }


    private class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mSolvedCheckBox;
        private ToDoItem mToDoItem;
        public ItemHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            Log.d(TAG, "ItemHolder: ");
            mTitleTextView = (TextView)
                    itemView.findViewById(R.id.list_item_todo_title_textview);
            mDateTextView = (TextView)
                    itemView.findViewById(R.id.list_item_todo_date_textview);
            mSolvedCheckBox = (CheckBox)
                    itemView.findViewById(R.id.list_item_todo_checkBox);

        }

        public void bindCrime(ToDoItem toDoItem){
            mToDoItem = toDoItem;
            mTitleTextView.setText(mToDoItem.getTitle());
            //mDateTextView.setText(mToDoItem.getDate().toString());
           // mSolvedCheckBox.setChecked(mToDoItem.isDone());

        }

        @Override
        public void onClick(View v) {
            //Toast.makeText(getActivity(), mToDoItem.getTitle() + " clicked", Toast.LENGTH_SHORT).show();
            Intent intent = ToDoPagerActivity.newIntent(getActivity(),mToDoItem.getItemNo());

            startActivity(intent);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_todo_list, menu);
    }

    private class ItemAdapter extends RecyclerView.Adapter<ItemHolder>{
        private List<ToDoItem> mToDoItems;

        public ItemAdapter(List<ToDoItem> mToDoItems){
            this.mToDoItems = mToDoItems;
        }

        @Override
        public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_todo,parent,false);
            return new ItemHolder(view);
        }

        @Override
        public void onBindViewHolder(ItemHolder holder, int position) {
            ToDoItem mToDoItem = mToDoItems.get(position);
            holder.bindCrime(mToDoItem);

        }

        @Override
        public int getItemCount() {
            return mToDoItems.size();
        }
    }
}

