package com.olegel.loadimages;

import android.graphics.Bitmap;

public interface ILoaded {
    void sucsessLoad(Bitmap bitmap);
    void error(String error);
}
