package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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
    private List<Day> TimeOfDay = new ArrayList<>();

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

            String[] cArr = city.text().split("°");// ????? ??????? ???????? ??????
            String[] tArr = temperature.text().split(" ");// ????? ??????? ???????? ???????????

            cityList = new ArrayList<>(Arrays.asList(cArr));//
            tempList = new ArrayList<>(Arrays.asList(tArr));//
            HrefValue = cityHref.eachAttr("href");//

            for (int i = 0; i < tempList.size(); i++) {
                CAT.put(Parser.ParserChar(cityList.get(i)), tempList.get(i));// ? ????? ?????????? ?? ??????? ? ???????????, ??????? ???? ???????? ?? ????????: ????-????????;
                CAH.put(Parser.ParserChar(cityList.get(i)), HrefValue.get(i));// ? ????? ?????????? ?? ??????? ? ???????, ??????? ???? ???????? ?? ????????: ????-????????;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String FindCity(String CityFind) {
        for (int i = 0; i < cityList.size(); i++) {
            if (CityFind.equals(Parser.ParserChar(cityList.get(i)))) {
                CityNowFound = Parser.ParserChar(cityList.get(i));  //?????? ?????????
                TempNowFound = tempList.get(i);                     //?????? ?????????
                URLCity = "https://pogoda7.ru" + HrefValue.get(i);
            }
        }

        System.out.printf("Город найден: %s; Погода на данный момент: %s;", CityNowFound, TempNowFound);
        System.out.println();
        return CityFind;
    }

    public String getWeatherFor8Days() {

        Document document = null;
        try {
            document = Jsoup.connect(URLCity).get();
            document.outputSettings().charset(charset);

            Elements InfoFor3days = document.select(" div.dayline");//div.city// div.listing > ul > li > a
            for (Element element : InfoFor3days) {

                String[] IArr = element.text().split("% ");
                Day TOD = new Day(Parser.ParserChar1(IArr[0]), IArr[1], IArr[2], IArr[3]);
                System.out.println("" + TOD.getDate());
                System.out.println(" " + TOD.getMorning());
                System.out.println(" " + TOD.getDay());
                System.out.println(" " + TOD.getEvening());
                TimeOfDay.add(TOD);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}
