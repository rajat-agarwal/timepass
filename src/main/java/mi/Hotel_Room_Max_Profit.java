package mi;
import java.util.Scanner;

/**
 * Created by rajat.agarwal on 04/02/17.
 */
public class Hotel_Room_Max_Profit {
    static int BOOKING_START_TIME = 5;
    /* sample input
    8
    6AM#8AM
    11AM#1PM
    7AM#3PM
    7AM#10AM
    10AM#12PM
    2PM#4PM
    1PM#4PM
    8AM#9AM

    4
    6AM#8AM
    7AM#8AM
    8AM#12PM
    11AM#1PM
    */

    private static int getNormalizedTime(String in) {
        int time = Integer.parseInt(in.substring(0, in.length() - 2));
        String unit = in.substring(in.length() - 2);
        if (unit.equalsIgnoreCase("PM") && time != 12) {
            time += 12;
        }
        return time;
    }

    private static int profitValue(String[] input1) {
        int[] checkoutTime = new int[25];
        for (String input : input1) {
            String[] in = input.split("#");

            int inTime = getNormalizedTime(in[0]);
            int outTime = getNormalizedTime(in[1]);

            // If multiple requests for same check-in time is available then select one which has earliest checkout time.
            if (checkoutTime[inTime] == 0 || checkoutTime[inTime] > outTime) {
                checkoutTime[inTime] = outTime;
            }
        }

        int i = BOOKING_START_TIME;
        int checkoutTimeSoFar = 0;
        do {
            checkoutTimeSoFar = checkoutTime[i];
        } while (checkoutTime[i++] == 0);

        int requestBooked = 0;
        for (; i < checkoutTime.length; i++) {
            if (checkoutTime[i] != 0) {

                if (checkoutTimeSoFar < checkoutTime[i]) {
                    if (checkoutTimeSoFar <= i) {
                        checkoutTimeSoFar = checkoutTime[i];
                        requestBooked++;
                    }
                } else {
                    checkoutTimeSoFar = checkoutTime[i];
                }
            }
        }

        requestBooked++;
        return requestBooked * 500;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int requests = scanner.nextInt();
        String[] input = new String[requests];
        for (int i = 0; i < requests; i++) {
            input[i] = scanner.next();
        }
        System.out.println(profitValue(input));
    }
}
