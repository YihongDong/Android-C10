//package com.example.me.sqlitesample;
//
//import android.content.ContentValues;
//import android.database.Cursor;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.SimpleCursorAdapter;
//
//public class MainActivity extends AppCompatActivity {
//    Button bt;
//    Button bt1;
//    Button bt2;
//    Button bt3;
//    ListView ls;
//    EditText tv1;
//    EditText  tv2;
//    EditText  tv3;
//    StudentClubDbHelper dbHelper;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        bt= (Button) findViewById(R.id.bt);
//        bt1= (Button) findViewById(R.id.bt1);
//        bt2= (Button) findViewById(R.id.bt2);
//        bt3= (Button) findViewById(R.id.bt3);
//        tv1=(EditText) findViewById(R.id.textView1);
//        tv2=(EditText) findViewById(R.id.textView2);
//        tv3=(EditText) findViewById(R.id.textView3);
//
//        dbHelper=new StudentClubDbHelper(MainActivity.this,1);
////        bt.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                ContentValues cv=new ContentValues();
////                String[] selection = {tv2.getText().toString()};
////                String[] selectionargs = {tv3.getText().toString()};
////                cv.put(tv2.getText().toString(),tv3.getText().toString());
////                dbHelper.insert(tv1.getText().toString(), cv);
//////                refreshListView(tv1.getText().toString(),selection,tv2.getText().toString(),selectionargs);
////            }
////        });
//
////        bt1.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                String[] selection = {tv2.getText().toString()};
////                String[] selectionargs = {tv3.getText().toString()};
////                dbHelper.delete(tv1.getText().toString(), tv2.getText().toString(),selectionargs);
////                refreshListView(tv1.getText().toString(),selection,tv2.getText().toString(),selectionargs);
////            }
////        });
////
////        bt2.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                String[] selection = {tv2.getText().toString()};
////                String[] selectionargs = {tv3.getText().toString()};
////                refreshListView(tv1.getText().toString(),selection,tv2.getText().toString(),selectionargs);
////            }
////        });
////
////        bt3.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                ContentValues cv=new ContentValues();
////                String[] selection = {tv2.getText().toString()};
////                String[] selectionargs = {tv3.getText().toString()};
////                cv.put(tv2.getText().toString(),tv3.getText().toString());
////                dbHelper.update(tv1.getText().toString(),cv, tv2.getText().toString(),selectionargs);
////                refreshListView(tv1.getText().toString(),selection,tv2.getText().toString(),selectionargs);
////            }
////        });
//
//        ls= (ListView) findViewById(R.id.list);
//        refreshListView();
//    }
//
//    private void refreshListView(){
//        Cursor cs=dbHelper.query(StudentClubDbContract.StudentsTable.TABLE_NAME);
//        if (cs!=null) {
//            SimpleCursorAdapter sca = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cs, new String[]{StudentClubDbContract.StudentsTable._ID}, new int[]{android.R.id.text1});
//            ls.setAdapter(sca);
//        }
//    }
//
////    private void refreshListView(String table,String[] projection,String selection,String[] selectionArgs){
////        Cursor cs=dbHelper.query(table,projection,selection,selectionArgs,null,null,null,null);
////        if (cs!=null) {
////            SimpleCursorAdapter sca = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cs, new String[] {selection}, new int[]{android.R.id.text1});
////            ls.setAdapter(sca);
////        }
////    }
//}


package com.example.me.sqlitesample;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {
    Button bt;
    Button bt1;
    Button bt2;
    Button bt3;
    ListView ls;
    EditText tv1;
    EditText  tv2;
    EditText  tv3;
    MatchDbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt= (Button) findViewById(R.id.bt);
        bt1= (Button) findViewById(R.id.bt1);
        bt2= (Button) findViewById(R.id.bt2);
        bt3= (Button) findViewById(R.id.bt3);
        tv1=(EditText) findViewById(R.id.textView1);
        tv2=(EditText) findViewById(R.id.textView2);
        tv3=(EditText) findViewById(R.id.textView3);

        dbHelper=new MatchDbHelper(MainActivity.this,1);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv=new ContentValues();
                String[] selection = {tv2.getText().toString()};
                String[] selectionargs = {tv3.getText().toString()};
                cv.put(tv2.getText().toString(),tv3.getText().toString());
                dbHelper.insert(tv1.getText().toString(), cv);
                refreshListView(tv1.getText().toString(),tv2.getText().toString());
            }
        });

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] selection = {tv2.getText().toString()};
                String[] selectionargs = {tv3.getText().toString()};
                dbHelper.delete(tv1.getText().toString(), tv2.getText().toString()+"=?",selectionargs);
                refreshListView(tv1.getText().toString(),tv2.getText().toString());
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] selection = {"*"};
                String[] selectionargs = {tv3.getText().toString()};
                refreshListView(tv1.getText().toString(),selection,tv2.getText().toString(),selectionargs);
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                ContentValues cv = new ContentValues();
                String[] selection = {tv2.getText().toString()};
                String[] selectionargs = {tv3.getText().toString()};
                cv.put(tv2.getText().toString(), "update");
                dbHelper.update(tv1.getText().toString(), cv, tv2.getText().toString()+"=?", selectionargs);
                refreshListView(tv1.getText().toString(),tv2.getText().toString());
           }
                               });
        ls= (ListView) findViewById(R.id.list);
        refreshListView();
    }

    private void refreshListView(){
        Cursor cs=dbHelper.query(MatchDbContract.StudentsTable.TABLE_NAME);
        if (cs!=null) {
            SimpleCursorAdapter sca = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cs, new String[]{MatchDbContract.StudentsTable._ID,MatchDbContract.StudentsTable.COLUMN_NAME_Name}, new int[]{android.R.id.text1,android.R.id.text2});
            ls.setAdapter(sca);
        }
    }
    private void refreshListView(String table,String[] projection,String selection,String[] selectionArgs){
        Cursor cs=dbHelper.query(table,projection,selection+"=?",selectionArgs,null,null,null,null);
        if (cs!=null) {
            SimpleCursorAdapter sca = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cs, new String[]{selection} , new int[]{android.R.id.text1});
            ls.setAdapter(sca);
        }
    }
    private void refreshListView(String table,String projection){
        Cursor cs=dbHelper.query(table);
        if (cs!=null) {
            SimpleCursorAdapter sca = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cs, new String[]{projection} , new int[]{android.R.id.text1});
            ls.setAdapter(sca);
        }
    }
}





