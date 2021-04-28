package com.patchnhack.chess4;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class HelpAdapter extends FragmentStateAdapter {

    BitmapDrawable imgs[] = new BitmapDrawable[5];
    public HelpAdapter(@NonNull FragmentActivity ctx) {
        super(ctx);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = new HelpFragment();
        Bundle args = new Bundle();
        args.putInt(HelpFragment.ARG_OBJECT, position);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public int getItemCount() {
        return 6;
    }
}
