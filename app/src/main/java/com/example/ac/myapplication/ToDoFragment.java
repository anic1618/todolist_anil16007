package com.example.ac.myapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;


public class ToDoFragment extends Fragment {

    public static final String TODO_ID="todo_id";

    private ToDoItem mToDoItem;
    private EditText mtitleText;
    private EditText mdetails;
    private CheckBox mCheckBox;



    public static ToDoFragment newInstance(Integer i){
        Bundle args = new Bundle();
        args.putInt(TODO_ID,i);

        ToDoFragment fragment = new ToDoFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Integer i = getArguments().getInt(TODO_ID);
        mToDoItem = MyDB.getDB(getActivity()).getToDoItem(i);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_todo, container, false);
        mtitleText = (EditText) v.findViewById(R.id.item_title);
        mdetails = (EditText) v.findViewById(R.id.detail_text);
        mCheckBox = (CheckBox) v.findViewById(R.id.checkBoxDone);

        mtitleText.setText(mToDoItem.getTitle());
        mdetails.setText(mToDoItem.getDetails());
        mCheckBox.setChecked(mToDoItem.isDone());

        mtitleText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               /* if(s.toString().equals("")){
                    Toast.makeText(getActivity(),"Enter")
                    return;
                }*/
                mToDoItem.setTitle(s.toString());
                MyDB.getDB(getActivity()).updateToDoItem(mToDoItem);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mdetails.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mToDoItem.setDetails(s.toString());
                MyDB.getDB(getActivity()).updateToDoItem(mToDoItem);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mToDoItem.setDone(isChecked);
             }
            }
        );
        return v;
    }





}
