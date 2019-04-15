package com.example.leeyo.memorize;

import android.util.Log;

public class search_data {

    public static String titleText;

    public search_data(String titleText) {
        this.titleText = titleText;
        Log.v("bbb===", titleText);
    }

    public static String getTitleText() {
        return titleText;
    }

    public static void setTitleText(String titleText) {
        titleText = titleText;
    }
}
