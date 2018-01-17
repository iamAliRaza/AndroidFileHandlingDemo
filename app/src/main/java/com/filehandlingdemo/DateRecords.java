package com.filehandlingdemo;

import android.os.Environment;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class DateRecords extends Thread {

    private final String PATH = Environment.getExternalStorageDirectory().getPath()+"/DateRecord.txt";
    private final File file = new File (PATH);
    private String date;
    private FileWriter fw;
    private BufferedWriter bw;
    private FileReader fd;
    private BufferedReader br;


    DateRecords( String date ) {

        this.date = date;

    }

    @Override
    public void run() {

        try{

            if (!file.exists()) {

                file.createNewFile();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        openStreams();           // method called
        if (!checkDate(date)){   // method called

            MainActivity.dateInfo = false;
            writeDate(date);     // method called
        }
        else {
            MainActivity.dateInfo = true;
        }
        closeStreams();          // method called
    }

    private void writeDate (String date ){

        try {

            bw.write(date+"\n");
            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean checkDate( String date ){

        String save;
        boolean b = false;
        try {

            while (( save = br.readLine())!= null){

                if (save.equals(date)){ b =  true; break;}
                else b = false;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return b;
    }

    void openStreams (){

        try
        {
            fw = new FileWriter(file, true);
            bw = new BufferedWriter(fw);
            fd = new FileReader(file);
            br = new BufferedReader(fd);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    void closeStreams(){

        try
        {
            fw.close();
            bw.close();
            fd.close();
            br.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

}
