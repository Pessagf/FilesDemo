package com.example.user.filesdemo;

import android.support.v7.app.ActionBarActivity;
import java.io.*;
import java.io.File;
import android.os.Bundle;
import android.app.Activity;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity {

    private EditText etInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInput = (EditText) findViewById(R.id.etInput);
    }

    public void writeToFile(View v){
        try{
            String inputStr = etInput.getText().toString();
           // FileOutputStream fos = openFileOutput("myfile.txt",MODE_PRIVATE);

            File root = Environment.getExternalStorageDirectory();
            File dir = new File(root.getAbsolutePath() + "/MyFiles");
            dir.mkdirs();
            File file = new File(dir,"myfile.txt");
            FileOutputStream fos = new FileOutputStream(file);


            fos.write(inputStr.getBytes());
            fos.flush();
            fos.close();

            Toast.makeText(this,"File saved successfuly!",Toast.LENGTH_SHORT).show();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void readFromFile(View v){
        try{
           // FileInputStream fis = openFileInput("myfile.txt");

            File root = Environment.getExternalStorageDirectory();
            File dir = new File(root.getAbsolutePath()+"MyFiles");
            File file = new File(dir,"myfile.txt");
            FileInputStream fis = new FileInputStream(file);
            StringBuffer stringBuffer = new StringBuffer();
            BufferedReader bReader = new BufferedReader(new InputStreamReader(fis));

            String strLine = null;

            while((strLine = bReader.readLine()) != null){
                stringBuffer.append(strLine + "\n");
            }

            bReader.close();
            fis.close();

            Toast.makeText(this,"File Content: \n" + stringBuffer.toString(),Toast.LENGTH_SHORT).show();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
