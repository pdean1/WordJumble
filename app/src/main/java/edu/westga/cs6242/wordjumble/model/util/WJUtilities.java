package edu.westga.cs6242.wordjumble.model.util;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import edu.westga.cs6242.wordjumble.model.enums.EWordLength;

/**
 * @author Patrick Dean
 * @version 20160329
 */
public class WJUtilities {
    public static final String NEW_GAME = "word_jumble.new_game";
    // This url points to data on the internet that contains the words with there associated help
    //   dialogs
    public static final String DATA_URL = "http://stu.westga.edu/~pdean1/wordjumble/data.txt";

    public static Map<String, String> GetWordsFromWeb(EWordLength wordLength) {
        // HashMap where the key is the word and the value is the help text associated with the word
        Map<String, String> mapWordsWithHelp = new HashMap<String, String>();
        // Process Variables
        Scanner scanner; // scanner will read from url
        URL     url;     // url scanner will read from
        try {
            url = new URL(WJUtilities.DATA_URL); // reference the url
            scanner = new Scanner(url.openStream()); // stream the data
            while (scanner.hasNext()) { // as long as there is more data
                String strTemp = scanner.nextLine(); // place the value in temp
                String arstrTemp[] = strTemp.split("-");
                if (arstrTemp.length > 3) {
                    continue;
                }
                if (wordLength == EWordLength.FIVE_LETTER_WORD
                        && arstrTemp[0].length() == 5) {
                    storeValues(mapWordsWithHelp, arstrTemp);
                } else if (wordLength == EWordLength.SIX_LETTER_WORD
                        && arstrTemp[0].length() == 6) {
                    storeValues(mapWordsWithHelp, arstrTemp);
                }
            }
        }
        catch (Exception exception)
        {
            return null; // error occured, unable to use this file at this time.
        }
        if (mapWordsWithHelp.isEmpty()) {
            return null;
        }
        return mapWordsWithHelp;
    }

    private static boolean storeValues(Map<String, String> map, String a[]) {
        try {
            map.put(a[0], a[1]);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
