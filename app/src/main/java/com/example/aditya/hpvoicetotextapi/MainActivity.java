package com.example.aditya.hpvoicetotextapi;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        Button button = (Button) findViewById(R.id.ClickMe);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                MyTask task = new MyTask();
                task.execute("test");
                Toast.makeText(MainActivity.this, "Button Clicked", Toast.LENGTH_SHORT).show();

            }

        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }



    private class MyTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            try {
                FileInputStream fstrm= new FileInputStream(Environment.getExternalStorageDirectory().getAbsolutePath()+"/hpnext.mp4");;


                // Set your server page url (and the file title/description)
                HttpFileUpload hfu = new HttpFileUpload("https://api.idolondemand.com/1/api/async/recognizespeech/v1", "my file title","my file description");

                hfu.Send_Now(fstrm);


            }catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }
    }
    }

