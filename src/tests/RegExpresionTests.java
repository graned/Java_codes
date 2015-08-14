/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author j.anaya.villagrana
 */
public class RegExpresionTests {

    /**
     * TEST MAIN CLASS
     */
    public static void main(String[] args) {
        //BRAKETS TESTS
        System.out.println("CORRECT BRAKET FORMAT TEST");
        String s2 = "asdvgbfdghjg   ((fdhsgj((jfgj(fjgkhlkh((kl(kh(jkl);k))df)gfjhf))jkf)j)fghj)fjf [][][][][][][][]";
        System.out.println("isCorrect?: " + isBraketFormatCorrect(s2));
        //REVERSE WORD TEST
        System.out.println("\nREVERSE WORD TEST");
        String st = "HELLO world!";
        System.out.println("reversed word: " + reverseWord(st));

        //REPLACE ALL TEST
        System.out.println("\nREPLACE ALL TEST");
        String s = "ABCSamZhubBXyzxYZabcaBc";
        String value = "abc";
        String replaceValue = "xYYz";
        System.out.println("in text: " + s);
        System.out.println("value: " + value + " will be replaced for " + replaceValue);
        System.out.println("result: " + replaceAll(value, s, replaceValue));

        //TO LOWER STRING TEST
        System.out.println("\nTO LOWER STRING TEST");
        String sl = "HELLO world HELLO HELLO";
        System.out.println("text that will be transformed to lower string: " + sl);
        System.out.println("result: " + toLowerString(sl));
        
        //SEPARATE UNIQUE VALUES TEST
        System.out.println("\nSEPARATE UNIQUE VALUES TEST");
        String sU = "RonSamJoeJoeSamRon";
        System.out.println("The unique values in string "+sU+" are:");
        separateUnique(sU);
    }

    /**
     * this method separates a string into its unique elements based on an regular expression
     * @param s2 
     */
    private static void separateUnique(String s2) {
        Matcher m2 = Pattern.compile("(\\S{2,})(?=.*?\\1)").matcher(s2);
        while (m2.find()) {
            for (int i = 1; i <= m2.groupCount(); i++) {
                System.out.println(m2.group(i));
            }
        }
    }

    /**
     * this method determines if a string of any size, contains a correct braket
     * format. <br>
     * for every open braket "[{(" there must be a close braket "]})"
     *
     * @param text
     * @return true if the braket format is correct, false if the braket format
     * is incorrect or there are no brakets
     */
    public static boolean isBraketFormatCorrect(String text) {
        //OLD IMPLEMENTATION RETURNED A LIST WITH ALL BRAKETS, NEW IMPLEMENTATION ONLY SAVES IN A STACK IF THE NEXT ELEMENT IS NOT
        //A CLOSE BRAKET
        //IMPLEMENTATION 3
        Stack<String> stack = new Stack();
        Pattern p = Pattern.compile("((\\[|\\]))|((\\{|\\}))|((\\(|\\)))");
        Matcher m = p.matcher(text);
        while (m.find()) {
            String foundBraket = m.group().trim();
            if (foundBraket.matches("(\\(|\\{|\\[)")) {
                stack.push(foundBraket);
            } else {
                try {
                    if (stack.size() != 0) {
                        String expectedBraket = getExpectedBraket(stack.peek());
                        if (expectedBraket.equals(foundBraket)) {
                            stack.pop();
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } catch (Exception e) {
                    if (e.getMessage().equals("TE DROGASTE!")) {
                        return false;
                    }
                }
            }
        }
        //System.out.println("list: " + stack);
        if (stack.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Determines which is the expected close braket based on the open braket
     * value passed as parameter
     *
     * @param value open braket, can be "[{("
     * @return the corresponding close braket "]})"
     * @throws Exception In case that the value passed is not any of the open
     * brackets it will throw an exception
     */
    private static String getExpectedBraket(String value) throws Exception {
        switch (value) {
            case "{":
                return "}";
            case "(":
                return ")";
            case "[":
                return "]";
            default:
                throw new Exception("HORROR!!");
        }
    }

    /**
     * this method reverses a string, i.e. Hello world! -> world! Hello
     *
     * @param sentence
     * @return the reverse string
     */
    public static String reverseWord(String sentence) {
        StringBuilder sb = new StringBuilder();
        String[] words = sentence.split("\\s");
        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i]);
            sb.append(" ");
        }
        return sb.toString();
    }

    /**
     * Own implementation of String method replaceAll
     *
     * @param reggex string text to look for
     * @param text string text
     * @param replaceWith if there is a match of reggex in text, the it will be
     * replaced by this value
     * @return the string representation with the replaced values.
     */
    public static String replaceAll(String reggex, String text, String replaceWith) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length();) {
            String t = text.substring(i, reggex.length() + i <= text.length() ? reggex.length() + i : i);
            if (reggex.equalsIgnoreCase(t)) {
                sb.append(replaceWith);
                i += reggex.length();
            } else {
                sb.append(text.charAt(i));
                i++;
            }
        }
        return sb.toString();
    }

    /**
     * own implementation of toLowerString method
     *
     * @param s string to be change to lower cases
     * @return string representation using only lower case strings
     */
    private static String toLowerString(String s) {
        String r = null;
        String outp = s;
        Pattern p = Pattern.compile("[A-Z]+.");
        Matcher m = p.matcher(s);
        boolean b = m.find();
        while (b) {
            r = m.group();
            outp = outp.replaceAll(r, r.toLowerCase());
            m = p.matcher(outp);
            b = m.find();
        }
        return outp;
    }
}
