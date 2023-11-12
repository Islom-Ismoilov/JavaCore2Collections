package Task1;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordFrequencyCounter {
    public static void main(String[] args) {
        // Part 1
        // Find and save song as a String

        String song = "In the town where I was born\n" +
                "Lived a man who sailed to sea\n" +
                "And he told us of his life\n" +
                "In the land of submarines\n" +
                "So we sailed on to the sun\n" +
                "'Til we found a sea of green\n" +
                "And we lived beneath the waves\n" +
                "In our yellow submarine\n" +
                "We all live in a yellow submarine\n" +
                "Yellow submarine, yellow submarine\n" +
                "We all live in a yellow submarine\n" +
                "Yellow submarine, yellow submarine\n" +
                "And our friends are all aboard\n" +
                "Many more of them live next door\n" +
                "And the band begins to play\n" +
                "We all live in a yellow submarine\n" +
                "Yellow submarine, yellow submarine\n" +
                "We all live in a yellow submarine\n" +
                "Yellow submarine, yellow submarine\n" +
                "Full steam ahead, Mister Boatswain, full steam ahead\n" +
                "Full steam ahead it is, Sergeant\n" +
                "(Cut the cable, drop the cable)\n" +
                "Aye-aye, sir, aye-aye\n" +
                "Captain, captain\n" +
                "As we live a life of ease (a life of ease)\n" +
                "Every one of us (every one of us)\n" +
                "Has all we need (has all we need)\n" +
                "Sky of blue (sky of blue)\n" +
                "And sea of green (sea of green)\n" +
                "In our yellow (in our yellow)\n" +
                "Submarine (submarine, aha)\n" +
                "We all live in a yellow submarine\n" +
                "A yellow submarine, yellow submarine\n" +
                "We all live in a yellow submarine\n" +
                "A yellow submarine, yellow submarine\n" +
                "We all live in a yellow submarine\n" +
                "Yellow submarine, yellow submarine\n" +
                "We all live in a yellow submarine\n" +
                "Yellow submarine, yellow submarine";

        // Remove commas, newline characters, and convert to lowercase
        song = song.replaceAll("[,\\n]", "").toLowerCase();

        // Split the song into an array of words
        String[] wordsArray = song.split(" ");

        // Create an array to store word frequencies
        String[] uniqueWordsArray = new String[wordsArray.length];
        int[] wordFrequencies = new int[wordsArray.length];

        // Count word frequencies and print the results
        int uniqueWordCount = 0;
        for (String word : wordsArray) {
            int index = indexOfWord(uniqueWordsArray, word, uniqueWordCount);
            if (index == -1) {
                uniqueWordsArray[uniqueWordCount] = word;
                wordFrequencies[uniqueWordCount] = 1;
                uniqueWordCount++;
            } else {
                wordFrequencies[index]++;
            }
        }

        // Print unique words and their frequencies
        for (int i = 0; i < uniqueWordCount; i++) {
            System.out.println(uniqueWordsArray[i] + ": " + wordFrequencies[i]);
        }

        // Part 2
        List<String> wordsList = new ArrayList<>();
        Set<String> uniqueWordsSet = new HashSet<>();

        // Update Part1 to use a List to count words
        for (String word : wordsArray) {
            if (!uniqueWordsSet.contains(word)) {
                wordsList.add(word);
                uniqueWordsSet.add(word);
            }
        }

        // Print unique words without duplicates
        System.out.println("Unique Words: " + wordsList);

        // Sort by string length
        Collections.sort(wordsList, (a, b) -> a.length() - b.length());
        System.out.println("Words sorted by length: " + wordsList);

        // Part 3
        // Remove all occurrences of "yellow" and "submarine" from the List
        wordsList.removeIf(word -> word.equals("yellow") || word.equals("submarine"));

        // Print the resulting List
        System.out.println("List after removing 'yellow' and 'submarine': " + wordsList);

        // Part 4
        try (FileWriter fileWriter = new FileWriter("song.txt")) {

            // Save a song "beatles yellow submarine" to a txt file
            fileWriter.write("In the town where I was born\n" +
                    "Lived a man who sailed to sea\n" +
                    "And he told us of his life\n" +
                    "In the land of submarines\n" +
                    "So we sailed on to the sun\n" +
                    "'Til we found a sea of green\n" +
                    "And we lived beneath the waves\n" +
                    "In our yellow submarine\n" +
                    "We all live in a yellow submarine\n" +
                    "Yellow submarine, yellow submarine\n" +
                    "We all live in a yellow submarine\n" +
                    "Yellow submarine, yellow submarine\n" +
                    "And our friends are all aboard\n" +
                    "Many more of them live next door\n" +
                    "And the band begins to play\n" +
                    "We all live in a yellow submarine\n" +
                    "Yellow submarine, yellow submarine\n" +
                    "We all live in a yellow submarine\n" +
                    "Yellow submarine, yellow submarine\n" +
                    "Full steam ahead, Mister Boatswain, full steam ahead\n" +
                    "Full steam ahead it is, Sergeant\n" +
                    "(Cut the cable, drop the cable)\n" +
                    "Aye-aye, sir, aye-aye\n" +
                    "Captain, captain\n" +
                    "As we live a life of ease (a life of ease)\n" +
                    "Every one of us (every one of us)\n" +
                    "Has all we need (has all we need)\n" +
                    "Sky of blue (sky of blue)\n" +
                    "And sea of green (sea of green)\n" +
                    "In our yellow (in our yellow)\n" +
                    "Submarine (submarine, aha)\n" +
                    "We all live in a yellow submarine\n" +
                    "A yellow submarine, yellow submarine\n" +
                    "We all live in a yellow submarine\n" +
                    "A yellow submarine, yellow submarine\n" +
                    "We all live in a yellow submarine\n" +
                    "Yellow submarine, yellow submarine\n" +
                    "We all live in a yellow submarine\n" +
                    "Yellow submarine, yellow submarine");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileReader fileReader = new FileReader("song.txt");
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            StringBuilder songFromFile = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                songFromFile.append(line);
            }

            // Define a custom exception: BeatlesException
            try {
                // Create a method to verify if a random string is found in the song
                verifyStringInSong(songFromFile.toString(), "Show must go on!");
            } catch (BeatlesException e) {
                System.err.println("Exception caught: " + e.getMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // verifyStringInSong method
    private static void verifyStringInSong(String songContent, String searchString) throws BeatlesException {
        if (!songContent.contains(searchString)) {
            throw new BeatlesException("Beatles");
        }
    }

    private static int indexOfWord(String[] array, String word, int size) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(word)) {
                return i;
            }
        }
        return -1;
    }
}

class BeatlesException extends Exception {
    public BeatlesException(String message) {
        super(message);
    }
}
