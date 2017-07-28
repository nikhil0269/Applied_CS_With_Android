package com.example.nikhil.ghost;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class SimpleDictionary implements GhostDictionary
{
    ArrayList<String> words;
    SimpleDictionary(InputStreamReader inputStreamReader)
    {
        BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
        String line;
        words=new ArrayList<>();
        try {
            while((line=bufferedReader.readLine())!=null)
            {
                String word=line.trim();
                if(word.length()>=MIN_WORD_LENGTH)
                {
                    words.add(word);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    String binSearch(String prefix,int l,int h)
    {
        int m=l+(h-l)/2;
        String s;
        if(l<=h)
        {
            if(words.get(m).length()>prefix.length())
            {
                s=words.get(m).substring(0,prefix.length());
            }
            else {
                s=words.get(m).substring(0);
            }
            if(prefix.compareTo(s)<0)
            {
                return binSearch(prefix,l,m-1);
            }
            else if(prefix.compareTo(s)==0)
            {
                return words.get(m);
            }
            else {
                return binSearch(prefix,m+1,h);
            }
        }
        return null;
    }

    @Override
    public boolean isWord(String word) {

        return words.contains(word);
    }

    @Override
    public String getAnyWordStartingWith(String prefix) {
        if(prefix==null)
        {
            Random random=new Random();
            return words.get((random.nextInt())%1000);
        }
        String temp=binSearch(prefix,0,words.size());
        return temp;
    }

    @Override
    public String getGoodWordStartingWith(String prefix) {
        return null;
    }
}