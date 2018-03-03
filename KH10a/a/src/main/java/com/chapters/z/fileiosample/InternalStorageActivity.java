package com.chapters.z.fileiosample;

import android.content.SharedPreferences;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class InternalStorageActivity extends AppCompatActivity {
    static final String KEY_NO="key_no";
    static final String FILENAMEPRE="文件";
    static final String FILENAMESUF=".txt";
    static final String FILECONTENT="这是私有内部存储";
    int no;
    ListView listView;
    EditText editText;
    Button btnSave,btnDelete;

    String currentSelectFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage);
        listView=findViewById(R.id.listView);
        editText=findViewById(R.id.editText);
        btnSave=findViewById(R.id.btnSave);
        btnDelete=findViewById(R.id.btnDelete);

        editText.setText(FILECONTENT);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                btnSave.setEnabled(true);
                btnDelete.setEnabled(true);
                TextView tx=view.findViewById(android.R.id.text1);
                currentSelectFile=tx.getText().toString();
                editText.setText(readFile(currentSelectFile));
            }
        });

        updateListView(listView);//显示文件列表
        no = readNo();//获取文件名后缀编号
    }

    public void btnOnClick(View v){
        String content=editText.getText().toString();
        switch (v.getId()){
            case R.id.btnNew:
                String filename=FILENAMEPRE+(no+1)+FILENAMESUF;
                if(writeFile(filename,content)){
                    saveNo();//将文件名后缀编号保存到键值对文件中
                    no=readNo();//更新No值
                    updateListView(listView);//刷新listview
                    Toast.makeText(InternalStorageActivity.this,"new file "+filename+"is added",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnSave:
                if(writeFile(currentSelectFile,content)){
                    updateListView(listView);//刷新listview
                    btnDelete.setEnabled(false);
                    btnSave.setEnabled(false);
                    Toast.makeText(InternalStorageActivity.this,"file "+currentSelectFile+"is updated",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnDelete:
                deleteFile(currentSelectFile);
                editText.setText(FILECONTENT);
                no=readNo();//更新No值
                updateListView(listView);//刷新listview
                btnDelete.setEnabled(false);
                btnSave.setEnabled(false);
                break;
        }
    }

    private boolean writeFile(String filename,String content){
        try{
            FileOutputStream os = openFileOutput (filename,MODE_PRIVATE);
            byte[] data =content.getBytes("GB2312");
            os.write(data);
            os.close();
            return true;
        }catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }

    private String readFile(String filename){
        try {
            FileInputStream fin = openFileInput(filename);
            int count=fin.available();//must not use fin.read()
            byte[] data=new byte[count];
            fin.read(data);
            String result=new String(data,"GB2312");
            fin.close();
            return result;
        }catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }

    private void saveNo(){
        SharedPreferences settings = getPreferences( 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(KEY_NO, no+1);
        editor.commit();
    }

    private int readNo(){
        String[] files=fileList();
        if(files.length==0){
            return 0;
        }
        SharedPreferences settings = getPreferences(0);
        return settings.getInt(KEY_NO, 0);//获取文件名后缀编号
    }

    private void updateListView(ListView listView){
        String[] files=fileList();
        ArrayAdapter arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, files);
            listView.setAdapter(arrayAdapter);

    }
}
