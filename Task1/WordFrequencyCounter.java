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

        String song = "Oh, I get by with a little help from my friends\n" +
                "Mm, I get high with a little help from my friends\n" +
                "Mm, I'm gonna try with a little help from my friends";

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

        // Update Part 1 to use a List to count words
        for (String word : wordsArray) {
            if (!uniqueWordsSet.contains(word)) {
                wordsList.add(word);
                uniqueWordsSet.add(word);
            }
        }

        // Print unique words without duplicates
        System.out.println("Unique Words: " + wordsList);

        // Sort words by string length
        Collections.sort(wordsList, (a, b) -> a.length() - b.length());
        System.out.println("Words sorted by length: " + wordsList);

        // Part 3
        // Remove all occurrences of "yellow" and "submarine" from the List
        wordsList.removeIf(word -> word.equals("yellow") || word.equals("submarine"));

        // Print the resulting List
        System.out.println("List after removing 'yellow' and 'submarine': " + wordsList);

        // Part 4
        try (FileWriter fileWriter = new FileWriter("song.txt")) {
            // Save song "beatles yellow submarine" to a txt file
            fileWriter.write("beatles yellow submarine");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileReader fileReader = new FileReader("song.txt");
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            StringBuilder songFromFile = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                songFromFile.append(line);
            }
            // Define a custom exception: BeatlesException
            try {
                // Create a method to verify if a random string is found in the song
                String randomStringToCheck = "Show must go on!";
                if (!songFromFile.toString().contains(randomStringToCheck)) {
                    throw new BeatlesException("Beatles");
                }
            } catch (BeatlesException e) {
                System.err.println("Exception caught: " + e.getMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
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

