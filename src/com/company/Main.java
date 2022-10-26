package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

public class Main {

    public static void main(String[] args) {
        Weather weather = new Weather();
        weather.CityName();
    }
}
