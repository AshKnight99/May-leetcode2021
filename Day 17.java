/*
Longest String Chain
Given a list of words, each word consists of English lowercase letters.

Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere in word1 to make it equal to word2. For example, "abc" is a predecessor of "abac".

A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, where word_1 is a predecessor of word_2, word_2 is a predecessor of word_3, and so on.

Return the longest possible length of a word chain with words chosen from the given list of words.

 

Example 1:

Input: words = ["a","b","ba","bca","bda","bdca"]
Output: 4
Explanation: One of the longest word chain is "a","ba","bda","bdca".
Example 2:

Input: words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
Output: 5
 

Constraints:

1 <= words.length <= 1000
1 <= words[i].length <= 16
words[i] only consists of English lowercase letters.
*/
class Solution {
    public int longestStrChain(String[] words) {
        List<Set<String>> W = new ArrayList<>(17);
        for (int i = 0; i < 17; i++)
            W.add(new HashSet<>());
        for (String word : words) 
            W.get(word.length()).add(word);
        Map<String, Integer> dp = new HashMap<>();
        int best = 1;
        for (int i = 16; i > 0; i--) {
            if (W.get(i-1).isEmpty()) continue;
            for (String word : W.get(i)) {
                int wVal = dp.getOrDefault(word, 1);
                for (int j = 0; j < word.length(); j++) {
                    String pred = word.substring(0,j) + word.substring(j+1);
                    if (W.get(i-1).contains(pred) && wVal >= dp.getOrDefault(pred,1)) {
                        dp.put(pred, wVal + 1);
                        best = Math.max(best, wVal + 1);
                    }
                }
            }
        }
        return best;
    }
}
