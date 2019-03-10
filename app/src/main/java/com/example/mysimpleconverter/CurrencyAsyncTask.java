package com.example.mysimpleconverter;

import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;


class CurrencyAsyncTask extends AsyncTask<Void, Void, Void> {

    private FileOperations fileOperations;
    private TextView initialServerLink;
    private TextView lastRefreshingDate;
    private String date;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(MainActivity.getInstance(), "Загружаю курсы " +
                "валют", Toast.LENGTH_LONG).show();
    }
    @Override
    protected Void doInBackground(Void... urls) {
        try {
            //совершаем get-запрос на получение json-текста всех курсов валют с сервера
            fileOperations = new FileOperations();
            String allContentFromURL = NetworkUtils.getAllContentFromURL(NetworkUtils.genURLJsonCurrencies());
            fileOperations.writeFile(allContentFromURL);

            //Парсим дату последнего обновления курса валют на сервере
            JSONObject jsonObject = new JSONObject(allContentFromURL);
            date = jsonObject.getString("Timestamp");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        lastRefreshingDate = (TextView) MainActivity.getInstance().findViewById(R.id.textView);
        initialServerLink = (TextView) MainActivity.getInstance().findViewById(R.id.textView4);

        lastRefreshingDate.setText(date);
        initialServerLink.setText("Ссылка на источник: " + NetworkUtils.httpJsonCurrencies);
    }
}
