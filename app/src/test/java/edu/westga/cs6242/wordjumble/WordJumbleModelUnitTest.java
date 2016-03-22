package edu.westga.cs6242.wordjumble;

import org.junit.Test;
import edu.westga.cs6242.wordjumble.edu.westga.cs6242.wordjumble.model.Model;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class WordJumbleModelUnitTest {
    /**
        Test that the initial word length is 5 which also verifies constructor set it up
     */
    @Test
    public void testInitialWordLength() {
        Model model = new Model();
        assertEquals(5, model.getWordLength());
    }

    /**
     *  Test that an initial random word is of length 5 and getRandomWord() works
     */
    @Test
    public void testRandomFiveLetterWordForLength() {
        Model model = new Model();
        assertEquals(5, model.getRandomWord().length() );
    }

    /**
     * Test that the word length can be set to Six and the getWordLength() works
     */
    @Test
    public void testSettingSixLetterWordLength() {
        Model model = new Model();
        model.setWordLengthToSix();
        assertEquals(6, model.getWordLength());
    }

    /**
     * Test that the getRandomWord() works after setting to length Six
     */
    @Test
    public void testRandomSixLetterWordForLength() {
        Model model = new Model();
        model.setWordLengthToSix();
        assertEquals(6, model.getRandomWord().length());
    }

    /**
     * Test that ScrambleWord() does its job, correct length and scrambled
     */
    @Test
    public void testScrambleWord() {
        Model model = new Model();
        model.setWordLengthToSix();
        String word = model.getRandomWord();
        String scrambledWord = model.scrambleWord(word);
        assertTrue(word.length() == scrambledWord.length() && !word.equals(scrambledWord));
    }

}