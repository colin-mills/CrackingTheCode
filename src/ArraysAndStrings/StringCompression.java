//1.6 StringCompression
//Pg. 91
//TC: O(n)
//SC: O(n) //if we count output in space complexity
package ArraysAndStrings;
import java.util.HashMap;

public class StringCompression {
    public static void main(String[] args) {
        System.out.println(compress("AAAAAAAAAAAAA"));
        System.out.println(compress("AAAAAAAAAAAAAaaaaaaaabbbbbbbb"));
        System.out.println(compress("abc"));
        System.out.println(compress("The quick fox jumped over the lazy log"));
        System.out.println(compress("Breakfast eatery croissant naplkin fork dog dumb"));

    }
    public static String compress(String str) {
        var dict = new HashMap<Character,Integer>();
        int compressedLength = 0;
        StringBuilder newStr = new StringBuilder(104); //104 should be the size if every character has less than 9 digits and is in the string
        for (int i=0; i < str.length(); i++) {
            compressedLength += addChar(dict, str.charAt(i));
        }
        compressedLength *= 2;
        if (str.length() < compressedLength) return str;
        else {
            for (char ch: dict.keySet()) {
                newStr.append(ch);
                newStr.append(dict.get(ch));
            }
        }
        return newStr.toString();
    }
    public static int addChar(HashMap<Character,Integer> dict, char putChar) {
        if (putChar == ' ') return 0;
        if (!dict.containsKey(putChar)) {
            dict.put(putChar, 1);
            return 1;
        } else {
            int newInt = dict.get(putChar) + 1;
            dict.replace(putChar, newInt);
            return newInt/10;
        }
    }
}

