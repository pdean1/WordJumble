package edu.westga.cs6242.wordjumble.model;

/**
 * Created by Patrick on 3/27/2016.
 * @version 20162703
 * This class represents a Word Jumble Game
 */
public class WordJumbleGame extends edu.westga.cs6242.wordjumble.model.Model {
    private String strCurrentWord;
    private String strScrambledWord;
    private String strUserAttempt;

    /**
     * Creates a new WordJumbleGame Object
     */
    public WordJumbleGame()
    {
        this.strCurrentWord = this.getRandomWord();
        this.strScrambledWord = this.scrambleWord(this.strCurrentWord);
        this.strUserAttempt = "";
    }

    /**
     * Updates this user's attempt to solve the word jumble
     * @param _strUserAttempt The updated user's attempt
     */
    public void updateUserAttempt(String _strUserAttempt)
    {
        this.strUserAttempt = _strUserAttempt;
    }

    /**
     * Checks to see if the user has won the game.
     * @return True if the game is over, false otherwise
     */
    public Boolean gameIsOver()
    {
        if (strUserAttempt.equals(strCurrentWord))
        {
            return true;
        }
        return false;
    }
}