/*
Maximum Product of Word Lengths
Given a string array words, return the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. If no such two words exist, return 0.

 

Example 1:

Input: words = ["abcw","baz","foo","bar","xtfn","abcdef"]
Output: 16
Explanation: The two words can be "abcw", "xtfn".
Example 2:

Input: words = ["a","ab","abc","d","cd","bcd","abcd"]
Output: 4
Explanation: The two words can be "ab", "cd".
Example 3:

Input: words = ["a","aa","aaa","aaaa"]
Output: 0
Explanation: No such pair of words.
 

Constraints:

2 <= words.length <= 1000
1 <= words[i].length <= 1000
words[i] consists only of lowercase English letters.
*/
class Solution {
    public int maxProduct(String[] words) {
        int best = 0;
        int[] bitsets = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            int wlen = words[i].length(), bitset = 0;
            for (int j = 0; j < wlen; j++)
                bitset |= 1 << (words[i].charAt(j) - 'a');
            for (int j = 0; j < i; j++)
                if ((bitsets[j] & bitset) == 0)
                    best = Math.max(best, wlen * words[j].length());
            bitsets[i] = bitset;
        }
        return best;
    }
}