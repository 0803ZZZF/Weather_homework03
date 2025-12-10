package com.example.weatherapp.bean; 

import java.util.List;

public class WeatherResponse {
    private String status; // 接口状态（1=成功）
    private List<WeatherLive> lives; // 实时天气列表
    private List<WeatherForecast> forecasts; // 预报天气列表

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public List<WeatherLive> getLives() { return lives; }
    public void setLives(List<WeatherLive> lives) { this.lives = lives; }
    public List<WeatherForecast> getForecasts() { return forecasts; }
    public void setForecasts(List<WeatherForecast> forecasts) { this.forecasts = forecasts; }
}
