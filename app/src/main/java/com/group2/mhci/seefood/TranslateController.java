package com.group2.mhci.seefood;

import java.util.ArrayList;

/**
 * Created by Brian on 4/3/2018.
 */

public class TranslateController {
    public static ArrayList<transSentence> enChoices = new ArrayList<transSentence>() {{
        add(transSentence.orderFood);
        add(transSentence.isForSale);
        add(transSentence.askPrice);
    }};
    public static ArrayList<String> enChoicesStr = new ArrayList<String>() {{
        add(transSentence.orderFood.ENShort);
        add(transSentence.isForSale.ENShort);
        add(transSentence.askPrice.ENShort);
        add("Custom sentence");
    }};

    public static ArrayList<transSentence> thChoices = new ArrayList<transSentence>() {{
        add(transSentence.ok);
        add(transSentence.notForSale);
        add(transSentence.runOut);
    }};
    public static ArrayList<String> thChoicesStr = new ArrayList<String>() {{
        add(transSentence.ok.THShort);
        add(transSentence.notForSale.THShort);
        add(transSentence.runOut.THShort);
        add("พิมพ์ประโยคเอง");
    }};


    public food currFood;
    public boolean enToTH = false; // Displaying English to Thai text, thai choices
    public String lastTrans = "";
    public String lastTransSource = "";

    public TranslateController(int catID, int itemID) {
        currFood = food.foodList[catID][itemID];
        lastTrans = currFood.TH;
        lastTransSource = currFood.EN;
    }

    public void translate(int selected) {
        enToTH = !enToTH;
        if(enToTH){
            transSentence translated = enChoices.get(selected).setFood(currFood.TH, currFood.EN);
            lastTransSource = translated.EN;
            lastTrans = translated.TH;
        }
        else {
            transSentence translated = thChoices.get(selected).setFood(currFood.TH, currFood.EN);
            lastTransSource = translated.TH;
            lastTrans = translated.EN;
        }
    }

    public void translate(String custom) {
        enToTH = !enToTH;
        lastTransSource = custom;
        if (enToTH) lastTrans = transSentence.trans(custom, "th");
        else lastTrans = transSentence.trans(custom, "en");
    }

    public ArrayList<String> getChoicesString(){
        if (enToTH) return thChoicesStr;
        else return enChoicesStr;
    }
}
