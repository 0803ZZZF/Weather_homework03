package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.weatherapp.bean.WeatherForecast;
import com.example.weatherapp.bean.WeatherResponse;
import com.example.weatherapp.bean.WeatherLive;
import com.example.weatherapp.utils.HttpUtil;
import com.google.gson.Gson;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import java.io.IOException;
import android.os.Build;
import android.os.StrictMode;

public class WeatherActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String AMAP_API_KEY = "234cbea645e84acefdaf1c95e9f494c8";
    // 城市编码（北京110101、上海310100、广州440100、深圳440300）
    private String currentCityCode = "440100"; // 默认广州

    // 控件声明
    private TextView tvCityName, tvLiveWeather, tvLiveTemp, tvLiveWind;
    private LinearLayout llForecastContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        // 初始化控件
        initViews();
        // 加载默认城市天气
        requestWeather(currentCityCode);
    }

    // 初始化控件
    private void initViews() {
        tvCityName = findViewById(R.id.tv_city_name);
        tvLiveWeather = findViewById(R.id.tv_live_weather);
        tvLiveTemp = findViewById(R.id.tv_live_temp);
        tvLiveWind = findViewById(R.id.tv_live_wind);
        llForecastContainer = findViewById(R.id.ll_forecast_container);

        // 绑定城市切换点击事件
        findViewById(R.id.tv_beijing).setOnClickListener(this);
        findViewById(R.id.tv_shanghai).setOnClickListener(this);
        findViewById(R.id.tv_guangzhou).setOnClickListener(this);
        findViewById(R.id.tv_shenzhen).setOnClickListener(this);
    }

    // 发送天气请求
    private void requestWeather(String cityCode) {
        HttpUtil.sendWeatherRequest(cityCode, AMAP_API_KEY, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 网络请求失败（主线程提示）
                runOnUiThread(() -> Toast.makeText(WeatherActivity.this, "请求失败，请检查网络", Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // 解析天气数据
                String json = response.body().string();
                Gson gson = new Gson();
                WeatherResponse weatherResponse = gson.fromJson(json, WeatherResponse.class);

                // 数据解析成功，更新UI（必须在主线程）
                if ("1".equals(weatherResponse.getStatus())) {
                    runOnUiThread(() -> {
                        updateLiveWeather(weatherResponse);
                        updateForecastWeather(weatherResponse);
                    });
                } else {
                    runOnUiThread(() -> Toast.makeText(WeatherActivity.this, "数据获取失败", Toast.LENGTH_SHORT).show());
                }
            }
        });
    }

    // 更新实时天气UI
    private void updateLiveWeather(WeatherResponse response) {
        if (response.getLives() != null && !response.getLives().isEmpty()) {
            WeatherLive live = response.getLives().get(0);
            tvCityName.setText(live.getCity());
            tvLiveWeather.setText(live.getWeather());
            tvLiveTemp.setText(live.getTemperature() + "°");
            tvLiveWind.setText(live.getWinddirection() + live.getWindpower() + "级");
        }
    }

    // 更新未来预报UI
    private void updateForecastWeather(WeatherResponse response) {
        llForecastContainer.removeAllViews(); // 清空旧数据
        if (response.getForecasts() != null && !response.getForecasts().isEmpty()) {
            WeatherForecast forecast = response.getForecasts().get(0);
            for (WeatherForecast.ForecastDay day : forecast.getCasts()) {
                // 创建TextView显示每日预报
                TextView tvDay = new TextView(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(0, 10, 0, 10); // 上下边距
                tvDay.setLayoutParams(params);
                tvDay.setTextSize(14);
                // 拼接预报信息
                String info = day.getDate() +
                        " 白天：" + day.getDayweather() + " " + day.getDaytemp() + "° | " +
                        "夜间：" + day.getNightweather() + " " + day.getNighttemp() + "° | " +
                        day.getDaywind() + day.getDaypower() + "级";
                tvDay.setText(info);
                llForecastContainer.addView(tvDay);
            }
        }
    }

    // 城市切换点击事件
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tv_beijing) {
            currentCityCode = "110101";
        } else if (id == R.id.tv_shanghai) {
            currentCityCode = "310100";
        } else if (id == R.id.tv_guangzhou) {
            currentCityCode = "440100";
        } else if (id == R.id.tv_shenzhen) {
            currentCityCode = "440300";
        }
        // 重新请求选中城市的天气
        requestWeather(currentCityCode);
    }
}