package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class Weather {
    private static String charset = "utf-8";
    private static String URL = "https://pogoda7.ru/prognoz/RU-Russia/r3266-LNR";//https://www.gismeteo.ru  https://www.gismeteo.ru/weather-perm-4476/now/
    private List<String> cityList;
    private List<String> tempList;
    private String CityNowFound;
    private String TempNowFound;

    public String MapCityAndTemp() {
        try {
//  System.out.println(city.text()); Выводит и Название города и Температуру в строке
            Document document = Jsoup.connect(URL).get();
            document.outputSettings().charset(charset);

            Elements city = document.select("div.listing > ul > li.gorod");//div.city// div.listing > ul > li > a
            Elements temperature = document.select("div.listing > ul > li > span.weather_temp_image > span.weather_temp");// weather-value div.temperature

            HashMap<String, String> CAT = new HashMap<>();

            String[] cArr = city.text().split("°");// через запятую отделяем города
            String[] tArr = temperature.text().split(" ");// через запятую отделяем Температуру
//
            cityList = new ArrayList<>(Arrays.asList(cArr));//
            tempList = new ArrayList<>(Arrays.asList(tArr));//
            for (int i = 0; i < tempList.size(); i++) {
                CAT.put(Parser.ParserChar(cityList.get(i)), tempList.get(i));// В цикле проходимся по Городам и Температуре, вбиваем туда значения по принципу: Ключ-Значение;
            }
            System.out.println("Список городов:" + CAT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String FindCity(String CityFind) {

        for (int i = 0; i < cityList.size(); i++) {
            if (CityFind.equals(Parser.ParserChar(cityList.get(i)))) {
                CityNowFound = Parser.ParserChar(cityList.get(i));  //Убрать дубликаты
                TempNowFound = tempList.get(i);                     //Убрать дубликаты
            }
        }
        System.out.printf("Город найден: %s; Температура на данный момент: %s;", CityNowFound, TempNowFound);
        return CityFind;
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