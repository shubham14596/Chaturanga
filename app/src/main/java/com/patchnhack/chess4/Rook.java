package com.patchnhack.chess4;

import android.graphics.Bitmap;

public class Rook extends Piece {
    Rook(int _player, Game _b, Bitmap _bitmap) {
        super(_player, _b, _bitmap);
    }

    public void setMoves(int r, int c){

    }
    public void getMoves(int r, int c) {
        int turn = ScreenActivity.current;
        int temp=1;
        for(int j=-1; j<=1; j+=2) {     // -1 for left and up ; +1 for right and down

            try{
                while( game.grid[r][c+temp*j]==null){
                    game.addIfValid(r, c, r, c+temp*j);
                    temp++;
                }
                Piece p = game.grid[r][c+temp*j];
                if(!p.sameTeam(turn)){
                    game.addIfValid(r, c, r, c+temp*j);
                }
            }
            catch(Exception ignored){}
            temp=1;
            try{
                while( game.grid[r+temp*j][c]==null){
                    game.addIfValid(r, c, r+temp*j, c);
                    temp++;
                }
                Piece p = game.grid[r+temp*j][c];
                if( !p.sameTeam(turn) ){
                    game.addIfValid(r, c, r+temp*j, c);
                }
            }
            catch(Exception ignored){}
            temp=1;
        }
    }


}
