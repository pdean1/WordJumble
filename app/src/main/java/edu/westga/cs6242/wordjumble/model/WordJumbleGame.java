package edu.westga.cs6242.wordjumble.model;

import android.os.Parcel;
import android.os.Parcelable;

import edu.westga.cs6242.wordjumble.controller.Controller;
import edu.westga.cs6242.wordjumble.model.enums.EWordLength;

/**
 * Created by Patrick on 3/27/2016.
 * @version 20162703
 * This class represents a Word Jumble Game
 */
public final class WordJumbleGame implements Parcelable {
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

    protected WordJumbleGame(Parcel in) {
        strCurrentWord = in.readString();
        strScrambledWord = in.readString();
        strUserAttempt = in.readString();
    }

    /**
     * Implemented from Parcelable
     */
    public static final Creator<WordJumbleGame> CREATOR = new Creator<WordJumbleGame>() {
        @Override
        public WordJumbleGame createFromParcel(Parcel in) {
            return new WordJumbleGame(in);
        }

        @Override
        public WordJumbleGame[] newArray(int size) {
            return new WordJumbleGame[size];
        }
    };

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

    /**
     * Describe the kinds of special objects contained in this Parcelable's
     * marshalled representation.
     *
     * @return a bitmask indicating the set of special object types marshalled
     * by the Parcelable.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(strCurrentWord);
        dest.writeString(strScrambledWord);
        dest.writeString(strUserAttempt);
    }
}