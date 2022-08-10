package com.company;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

public class JSONGetter
{
    public static String jsonIn;
    public static String url;


    private static String readAll(Reader rd) throws Exception
    {
        StringBuilder sb = new StringBuilder();
        int cp;

        while( (cp = rd.read()) != -1 )
        {
            sb.append((char)cp);
        }

        return sb.toString();
    }

    public static String ConnectAndGet() throws Exception
    {
        jsonIn = "";
        InputStream is = new URL(url).openStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

        jsonIn = readAll(rd);

        is.close();

        return jsonIn;
    }
}
