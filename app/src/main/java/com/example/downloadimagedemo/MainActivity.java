package com.example.downloadimagedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    // widgets
    private ImageView mImgDownload;

    public class ImageDownloaderTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {

            try {
                URL url = new URL(urls[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.connect();
                InputStream inputStream = httpURLConnection.getInputStream();
                return BitmapFactory.decodeStream(inputStream);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImgDownload = findViewById(R.id.ivKrishna);
    }

    public void onClickMeClick(View view) throws ExecutionException, InterruptedException {
        Log.i("Interaction: ", "Button Tapped");

        ImageDownloaderTask imageDownloaderTask = new ImageDownloaderTask();
        Bitmap myImage;

        myImage = imageDownloaderTask
                .execute("https://c4.wallpaperflare.com/wallpaper/771/2/557/god-lord-krishna-wallpaper-preview.jpg")
                .get();

        mImgDownload.setImageBitmap(myImage);
    }
}