/*
Ambiguous Coordinates
We had some 2-dimensional coordinates, like "(1, 3)" or "(2, 0.5)".  Then, we removed all commas, decimal points, and spaces, and ended up with the string s.  Return a list of strings representing all possibilities for what our original coordinates could have been.

Our original representation never had extraneous zeroes, so we never started with numbers like "00", "0.0", "0.00", "1.0", "001", "00.01", or any other number that can be represented with less digits.  Also, a decimal point within a number never occurs without at least one digit occuring before it, so we never started with numbers like ".1".

The final answer list can be returned in any order.  Also note that all coordinates in the final answer have exactly one space between them (occurring after the comma.)

Example 1:
Input: s = "(123)"
Output: ["(1, 23)", "(12, 3)", "(1.2, 3)", "(1, 2.3)"]
Example 2:
Input: s = "(00011)"
Output:  ["(0.001, 1)", "(0, 0.011)"]
Explanation: 
0.0, 00, 0001 or 00.01 are not allowed.
Example 3:
Input: s = "(0123)"
Output: ["(0, 123)", "(0, 12.3)", "(0, 1.23)", "(0.1, 23)", "(0.1, 2.3)", "(0.12, 3)"]
Example 4:
Input: s = "(100)"
Output: [(10, 0)]
Explanation: 
1.0 is not allowed.
 

Note:

4 <= s.length <= 12.
s[0] = "(", s[s.length - 1] = ")", and the other elements in s are digits.
*/
class Solution {
    public List<String> ambiguousCoordinates(String s) {
        StringBuilder base = new StringBuilder(s);
        final int last = base.length() - 1;
        List<String> ans = new LinkedList<String>();
        for (int i = 2; i < last; i++) {
            // comma position at i, check for valid numbers around i
            // build two lists of viable numbers and concatenate combinations
            LinkedList<CharSequence> a = new LinkedList<>();
            LinkedList<CharSequence> b = new LinkedList<>();
            validNums(base, 1, i, a);
            validNums(base, i, last, b);
            for (CharSequence part1 : a) {
                for (CharSequence part2 : b) {
                    ans.add(buildSequence("(, )", 1, part1, 3, part2).toString());
                }
            }
        }
        return ans;
    }
        
    private void validNums(StringBuilder base, int lo, int hi, List<CharSequence> parts) {
        // non-decimal check
        // starts with zero + more than 1 digit = invalid
        if (base.charAt(lo) == '0' && hi-lo > 1) {}
        else { parts.add(base.subSequence(lo, hi)); }
        
        // decimals check
        if (base.charAt(hi - 1) != '0') { // if trailing zero, no valid decimals
            if (base.charAt(lo) == '0') { // if starts with 0, only 1 valid decimal
                parts.add(buildSequence(".", 0, base.subSequence(lo, lo+1), 1, base.subSequence(lo+1, hi)));
            } else {
                for (int pos = lo + 1; pos < hi; pos++) {
                    parts.add(buildSequence(".", 0, base.subSequence(lo, pos), 1, base.subSequence(pos, hi)));
                }
            }   
        }
    }
    
    private StringBuilder buildSequence(String template, int pos1, CharSequence part1, int pos2, CharSequence part2) {
		// StringBuilder operations are significantly faster than String concatenations
        StringBuilder temp = new StringBuilder(template);
        temp.insert(pos2, part2);
        temp.insert(pos1, part1);
        return temp;
    }
}