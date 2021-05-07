/*
Delete Operation for Two Strings
Given two strings word1 and word2, return the minimum number of steps required to make word1 and word2 the same.

In one step, you can delete exactly one character in either string.

 

Example 1:

Input: word1 = "sea", word2 = "eat"
Output: 2
Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
Example 2:

Input: word1 = "leetcode", word2 = "etco"
Output: 4
 

Constraints:

1 <= word1.length, word2.length <= 500
word1 and word2 consist of only lowercase English letters.
*/
class Solution {
    
    public int minDistance(String W1, String W2) {
        // LCS problem
       int m = W1.length(), n = W2.length();
        if (m < n) {
            String tempStr = W1;
            W1 = W2;
            W2 = tempStr;
            int tempInt = n;
            n = m;
            m = tempInt;
        }
        char[] WA1 = W1.toCharArray(), WA2 = W2.toCharArray();
        int[] dpLast = new int[n + 1], dpCurr = new int[n + 1];
        for (char c1 : WA1) {
            for (int j = 0; j < n; j++) 
                dpCurr[j + 1] = (c1 == WA2[j]) ? dpLast[j] + 1: Math.max(dpCurr[j], dpLast[j + 1]);
            int[] tempArr = dpLast;
            dpLast = dpCurr;
            dpCurr = tempArr;
        }
        return m + n - 2 * dpLast[n];
    }
}
/*
2D dp array - 
 if(word1 == null || word2 == null){
            return  0;
        }
        int m = word1.length() , n = word2.length();
        int[][] dp = new int[m + 1][ n + 1];
        for(int i =0 ; i <= m ; i++){
            for(int j = 0 ; j <=n ; j++){
                if(i ==0 || j==0){
                    dp[i][j] = 0;
                }
                else if(word1.charAt(i - 1) == word2.charAt(j  - 1)){
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                }else{
                    dp[i][j] = Math.max(dp[i - 1][j] , dp[i][j - 1]);
                }
            }
        }
        
        int del = m + n - 2 * dp[m][n];
       return del;
}
}