package edu.westga.cs6242.wordjumble.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Wayne Mullins on 3/18/2016
 * @version 03/18/2016
 */
public class Model {

    private ArrayList<String> fiveCharWordList;
    private ArrayList<String> sixCharWordList;
    private ArrayList<String> wordSource;
    private Random random;

    public Model() {
        this.fiveCharWordList = new ArrayList<>(Arrays.asList(
                "buxom", "chump", "crazy", "gauze", "juice", "maize", "quick", "topaz"));

        this.sixCharWordList = new ArrayList<>(Arrays.asList(
                "cajole", "gazebo", "hijack", "logjam", "jockey", "junket", "piques", "squeak"));

        //by default, use 5 letter words
        //this.setWordLengthToFive(); removed to favor variability
        random = new Random();
        if (random.nextInt(100) > 50)
            this.setWordLengthToFive();
        else
            this.setWordLengthToSix();
    }//constructor

    /**
     * getWordLength()
     *      Returns the current work length.
     * @return - current word length as int
     */
    public int getWordLength() {
        if (this.wordSource == this.fiveCharWordList)
            return 5;
        return 6;
    }//getWordLength()

    /**
     * setWordLengthToFive()
     *      Sets the word source to list of 5 letter words
     */
    public void setWordLengthToFive() {
        this.wordSource = this.fiveCharWordList;
    }//setwordLengthToFive()

    /**
     * setWordLengthToSix()
     *       Sets the word source to list of 6 letter words
     */
    public void setWordLengthToSix() {
        this.wordSource = this.sixCharWordList;
    }//setWordLengthToSix(

    /**
     * getRandomWord()
     *      Gets a random word from the existing word source
     * @return - a random word
     */
    public String getRandomWord() {
        int index = random.nextInt(this.wordSource.size());
        return this.wordSource.get(index);
    }//getRandomWord();

    /**
     * scrambleWord()
     * @param word - word to scramble
     * @return - scrambled word
     */
    public String scrambleWord(String word) {
        char[] chars = word.toCharArray();
        for (int index = 0; index < chars.length; index++) {
            int index2 = random.nextInt(chars.length);
            char temp = chars[index2];
            chars[index2] = chars[index];
            chars[index] = temp;
        }
        return new String(chars);
    }//scrambleWord()

}//class Model
