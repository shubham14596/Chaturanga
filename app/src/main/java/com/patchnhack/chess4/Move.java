package com.patchnhack.chess4;

public class Move {
    static int pr,pc;
    static boolean redturn = true;
    static int moveslist[][] = new int[8][8];
    static int kpos[][] = new int[4][2];
    static char[][] Chessboard;

    static void init(){
        Chessboard = new char[][]{
                {'B', 'P', ' ', ' ', 'm', 'h', 'g', 'j'},
                {'N', 'P', ' ', ' ', 's', 's', 's', 's'},
                {'R', 'P', ' ', ' ', ' ', ' ', ' ', ' '},
                {'K', 'P', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', 'S', 'M'},
                {' ', ' ', ' ', ' ', ' ', ' ', 'S', 'H'},
                {'p', 'p', 'p', 'p', ' ', ' ', 'S', 'G'},
                {'b', 'n', 'r', 'k', ' ', ' ', 'S', 'J'}};
        // coordinates of all kings
        kpos[0][0]=7; kpos[0][1]=3;
        kpos[1][0]=3; kpos[1][1]=0;
        kpos[2][0]=0; kpos[2][1]=4;
        kpos[3][0]=4; kpos[3][1]=7;
    }

    static byte addMove(int r, int c, int newr, int newc){

        byte res = 0;
        char piece=Chessboard[r][c];
        char old = Chessboard[newr][newc];
        Chessboard[r][c]=' ';
        Chessboard[newr][newc]= piece;
        if(kingsafe()) { moveslist[newr][newc]=1; res=1; }
        Chessboard[r][c]=piece;
        Chessboard[newr][newc]=old;
        return res;
    }

    public static boolean posibleMoves(int turn) {
        for (int i=0; i<8; i++) {
            for(int j=0; j<8; j++){
                switch(turn){
                    case 0:{
                        switch(Chessboard[i][j]) {
                            case 'p': if (movespawn1(i, j) != null) return true; break;
                            case 'b': if (movesboat(i, j) != null) return true; break;
                            case 'n': if (movesknight(i, j) != null) return true; break;
                            case 'r': if (movesrook(i, j) != null) return true; break;
                            case 'k': if (movesking(i, j) != null) return true; break;
                        }
                        break;
                    }
                    case 1:{
                        switch(Chessboard[i][j]) {
                            case 'P': if (movespawn2(i, j) != null) return true; break;
                            case 'B': if (movesboat(i, j) != null) return true; break;
                            case 'N': if (movesknight(i, j) != null) return true; break;
                            case 'R': if (movesrook(i, j) != null) return true; break;
                            case 'K': if (movesking(i, j) != null) return true; break;
                        }
                        break;
                    }
                    case 2:{
                        switch(Chessboard[i][j]) {
                            case 's': if (movespawn3(i, j) != null) return true; break;
                            case 'j': if (movesboat(i, j) != null) return true; break;
                            case 'g': if (movesknight(i, j) != null) return true; break;
                            case 'h': if (movesrook(i, j) != null) return true; break;
                            case 'm': if (movesking(i, j) != null) return true; break;
                        }
                        break;
                    }
                    case 3:{
                        switch(Chessboard[i][j]) {
                            case 'S': if (movespawn4(i, j) != null) return true; break;
                            case 'J': if (movesboat(i, j) != null) return true; break;
                            case 'G': if (movesknight(i, j) != null) return true; break;
                            case 'H': if (movesrook(i, j) != null) return true; break;
                            case 'M': if (movesking(i, j) != null) return true; break;
                        }
                        break;
                    }
                }
            }
        }
        return false;//x1,y1,x2,y2,captured piece
    }

    static int[][] movespawn1(int r,int c){
        moveslist = new int[8][8]; byte count=0;
        // capture
        for(int j=-1; j<=1; j+=2) {
            try{
            if (Character.isUpperCase(Chessboard[r-1][c+j])){
                count+=addMove(r, c, r-1, c+j);
            }
            } catch (Exception e){}

        }
        // move one up
        if(Chessboard[r-1][c]==' '){
            count+=addMove(r, c, r-1, c);
        }
        pr = r; pc = c;
        return count>0 ? moveslist : null;
    }
    static int[][] movespawn2(int r,int c){
        moveslist = new int[8][8]; byte count=0;
        // capture
        for(int j=-1; j<=1; j+=2) {
            try{
            if (Character.isLowerCase(Chessboard[r+j][c+1]) ){
                count+=addMove(r, c, r+j, c+1);
            }
            } catch (Exception e){}

        }
        // move one up
        if(Chessboard[r][c+1]==' '){
            count+=addMove(r, c, r, c+1);
        }
        pr = r; pc = c;
        return count>0 ? moveslist : null;
    }
    static int[][] movespawn3(int r,int c){
        moveslist = new int[8][8]; byte count=0;

        // capture
        for(int j=-1; j<=1; j+=2) {
            try{
            if (Character.isUpperCase(Chessboard[r+1][c+j])){
                count+=addMove(r, c, r+1, c+j);
            }
            } catch (Exception e){}

        }
        // move one up
        if(Chessboard[r+1][c]==' '){
            count+=addMove(r, c, r+1, c);
        }
        pr = r; pc = c;
        return count>0 ? moveslist : null;
    }

    static int[][] movespawn4(int r,int c){
        moveslist = new int[8][8]; byte count=0;
        char piece=Chessboard[r][c];
        // capture
        for(int j=-1; j<=1; j+=2) {
            try{
            if (Character.isLowerCase(Chessboard[r+j][c-1]) ){
                count+=addMove(r, c, r+j, c-1);
            }
            } catch (Exception e){}

        }
        // move one up
        if(Chessboard[r][c-1]==' '){
            count+=addMove(r, c, r, c-1);
        }
        pr = r; pc = c;
        return count>0 ? moveslist : null;
    }

    static int[][] movesrook(int r,int c){
        moveslist = new int[8][8]; byte count=0;
        int temp=1;
        for(int j=-1; j<=1; j+=2) {     // -1 for left and up ; +1 for right and down

            try{
                while( Chessboard[r][c+temp*j]==' '){
                    count+=addMove(r, c, r, c+temp*j);
                    temp++;
                }
                if( redturn?Character.isUpperCase(Chessboard[r][c+temp*j]):Character.isLowerCase(Chessboard[r][c+temp*j])){
                    count+=addMove(r, c, r, c+temp*j);
                }
            }
            catch(Exception e){}
            temp=1;
            try{
                while( Chessboard[r+temp*j][c]==' '){
                    count+=addMove(r, c, r+temp*j, c);
                    temp++;
                }
                if( redturn?Character.isUpperCase(Chessboard[r+temp*j][c]):Character.isLowerCase(Chessboard[r+temp*j][c])){
                    count+=addMove(r, c, r+temp*j, c);
                }
            }
            catch(Exception e){}
            temp=1;
        }
        pr = r; pc = c;
        return count>0 ? moveslist : null;
    }

    static int[][] movesknight(int r,int c){
        moveslist = new int[8][8]; byte count=0;

        int newr,newc;
        for(int j=-1; j<=1; j+=2){
            for(int k=-1; k<=1; k+=2){
                    // one above and one down
                  if((newr=r+j) >=0 && newr<8 && (newc=c+k*2)>=0 && newc<8){ // up-left and up-right // down-left and down-right
                    if( (Chessboard[newr][newc]==' ') || (redturn ? Character.isUpperCase(Chessboard[newr][newc]):Character.isLowerCase(Chessboard[newr][newc])) ){
                        count+=addMove(r, c, newr, newc);
                    }
                }
                    // two above and two down
                if((newr=r+j*2) >=0 && newr<8 && (newc=c+k)>=0 && newc<8){
                    if( (Chessboard[newr][newc]==' ') || (redturn?Character.isUpperCase(Chessboard[newr][newc]):Character.isLowerCase(Chessboard[newr][newc]))){
                        count+=addMove(r, c, newr, newc);
                    }
                }
            }
        }
        pr = r; pc = c;
        return count>0 ? moveslist : null;
    }

    static int[][] movesboat(int r,int c){
        moveslist = new int[8][8]; byte count=0;

        for(int i=-1; i<=1; i+=2){
            for(int j=-1; j<=1; j+=2){
                try {
                    if (Chessboard[r + i*2][c + j*2] == ' ' || (redturn ? Character.isUpperCase(Chessboard[r + i*2][c + j*2]) : Character.isLowerCase(Chessboard[r + i*2][c + j*2])) ) {
                        count+=addMove(r, c, r+i*2, c+j*2);
                    }
                }
                catch(Exception e){}
            }
        }
        pr = r; pc = c;
        return count>0 ? moveslist : null;
    }

    static int[][] movesking(int r,int c){
        moveslist = new int[8][8]; byte count = 0;

        int newr, newc;
        for(int i=0; i<9; i++){
            if(i!=4){
                try {
                    newr = r-1+i/3; newc = c-1+i%3;
                    if(Chessboard[newr][newc] == ' ' ||  (redturn?Character.isUpperCase(Chessboard[newr][newc]):Character.isLowerCase(Chessboard[newr][newc]))) {
                        int turn = ScreenActivity.current;
                        kpos[turn][0]=newr; kpos[turn][1]=newc;
                        count+=addMove(r, c, newr, newc);
                        kpos[turn][0]=r; kpos[turn][1]=c;
                    }
                }
                catch(Exception e){}
            }
        }
        pr = r; pc = c;
        return count>0 ? moveslist : null;
    }

    static boolean makeMove(int r,int c){
    if(moveslist[r][c]==1) {
        char piece = Chessboard[pr][pc];
        int turn = ScreenActivity.current;
        if(piece == 'k' || piece == 'K' || piece == 'm' || piece == 'M'){
            kpos[turn][0] = pr; kpos[turn][1] = pc;
        }
        char newPos = Chessboard[r][c];
        if(newPos == 'k' || newPos == 'K' || newPos == 'm' || newPos == 'M')
            if (kpos[(turn+1)%4][0] != -1) kpos[(turn+1)%4][0]=-1;
            else if(kpos[(turn+3)%4][0]!=-1) kpos[(turn+3)%4][0]=-1;
        Chessboard[r][c] = piece;
        Chessboard[pr][pc]=' ';
        redturn = !redturn;
        moveslist=new int[8][8];
        return true;
    }
    return false;
    }

    static boolean kingsafe(){
        int turn = ScreenActivity.current;

        // boat
        for(int i=-1; i<=1; i+=2) {
            for (int j = -1; j <= 1; j += 2) {
                try {
                    char pos = Chessboard[kpos[turn][0] + i*2][kpos[turn][1] + j*2];
                    if (redturn? ('B' == pos || 'J' == pos ): ('b' == pos || 'j' == pos)) return false;
                } catch (Exception e) {}
            }
        }

        // rook
        int temp = 1;
        for(int i=-1; i<=1; i+=2){
            try{
                while(' ' == Chessboard[kpos[turn][0]][kpos[turn][1]+temp*i]) temp++;
                char pos = Chessboard[kpos[turn][0]][kpos[turn][1] + temp*i];
                if(redturn? ('H' == pos || 'R' == pos): ('h' == pos || 'r' == pos) ) return false;
            }
            catch (Exception e){}
            temp = 1;
            try{
                while(' ' == Chessboard[kpos[turn][0]+temp*i][kpos[turn][1]]) temp++;
                char pos = Chessboard[kpos[turn][0]+temp*i][kpos[turn][1]];
                if(redturn? ('H' == pos || 'R' == pos): ('h' == pos || 'r' == pos) ) return false;
            }
            catch (Exception e){}
        }

        // knight
        for(int i=-1; i<=1; i+=2) {
            for (int j = -1; j <= 1; j += 2) {
                try {
                    char pos = Chessboard[kpos[turn][0] + i][kpos[turn][1] + j*2];
                    if (redturn? ('N' == pos || 'G' == pos ): ('n' == pos || 'g' == pos)) return false;

                } catch (Exception e) {}
                try {
                    char pos = Chessboard[kpos[turn][0] + i*2][kpos[turn][1] + j];
                    if (redturn? ('N' == pos || 'G' == pos ): ('n' == pos || 'g' == pos)) return false;

                } catch (Exception e) {}
            }
        }

        //pawn
        try{
            if(redturn? ('P' == Chessboard[kpos[turn][0]-1][kpos[turn][1]-1]): 'p' == Chessboard[kpos[turn][0]+1][kpos[turn][1]+1])
                return false;
        }
        catch(Exception e){}
        try{
            if(redturn? ('P' == Chessboard[kpos[turn][0]+1][kpos[turn][1]-1]): 'p' == Chessboard[kpos[turn][0]+1][kpos[turn][1]-1])
                return false;
        }
        catch(Exception e){}
        try{
            if(redturn? ('S' == Chessboard[kpos[turn][0]-1][kpos[turn][1]+1]): 's' == Chessboard[kpos[turn][0]-1][kpos[turn][1]-1])
                return false;
        }
        catch(Exception e){}
        try{
            if(redturn? ('S' == Chessboard[kpos[turn][0]+1][kpos[turn][1]+1]): 's' == Chessboard[kpos[turn][0]-1][kpos[turn][1]+1])
                return false;
        }
        catch(Exception e){}

        //king
        for (int i=-1; i<=1; i++) {
            for (int j=-1; j<=1; j++) {
                if (i!=0 || j!=0) {
                    try {
                        char pos = Chessboard[kpos[turn][0]+i][kpos[turn][1]+j];
                        if (redturn ? 'K' == pos || 'M' == pos : 'k' == pos || 'm' == pos) {
                            return false;
                        }
                    } catch (Exception e) {}
                }
            }
        }
    return true;
    }

}
