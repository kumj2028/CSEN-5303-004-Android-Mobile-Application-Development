package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    final int X_TURN = 1;
    final int O_TURN = 2;
    final int X_WIN = 3;
    final int O_WIN = 4;
    final int X_TIE = 5;
    final int O_TIE = 6;


    int board[][];
    int state;
    int xWins;
    int oWins;
    int ties;
    SharedPreferences sharedPrefs;
    TextView xWinsView, oWinsView, tiesView, stateView;
    Button buttons[][], resetScores, newGame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        associateViews();
        loadSharedPrefs();
        setViewText();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveSharedPrefs();
    }

    private void associateViews() {
        xWinsView = findViewById(R.id.x_wins_view);
        oWinsView = findViewById(R.id.o_wins_view);
        tiesView = findViewById(R.id.ties_view);
        stateView = findViewById(R.id.state_view);
        buttons = new Button[3][3];
        int intID;
        String strID;
        for (int i = 0; i < 3; i+=1) {
            for (int j = 0; j < 3; j+=1) {
                intID = i * 3 + j;
                strID = "button" + Integer.toString(intID);
                buttons[i][j] = findViewById(getResources().getIdentifier(strID, "id", getPackageName()));
                buttons[i][j].setOnClickListener(this);
            }
        }
        resetScores = findViewById(R.id.reset_scores);
        resetScores.setOnClickListener(this);
        newGame = findViewById(R.id.new_game);
        newGame.setOnClickListener(this);

    }

    private void loadSharedPrefs() {
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        xWins = sharedPrefs.getInt("xWins", 0);
        oWins = sharedPrefs.getInt("oWins", 0);
        ties = sharedPrefs.getInt("ties", 0);
        state = sharedPrefs.getInt("state", X_TURN);
        board = new int[3][3];
        for (int i = 0; i < 3; i+=1) {
            for (int j = 0; j < 3; j+=1) {
                String key = "board" + Integer.toString(i) + Integer.toString(j);
                board[i][j] = sharedPrefs.getInt(key, 0);
            }
        }
    }

    private void saveSharedPrefs() {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putInt("xWins", xWins);
        editor.putInt("oWins", oWins);
        editor.putInt("ties", ties);
        editor.putInt("state", state);
        for (int i = 0; i < 3; i+=1) {
            for (int j = 0; j < 3; j+=1) {
                String key = "board" + Integer.toString(i) + Integer.toString(j);
                editor.putInt(key, board[i][j]);
            }
        }
        editor.apply();
    }

    private void setViewText() {
        xWinsView.setText("X's wins: " + Integer.toString(xWins));
        oWinsView.setText("O's wins: " + Integer.toString(oWins));
        tiesView.setText("Ties: " + Integer.toString(ties));
        if (state == X_TURN) {
            stateView.setText("X's move");
        } else if (state == O_TURN) {
            stateView.setText("O's move");
        } else if (state == X_WIN) {
            stateView.setText("X wins");
        } else if (state == O_WIN) {
            stateView.setText("O wins");
        } else {
            stateView.setText("Tie");
        }
        for (int i = 0; i < 3; i+=1) {
            for (int j = 0; j < 3; j+=1) {
                if (board[i][j] == 0) {
                    buttons[i][j].setText("");
                } else if (board[i][j] == 1) {
                    buttons[i][j].setText("X");
                } else {
                    buttons[i][j].setText("O");
                }
            }
        }
    }

    boolean winCheck(int turn) {
        if (horizontalWin(turn) || verticalWin(turn) || diagonalWin(turn)) {
            if (turn == 1) {
                xWins += 1;
                state = X_WIN;
            } else {
                oWins += 1;
                state = O_WIN;
            }
            return true;
        }
        if (boardFilled()) {
            ties += 1;
            if (turn == X_TURN) {
                state = X_TIE;
            } else {
                state = O_TIE;
            }
            return true;
        }
        return false;
    }

    boolean horizontalWin(int turn) {
        boolean possible_win;
        for (int row[] : board) {
            possible_win = true;
            for (int cell : row) {
                if (cell != turn) {
                    possible_win = false;
                    break;
                }
            }
            if (possible_win) {
                return true;
            }
        }
        return false;
    }
    boolean verticalWin(int turn) {
        boolean possible_win;
        for (int j = 0; j < board[0].length; j+=1) {
            possible_win = true;
            for (int i = 0; i < board.length; i+=1) {
                if (board[i][j] != turn) {
                    possible_win = false;
                    break;
                }
            }
            if (possible_win) {
                return true;
            }
        }
        return false;
    }

    boolean diagonalWin(int turn) {
        boolean possible_win = true;
        // Diagonal 1 check
        for (int i = 0; i < board.length; i+=1) {
            if (board[i][i] != turn) {
                possible_win = false;
                break;
            }
        }
        if (possible_win) {
            return true;
        }
        // Diagonal 2 check
        possible_win = true;
        for (int i = 0; i < board.length; i+=1) {
            if (board[i][board.length-i-1] != turn) {
                possible_win = false;
                break;
            }
        }
        return possible_win;
    }

    private boolean boardFilled() {
        for (int row[] : board) {
            for (int cell : row) {
                if (cell == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        int vID = v.getId();
        if (vID == R.id.reset_scores) {
            xWins = 0;
            oWins = 0;
            ties = 0;
            setViewText();
        } else if (vID == R.id.new_game) {
            board = new int[][] {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
            if (state == X_TURN || state == X_WIN || state == X_TIE) {
                state = O_TURN;
            } else {
                state = X_TURN;
            }
            setViewText();
        } else {
            if (state == X_WIN || state == O_WIN || state == X_TIE || state == O_TIE) {
                Toast.makeText(this, "game has ended, press 'NEW GAME' to start a new game", Toast.LENGTH_LONG).show();
                return;
            }
            for (int i = 0; i < 3; i+=1) {
                for (int j = 0; j < 3; j+=1) {
                    if (buttons[i][j].getId() == v.getId()) {
                        if (board[i][j] == 0) {
                            board[i][j] = state;
                            boolean win = winCheck(state);
                            if (!win) {
                                if (state == X_TURN) {
                                    state = O_TURN;
                                } else {
                                    state = X_TURN;
                                }
                            }
                            setViewText();
                        } else {
                            Toast.makeText(this, "this square has already been played", Toast.LENGTH_LONG).show();
                        }
                        return;
                    }
                }
            }
        }
    }
}