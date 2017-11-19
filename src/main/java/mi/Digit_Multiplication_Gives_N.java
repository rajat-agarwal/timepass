package mi;

import java.util.Scanner;

/*
Given a positive integer N, find the smallest number S such that the product of all the digits of S is equal to the number N.
http://practice.geeksforgeeks.org/problems/digit-multiplier/0

Input
2
100
26

Output
455
-1

*/

public class Digit_Multiplication_Gives_N {
    private static String findLower(int input){
        if(input == 1){
            return  "1";
        }
        int n = 9;
        String result = "-1";
        StringBuilder sb = new StringBuilder();
        while(input > 1 && n > 1){
            if(input % n == 0){
                sb.append(n);
                input = input/n;
            }else{
                n--;
            }
        }
        if(input == 1){
            result = sb.reverse().toString();
        }
        return result;
    }
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            System.out.println(findLower(sc.nextInt()));
        }
    }
}
