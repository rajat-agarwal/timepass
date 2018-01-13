package mi;

/*
https://www.geeksforgeeks.org/dynamic-programming-set-37-boolean-parenthesization-problem/
A boolean expression is given. Find number of ways to parenthesise the expression such that it evaluates to true.
Solution:
Let T(i, j) represents the number of ways to parenthesize the symbols between i and j (both inclusive)
such that the subexpression between i and j evaluates to true.

Let F(i, j) represents the number of ways to parenthesize the symbols between i and j (both inclusive)
such that the subexpression between i and j evaluates to false.

Base Cases:
T(i, i) = 1 if symbol[i] = 'T'
T(i, i) = 0 if symbol[i] = 'F'
F(i, i) = 1 if symbol[i] = 'F'
F(i, i) = 0 if symbol[i] = 'T'
 */
public class Num_Ways_To_Parenthesize_Boolean_Expression_To_Evaluate_True {
    private static int countPermutations(char[] symb, char[] oper, int n) {
        int[][] F = new int[n][n];
        int[][] T = new int[n][n];

        // All diagonal entries in T[i][i] are 1 if symbol[i]
        // is T (true).  Similarly, all F[i][i] entries are 1 if
        // symbol[i] is F (False)
        for (int i = 0; i < n; i++) {
            F[i][i] = (symb[i] == 'F') ? 1 : 0;
            T[i][i] = (symb[i] == 'T') ? 1 : 0;
        }

        for (int len = 2; len <= n; ++len) {
            for (int i = 0, j = len - 1; j < n; ++i, ++j) {
                for (int k = i; k < j; k++) {

//                    int tik = T[i][k] + F[i][k];
//                    int tkj = T[k + 1][j] + F[k + 1][j];

                    // Follow the recursive formulas according to the current
                    // operator
                    if (oper[k] == '&') {
                        T[i][j] += T[i][k] * T[k + 1][j];
                        F[i][j] += F[i][k] * F[k + 1][j] +
                                F[i][k] * T[k + 1][j] +
                                T[i][k] * F[k + 1][j];
                    }
                    if (oper[k] == '|') {
                        T[i][j] += F[i][k] * T[k + 1][j] +
                                T[i][k] * T[k + 1][j] +
                                T[i][k] * F[k + 1][j];
                        F[i][j] += F[i][k] * F[k + 1][j];
                    }
                    if (oper[k] == '^') {
                        T[i][j] += F[i][k] * T[k + 1][j] + T[i][k] * F[k + 1][j];
                        F[i][j] += T[i][k] * T[k + 1][j] + F[i][k] * F[k + 1][j];
                    }
                }
            }
        }
        return T[0][n - 1];
    }

    public static void main(String[] args) {
        String symbols = "TTFT";
        String operators = "|&^";

        System.out.println(countPermutations(symbols.toCharArray(), operators.toCharArray(), symbols.length()));
    }
}
