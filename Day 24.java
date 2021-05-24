/*
To Lower Case
Given a string s, return the string after replacing every uppercase letter with the same lowercase letter.

 

Example 1:

Input: s = "Hello"
Output: "hello"
Example 2:

Input: s = "here"
Output: "here"
Example 3:

Input: s = "LOVELY"
Output: "lovely"
 

Constraints:

1 <= s.length <= 100
s consists of printable ASCII characters.
*/
class Solution {
    public String toLowerCase(String s) {
        int val = 'a' - 'A';        
        String str = "";
        for (char ch : s.toCharArray()){
            int v = (int)ch;
            if(v >= 65 && v <= 91)
                str += (char) (ch + val);
            else str += ch;
                
        }
        return str;
        
    }
}