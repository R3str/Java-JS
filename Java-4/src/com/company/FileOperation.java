package com.company;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

public class FileOperation {
    public static void Serialization(String filename, Country[] countries)
    {
        try
        {
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            out.writeObject(countries);
            out.close();
            fileOut.close();
        }
        catch (Exception ex)
        {
            System.out.println("Ошибка: " + ex.getMessage());
        }
    }

    public static Country[] Deserialization(String filename)
    {
        Country[] countries = new Country[1];

        try
        {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            countries = (Country[]) in.readObject();

            in.close();
            fileIn.close();
        }
        catch (Exception ex)
        {
            System.out.println("Ошибка: " + ex.getMessage());
        }

        return countries;
    }

    public static void JacksonSeralization(String filename, Country[] countries)
    {
        try
        {
            new ObjectMapper().writeValue(new File(filename), countries);
        }
        catch (Exception ex)
        {
            System.out.println("Ошибка: " + ex.getMessage());
        }
    }

    public static Country[] JacksonDeseralization(String filename) throws IOException
    {
        Country[] countries = new ObjectMapper().readValue(new File(filename), Country[].class);

        try
        {
            new ObjectMapper().readValue(new File(filename), Country.class);
        }
        catch (Exception ex)
        {
            System.out.println("Ошибка: " + ex.getMessage());
        }

        return countries;
    }
}
