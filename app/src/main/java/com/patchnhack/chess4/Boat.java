package com.patchnhack.chess4;

import android.graphics.Bitmap;

public class Boat extends Piece{
    Boat(int _player, Game _b, Bitmap _bitmap) {
        super(_player, _b, _bitmap);
    }

    @Override
    public void getMoves(int r, int c) {

        int turn = ScreenActivity.current;
        for(int i=-1; i<=1; i+=2){
            for(int j=-1; j<=1; j+=2){
                try {
                    Piece p = game.grid[r + i*2][c + j*2];
                    if (p == null || !p.sameTeam(turn)) {
                        game.addIfValid(r, c, r+i*2, c+j*2);
                    }
                }
                catch(Exception ignored){}
            }
        }
    }
}
