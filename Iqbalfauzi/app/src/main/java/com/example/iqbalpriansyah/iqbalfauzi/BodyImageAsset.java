package com.example.iqbalpriansyah.iqbalfauzi;

import java.util.ArrayList;
import java.util.List;

public class BodyImageAsset {

    private static List<Integer> cloths = new ArrayList<Integer>() {
        {
            add(R.drawable.cloth1);
            add(R.drawable.cloth2);
            add(R.drawable.cloth3);
            add(R.drawable.cloth4);
        }
    };

    public static List<Integer> getCloths(){
        return cloths;
    }
}
