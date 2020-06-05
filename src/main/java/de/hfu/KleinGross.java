package de.hfu;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KleinGross {
    /**
     * Programm
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException  {
        BufferedReader Reader = new BufferedReader(new InputStreamReader(System.in));
        String S = Reader.readLine();
        System.out.println(S.toUpperCase());

    }

}
