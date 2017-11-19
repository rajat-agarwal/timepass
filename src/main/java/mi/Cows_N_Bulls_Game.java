package mi;
import java.io.Console;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by rajat.agarwal on 16/02/17.
 */
public class Cows_N_Bulls_Game {

    private static String createNumber() {
        String input = "";
        input += (int) (Math.random() * 9 + 1);
        while (input.length() != 4) {
            int r = (int) (Math.random() * 10);
            if (!input.contains(String.valueOf(r))) {
                input += r;
            }
        }
        return input;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enable hidden mode? (y/n)");

        int moves = sc.nextLine().charAt(0) == 'y' ? playHidden() : playNormal();

        System.out.println("Congrats. You have cracked the puzzle in " + moves + "moves");
        sc.close();
    }

    private static int getCowsBulls(String assumedNumber, char[] n) {
        int cows = 0;
        int bulls = 0;
        for (int i = 0; i < n.length; i++) {
            if (assumedNumber.contains(String.valueOf(n[i]))) {
                if (assumedNumber.charAt(i) == n[i]) {
                    bulls++;
                } else {
                    cows++;
                }
            }
        }
        System.out.println(cows + "c  " + bulls + "b\n\n\n\n");
        return bulls;

    }

    private static int playHidden() {
        System.out.println("Hidden mode enabled \n");
        String assumedNumber = createNumber();
        Console console = System.console();
        int moves = 0;

        while (true) {
            moves++;
            if (getCowsBulls(assumedNumber, console.readPassword()) == 4) {
                break;
            }
        }
        return moves;
    }

    private static int playNormal() {
        String assumedNumber = createNumber();
        Scanner sc = new Scanner(System.in);
        int moves = 0;
        while (sc.hasNextLine()) {
            moves++;
            if (getCowsBulls(assumedNumber, sc.nextLine().toCharArray()) == 4) {
                break;
            }
        }
        return moves;
    }
}
