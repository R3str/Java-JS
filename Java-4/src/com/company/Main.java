package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Main extends FileOperation {

    public static void main(String[] args) {
        Country[] countries;

        String filenameForMain = "Main.txt", filenameForJackson = "Jackson.txt";
        String choise = "";


/*———————————————————— Нативная десериализация ————————————————————*/
        countries = Deserialization(filenameForMain);

/*———————————————————— Десериализация с помощью внешней библиотеки ————————————————————
        countries = JacksonDeseralization(filenameForJackson);*/


        while (!choise.equals("5"))
        {
            Country.ShowCountry(countries);

            System.out.print("\nВыберите действие:\n" +
                    "  1. Добавить страну\n" +
                    "  2. Убрать страну\n" +
                    "  3. Сохранить базу данных\n" +
                    "  4. Загрузить начальную копию базы данных\n" +
                    "  5. Выход из программы\n" +
                    "Ваш выбор: ");

            Scanner scanner = new Scanner(System.in);

            choise = Country.CheckNumberOrTitle(scanner.nextLine(), "number");

            switch (choise)
            {
                case "1": countries = Country.Add(countries); break;
                case "2": countries = Country.Delete(countries); break;
                case "3":
/*———————————————————— Нативная сериализация ————————————————————*/
                    Serialization(filenameForMain, countries);

/*———————————————————— Сериализация с помощью внешней библиотеки ————————————————————
                    JacksonSeralization(filenameForJackson, countries);*/

                    System.out.println("Успешно сохранено\n"); break;
                case "4":
/*———————————————————— Нативная десериализация————————————————————*/
                    countries = Deserialization(filenameForMain);

/*———————————————————— Десериализация с помощью внешней библиотеки ————————————————————
                    countries = JacksonDeseralization(filenameForJackson);*/

                    System.out.println("Успешно загружено"); break;
                case "5": System.out.println("Завершение программы"); break;
                default: System.out.println("Не верное значение. Попробуйте еще раз\n");
            }
        }

    }
}
