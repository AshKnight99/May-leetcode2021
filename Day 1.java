/*
Prefix and Suffix Search
Design a special dictionary which has some words and allows you to search the words in it by a prefix and a suffix.

Implement the WordFilter class:

WordFilter(string[] words) Initializes the object with the words in the dictionary.
f(string prefix, string suffix) Returns the index of the word in the dictionary which has the prefix prefix and the suffix suffix. If there is more than one valid index, return the largest of them. If there is no such word in the dictionary, return -1.
 

Example 1:

Input
["WordFilter", "f"]
[[["apple"]], ["a", "e"]]
Output
[null, 0]

Explanation
WordFilter wordFilter = new WordFilter(["apple"]);
wordFilter.f("a", "e"); // return 0, because the word at index 0 has prefix = "a" and suffix = 'e".
 

Constraints:

1 <= words.length <= 15000
1 <= words[i].length <= 10
1 <= prefix.length, suffix.length <= 10
words[i], prefix and suffix consist of lower-case English letters only.
At most 15000 calls will be made to the function f.
*/
class TrieNode {
    public TrieNode[] children = new TrieNode[26];
    public List<Integer> vals = new ArrayList<>();
}

class WordFilter {
    private TrieNode pTrie = new TrieNode();
    private TrieNode sTrie = new TrieNode();
    
    public WordFilter(String[] words) {
        for (int index = 0; index < words.length; index++) {
            char[] word = words[index].toCharArray();
            int wlen = word.length;
            insert(word, index, pTrie, 0, wlen, 1);
            insert(word, index, sTrie, wlen-1, -1, -1);
        }
    }
            
    private void insert(char[] word, int index, TrieNode trie, int start, int end, int step) {
        for (int i = start; i != end; i += step) {
            int c = word[i] - 'a';
            if (trie.children[c] == null)
                trie.children[c] = new TrieNode();
            trie = trie.children[c];
            trie.vals.add(index);
        }
    }
    
    private List<Integer> retrieve(char[] word, TrieNode trie, int start, int end, int step) {
        for (int i = start; i != end; i += step) {
            trie = trie.children[word[i]-'a'];
            if (trie == null) return new ArrayList<>();
        }
        return trie.vals;
    }
    
    public int f(String pre, String suf) {
        List<Integer> pVals = retrieve(pre.toCharArray(), pTrie, 0, pre.length(), 1);
        List<Integer> sVals = retrieve(suf.toCharArray(), sTrie, suf.length()-1, -1, -1);
        int svix = sVals.size() - 1, pvix = pVals.size() - 1;
        while (svix >= 0 && pvix >= 0) {
            int sVal = sVals.get(svix), pVal = pVals.get(pvix);
            if (sVal == pVal) return sVal;
            if (sVal > pVal) svix--;
            else pvix--;
        }
        return -1;
    }
}