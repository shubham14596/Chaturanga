package com.patchnhack.chess4;

import android.graphics.Bitmap;

public class King extends Piece {
    King(int _player, Game _b, Bitmap _bitmap) {
        super(_player, _b, _bitmap);
    }

    @Override
    public void getMoves(int r, int c) {
        int turn = ScreenActivity.current;
        int newr, newc;
        for(int i=0; i<9; i++){
            if(i!=4){
                try {
                    newr = r-1+i/3; newc = c-1+i%3;
                    Piece p = game.grid[newr][newc];
                    if (p == null || !p.sameTeam(turn)) {
                        game.kPos[turn][0]=newr; game.kPos[turn][1]=newc;
                        game.addIfValid(r, c, newr, newc);
                        game.kPos[turn][0]=r; game.kPos[turn][1]=c;
                    }
                }
                catch(Exception ignored){}
            }
        }
    }

}
