package com.template.memberapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
Button btnAdd;
DatabaseHelper databaseHelper;
RecyclerView recyclerView;
LinearLayoutManager layoutManager;
ArrayList<MemberModel>memberList;
MemberAdapter memberAdapter;
TextView  mCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd=findViewById(R.id.btnAddNew);
        recyclerView=findViewById(R.id.recView);
        mCount=findViewById(R.id.mCount);

        memberList=new ArrayList<>();

        databaseHelper=new DatabaseHelper(MainActivity.this);

try {
    memberList = databaseHelper.getMemberDtFromDb();
    Log.e("","mem list:"+memberList.size());

    if(memberList.size()>0) {
        Log.e("","mem list:"+memberList.size());
        String count= String.valueOf(memberList.size());
        mCount.setText(count);
    }
}catch (Exception e){
    e.printStackTrace();
}



    layoutManager = new LinearLayoutManager(MainActivity.this);
    // recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    recyclerView.setLayoutManager(layoutManager);
    memberAdapter = new MemberAdapter(MainActivity.this, memberList);
    recyclerView.setAdapter(memberAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(i);

            }
        });



    }
}