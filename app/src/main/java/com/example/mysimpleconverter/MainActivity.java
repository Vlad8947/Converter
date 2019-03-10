package com.example.mysimpleconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static MainActivity instance;
    private UIController uiController;

    public MainActivity() {
        instance = this;
    }

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiController = new UIController();
        uiController.setOnCreateInterface();
    }

    public void onButtonClick(View v) {
        uiController.onButtonClick(v);
    }

    public void onRefreshClick(View v) {
        uiController.onRefreshClick(v);
    }

    public static MainActivity getInstance() {
        return instance;
    }
}
