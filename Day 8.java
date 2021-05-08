/*
Super Palindromes
Let's say a positive integer is a super-palindrome if it is a palindrome, and it is also the square of a palindrome.

Given two positive integers left and right represented as strings, return the number of super-palindromes integers in the inclusive range [left, right].

 

Example 1:

Input: left = "4", right = "1000"
Output: 4
Explanation: 4, 9, 121, and 484 are superpalindromes.
Note that 676 is not a superpalindrome: 26 * 26 = 676, but 26 is not a palindrome.
Example 2:

Input: left = "1", right = "2"
Output: 1
 

Constraints:

1 <= left.length, right.length <= 18
left and right consist of only digits.
left and right cannot have leading zeros.
left and right represent integers in the range [1, 1018].
left is less than or equal to right.
*/
class Solution {
    public int superpalindromesInRange(String left, String right) {
    List<Long> palindromicNo = new ArrayList<>();

        Long leftNo = Long.parseLong(left);
        Long rightNo = Long.parseLong(right);

        int count = 0;

        for(long i = 1;i < 10;i++){
            palindromicNo.add(i);
        }

// as max is 10 ^ 18 i.e, 10 ^ (9 + 9) => (10 ^ (4 + 4) * 10)
        for(long i = 1;i < 10000;i++){
            String leftPart = Long.toString(i); //(10 ^ 4)
            String rightPart = new StringBuilder(leftPart).reverse().toString(); //(10 ^ 4)

            palindromicNo.add(Long.parseLong(leftPart + rightPart)); //(10 ^ 8)

            for(int digit = 0;digit < 10;digit++){
                palindromicNo.add(Long.parseLong(leftPart + digit + rightPart)); //(10 ^ 9)
            }
        }


        for(long no : palindromicNo){
            long squareNo = no * no; 
           
            if(leftNo <= squareNo && rightNo >= squareNo &&  isPalindromic(Long.toString(squareNo))){
                //System.out.print(no);
                count++;
            }
        }
        return count;
    }

    private boolean isPalindromic(String str){
        int start = 0;
        int end = str.length() - 1;
        while(start< end){
            if(str.charAt(start)!= str.charAt(end)){
                return false;
            } else{
                start++;
                end--;
            }
        }
        //System.out.print(str);
        return true;
    }
}