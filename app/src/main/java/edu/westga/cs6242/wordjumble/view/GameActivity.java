package edu.westga.cs6242.wordjumble.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import edu.westga.cs6242.wordjumble.R;
import edu.westga.cs6242.wordjumble.model.WordJumbleGame;
import edu.westga.cs6242.wordjumble.model.util.WJUtilities;

public class GameActivity extends AppCompatActivity {

    private WordJumbleGame wjgGame;
    private EditText       etUserInput;
    private TextView       tvScrambledWord;
    private TextView       tvHUD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        // this.wjgGame = new WordJumbleGame(); // creates a new WordJumbleGame Instance
        this.wjgGame = getIntent().getParcelableExtra(WJUtilities.NEW_GAME);
        this.initViewVariables();
        this.tvScrambledWord.setText(this.wjgGame.getStrScrambledWord());
    }

    public void click_AttemptToSubmitAnswer(View v)
    {
        final View gameView = v;
        this.hideHUDMessage();
        String strUserAttempt = this.etUserInput.getText().toString();
        this.wjgGame.updateUserAttempt(strUserAttempt);
        if (this.wjgGame.gameIsOver())
        {
            AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(this);
            dlgBuilder.setMessage("You won with game the answer of:\n" +
                    this.wjgGame.getStrUserAttempt());
            dlgBuilder.setTitle("You won the game!");
            dlgBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(gameView.getContext(), MainActivity.class);
                    startActivity(intent);
                }
            });
            dlgBuilder.setCancelable(false);
            dlgBuilder.create().show();
        }
        else
        {
            this.displayHUDMessage("Incorrect answer! Please try again.");
        }
    }

    public void click_ClearUserInput(View v)
    {
        this.hideHUDMessage();
        this.etUserInput.setText("");
    }


    /// Private Helper Methods

    private void initViewVariables()
    {
        this.etUserInput = (EditText) findViewById(R.id.etUserAttempt);
        this.tvScrambledWord = (TextView) findViewById(R.id.tvScrambledWord);
        this.tvHUD = (TextView) findViewById(R.id.tvHUD);
        this.tvHUD.setText("");
        this.tvHUD.setVisibility(View.INVISIBLE);
    }

    private void displayHUDMessage(String s)
    {
        this.tvHUD.setText(s);
        this.tvHUD.setVisibility(View.VISIBLE);
    }

    private void hideHUDMessage()
    {
        this.tvHUD.setText("");
        this.tvHUD.setVisibility(View.INVISIBLE);
    }
}
