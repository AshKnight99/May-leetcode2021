/*
Find and Replace Pattern
Given a list of strings words and a string pattern, return a list of words[i] that match pattern. You may return the answer in any order.

A word matches the pattern if there exists a permutation of letters p so that after replacing every letter x in the pattern with p(x), we get the desired word.

Recall that a permutation of letters is a bijection from letters to letters: every letter maps to another letter, and no two letters map to the same letter.

 

Example 1:

Input: words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
Output: ["mee","aqq"]
Explanation: "mee" matches the pattern because there is a permutation {a -> m, b -> e, ...}. 
"ccc" does not match the pattern because {a -> c, b -> c, ...} is not a permutation, since a and b map to the same letter.
Example 2:

Input: words = ["a","b","c"], pattern = "a"
Output: ["a","b","c"]
 

Constraints:

1 <= pattern.length <= 20
1 <= words.length <= 50
words[i].length == pattern.length
pattern and words[i] are lowercase English letters.
*/
class Solution {
    List<String> ans;
    Map<Character, Character> codex;
    char[] cipher;
    
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        ans = new ArrayList<>();
        codex = new HashMap<>();
        cipher = pattern.toCharArray();
        for (int i = 0; i < pattern.length(); i++)
            cipher[i] = translate(cipher[i]);
        for (String word : words) compare(word);
        return ans;
    }
    private char translate(char c) {
        if (!codex.containsKey(c))
            codex.put(c, (char)(97 + codex.size()));
        return codex.get(c);
    }
    private void compare(String word) {
        codex.clear();
        for (int i = 0; i < word.length(); i++)
            if (translate(word.charAt(i)) != cipher[i]) return;
        ans.add(word);
    }
}