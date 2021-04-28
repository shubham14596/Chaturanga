package com.patchnhack.chess4;

import android.graphics.Bitmap;

public class Knight extends Piece {
    Knight(int _player, Game _b, Bitmap _bitmap) {
        super(_player, _b, _bitmap);
    }

    @Override
    public void getMoves(int r, int c) {
        int turn = ScreenActivity.current;
        int newr,newc;
        for(int j=-1; j<=1; j+=2){
            for(int k=-1; k<=1; k+=2){
                // one above and one down
                try {
                    Piece p = game.grid[r + j][c + k * 2];
                    if (p == null || !p.sameTeam(turn)) {
                        game.addIfValid(r, c, r+j, c+k *2);
                    }

                }
                catch (Exception ignored){}
                // two above and two down
                try {
                    Piece p = game.grid[r + j*2][c + k ];
                    if (p == null || !p.sameTeam(turn)) {
                        game.addIfValid(r, c, r+j*2, c+k);
                    }
                }
                catch (Exception ignored){}
            }
        }
    }


}
