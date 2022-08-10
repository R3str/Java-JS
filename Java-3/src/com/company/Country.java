package com.company;

import java.util.Scanner;

public class Country {
    public String name;
    public String capital;
    public int population;

    public Country()
    {
        this.name = "";
        this.capital = "";
        this.population = 0;
    }

    public Country(String name, String capital, int population)
    {
        this.name = CheckNumberOrTitleForConstructor(name, "title") ? name : "Error";
        this.capital = CheckNumberOrTitleForConstructor(capital, "title") ? capital : "Error";
        String stringPopulation = String.valueOf(population);
        this.population = CheckNumberOrTitleForConstructor(stringPopulation, "number") ? population : -1;
    }

    public static Country[] Add(Country[] countries)
    {
        Scanner scanner = new Scanner(System.in);
        int newSizeMassiveInt, intPoulation;
        String name, capital, stringPopulation;

        System.out.print("Сколько хотите добавить стран: ");
        String newSizeMassive = CheckNumberOrTitle(scanner.nextLine(), "number");
        newSizeMassiveInt = Integer.parseInt(newSizeMassive);

        Country[] reservMassive = new Country[newSizeMassiveInt+countries.length];

        for (int i = 0; i < reservMassive.length; i++)
        {
            reservMassive[i] = new Country();
            if(i < countries.length)
                reservMassive[i] = countries[i];
        }

        for (int i = countries.length; i < reservMassive.length; i++)
        {
            System.out.println("\nСтрана №" + (i + 1));
            System.out.print("Введите название страны: ");
            name = CheckNumberOrTitle(scanner.nextLine(), "title");

            System.out.print("Введите название столицы: ");
            capital = CheckNumberOrTitle(scanner.nextLine(), "title");

            System.out.print("Введите кол-во населения: ");
            stringPopulation = CheckNumberOrTitle(scanner.nextLine(), "number");

            intPoulation = Integer.parseInt(stringPopulation);
            reservMassive[i] = new Country(name, capital,intPoulation);
            }

        return reservMassive;
    }

    public static Country[] Delete(Country[] countries)
    {
        Scanner scanner = new Scanner(System.in);

        int intNumberDelete, j = 0;

        System.out.print("Какую страну хотите удалить?\nВведите номер страны: ");
        String stringNumberDelete = CheckNumberOrTitle(scanner.nextLine(), "number");

        intNumberDelete = (Integer.parseInt(stringNumberDelete)-1);


        Country[] reservMassive = new Country[countries.length-1];

        for (int i = 0; i < reservMassive.length; i++) {
            if(i >= intNumberDelete)
                reservMassive[i] = countries[i+1];
            else
                reservMassive[i] = countries[i];
        }

        return reservMassive;
    }

    public static void ShowCountry(Country[] countries) {
        for (int i = 0; i < countries.length; i++)
        {
            System.out.println("\nСтрана №" + (i + 1));
            System.out.println("Страна: " + countries[i].name);
            System.out.println("Столица: " + countries[i].capital);
            System.out.println("Население: " + countries[i].population);
            System.out.println("");
        }
    }

    public static String CheckNumberOrTitle(String number, String modeWork)
    {
        Scanner scanner = new Scanner(System.in);
        boolean isDigit;

        do
        {
            isDigit = true;

            if(modeWork == "number")
            {
                for (int i = 0; i < number.length(); i++) {
                    if (!Character.isDigit(number.charAt(i))) {
                        isDigit = false;
                        break;
                    }
                }
            }
            if(modeWork == "title")
            {
                for (int i = 0; i < number.length(); i++) {
                    if (Character.isDigit(number.charAt(i))) {
                        isDigit = false;
                        break;
                    }
                }
            }

            if(isDigit)
                break;

            System.out.println("Некорректное значение! Попробуйте еще раз");
            number = scanner.nextLine();

        }while(!isDigit);

        return number;
    }

    public static boolean CheckNumberOrTitleForConstructor(String number, String modeWork)
    {
        boolean isDigit;

        isDigit = true;

        if(modeWork == "number")
        {
            for (int i = 0; i < number.length(); i++) {
                if (!Character.isDigit(number.charAt(i))) {
                    isDigit = false;
                    break;
                }
            }
        }
        if(modeWork == "title")
        {
            for (int i = 0; i < number.length(); i++) {
                if (Character.isDigit(number.charAt(i))) {
                    isDigit = false;
                    break;
                }
            }
        }

        return isDigit;
    }
}
