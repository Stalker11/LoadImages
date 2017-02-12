package com.olegel.loadimages;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executors;

import javax.net.ssl.HttpsURLConnection;

public class ImageLoader {
    private String myUrl = null;
    private Bitmap bitmap;
    private InputStream in;
    private ILoaded listener;
    private URL url;
    private int width;
    private int height;
    private boolean savedImage;
    private static final String TAG = ImageLoader.class.getSimpleName();
    private Handler handler = new Handler();

    public ImageLoader(String url, ILoaded listener, boolean savedImage) {
        this.myUrl = url;
        this.listener = listener;
        this.savedImage = savedImage;
        loadImage();
    }

    public ImageLoader(String myUrl,ILoaded listener, int width, int height, boolean savedImage) {
        this.listener = listener;
        this.myUrl = myUrl;
        this.width = width;
        this.height = height;
        this.savedImage = savedImage;
        loadImage();
    }

    private void loadImage(){
        Executors.newFixedThreadPool(1).execute(new Runnable() {
            @Override
            public void run() {
                try {
                    url = new URL(myUrl);
                    in = url.openStream();
                    bitmap = BitmapFactory.decodeStream(in);
                    Log.d(TAG, "run: "+bitmap.getDensity());
                    if(savedImage){
                       new SaveImages().saveImage(bitmap,"interested");
                        Log.d(TAG, "runner: ");
                    }
                    if(width != 0 && height != 0){
                       changeBitmap();
                        setCallBackSucsess();
                    }else {
                        setCallBackSucsess();
                    }

                } catch (MalformedURLException e) {
                  setCallBackError(e.getMessage());
                    e.printStackTrace();
                } catch (IOException e){
                   setCallBackError(e.getMessage());
                }finally {
                    if(url != null){

                    }
                }
            }
        });
    }
private Bitmap changeBitmap(){
    Log.d(TAG, "changeBitmap: "+bitmap.getDensity());
   bitmap = Bitmap.createScaledBitmap(bitmap,width,height,false);
    Log.d(TAG, "changeBitmap: "+bitmap.getDensity());
    return bitmap;
}
    private void setCallBackSucsess(){
        handler.post(new Runnable() {
            @Override
            public void run() {
                listener.sucsessLoad(bitmap);
            }
        });

    }
    private void setCallBackError(final String error){
        handler.post(new Runnable() {
            @Override
            public void run() {
                listener.error(error);
            }
        });

    }
}
