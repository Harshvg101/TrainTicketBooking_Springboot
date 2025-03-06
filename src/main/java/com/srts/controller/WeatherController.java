//package com.srts.controller;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//import org.json.JSONO bject;
//
//@RestController
//@RequestMapping("/weather")
//public class WeatherController {
//
//    private final String API_KEY = "YOUR_OPENWEATHERMAP_API_KEY"; // Get an API key from OpenWeatherMap
//
//    @GetMapping("/current")
//    public String getWeather(@RequestParam String city) {
//        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + API_KEY + "&units=metric";
//        RestTemplate restTemplate = new RestTemplate();
//        String response = restTemplate.getForObject(url, String.class);
//
//        JSONObject jsonObject = new JSONObject(response);
//        double temp = jsonObject.getJSONObject("main").getDouble("temp");
//        String weather = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");
//
//        return "Temperature: " + temp + "°C, Condition: " + weather;
//    }
//}
