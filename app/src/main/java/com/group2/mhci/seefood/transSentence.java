package com.group2.mhci.seefood;

import android.os.StrictMode;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.apache.commons.text.StringEscapeUtils;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Brian on 3/3/2018.
 */

public class transSentence {
    public String TH;
    public String EN;
    public String THShort;
    public String ENShort;

    public transSentence(String TH, String EN, String THShort, String ENShort) {
        this.TH = TH;
        this.EN = EN;
        this.THShort = THShort;
        this.ENShort = ENShort;
    }
    // Replaces %s with food name.
    public transSentence setFood(String THfoodName, String ENfoodName){
        return new transSentence(
                this.TH.replaceAll("%s", THfoodName),
                this.EN.replaceAll("%s", ENfoodName),
                this.THShort.replaceAll("%s", THfoodName),
                this.ENShort.replaceAll("%s", ENfoodName)
        );
    }

    // Template sentences
    // Sample usage: transSentence a = transSentence.orderFood.setFood("แกงเขียวหวาน", "Green Curry")
    // a.TH, a.EN, a.THShort, a.ENShort

    public static transSentence orderFood = new transSentence(
            "ขอสั่งเป็น%sครับ",
            "I would like to order %s please.",
            "สั่งอาหาร",
            "Order the dish"
    );

    public static transSentence isForSale = new transSentence(
            "มี%sขายมั้ยครับ?",
            "Do you sell %s?",
            "มีขายมั้ย?",
            "Is it available?"
    );

    public static transSentence notForSale = new transSentence(
            "ขออภัย, ไม่มีขายครับ",
            "Sorry, we do not sell it.",
            "ไม่มีขาย",
            "Not for sale"
    );

    public static transSentence askPrice = new transSentence(
            "%sราคาเท่าไหร่ครับ",
            "How much is %s?",
            "ถามราคา",
            "Ask price"
    );

    public static transSentence ok = new transSentence(
            "ตกลง",
            "Ok",
            "ตกลง",
            "Ok"
    );

    public static transSentence runOut = new transSentence(
            "ขออภัย, %sหมดครับ",
            "Sorry, we ran out of %s.",
            "หมด",
            "Ran out"
    );



    // Example trans("สวัสดี", "en") to translate "สวัสดี" to english
    public static String trans(String input, String targetLang) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        HttpURLConnection connection = null;
        try {
            String urlParameters = "q="+URLEncoder.encode(input, "UTF-8")+"&target="+targetLang;
            //Create connection
            URL url = new URL("https://translation.googleapis.com/language/translate/v2?key=AIzaSyD-iRIIIc__l3gAKAnCm5P-TmfQXnN11cA");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");


            connection.setRequestProperty("Content-Length",
                    Integer.toString(urlParameters.getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");

            connection.setUseCaches(false);
            connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream (
                    connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.close();

            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();

            Log.d("RAW RESPONSE", response.toString());
            JSONObject obj = new JSONObject(response.toString());
            JSONObject data = obj.getJSONObject("data");
            JSONArray translations = data.getJSONArray("translations");
            JSONObject firstTrans = translations.getJSONObject(0);
            String firstTransString = firstTrans.getString("translatedText");
            return StringEscapeUtils.unescapeXml(firstTransString); // Unescape characters like &#39; to '
        } catch (Exception e) {
            e.printStackTrace();
            return "Translation Error";
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
