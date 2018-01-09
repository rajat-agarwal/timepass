package mi;

/*
Incomplete solution
 */
public class Given_1_To_9_Digits_Make_Result_100_Using_Any_Mathematical_Operator {
    private static final int MAX_VAL = 1000;
    private static int findMinOperations(int[] operand, int s, int resultSoFar, char[] operators) {
        if (s == operand.length)
            return resultSoFar == 100 ? 0 : MAX_VAL;

        int minOperations = MAX_VAL;
        for (char c : operators) {
            int currResult = resultSoFar;
            switch (c) {
                case '+':
                    currResult += operand[s];
                    break;
                case '-':
                    currResult -= operand[s];
                    break;
                case '*':
                    currResult *= operand[s];
                    break;
                case '/':
                    currResult /= operand[s];
                    break;
                case 0:
                    currResult = currResult * 10 + operand[s];
                    break;
            }
            minOperations = Math.min(minOperations,
                    findMinOperations(operand, s + 1, currResult, operators) + 1);

        }
        return minOperations;
    }

    public static void main(String[] args) {
        char[] operators = {'+', '-', '*', '/', 0}; //ASCII 0 represents no operation, operands will be clubbed
        int[] operand = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        System.out.println(findMinOperations(operand, 1, operand[0], operators));
    }
}
