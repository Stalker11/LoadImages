package com.olegel.loadimages;

import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements ILoaded{
private EditText edit;
    private Button enterButton;
    private ImageView imageView;
    private Handler handler;
    private MainActivity act;
    private String link = "http://elenergi.ru/wp-content/uploads/2017/01/%D0%9D%D0%BE%D0%B2%D1%8B%D0%B5-%D1%81%D0%BF%D0%B5%D0%BA%D1%82%D1%80%D0%B0%D0%BB%D1%8C%D0%BD%D1%8B%D0%B5-%D0%B4%D0%B0%D1%82%D1%87%D0%B8%D0%BA%D0%B8-%D0%B4%D0%BB%D1%8F-%D1%81%D0%BE%D1%80%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%BA%D0%B8-%D0%BF%D1%80%D0%BE%D0%B4%D1%83%D0%BA%D1%86%D0%B8%D0%B8.jpg";
    private static final String TAG = MainActivity.class.getSimpleName();
    public MainActivity() {
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit = (EditText)findViewById(R.id.enter_link);
        enterButton = (Button)findViewById(R.id.enter_link_button);
        imageView = (ImageView)findViewById(R.id.load_image);
        act = this;
        handler = new Handler();
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ImageLoader(link,act,true);
                edit.getText();
            }
        });
    }

    @Override
    public void sucsessLoad(final Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
        Log.d(TAG, "sucsessLoad: "+bitmap.getDensity());

    }

    @Override
    public void error(final String error) {
        Log.d(TAG, "error: "+error);

    }
}
