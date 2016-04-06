package edu.westga.cs6242.wordjumble.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        InputFilter[] filters = new InputFilter[1];
        filters[0] = new InputFilter.LengthFilter(this.wjgGame.getStrScrambledWord().length());
        etUserInput.setFilters(filters);
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

    public void click_DisplayHelp(View v) {
        Context context = getApplicationContext();
        String text = this.wjgGame.getStrCurrentWord();
        String disp = "";
        if (text.length() == 5) {
            disp = text.substring(0,2);
        }
        else {
            disp = text.substring(0, 3);
        }
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, disp, duration);
        toast.show();
    }

    /// Private Helper Methods

    private void initViewVariables()
    {
        this.etUserInput = (EditText) findViewById(R.id.etUserAttempt);
        this.tvScrambledWord = (TextView) findViewById(R.id.tvScrambledWord);
        this.tvHUD = (TextView) findViewById(R.id.tvHUD);
        this.tvHUD.setText("");
        this.tvHUD.setVisibility(View.INVISIBLE);
        this.tvHUD.setTextColor(Color.RED); // sets the error text red
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
