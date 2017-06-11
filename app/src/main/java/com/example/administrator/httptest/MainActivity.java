package com.example.administrator.httptest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=(ImageView)findViewById(R.id.imageView);
       new DownloadImageTask().execute("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png");
    }
    class DownloadImageTask extends AsyncTask<String, Void,Bitmap> {

        @Override
        protected Bitmap doInBackground(String... params) {
            // TODO Auto-generated method stub
            return downloadImage(params[0]);
        }

        protected void onPostExecute(Bitmap bitmap) {
            // TODO Auto-generated method stub
            imageView.setImageBitmap(bitmap);
        }

    }
    private Bitmap downloadImage(String Url)
    {
        Bitmap bitmap=null;
        InputStream in =null;
        try {
            URL url =new URL(Url);
            URLConnection conn = url.openConnection();
            HttpURLConnection httpConn=(HttpURLConnection)conn;
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            if(httpConn.getResponseCode()==HttpURLConnection.HTTP_OK)
            {
                in=httpConn.getInputStream();
            }
            bitmap = BitmapFactory.decodeStream(in);
            in.close();
        }
        catch (IOException e)
        {
            Log.d("MainActivity",e.getLocalizedMessage());
        }
        return bitmap;
    }

}

