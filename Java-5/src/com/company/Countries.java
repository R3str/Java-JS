package com.company;

import java.util.ArrayList;

public class Countries {
    private ArrayList<Country> countries;

    public Countries()
    {
        this.countries = new ArrayList<>();
    }

    public Countries(ArrayList<Country> countries)
    {
        setCountries(countries);
    }


    public ArrayList<Country> getCountries() { return countries; }

    public void setCountries(ArrayList<Country> countries) { this.countries = countries; }


    public void Add(Country country)
    {
        this.countries.add(country);
    }

    public void SortAsc()
    {
        this.getCountries().sort(Country.byNameAsc);
    }

    public void SortDesc()
    {
        this.getCountries().sort(Country.byNameDesc);
    }


    public String toString()
    {
        String result = "Ваш список:" + System.lineSeparator();

        for (Country c : countries) {
            result += c;
        }
        return result;
    }

}
