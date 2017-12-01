package mi;

public class Power_Set_String {
    public static void main(String[] args) {
        String input = "abc";
        generatePowerSet(input);
    }

    private static void printString(int pattern, char[] chars) {
        int i = 0;
        StringBuilder sb = new StringBuilder(chars.length);
        while (pattern > 0) {
            if ((pattern & 1) == 1)
                sb.append(chars[i]);
            pattern = pattern >> 1;
            i++;
        }
        System.out.println(sb.toString());
    }

    private static void generatePowerSet(String in) {
        char[] input = in.toCharArray();
        int end = (int) Math.pow(2, input.length);
        for (int i = 1; i < end; i++) {
            printString(i, input);
        }
    }
}
