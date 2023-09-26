package com.project;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        String contents = "";

        File file1 = new File("O:\\6th\\ds\\project\\PatientsManagementSystem2\\input\\DSinp1.txt");
        BufferedReader br = new BufferedReader(new FileReader(file1));

        while ((contents = br.readLine()) != null) {
            String[] words = contents.split(" ");
            if (words[0].equals("Add")) {
                Operations.insert(Integer.parseInt(words[1]), Integer.parseInt(words[2]));
            } else if (words[0].equals("Update")) {
                Operations.update(Integer.parseInt(words[1]), Integer.parseInt(words[2]));
            } else if (words[1].equals("Sickest")) {
                Operations.serveSickest();
            } else if (words[1].equals("First")) {
                Operations.serveFirst();
            }
        }

    }
}
