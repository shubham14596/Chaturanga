package com.patchnhack.chess4;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HelpFragment extends Fragment {
    public static final String ARG_OBJECT = "Page";

    int[] imgs = new int[]{
            R.drawable.helpmain,
            R.drawable.hking,
            R.drawable.hknight,
            R.drawable.hrook,
            R.drawable.hpawn,
            R.drawable.hboat};
    String[] detail = {"Play in team with diagonal mate.","King moves 1 square","Knight jumps 2+1","Rook moves straight horizontal and vertical","Pawn moves one up, kills diagonally","Boat jumps two squares diagonal"};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        int p = args.getInt(ARG_OBJECT);

        Resources r = getResources();
        BitmapDrawable d = new BitmapDrawable(r, BitmapFactory.decodeResource(r, imgs[p]));

        ((ImageView) view.findViewById(R.id.img)).setImageDrawable(d);
        ((TextView) view.findViewById(R.id.idx))
                .setText(detail[p]);

    }
}
