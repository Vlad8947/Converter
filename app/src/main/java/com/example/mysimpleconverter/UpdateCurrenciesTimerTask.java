package com.example.mysimpleconverter;

import java.util.TimerTask;

import static com.example.mysimpleconverter.UIController.isOnline;

public class UpdateCurrenciesTimerTask extends TimerTask {
    private MainActivity mainActivity;
    private CurrencyAsyncTask currencyAsyncTask;

    public UpdateCurrenciesTimerTask() { mainActivity = MainActivity.getInstance(); }

    @Override
    public void run() {
        if (isOnline(mainActivity)) {
            currencyAsyncTask = new CurrencyAsyncTask();
            currencyAsyncTask.execute();
        }
    }
}
