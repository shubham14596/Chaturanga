package com.patchnhack.chess4;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.fragment.app.DialogFragment;

import static com.patchnhack.chess4.utils.dpi;
import static com.patchnhack.chess4.utils.tint;

public class ColorDiag extends DialogFragment {
    Context mCon;
    int col;
    View view;
    public ColorDiag(Context c, View v){
        mCon = c;
        view = v;
    }
    public void setValue(int color){
        ((ImageView)view).setImageBitmap(tint(BitmapFactory.decodeResource(mCon.getResources(), R.drawable.king), color));
    };
    @Override
    public void onResume() {
        super.onResume();
        Window win = getDialog().getWindow();
        win.setLayout(dpi(300,mCon), ViewGroup.LayoutParams.WRAP_CONTENT);
        win.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View v = inflater.inflate(R.layout.dialog_color, null);
        GridView gridview = (GridView) v.findViewById(R.id.color_diag);;
        gridview.setAdapter(new ColorAdapter(mCon));
        builder.setView(v);

        // Create the AlertDialog object and return it
        return builder.create();
    }

}
