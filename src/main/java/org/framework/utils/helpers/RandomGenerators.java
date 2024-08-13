package org.framework.utils.helpers;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomGenerators {

    public RandomGenerators() {
    }

    public static int getRandomInteger(int max,int min) {

        int range = max - min + 1;
        int randomNumber = (int) (Math.random() * range) + min;

        return randomNumber;
    }
    public static Set getRandomUniqueIntegers(int max,int min,int size) {

        Set<Integer> randomNumbers = new HashSet<>();
        Random random = new Random();

        while (randomNumbers.size() < size) {
            int randomNumber = random.nextInt(max - min + 1) + min;
            randomNumbers.add(randomNumber);
        }
      return randomNumbers;
    }

    public static String generateRandomString(int size) {
        // Generate the character set programmatically for A-Z
        StringBuilder characters = new StringBuilder();
        for (char c = 'A'; c <= 'Z'; c++) {
            characters.append(c);
        }

        Random random = new Random();
        StringBuilder result = new StringBuilder(size);

        for (int i = 0; i < size; i++) {
            int index = random.nextInt(characters.length());
            result.append(characters.charAt(index));
        }

        return result.toString();
    }


}
