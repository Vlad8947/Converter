package com.example.mysimpleconverter;

import android.net.Uri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;


public class NetworkUtils {
    final public static String httpJsonCurrencies = "https://www.cbr-xml-daily.ru/daily_json.js";

    public static URL genURLJsonCurrencies() {
        Uri builtUri = Uri.parse(httpJsonCurrencies)
                .buildUpon()
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getAllContentFromURL(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        String jsonText;
        try {
            InputStream in = urlConnection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")));
            jsonText = readAll(rd);
        } finally {
            urlConnection.disconnect();
        }
        return jsonText;
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }


}
