package com.company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Main {

    public static void main(String[] args) throws Exception {
//========== Переход по ссылке и считывание ==========
        JSONGetter.url = "https://api.covid19api.com/countries";
        String jsonData = JSONGetter.ConnectAndGet();

//========== Перевод строки в JSON массив ==========
        JSONArray jsonArray = (JSONArray) new JSONParser().parse(jsonData);

        Countries countries = new Countries();

//========== Заполнение основного массива ==========
        for (Object jsonObject : jsonArray)
        {
            JSONObject current = (JSONObject) jsonObject;

            String nameCountry = (String) current.get("Country");
            String slug = (String) current.get("Slug");
            String iSO2 = (String) current.get("ISO2");

            countries.Add( new Country(nameCountry, slug, iSO2) );
        }

        System.out.println(countries);

//========== Сортировка по алфавиту ==========
        countries.SortAsc();
        System.out.println(countries);

//========== Сортировка в обратном порядке ==========
        countries.SortDesc();
        System.out.println(countries);
    }
}
