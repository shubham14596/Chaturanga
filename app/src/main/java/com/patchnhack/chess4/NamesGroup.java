package com.patchnhack.chess4;

import android.graphics.Color;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class NamesGroup {
    ArrayList<TextView> mNames;
    int cur=0;
    int colorBackOff = Color.BLACK, colorBackOn = Color.parseColor("#ece0d1");
    int colorOn = Color.parseColor("#0088ff");
    int colorOff = Color.GRAY;
    NamesGroup(ArrayList<TextView> names) {
        mNames = names;
    }

    public void setTurn(int i){
        if(i!=cur){
            TextView pre = mNames.get(cur);
            pre.setBackgroundColor(colorBackOff);
            pre.setTextColor(colorOff);

            cur=i;
        }

        TextView next = mNames.get(i);
        next.setBackgroundColor(colorBackOn);
        next.setTextColor(colorOn);
    }
}
