package com.patchnhack.chess4;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.List;

public class BoardDrawable extends Drawable {
    Context c;
//    Bitmap  back,boat,rook,knight,king,pawn,rboat,rrook,rknight,rking,rpawn,gboat,grook,gknight,gking,gpawn,yboat,yrook,yknight,yking,ypawn,bboat,brook,bknight,bking,bpawn;
    int wide;
    int step;
    int back;

    Piece last;
    List<Point> moves;
    Piece[][] grid;

    BoardDrawable(Context cxt, int size, Piece[][] _grid, int _back){
        c= cxt;
        Resources r = c.getResources();
        wide = size;
        step = wide/8;
        grid = _grid;
        back = _back;
    }

    void setMoves(List<Point> _moves){
        moves = _moves;
    }

    void setState(List<Point> _moves, Piece[][] _grid){
        moves = moves;
        grid = _grid;
    }

    @SuppressLint("Range")
    @Override
    public void draw(Canvas canvas) {
        // draw board ***not working here

//        BitmapDrawable b = new BitmapDrawable(c.getResources(), BitmapFactory.decodeResource(c.getResources(),R.drawable.dust));

        // background
        Paint p = new Paint();

        p.setColor(back);
        canvas.drawPaint(p);   //draw background

        p.setStyle(Paint.Style.STROKE);

        p.setColor(Color.BLACK);
        p.setStrokeWidth(4);

        float[] points =new float[72];   // horizon. and vertical lines coordinates

        // good work ### single loop for horizontal and vertical
        points[2]=points[39]=step*8;
        for(int i=1; i<=8;i++){
            points[i*4]=points[i*4+33]=i*step;
            points[i*4+2]=points[i*4+35]=i*step;
            points[i*4+3]=points[i*4+34]=step*8;
        }

        canvas.drawLines(points,p);     // draw board lines


        if(Build.VERSION.SDK_INT>=21) {
//            p.setColor(0xFFdcaa59);
            p.setStyle(Paint.Style.FILL_AND_STROKE);
            p.setColor(0xFFD7B14A);

//            p.setStrokeWidth(10);
            int left = 0, top = wide - step * 2, right = step * 4, bottom = wide;
//            canvas.drawRect(left,top,right,bottom,p);
            roundRect(canvas, left, top, right, bottom, new float[]{0,0, 15,15, 0,0, 0,0}, p);
            left = 0; top = 0; right = step*2; bottom = step*4;
//            p.setColor(Color.GREEN);
            roundRect(canvas, left, top, right, bottom, new float[]{0,0, 0,0, 15,15, 0,0}, p);
            left = step*4; top = 0; right = wide; bottom = step*2;
//            p.setColor(Color.YELLOW);
            roundRect(canvas, left, top, right, bottom, new float[]{0,0, 0,0, 0,0, 15,15}, p);
            left = step*6; top = step*4; right = wide; bottom = wide;
//            p.setColor(Color.BLACK);
            roundRect(canvas, left, top, right, bottom, new float[]{15,15, 0,0, 0,0, 0,0}, p);
        }

        // pieces

        Bitmap piece;
        for(int i=0; i<8; i++) {
            for (int j = 0; j < 8; j++) {
                if(grid[i][j]!=null){
                    piece =grid[i][j].bitmap;
                    canvas.drawBitmap(piece,j*step,i*step,null);    // draw pieces
                }
            }
        }

        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(6);
        // possible moves
        p.setARGB(255,255,0,0);
        Bitmap bmp = Bitmap.createBitmap(wide, wide, Bitmap.Config.ARGB_8888);
        Canvas genmove = new Canvas(bmp);
        if(moves!=null) {
            for(Point m: moves){
                genmove.drawCircle((step/2)+m.y*step,(step/2)+m.x*step,20, p);
            }
//            for(int i =0; i<8; i++){
//                for(int j=0; j<8; j++)
//                    if(moves[i][j]==1)
//                        genmove.drawCircle((step/2)+j*step,(step/2)+i*step,20, p);
//            }
            canvas.drawBitmap(bmp, 0, 0, null);  //draw possible moves
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void roundRect(Canvas canvas, int left, int top, int right, int bottom, float[] corners, Paint p) {
        Path pth = new Path();
        pth.addRoundRect(left,top,right,bottom, corners, Path.Direction.CW);
        canvas.drawPath(pth,p);
    }

    @Override
    public void setAlpha(int alpha) {    }
    @Override
    public void setColorFilter(ColorFilter colorFilter) { }
    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }


}
