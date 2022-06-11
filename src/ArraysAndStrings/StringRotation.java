//1.8 String Rotation
//Pg. 91
//TC: O(A + B) //where A is the length of s1 and B is the length of s2
//SC: O(A + B) //we will need to copy String to pass to isSubstring

package ArraysAndStrings;

public class StringRotation {
    public static void main (String[] args) {
        System.out.println(isRotatedSubstring(null,null));
        System.out.println(isRotatedSubstring("",""));
        System.out.println(isRotatedSubstring(null,""));
        System.out.println(isRotatedSubstring("T",""));

    }
    /* why this works
            s1 = xy = waterbottle
            s2 = yx = bottlewater
            x = water
            y = bottle
            Therefore,
            s1s1 = xyxy = water_bottlewater_bottle
            so xyxy will contain the pivoted string
         */
    public static boolean isRotatedSubstring(String s1, String s2) {
        if (null == s1 || null == s2) return s1 == s2;
        if (s1.length() == 0 || s2.length() == 0) return s1.equals(s2);

        if (s1.length() != s2.length()) return false;

        String s1s1 = s1+s1;
        return isSubstring(s1s1, s2);

    }
    public static boolean isSubstring (String s1, String s2) {
        //dummy class to appease the IDE
        //we can just assume this is initialized
        return true;
    }
}
