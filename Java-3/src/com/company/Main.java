package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Main extends FileOperation {

    public static void main(String[] args) throws IOException {
        Country[] countries;

        String filename = "note.txt", reservFilename = "reservNote.txt";
        String choise = "";

        countries = Load(filename);
        Save(reservFilename, countries);

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
                case "3": Save(filename, countries); System.out.println("Успешно сохранено"); break;
                case "4": countries = Load(reservFilename); System.out.println("Успешно загружено"); break;
                case "5": System.out.println("Завершение программы"); break;
                default: System.out.println("Не верное значение. Попробуйте еще раз\n");
            }
        }
    }
}
