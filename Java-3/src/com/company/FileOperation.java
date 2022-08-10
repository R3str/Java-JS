package com.company;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FileOperation
{
    public static void Save(String filename, Country[] countries) throws IOException
    {
        try
        {
            FileWriter fw = new FileWriter(filename);
            fw.write(countries.length + "\n");

            for(int i = 0; i < countries.length; i++)
            {
                fw.write(countries[i].name + "\n");
                fw.write(countries[i].capital + "\n");
                fw.write(countries[i].population + "\n");
            }

            fw.close();
        }
        catch (IOException ex)
        {
            System.out.println("Ошибка: " + ex.getMessage());
        }
    }

    public static Country[] Load(String filename) throws IOException
    {
        Country[] countries = new Country[0];

        try
        {
            Scanner scanner = new Scanner(new FileReader(filename));
            int i = 0;

            countries = new Country[Integer.parseInt(scanner.nextLine())];

            for (int j = 0; j < countries.length; j++)
                countries[j] = new Country();

            while (scanner.hasNextLine())
            {
                countries[i].name = scanner.nextLine();
                countries[i].capital = scanner.nextLine();
                countries[i].population = Integer.parseInt(scanner.nextLine());

                i++;
            }
        }
        catch (Exception ex)
        {
            System.out.println("Ошибка: " + ex.getMessage());
        }

        return countries;
    }

}
