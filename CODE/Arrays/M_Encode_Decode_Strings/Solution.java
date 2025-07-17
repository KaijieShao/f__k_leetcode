package CODE.Arrays.M_Encode_Decode_Strings;

import java.util.List; 
// List is an interface | programming to an interface
import java.util.ArrayList; 
// ArrayList is a class that implements the List interface

public class Solution {

    public String encode(List<String> strs) { 
    // Encodes a list of strings into a single string

        StringBuilder res = new StringBuilder(); 
        // StringBuilder is a Standard Java library to build & modify strings

        for (String s : strs) {
            res.append(s.length()).append('#').append(s);
            // 1. `.s.length()` converts the string’s length to its decimal representation.
            // 2. `.append('#')` adds a delimiter (‘#’) so we know where the length ends.
            // 3. `.append(s)` appends the actual string contents.
            // Example: if s = “hello”, this appends “5#hello”.
        }

        return res.toString(); 
        // Turns the entire sequence built from the array of strings into a single String.
    }

    // Now, we may get a list like '3#dog4#good'

    public List<String> decode(String str) {

        List<String> res = new ArrayList<>(); 
        // List<String> is the interface type (more flexible for code, allows switching to other List types if needed).
        // new ArrayList<>() is the actual implementation (memory allocation, underlying behavior).

        int i = 0; 
        // Use `i` as a pointer to scan through the encoded `str`.

        while (i < str.length()) { 
        // This Outer loop goes through the entire big encoded string
            int j = i; 
            // Both i and j are pointing to the start of the next encoded piece in the string

            while (str.charAt(j) != '#') { 
            // The inner loop stops at # to find the substring length.
            // Then, use that length to extract the actual string.
                j++;                 
            }

            // After the inner while loop:
            // i still points to the start of the length digits (the head of the current encoded part).
            // j points to the position of the # delimiter (right after the last digit of the length).

            int length = Integer.parseInt(str.substring(i, j));
            // The encoded format does not store the length as a number object, but as a sequence of characters
            // i points to the start of the length digits (inclusive)
            // j points to the position of the # delimiter (exclusive)
            // i.e., if str = “5#hello”, then i = 0 and j = 1 -> str.substring(i, j) is “5” -> convert to digit 5

            i = j + 1; 
            // Move i to the position right after the #
            j = i + length; 
            // Set j + length so that the substring from i to j is exactly the next string

            res.add(str.substring(i, j));
            // Extracts the actual substring of the specified length
            // str.substring(start, end) -> returns a new string from index start (inclusive) to end (exclusive)

            i = j; 
            // Since str.substring(i, j) is inclusive of i and exclusive of j, after decoding, j points to the index just after the actual string.
            // Setting i = j prepares for the next iteration, so i starts at the next encoded length.
        }

        return res; 
        // Return the list of decoded strings.
    }
}

