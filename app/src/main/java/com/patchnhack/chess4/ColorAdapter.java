package com.patchnhack.chess4;

import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import static com.patchnhack.chess4.utils.dpi;

public class ColorAdapter extends BaseAdapter {
    private Context mContext;

    // Constructor
    public ColorAdapter(Context c) {
        mContext = c;
    }

    @Override
    public int getCount() {
        return mColors.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Button btn;

        if(convertView == null) {
            btn = new Button(mContext);
            btn.setMinWidth(dpi(20, mContext));
            btn.setMinHeight(dpi(20,mContext));
            btn.setMaxWidth(dpi(20, mContext));
            btn.setMaxHeight(dpi(20, mContext));
            btn.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//            btn.setPadding(10,10,10,10);
            btn.setText("");
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ColorDiag f = (ColorDiag) ((AppCompatActivity)mContext).getSupportFragmentManager().findFragmentByTag("colorDialog");
                    f.setValue(mColors[position]);
                    f.dismiss();
                }
            });
        }
        else
            btn = (Button) convertView;

        btn.setBackground(getColor(mColors[position]));
//        btn.getBackground().setColorFilter(mColors[position], PorterDuff.Mode.SRC_ATOP);
        return btn;
    }
    private GradientDrawable getColor(int color){
        GradientDrawable d = new GradientDrawable();
        d.setShape(GradientDrawable.RECTANGLE);
        d.setCornerRadii(new float[]{10,10,10,10,10,10,10,10});
        d.setColor(color);
        d.setStroke(3,Color.BLACK);
        d.setSize(dpi(20, mContext), dpi(20, mContext));
        return d;
    }

    public int[] mColors = {
            Color.GRAY,Color.RED,
            Color.MAGENTA,Color.GREEN,
            Color.BLACK,Color.BLUE,
            Color.CYAN,Color.YELLOW,
            Color.parseColor("#FFFF00"), Color.parseColor("#FF37a0"),
            Color.parseColor("#2F17a0"),Color.parseColor("#7F99a0")};
}