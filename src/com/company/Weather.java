package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class Weather {
    private static String charset = "utf-8";
    private String region;

    private String URL;//https://www.gismeteo.ru  https://www.gismeteo.ru/weather-perm-4476/now/
    private List<String> cityList;
    private List<String> tempList;
    private List<String> HrefValue;
    private List<String> infoList;
    private String CityNowFound;
    private String TempNowFound;
    private String URLCity;

    public Weather(String Region) {
        region = Region;
        URL = "https://pogoda7.ru/prognoz/RU-Russia/r" + region;
    }

    public String MapCityAndTemp() {
        try {
            Document document = Jsoup.connect(URL).get();
            document.outputSettings().charset(charset);

            Elements city = document.select("div.listing > ul > li.gorod ");//div.city// div.listing > ul > li > a
            Elements temperature = document.select("div.listing > ul > li > span.weather_temp_image > span.weather_temp");// weather-value div.temperature
            Elements cityHref = document.select("div.listing > ul > li.gorod >a");
            HashMap<String, String> CAT = new HashMap<>();
            HashMap<String, String> CAH = new HashMap<>();

            String[] cArr = city.text().split("°");// через запятую отделяем города
            String[] tArr = temperature.text().split(" ");// через запятую отделяем Температуру
//
            cityList = new ArrayList<>(Arrays.asList(cArr));//
            tempList = new ArrayList<>(Arrays.asList(tArr));//
            HrefValue = cityHref.eachAttr("href");//

            for (int i = 0; i < tempList.size(); i++) {
                CAT.put(Parser.ParserChar(cityList.get(i)), tempList.get(i));// В цикле проходимся по Городам и Температуре, вбиваем туда значения по принципу: Ключ-Значение;
                CAH.put(Parser.ParserChar(cityList.get(i)), HrefValue.get(i));// В цикле проходимся по Городам и Ссылкам, вбиваем туда значения по принципу: Ключ-Значение;
            }
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
                URLCity = "https://pogoda7.ru" + HrefValue.get(i);
            }
        }

        System.out.printf("Город найден: %s; Температура на данный момент: %s;", CityNowFound, TempNowFound);
        System.out.println();
        return CityFind;
    }

    public String getWeatherFor8Days() {
        Document document = null;
        try {
            document = Jsoup.connect(URLCity).get();
            document.outputSettings().charset(charset);

            Elements InfoFor3days = document.select("div.table-cell-day ");//div.city// div.listing > ul > li > a
            infoList = InfoFor3days.eachAttr("title");

            System.out.println();
            String[] IArr = InfoFor3days.text().split("%");// через запятую отделяем Температуру

            infoList = new ArrayList<>(Arrays.asList(IArr));//
            for (int i = 0; i < infoList.size(); i++) {
                System.out.println(infoList.get(i) + "%;");
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
