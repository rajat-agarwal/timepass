package mi;

import java.util.Scanner;

public class Print_Comments_From_Java_Prog {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean blockCommentStarted = false;

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();

            if (line.startsWith("/*"))
                blockCommentStarted = true;

            if (blockCommentStarted) {
                System.out.println(line);
                if (line.endsWith("*/"))
                    blockCommentStarted = false;
            } else if (line.contains("//")) {
                System.out.println(line.substring(line.indexOf("//")));
            }
        }
    }
}
