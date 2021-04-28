package com.patchnhack.chess4;

import android.graphics.Bitmap;
import android.graphics.Point;

import static com.patchnhack.chess4.utils.tint;

public abstract class Piece {
    int player;
    int color;
    Bitmap bitmap;
    Game game;

    Piece(int _player, Game _b, Bitmap _bitmap){
        player = _player;
        game = _b;
        bitmap = tint(_bitmap, ScreenActivity.col[player]);
    }
    public abstract void getMoves(int r, int c);

    public boolean sameTeam(int player){
        return ((player^this.player)&1)==0;
    }
    public boolean sameTeam(Point p){
        Piece piece= game.getPiece(p);
        return sameTeam(piece.player);
    }
}
