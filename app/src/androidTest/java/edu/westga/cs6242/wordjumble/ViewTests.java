package edu.westga.cs6242.wordjumble;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import edu.westga.cs6242.wordjumble.view.GameActivity;
import edu.westga.cs6242.wordjumble.view.MainActivity;

/**
 * This class is for instrumentation testing of the game activity
 */
public class ViewTests extends ActivityInstrumentationTestCase2<MainActivity> {

    public ViewTests() {
        super(MainActivity.class);
    }

    public void testForExistenceOfViews() {
        MainActivity a = new MainActivity();
        assertNotNull(a);
        GameActivity b = new GameActivity();
        assertNotNull(b);
    }

    public void testUserAttemptingAFiveLetterWordGame() {
        Instrumentation instrumentation = getInstrumentation();
        Instrumentation.ActivityMonitor monitor
                = instrumentation.addMonitor(GameActivity.class.getName(), null, false);
        Activity currentActivity = getActivity();
        assertNotNull(currentActivity);
        assertEquals(true,
                currentActivity.findViewById(R.id.rbFiveLetterGame) instanceof RadioButton);
        assertEquals(true,
                currentActivity.findViewById(R.id.btnStartGame) instanceof Button);
        final RadioButton btnFive
                = (RadioButton) currentActivity.findViewById(R.id.rbFiveLetterGame);
        final Button btnStart = (Button) currentActivity.findViewById(R.id.btnStartGame);
        currentActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                btnFive.setChecked(true);
                btnStart.performClick();
            }
        });
        Activity gameActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        assertNotNull(gameActivity);
        final TextView tvSolvableString
                = (TextView) gameActivity.findViewById(R.id.tvScrambledWord);
        assertEquals(5, tvSolvableString.getText().length());
        instrumentation.removeMonitor(monitor);
        gameActivity.finish();
        currentActivity.finish();
    }

    public void testUserAttemptingASixLetterWordGame() {
        Instrumentation instrumentation = getInstrumentation();
        Instrumentation.ActivityMonitor monitor
                = instrumentation.addMonitor(GameActivity.class.getName(), null, false);
        Activity currentActivity = getActivity();
        assertNotNull(currentActivity);
        assertEquals(true,
                currentActivity.findViewById(R.id.rbSixLetterGame) instanceof RadioButton);
        assertEquals(true,
                currentActivity.findViewById(R.id.btnStartGame) instanceof Button);
        final RadioButton btnSix
                = (RadioButton) currentActivity.findViewById(R.id.rbSixLetterGame);
        final Button btnStart = (Button) currentActivity.findViewById(R.id.btnStartGame);
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                btnSix.setChecked(true);
            }
        });
        getInstrumentation().waitForIdleSync();
        currentActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                btnStart.performClick();
            }
        });
        Activity gameActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        assertNotNull(gameActivity);
        final TextView tvSolvableString
                = (TextView) gameActivity.findViewById(R.id.tvScrambledWord);
        assertEquals(6, tvSolvableString.getText().length());
        instrumentation.removeMonitor(monitor);
        gameActivity.finish();
        currentActivity.finish();
    }
}
