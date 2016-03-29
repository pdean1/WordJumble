package edu.westga.cs6242.wordjumble.model;

import edu.westga.cs6242.wordjumble.controller.Controller;
import edu.westga.cs6242.wordjumble.model.enums.EWordLength;

/**
 * Created by Patrick on 3/27/2016.
 * @version 20162703
 * This class represents a Word Jumble Game
 */
public final class WordJumbleGame {
    private String     strCurrentWord;      // the current word the user is attempting to solve
    private String     strScrambledWord;    // a scrambled version of strCurrentWord
    private String     strUserAttempt;      // the user's attempt at solving the strScrambledWord
    private Controller _controller;         // controller for interacting with the model

    /**
     * Default constructor for a WordJumbleGame
     */
    public WordJumbleGame()
    {
        this(EWordLength.FIVE_LETTER_WORD);
    }

    /**
     * Creates a new WordJumbleGame Object with a word of length length
     */
    public WordJumbleGame(EWordLength length)
    {
        this._controller      = new Controller(length);
        this.strCurrentWord   = this._controller.getRandomWord();
        this.strScrambledWord = this._controller.scrambleWord(this.strCurrentWord);
        this.strUserAttempt   = "";
    }

    public String getStrCurrentWord()
    {
        return this.strCurrentWord;
    }

    public String getStrScrambledWord()
    {
        return this.strScrambledWord;
    }

    public String getStrUserAttempt()
    {
        return this.strUserAttempt;
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
        if (strUserAttempt.equalsIgnoreCase(strCurrentWord)) // a equal to b regardless of case...
        {
            return true;
        }
        return false;
    }
}