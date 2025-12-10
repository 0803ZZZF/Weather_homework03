package com.example.weatherapp.bean; 

import java.util.List;

public class WeatherForecast {
    private String city; // 城市名
    private List<ForecastDay> casts; // 每日预报列表

    // Getter和Setter方法
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public List<ForecastDay> getCasts() { return casts; }
    public void setCasts(List<ForecastDay> casts) { this.casts = casts; }

    // 内部类：每日预报详情
    public static class ForecastDay {
        private String date; // 日期
        private String dayweather; // 白天天气
        private String nightweather; // 夜间天气
        private String daytemp; // 白天温度
        private String nighttemp; // 夜间温度
        private String daywind; // 白天风向
        private String daypower; // 白天风力

        // Getter和Setter方法
        public String getDate() { return date; }
        public void setDate(String date) { this.date = date; }
        public String getDayweather() { return dayweather; }
        public void setDayweather(String dayweather) { this.dayweather = dayweather; }
        public String getNightweather() { return nightweather; }
        public void setNightweather(String nightweather) { this.nightweather = nightweather; }
        public String getDaytemp() { return daytemp; }
        public void setDaytemp(String daytemp) { this.daytemp = daytemp; }
        public String getNighttemp() { return nighttemp; }
        public void setNighttemp(String nighttemp) { this.nighttemp = nighttemp; }
        public String getDaywind() { return daywind; }
        public void setDaywind(String daywind) { this.daywind = daywind; }
        public String getDaypower() { return daypower; }
        public void setDaypower(String daypower) { this.daypower = daypower; }
    }
}
