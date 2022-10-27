package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Weather {
    private static String charset = "utf-8";
    private static String URL = "https://pogoda7.ru/prognoz/RU-Russia";//https://www.gismeteo.ru  https://www.gismeteo.ru/weather-perm-4476/now/

    public String CityName() {
        try {

            Document document = Jsoup.connect(URL).get();
            document.outputSettings().charset(charset);

            Elements city = document.select("div.listing > ul > li > a");//div.city
            Elements temperature = document.select("div.listing > ul > li > span.weather_temp_image > span.weather_temp");// weather-value div.temperature

            HashMap<String, String> CAT = new HashMap<>();

            String[] cArr = city.text().replace(" ", ",").split(",");
            String[] tArr = temperature.text().replace(" ", ",").split(",");

            List<String> cityList = new ArrayList<>(Arrays.asList(cArr));
            List<String> tempList = new ArrayList<>(Arrays.asList(tArr));

            for (int i = 0; i < tempList.size(); i++) {
                if (cityList.get(i) == cityList.get(5)) {
                    cityList.remove(cityList.get(5));
                }
                CAT.put(cityList.get(i), tempList.get(i));
            }
            System.out.println(CAT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void CityAndTemp(){

    }
}
//https://www.gismeteo.ru/ //https://world-weather.ru/pogoda/russia/vladimir/
//temperature city       ыыы  //h1, #weather-now-number

/*  private static String charset = "utf-8";
    private static String URL = "https://www.gismeteo.ru/weather-vladimir-4350/now/";//https://www.gismeteo.ru  https://www.gismeteo.ru/weather-perm-4476/now/

    public String CityName(){
        try {
            Document document = Jsoup.connect(URL).get();
            document.outputSettings().charset(charset);
            Elements city = document.select(" div.page-title");//div.city
            Elements temperature = document.select("div.weather-value > span");// weather-value div.temperature
            System.out.println("Temperature "+ temperature);
            System.out.printf("Город %s Температура %s",  Parser.ParserChar(city.toString()),  Parser.ParserNumeric(temperature.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }*/