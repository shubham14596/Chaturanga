package com.patchnhack.chess4;

import android.graphics.Bitmap;

public class Pawn extends Piece {
    static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}};
    Pawn(int _player, Game _b, Bitmap _bitmap) {
        super(_player, _b, _bitmap);
    }

    @Override
    public void getMoves(int r, int c) {
        int turn = ScreenActivity.current;

        // check capture
        int left, right, upx = dir[turn][0], upy = dir[turn][1];
        for(int j=-1; j<=1; j+=2) {
            try{
                left = r+(upx==0?j:upx);
                right = c+(upy==0?j:upy);
                if (!game.grid[left][right].sameTeam(turn)){
                    game.addIfValid(r, c, left, right);
                }
            } catch (Exception ignored){}

        }
        // move one up
        if(game.grid[r+upx][c+upy]==null){
            game.addIfValid(r, c, r+upx, c+upy);
        }
    }

}
