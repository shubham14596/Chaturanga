package com.patchnhack.chess4;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

//  -,- | -,+
//  ____|____
//      |
//  +,- | +,+

// set kpos whenever any king change position ***need to improve
public class Game {
    Point last= new Point();
    Piece[][] grid;
    List<Point> moves = new ArrayList<>();
    int[][] kPos;

    public Piece getPiece(Point p){
        return grid[p.x][p.y];
    }

    public void setMoves(Piece p, int r, int c){
        moves = new ArrayList<>();
        p.getMoves(r,c);

        System.out.println("NO OF MOVES:"+moves.size());
        last.x=r;last.y=c;
    }
    public void movePiece(Piece piece, int x1, int y1, int x2, int y2){

    }

    public boolean makeMove(int r,int c){
        boolean valid = false;
        for(Point m: moves)
            if(m.x==r && m.y==c){ valid = true; break; }
        if(valid) {
            moves = new ArrayList<>();
            Piece piece = grid[last.x][last.y];
            int turn = ScreenActivity.current;
            if(piece instanceof King){
                kPos[turn][0] = last.x; kPos[turn][1] = last.y;
            }
            Piece newPiece = grid[r][c];
            if(newPiece instanceof King)
                if (kPos[newPiece.player][0] != -1) kPos[newPiece.player][0]=-1;
            grid[r][c] = piece;
            grid[last.x][last.y] = null;
            return true;
        }
        return false;
    }

    public void addIfValid(int r, int c, int newR, int newC){
        Piece piece=grid[r][c];
        Piece old = grid[newR][newC];
        grid[r][c]=null;
        grid[newR][newC]= piece;
        if(isValid()) moves.add(new Point(newR, newC));
        grid[r][c]=piece;
        grid[newR][newC]=old;
    }

    private boolean isValid(){
        int turn = ScreenActivity.current;
        // boat
        for(int i=-1; i<=1; i+=2) {
            for (int j = -1; j <= 1; j += 2) {
                try {
                    Piece p = grid[kPos[turn][0] + i*2][kPos[turn][1] + j*2];
                    if (p instanceof Boat && !p.sameTeam(turn)) return false;
                } catch (Exception ignored) {}
            }
        }
        System.out.println("::Checked BOAT::");
        // rook
        int temp ;
        for(int i=-1; i<=1; i+=2){
            temp=1;
            try{
                // left right
                while(null == grid[kPos[turn][0]][kPos[turn][1]+temp*i]) temp++;
                Piece p = grid[kPos[turn][0]][kPos[turn][1] + temp*i];
                if (p instanceof Rook && !p.sameTeam(turn)) return false;
            }
            catch (Exception ignored){}
            temp = 1;
            try{
                // up down
                while(null == grid[kPos[turn][0]+temp*i][kPos[turn][1]]) temp++;
                Piece p = grid[kPos[turn][0]+temp*i][kPos[turn][1]];
                if (p instanceof Rook && !p.sameTeam(turn)) return false;
            }
            catch (Exception ignored){}
        }
        System.out.println("::Checked ROOK::");

        // knight
        for(int i=-1; i<=1; i+=2) {
            for (int j = -1; j <= 1; j += 2) {
                try {
                    Piece p = grid[kPos[turn][0] + i][kPos[turn][1] + j*2];
                    if (p instanceof Knight && !p.sameTeam(turn)) return false;

                } catch (Exception ignored) {}
                try {
                    Piece p = grid[kPos[turn][0] + i*2][kPos[turn][1] + j];
                    if (p instanceof Knight && !p.sameTeam(turn)) return false;

                } catch (Exception ignored) {}
            }
        }
        System.out.println("::Checked KNIGHT::");

        //pawn
        try {
            Piece p1 = grid[kPos[turn][0] - 1][kPos[turn][1] - 1];
            if (p1 instanceof Pawn) {
                if (even(turn) ? p1.player == 1 : p1.player == 2)
                    return false;
            }
        }
        catch(Exception ignored){}
        try {
            Piece p2 = grid[kPos[turn][0]+1][kPos[turn][1]-1];
            if (p2 instanceof Pawn) {
                if (even(turn) ? p2.player == 3 : p2.player == 0)
                    return false;
            }
        }
        catch(Exception ignored){}
        try {
            Piece p3 = grid[kPos[turn][0]-1][kPos[turn][1]+1];
            if (p3 instanceof Pawn) {
                if (even(turn) ? p3.player == 1 : p3.player == 2)
                    return false;
            }
        }
        catch(Exception ignored){}
        try {
            Piece p4 = grid[kPos[turn][0]+1][kPos[turn][1]+1];
            if (p4 instanceof Pawn) {
                if (even(turn) ? p4.player == 3 : p4.player == 0)
                    return false;
            }
        }
        catch(Exception ignored){}

        //king
        for (int i=-1; i<=1; i++) {
            for (int j=-1; j<=1; j++) {
                if (i!=0 || j!=0) {
                    try {
                        Piece p = grid[kPos[turn][0]+i][kPos[turn][1]+j];
                        if (p instanceof King && !p.sameTeam(turn)) return false;
                    } catch (Exception ignored) {}
                }
            }
        }
        return true;
    }

    private boolean even(int player){ return (player&1) == 0;}
}
