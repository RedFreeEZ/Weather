package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Weather {
    private static String charset = "utf-8";
    private static String URL = "https://www.gismeteo.ru/";

    public String CityName(){
        try {
            Document document = Jsoup.connect(URL).get();
            document.outputSettings().charset(charset);
            Elements city = document.select(" div.city");
            Elements temperature = document.select("div.temperature");
            System.out.printf("Город %s Температура %s", Parser.ParserChar(city.toString()),  Parser.ParserNumeric(temperature.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
//https://www.gismeteo.ru/ //https://world-weather.ru/pogoda/russia/vladimir/
//temperature city       ыыы  //h1, #weather-now-number