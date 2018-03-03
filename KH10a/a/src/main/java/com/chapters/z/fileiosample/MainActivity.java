package com.chapters.z.fileiosample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnOnClick(View v){
        Intent intent=new Intent();
        switch (v.getId()){
            case R.id.btn1:
                intent.setClass(this,InternalStorageActivity.class);
                break;
            case R.id.btn2:
                intent.setClass(this,ExternalStorageActivity.class);
                break;
            case R.id.btn3:
                intent.setClass(this,ShareDirectoryActivity.class);
                break;
            case R.id.btn4:
//                intent.setClass(this,ShareDirectoryActivity.class);
                break;
        }
        startActivity(intent);
    }
}
