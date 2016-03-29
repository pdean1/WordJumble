package edu.westga.cs6242.wordjumble.controller;

import edu.westga.cs6242.wordjumble.model.Model;
import edu.westga.cs6242.wordjumble.model.enums.EWordLength;
import edu.westga.cs6242.wordjumble.model.interfaces.IModel;

/**
 * Created by Wayne Mullins on 3/18/2016
 * @version 03/18/2016
 */
public class Controller implements IModel {
    private Model _model;
    public Controller(EWordLength length)
    {
        this._model = new Model(length);
    }
    /**
     * getWordLength()
     * Returns the current work length.
     *
     * @return - current word length as int
     */
    @Override
    public int getWordLength() {
        return this._model.getWordLength();
    }

    /**
     * setWordLengthToFive()
     * Sets the word source to list of 5 letter words
     */
    @Override
    public void setWordLengthToFive() {
        this._model.setWordLengthToFive();
    }

    /**
     * setWordLengthToSix()
     * Sets the word source to list of 6 letter words
     */
    @Override
    public void setWordLengthToSix() {
        this._model.setWordLengthToSix();
    }

    /**
     * getRandomWord()
     * Gets a random word from the existing word source
     *
     * @return - a random word
     */
    @Override
    public String getRandomWord() {
        return this._model.getRandomWord();
    }

    /**
     * scrambleWord()
     *
     * @param word - word to scramble
     * @return - scrambled word
     */
    @Override
    public String scrambleWord(String word) {
        return this._model.scrambleWord(word);
    }
}//class Controller
