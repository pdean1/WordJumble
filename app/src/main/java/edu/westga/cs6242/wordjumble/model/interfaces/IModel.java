package edu.westga.cs6242.wordjumble.model.interfaces;

/**
 * Interface for those wishing to implement the base model
 * @author Patrick Dean
 * @version 20160329
 */
public interface IModel {
    /**
     * getWordLength()
     *      Returns the current work length.
     * @return - current word length as int
     */
    int getWordLength();

    /**
     * setWordLengthToFive()
     *      Sets the word source to list of 5 letter words
     */
    void setWordLengthToFive();

    /**
     * setWordLengthToSix()
     *       Sets the word source to list of 6 letter words
     */
    void setWordLengthToSix();

    /**
     * getRandomWord()
     *      Gets a random word from the existing word source
     * @return - a random word
     */
    String getRandomWord();

    /**
     * scrambleWord()
     * @param word - word to scramble
     * @return - scrambled word
     */
    String scrambleWord(String word);
} // end of IModel
