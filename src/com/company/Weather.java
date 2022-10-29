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
//  System.out.println(city.text()); ������� � �������� ������ � ����������� � ������
            Document document = Jsoup.connect(URL).get();
            document.outputSettings().charset(charset);

            Elements city = document.select("div.listing > ul > li.gorod");//div.city// div.listing > ul > li > a
            Elements temperature = document.select("div.listing > ul > li > span.weather_temp_image > span.weather_temp");// weather-value div.temperature

            HashMap<String, String> CAT = new HashMap<>();

            String[] cArr = city.text().split("�");// ����� ������� �������� ������
            String[] tArr = temperature.text().split(" ");// ����� ������� �������� �����������
//
            cityList = new ArrayList<>(Arrays.asList(cArr));//
            tempList = new ArrayList<>(Arrays.asList(tArr));//
            for (int i = 0; i < tempList.size(); i++) {
                CAT.put(Parser.ParserChar(cityList.get(i)), tempList.get(i));// � ����� ���������� �� ������� � �����������, ������� ���� �������� �� ��������: ����-��������;
            }
            System.out.println("������ �������:" + CAT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String FindCity(String CityFind) {

        for (int i = 0; i < cityList.size(); i++) {
            if (CityFind.equals(Parser.ParserChar(cityList.get(i)))) {
                CityNowFound = Parser.ParserChar(cityList.get(i));  //������ ���������
                TempNowFound = tempList.get(i);                     //������ ���������
            }
        }
        System.out.printf("����� ������: %s; ����������� �� ������ ������: %s;", CityNowFound, TempNowFound);
        return CityFind;
    }
}
//https://www.gismeteo.ru/ //https://world-weather.ru/pogoda/russia/vladimir/
//temperature city       ���  //h1, #weather-now-number

/*  private static String charset = "utf-8";
    private static String URL = "https://www.gismeteo.ru/weather-vladimir-4350/now/";//https://www.gismeteo.ru  https://www.gismeteo.ru/weather-perm-4476/now/

    public String CityName(){
        try {
            Document document = Jsoup.connect(URL).get();
            document.outputSettings().charset(charset);
            Elements city = document.select(" div.page-title");//div.city
            Elements temperature = document.select("div.weather-value > span");// weather-value div.temperature
            System.out.println("Temperature "+ temperature);
            System.out.printf("����� %s ����������� %s",  Parser.ParserChar(city.toString()),  Parser.ParserNumeric(temperature.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }*/