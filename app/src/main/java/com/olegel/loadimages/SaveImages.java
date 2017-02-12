package com.olegel.loadimages;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaScannerConnection;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;

public class SaveImages {
    private static final String TAG = SaveImages.class.getSimpleName();
    private static Context context;
    private File mediaStorageDir;
    public static void setContext(Context cont){
        context = cont;
    }
    public void saveImage(Bitmap image,String imageName) {
        String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), context.getResources().getString(R.string.app_name));
        Log.d(TAG, "saveImage: "+mediaStorageDir.getAbsolutePath());
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(TAG, "failed to create directory");
                return;
            }
        }
        try {
            File file = new File(mediaStorageDir, imageName + ".jpg");
            Log.d(TAG, "saveImage: " + file.exists()+"  "+File.separator);
            if (file.exists()) {
               // ToastsMessage.message(context.getResources().getString(R.string.image_is));
                return;
            }
            Log.d(TAG, "saveImage: " + file.exists() + "  " + file.getAbsolutePath() + " " + file.canRead());
            FileOutputStream stream = new FileOutputStream(file);
            Bitmap bitmap = image;
            bitmap.compress(Bitmap.CompressFormat.JPEG, 85, stream);
            String[] path = new String[]{file.getAbsolutePath()};

            stream.flush();
            stream.close();
            MediaScannerConnection.scanFile(context, path, null, null);

           // ToastsMessage.message(context.getResources().getString(R.string.image_saved));
        } catch (Exception e) {
            Log.d(TAG, "saveImage: " + e.getMessage());
           // ToastsMessage.message(e.getMessage());
        }
    }
}
