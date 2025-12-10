package com.example.weatherapp.utils; 

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtil {
    // 发送天气请求的方法
    public static void sendWeatherRequest(String cityCode, String apiKey, okhttp3.Callback callback) {
        // 高德天气API地址（拼接城市编码+API Key）
        String url = "https://restapi.amap.com/v3/weather/weatherInfo?city=" + cityCode + "&extensions=all&key=" + apiKey;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(callback); // 异步请求
    }
}
