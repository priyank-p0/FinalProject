package com.example.priyankpatel.myapplication.backend;
import com.example.joke.Generatejoke;
/** The object model for the data we are sending through endpoints */
public class MyBean {

    private String myData;

    public String getData() {
        Generatejoke generatejoke=new Generatejoke();
        String name=generatejoke.getJoke();
    return name;
    }

    public void setData(String data) {
        myData = data;
    }
}