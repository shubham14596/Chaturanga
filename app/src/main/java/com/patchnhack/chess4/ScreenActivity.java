package com.patchnhack.chess4;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

// game architecture *** touch , process , view
public class ScreenActivity extends AppCompatActivity {
//    TextView[] names = new TextView[4];
    NamesGroup pnames;
    TextView turnTxt;
    ImageView boardView;
    BoardDrawable boardDrawable;
    Game game;
    static int current;
    static int[] col;
    boolean ai = false;
    Point screen = new Point();
    int unit;
    int size;

    ViewModel mViewModel;
    public void initBoard() {
        current = 0;
        pnames.setTurn(0);
        turnTxt.setText("Player 1 turn");
        Resources r = getResources();
        int pieceW = size/8;
        Bitmap boat, rook, knight, king, pawn, back;
        boat = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(r, R.drawable.boat), pieceW, pieceW, true);
        rook = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(r, R.drawable.rook), pieceW, pieceW, true);
        knight = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(r, R.drawable.knight), pieceW, pieceW, true);
        king = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(r, R.drawable.king), pieceW, pieceW, true);
        pawn = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(r, R.drawable.pawn), pieceW, pieceW, true);
//        back = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(r, R.drawable.dust_1), size, size, true);

        // get colors *** change to get color from prefs
        col = new int[]{Color.WHITE, Color.GREEN, Color.YELLOW, Color.BLACK};

        game = new Game();
//        game.grid = new Piece[][]{
//                {null,null,null,null,null,null,null,null},
//                {null,null,null,null,null,null,null,null},
//                {null,null,null,null,null,null,null,null},
//                {null,null,null,null,null,null,null,null},
//                {null,null,null,null,null,null,null,null},
//                {null,null,null,new King(0, game, king),null,null,null,null},
//                {null,null,null,null,null,null,null,null},
//                {null,null,null,null,null,null,null,null}
//        };
        game.grid = new Piece[][]{
                {new Boat(1, game,boat), new Pawn(1, game,pawn), null, null, new Knight(2, game,knight), new Rook(2, game,rook), new King(2, game,king), new Boat(2, game,boat)},
                {new King(1, game,king), new Pawn(1, game,pawn), null, null, new Pawn(2, game,pawn), new Pawn(2, game,pawn), new Pawn(2, game,pawn), new Pawn(2, game,pawn)},
                {new Rook(1, game,rook), new Pawn(1, game,pawn), null, null, null, null, null, null},
                {new Knight(1, game,knight), new Pawn(1, game,pawn), null, null, null, null, null, null},
                {null, null, null, null, null, null, new Pawn(3, game,pawn), new Knight(3, game,knight)},
                {null, null, null, null, null, null, new Pawn(3, game,pawn), new Rook(3, game,rook)},
                {new Pawn(0, game,pawn), new Pawn(0, game,pawn), new Pawn(0, game,pawn), new Pawn(0, game,pawn), null, null, new Pawn(3, game,pawn), new King(3, game,king)},
                {new Boat(0, game,boat), new King(0, game,king), new Rook(0, game,rook), new Knight(0, game,knight), null, null, new Pawn(3, game,pawn), new Boat(3, game,boat)}};

        game.kPos = new int[][]{{7,1},{1,0},{0,6},{6,7}};
        game.moves = new ArrayList<>();
        boardDrawable = new BoardDrawable(this, size, game.grid, Color.parseColor("#FDE5B4"));
        boardView.setImageDrawable(boardDrawable);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen);

//        mViewModel = new ViewModelProvider(this).get(StateViewModel.class);
        getWindowManager().getDefaultDisplay().getSize(screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        size = Math.min(screen.x, screen.y);

        current = 0;
        boardView = (ImageView) findViewById(R.id.drawarea);

        // get names from prefs ***to be implemented
        ArrayList<TextView> nameList = new ArrayList<>();
        nameList.add((TextView) findViewById(R.id.p1));
        nameList.add((TextView) findViewById(R.id.p2));
        nameList.add((TextView) findViewById(R.id.p3));
        nameList.add((TextView) findViewById(R.id.p4));
        turnTxt = (TextView) findViewById(R.id.turn);
//        names[1] = (TextView) findViewById(R.id.p1);
        pnames = new NamesGroup(nameList);
        pnames.setTurn(current);
//        names[0].setBackgroundColor(Color.parseColor("#ece0d1"));
//        names[0].setTextColor(Color.BLACK);

        initBoard();
        unit = size / 8;

        if(ai) {
            boardView.setOnTouchListener(new View.OnTouchListener() {

                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        int x = (int) event.getX()/ unit;     int y = (int) event.getY()/ unit;
                        return false;
                    }
            });
        }
        else{
            boardView.setOnTouchListener(new fourplayer(this));
        }

    }

    public void onSkip(View view) {
        current=(current+1)%4;
        pnames.setTurn(current);
        String txt = "Player "+(current+1)+" turn";
        turnTxt.setText(txt);
    }


    class fourplayer implements View.OnTouchListener{
        Context con;
        fourplayer(Context _con){
            con = _con;
        }
        private void nextTurn(){
            current = (current +1)%4;
            pnames.setTurn(current);
            String txt = "Player "+(current+1)+" turn";
            turnTxt.setText(txt);
            if(game.kPos[current][0]==-1) {
//                    || !Move.posibleMoves(current))
                nextTurn();
            }
        }

        void gameOver(){
            AlertDialog over = new AlertDialog.Builder(con)
                    .setTitle("Game Over!!")
                    .setMessage("Play Again?")

                    // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton("YES", (dialog, which) -> {
                        initBoard();
                        boardView.invalidate();
                    })

                    .setNegativeButton("NO", (dialog, which) -> {
                        finish();
                    })
                    .setIcon(R.drawable.ic_baseline_report_48)
                    .show();
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {     // get piece and turn info
            // show moves or movePiece

            int c = (int) event.getX()/ unit;     int r = (int) event.getY()/ unit;   // get touched square x,y
//            if(event.getAction()==MotionEvent.ACTION_DOWN) {
            Piece p = game.grid[r][c];

            // if touched own piece, getpossiblemoves else chk movepossible
            if(p!=null && p.player == ScreenActivity.current){
                game.setMoves(p,r,c);
                boardDrawable.moves = game.moves;
                boardView.invalidate();
            }
            else if(!game.moves.isEmpty()) {
                if (game.makeMove(r, c)) {

                    boardDrawable.moves=game.moves;
                    boardView.invalidate();
                    if((game.kPos[0][0]==-1 && game.kPos[2][0]==-1) || (game.kPos[1][0]==-1 && game.kPos[3][0]==-1))
                        gameOver();
                    //      if(game.grid[r][c] instanceOf Pawn && y==0)
                    //        choosenewpiece();
                    nextTurn();
                }
            }

            return false;
        }
    }

}