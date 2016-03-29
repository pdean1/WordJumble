package edu.westga.cs6242.wordjumble.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;

import edu.westga.cs6242.wordjumble.R;
import edu.westga.cs6242.wordjumble.model.WordJumbleGame;
import edu.westga.cs6242.wordjumble.model.enums.EWordLength;
import edu.westga.cs6242.wordjumble.model.util.WJUtilities;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Event Listener for when the user clicks the start game application.
     * @param v This view
     */
    public void click_StartNewWordJumbleGame(View v)
    {
        EWordLength length = EWordLength.FIVE_LETTER_WORD;
        RadioButton rbSix  = (RadioButton) findViewById(R.id.rbSixLetterGame);
        if (rbSix.isChecked())
            length = EWordLength.SIX_LETTER_WORD;
        WordJumbleGame game = new WordJumbleGame(length);
        Bundle bundle = new Bundle();
        bundle.putParcelable(WJUtilities.NEW_GAME, game);
        Intent intent = new Intent(v.getContext(), GameActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
 }
