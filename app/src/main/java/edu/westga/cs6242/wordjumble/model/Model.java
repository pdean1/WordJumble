package edu.westga.cs6242.wordjumble.model;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import edu.westga.cs6242.wordjumble.model.enums.EWordLength;
import edu.westga.cs6242.wordjumble.model.interfaces.IModel;

/**
 * Created by Wayne Mullins on 3/18/2016
 * @version 03/18/2016
 */
public class Model implements IModel {

    private ArrayList<String> fiveCharWordList;
    private ArrayList<String> sixCharWordList;
    private ArrayList<String> wordSource;
    private Random random;

    //standard constructor used by Controller/UI
    public Model(EWordLength length) {

        this(length,false);
    }//constructor(EWordLength)

    //detailed constructor to specify unitTestMode
    public Model(EWordLength length, boolean unitTestMode) {
        random = new Random();

        //Code for Part 1 and JUnit Testing
        this.fiveCharWordList = new ArrayList<>(Arrays.asList(
                "buxom", "chump", "crazy", "gauze", "juice", "maize", "quick", "topaz"));

        this.sixCharWordList = new ArrayList<>(Arrays.asList(
                "cajole", "gazebo", "hijack", "logjam", "jockey", "junket", "piques", "squeak"));

        // Determines what size of word to play with
        switch (length) {
            case FIVE_LETTER_WORD:
                this.setWordLengthToFive();
                break;
            case SIX_LETTER_WORD:
                this.setWordLengthToSix();
                break;
            default: // by default, use 5 letter words
                this.setWordLengthToFive();
        }

        //if we're in JUnit Test mode, test against fixed array because the necessary Application
        //Context is not directly available under JUnit and is required to obtain file paths in the
        //Assets directory.
        if (unitTestMode)
            return;

        /*--------------------------------------------------------------------------------
        //Code for Part 2 and Instrumentation testing
         */
        this.fiveCharWordList = new ArrayList<>();
        this.sixCharWordList = new ArrayList<>();

        LoadWordsFromFile("FiveCharWords.txt", fiveCharWordList);
        LoadWordsFromFile("SixCharWords.txt", sixCharWordList);

    }//constructor(EWordLength)

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

    private void LoadWordsFromFile(String filePath, ArrayList<String> wordList) {
        try {
            Context context = App.getContext();
            AssetManager am = App.getContext().getAssets();
            InputStream is = am.open(filePath);
            Scanner reader = new Scanner(is);
            wordList.clear();
            while (reader.hasNextLine()) {
                String word = reader.nextLine();
                wordList.add(word);
            }
        } catch (Exception ex) {
            assert false;
        }
    }

}//class Model
