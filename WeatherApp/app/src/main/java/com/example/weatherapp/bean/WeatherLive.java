package com.example.weatherapp.bean; // 替换成你的包名

public class WeatherLive {
    private String city; // 城市名
    private String weather; // 实时天气
    private String temperature; // 实时温度
    private String winddirection; // 风向
    private String windpower; // 风力

    // Getter和Setter方法
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getWeather() { return weather; }
    public void setWeather(String weather) { this.weather = weather; }
    public String getTemperature() { return temperature; }
    public void setTemperature(String temperature) { this.temperature = temperature; }
    public String getWinddirection() { return winddirection; }
    public void setWinddirection(String winddirection) { this.winddirection = winddirection; }
    public String getWindpower() { return windpower; }
    public void setWindpower(String windpower) { this.windpower = windpower; }
}