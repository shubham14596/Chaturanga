package com.patchnhack.chess4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton btn = (ImageButton) findViewById(R.id.button);

        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.start_btn);
        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                v.startAnimation(myAnim);
                Intent i = new Intent(getApplicationContext(), ScreenActivity.class);
                startActivity(i);
            }
        });
    }

    public void review(View view){
        Uri marketUri = Uri.parse("market://details?id=" + getPackageName());
        startActivity(new Intent(Intent.ACTION_VIEW, marketUri));
    }

    public void showHelp(View view){
        Intent i = new Intent(this, HelpActivity.class);
        startActivity(i);
    }

    public void onPublic(View v){
        AlertDialog soon = new AlertDialog.Builder(this)
                .setTitle("Coming Soon!!")
                .setMessage("")
                .show();
    }

    public void setColor(View view) {

        ColorDiag cd = new ColorDiag(this, view);
        cd.show(getSupportFragmentManager(),"colorDialog");
    }
}