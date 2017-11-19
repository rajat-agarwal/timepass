package mi;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by rajat.agarwal on 12/03/17.
 */

/*
5 2 2 4
1 2 1
1 3 1
2 4 1
1 5 2
2 6 2
 */
public class Lift_Optimised {
    private static int getMaxFloor(int floor, int dFloor) {
        return dFloor < floor ? floor : dFloor;
    }

    public static void main(String[] args) {

        class Data {
            int arrivalTime, floor;

            public Data(int arrivalTime, int floor) {
                this.arrivalTime = arrivalTime;
                this.floor = floor;
            }
        }

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int w = in.nextInt();
        int c = in.nextInt();
        int m = in.nextInt();

        int lastTripNo = 0, totalTIme = 0, nextTakeOffTime = 0, currCapacity = 0;
        int maxFloor = 0, roryPosInQueue = 0, roryTripNo = 0;

        List<Data> teacherQueue = new LinkedList<>();
        List<Data> studentQueue = new LinkedList<>();

        while (n-- > 0) {
            char s_t = in.nextInt() == 1 ? 'S' : 'T';
            Data d = new Data(in.nextInt(), in.nextInt());
            nextTakeOffTime = nextTakeOffTime == 0 ? d.arrivalTime + w : nextTakeOffTime;
            m--;
            if (s_t == 'T') {
                teacherQueue.add(d);
            } else {
                studentQueue.add(d);
                roryPosInQueue = m == 0 ? studentQueue.size() : roryPosInQueue;
            }
        }

        while (!teacherQueue.isEmpty() || !studentQueue.isEmpty()) {
            int teacherMinTime = 0;
            while (currCapacity < c && !teacherQueue.isEmpty() && teacherMinTime <= nextTakeOffTime) {
                Data d = teacherQueue.get(0);
                teacherMinTime = d.arrivalTime;
                if (d.arrivalTime <= nextTakeOffTime) {
                    currCapacity++;
                    maxFloor = getMaxFloor(teacherQueue.remove(0).floor, maxFloor);
                }
            }
            int studentMinTime = 0;
            while (currCapacity < c && !studentQueue.isEmpty() && studentMinTime <= nextTakeOffTime) {
                Data d = studentQueue.get(0);
                studentMinTime = d.arrivalTime;
                if (d.arrivalTime <= nextTakeOffTime) {
                    currCapacity++;
                    maxFloor = getMaxFloor(studentQueue.remove(0).floor, maxFloor);
                    roryPosInQueue--;
                    if (roryPosInQueue == 0) {
                        roryTripNo = lastTripNo + 1;
                    }
                }
            }

            totalTIme = nextTakeOffTime + maxFloor;
            teacherMinTime = !teacherQueue.isEmpty() ? teacherQueue.get(0).arrivalTime : Integer.MAX_VALUE;
            studentMinTime = !studentQueue.isEmpty() ? studentQueue.get(0).arrivalTime : Integer.MAX_VALUE;
            int nextStartTime = Math.min(teacherMinTime, studentMinTime);
            nextTakeOffTime += 2 * maxFloor;
            nextTakeOffTime = nextTakeOffTime < nextStartTime ? nextStartTime + w : nextTakeOffTime + w;

            maxFloor = 0;
            currCapacity = 0;
            lastTripNo++;
        }

        System.out.println(roryTripNo + " " + totalTIme);
    }
}
