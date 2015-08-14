/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author j.anaya.villagrana
 */
public class OperationTests {
    public static void main(String[] args) throws Exception {
        //MAX PRIME PALINDORME OPERATION TEST     
        System.out.println("MAX PRIME PALINDROME TEST");
        int limit = 1000;
        System.out.println("The max prime palindrome from 0 to "+limit+" is: " + maxPrimePalindrome(1000));
        
        //ADD PRIME NUMBER TEST
        System.out.println("\nADD PRIME NUMBER TEST");
        System.out.println("the result of adding all prime numbers from 0 to "+limit+" is: "+addPrimeNumbers(limit));
        
        //SAME BIT TEST
        System.out.println("\nSAME BIT TEST");
        String line = "125,1,2";
        String[] values = line.split(",");
        int n = Integer.parseInt(values[0]);
        int p1 = Integer.parseInt(values[1]);
        int p2 = Integer.parseInt(values[2]);
        System.out.println("the bits in position "+p1+" and "+p2+" are equal?: "+sameBits(n, p1, p2));
        
        //FIBONACCI TEST
        System.out.println("\nFIBONACCI TEST");
        int position = 12;
        System.out.println("the FIBONACCI number in position "+position+" is: " + calculateFibonacci(position));

        //FACTORIAL TEST
        System.out.println("\nFACTORIAL TEST");
        int f = 3;
        System.out.println("factorial of " + f + " is: " + factorial(BigInteger.valueOf(f)));
       
        //COLLATZ NUMBER TEST
        System.out.println("\nCOLLATZ NUMBER TEST");
        int j = 10;
        System.out.println("for " + j + " random numbers:");
        int[] array = new int[j];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 100);

        }
        determineCollatz(array);

    }

    /**
     * This method adds all prime numbers from 0 to a specified limit
     * @param limit addition limit
     * @return The result of adding all prime numbers
     */
    private static int addPrimeNumbers(int limit){
        int sum = 0;
        for (int i = 1, primes = 1; primes <= limit; i++) {
            if (isPrimeNumber(i)) {
                sum += i;
                primes++;
            }
        }
        return sum;
    }
    /**
     * This method will determine the max prime number that is consider a palindrome, from 0 to a specified limit
     * @param limit 
     * @return the max palindrome prime number
     */
    private static int maxPrimePalindrome(int limit){
        int counter = 0;
        int maxPrimePalindrome = 0;
        long startTime = System.currentTimeMillis();
        for (int i = 1; i <= limit; i++) {
            counter++;
            if (isPrimeNumber(i)) {
                if (isPalindrome(i) && i > maxPrimePalindrome) {
                    maxPrimePalindrome = i;
                }
            }
            if (i > 2) {
                ++i;
            }
        }
        long endTime = System.currentTimeMillis();
        double elapsedTime = (endTime - startTime);
        System.out.println("Elapsed excecution time: " + elapsedTime + " milliseconds");
        System.out.println("Total iterations: " + counter);
        return maxPrimePalindrome;
    }
    public static void determineCollatz(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        System.out.println("\nwithout cache");
        long startTime = System.currentTimeMillis();
        System.out.println("start time: " + startTime);
        for (int i : arr) {
            System.out.println("collatz number - f(" + i + ") = " + collatz(i, 0));
            //collatz(i, 0);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("endTime: " + endTime);
        double elapsedTime = (endTime - startTime);
        System.out.println("Elapsed excecution time: " + elapsedTime + " milliseconds");

        System.out.println("\nwith cache");
        long startTime2 = System.currentTimeMillis();
        System.out.println("start time: " + startTime2);
        for (int i : arr) {
            System.out.println("collatz number - f(" + i + ") = " + collatz(i, 0, map));
            //collatz(i, 0, map);
        }
        long endTime2 = System.currentTimeMillis();
        System.out.println("endTime: " + endTime2);
        double elapsedTime2 = (endTime2 - startTime2);
        System.out.println("Elapsed excecution time: " + elapsedTime2 + " milliseconds");

    }

    public static int collatz(int number, int counter) {
        if (number <= 1) {
            return counter;
        } else {
            if (number % 2 == 0) {
                return collatz(number / 2, ++counter);
            } else {
                return collatz((3 * number) + 1, ++counter);
            }
        }
    }

    public static int collatz(int number, int counter, Map<Integer, Integer> cache) {
        int temporalNumber = 0;
        if (number <= 1 || cache.containsKey(number)) {
            return cache.containsKey(number) ? cache.get(number) : 1;
        } else {
            if (number % 2 == 0) {
                temporalNumber = number / 2;
            } else {
                temporalNumber = (3 * number) + 1;
            }
            counter = collatz(temporalNumber, 0, cache);
            if (temporalNumber > 1) {
                counter++;
            }
            cache.put(number, counter);
        }
        return counter;
    }

    public static String generatePermutations(String word) {
        //BigInteger permutations = factorial(BigInteger.valueOf(word.length()));
        Set<String> setPermutations = new HashSet<String>();
        StringBuilder sb = null;
        int currentIndex = 0;
        setPermutations.add(word);
        while (currentIndex < word.length()) {
            for (int i = 0; i < word.length(); i++) {
                sb = new StringBuilder().append(swap(word.toCharArray(), i, currentIndex));
                setPermutations.add(sb.toString());
                //System.out.println(sb.toString());
                //permutations.subtract(BigInteger.ONE);
            }
            currentIndex++;
        }
        System.out.println("setPermutations: " + setPermutations);
        return null;
    }

    public static void permutation(String str) {
        permutation("", str);
    }

    private static void permutation(String prefix, String str) {
        int n = str.length();
        if (n == 0) {
            System.out.println(prefix);
        } else {
            for (int i = 0; i < n; i++) {
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n));
            }
        }
    }

    public static char[] swap(char[] array, int indexChange, int indexVal) {
        int mcm = array[indexChange] + array[indexVal];
        array[indexChange] = (char) (mcm - array[indexChange]);
        array[indexVal] = (char) (mcm - array[indexVal]);
        return array;
    }

    /**
     * this method calculates the value of the fibonnacci number based on a position
     * @param limit position of the fibonacci number
     * @return the fibonacci number that is located in the specified position
     */
    public static long calculateFibonacci(long position) {
        long previousFibonacci = 0;
        long fibonacciNumber = 0;
        long temp = 0;
        for (int i = 1; i <= position; i++) {
            if (fibonacciNumber == 0) {
                ++fibonacciNumber;
            } else {
                temp = fibonacciNumber;
                fibonacciNumber += previousFibonacci;
                previousFibonacci = temp;
            }
        }
        return fibonacciNumber;
    }

    public static BigInteger factorial(BigInteger number) {
        if (number.intValue() == 0) {
            return BigInteger.ONE;
        } else {
            return number.multiply(factorial(number.subtract(BigInteger.ONE)));
        }
    }

    /**
     * Determines if 2 bits from a number are equal or not
     * @param number number that will be transform to its bit representation
     * @param pos1 position of the first bit to compare
     * @param pos2 position of the second bit to compare
     * @return true if the bits are equal, false if they are not equal
     */
    public static boolean sameBits(int number, int pos1, int pos2) {
        String binary = Integer.toBinaryString(number);
        System.out.println("bit representation of number "+number+" is: "+binary);
        return binary.charAt(binary.length() - pos1) == binary.charAt(binary.length() - pos2);
    }
    
    public static long mcd(int a, int n) {
        //USING EULER FORMULA TO DETERMINE IF A NUMBER IS PRIME OR NOT
        //long exp = (long)Math.pow(a, ((n-1)/2)); 
        BigInteger exp = BigInteger.valueOf((long) a).modPow(BigInteger.valueOf((n - 1) / 2), BigInteger.valueOf(n));
        //pow(a, ((n-1)/2)); 
        return exp.longValue();

    }

    public static boolean isPalindrome(int number) {
        boolean isPalindrome = true;
        String numString = String.valueOf(number);
        for (int i = 0, j = numString.length() - 1; i <= numString.length() / 2; i++, j--) {
            if (numString.charAt(i) != numString.charAt(j)) {
                isPalindrome = false;
                break;
            }
        }
        return isPalindrome;
    }

    public static boolean isPrimeNumber(int number) {
        int multiplierCounter = 1;
        boolean isPrime = true;
        int limit = number;
        if (number > 1) {
            if (number > 1000) {
                limit = (int) (limit * .25);
            } else {
                multiplierCounter--;
            }
            for (int i = 1; i <= (limit); i++) {
                if (i == 1 || number == 2) {
                    multiplierCounter++;
                } else if (number % i == 0) {
                    multiplierCounter++;
                    if (multiplierCounter > 2) {
                        isPrime = false;
                        break;
                    }
                }
            }
        } else {
            isPrime = false;
        }
        return isPrime;
    }


    /*
     public static boolean isPrimeNumber(int number){
     /*
     MILLER - RABIN TESTS
     FOR NUMBERS LESS THAN 25,326,001, WE CAN USE BASES 2,3 AND 5
     FOR NUMBERS LESS THAN 4,759,123,141, WE CAN USE BASES 2, 7 AND 61.
     FOR NUMBERS LESS THAN 2,152,302,898,747 WE CAN USE BASES 2, 3, 5, 7 AND 11.
     FOR NUMBERS LESS THAN 341,550,071,728,321 WE CAN USE BASES 2, 3, 5, 7, 11, 13 AND 17.
        
        
     if(number <= 25326001L){
     if(mcd(2,number)==1 && mcd(3,number)==1 && mcd(5,number)==1){
     return true;
     }
     }
     return false;
     }
     */
}
