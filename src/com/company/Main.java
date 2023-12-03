package com.company;


public class Main {

    public static void main(String[] args) {
        // write your code here
        Solution solution = new Solution();
        System.out.println(solution.solution1(6, 1, 1));
        System.out.println(solution.solution2(new int[]{1, 2, 1, 2, 5, 1}));
    }

}

class Solution {

    public String solution1(int A, int B, int C) {
        // Time complexity: O(n) where n = A+B+C
        // Space complexity: O(n) to store n characters

        StringBuilder sb = new StringBuilder();

        while(true) {
            char lastChar = 'd';
            if(sb.length() >= 1)
                lastChar = sb.charAt(sb.length() - 1);

            if(A == B && B == C && A > 0) {
                sb.append('a');
                A--;
            } else if(A > B && A > C && A > 0 && lastChar != 'a') {
                if((A - B >= 2 || A - C >= 2) && A >= 2) {
                    sb.append("aa");
                    A -= 2;
                } else {
                    sb.append('a');
                    A--;
                }
            } else if(B > A && B > C && B > 0 && lastChar != 'b') {
                if((B - A >= 2 || B - C >= 2) && B >= 2) {
                    sb.append("bb");
                    B -= 2;
                } else {
                    sb.append("b");
                    B--;
                }
            } else if(C > A && C > B && C > 0 && lastChar != 'c') {
                if((C - A >= 2 || C - B >= 2) && C >= 2) {
                    sb.append("cc");
                    C -= 2;
                } else {
                    sb.append('c');
                    C--;
                }
            } else if ((A >= C || A >= B) && A > 0 && lastChar != 'a') {
                sb.append('a');
                A--;
            } else if((B >= A || B >= C) && B > 0 && lastChar != 'b') {
                sb.append("b");
                B--;
            } else if((C >= A || C >= B) && C > 0 && lastChar != 'c') {
                sb.append('c');
                C--;
            } else {
                break;
            }
        }

        return sb.toString();
    }

    public int solution2(int[] A) {
        // write your code in Java SE 8
        int answer = 0;

        // Check is already aesthetically pleasing
        if(checkIsPleasing(A))
            return 0;

        // Remove each element and check is pleasing
        for(int i = 0; i < A.length; i++) {
            // Skip if left and right of to remove element has the same value
            if(i > 0 && i < A.length - 1)
                if(A[i-1] == A[i+1])
                    continue;

            // Increment answer if it's pleasing
            if(checkIsPleasing(cloneArray(A, i))) {
                answer++;
            }
        }

        // Return -1 if no answer
        if(answer == 0)
            return -1;

        return answer;
    }

    // Clone a given array excluding a given index
    public int[] cloneArray(int[] A, int except) {
        int[] ans = new int[A.length - 1];

        for(int i = 0, j = 0; i < A.length; i++, j++) {
            if(i == except) {
                i++;

                if(i >= A.length)
                    break;
            }

            ans[j] = A[i];
        }

        return ans;
    }

    public boolean checkIsPleasing(int[] A) {
        int prev = A[1] - A[0];

        for(int i = 2; i < A.length; i++) {
            if((prev > 0 && A[i] - A[i-1] > 0) || (prev < 0 && A[i] - A[i-1] < 0))
                return false;

            prev = A[i] - A[i-1];
        }

        return true;
    }
}
