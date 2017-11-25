package mi;

import com.sun.javafx.binding.SelectBinding;
import mi.utilities.AssortedMethods;

import javax.sound.midi.Soundbank;
import java.util.Arrays;

public class Rippling_Select_Song_Playlist {
    private static int taken(int[][] dp, int i, int duration, int songDuration) {
        if (songDuration < duration && dp[i][duration - songDuration] > 0)
            return 1 + dp[i][duration - songDuration];

        return songDuration == duration ? 1 : 0;
    }

    private static int maximiseSongsDP(int[] songs, int duration) {
        int[][] dp = new int[songs.length + 1][duration + 1];
        for (int i = 1; i <= songs.length; i++) {
            for (int j = 1; j <= duration; j++) {
                dp[i][j] = Math.max(taken(dp, i, j, songs[i - 1]), dp[i - 1][j]);
            }
        }
        return dp[songs.length][duration];
    }

    private static int maximiseSongsDPSpaceOptimised(int[] songs, int duration) {
        int[][] dp = new int[2][duration + 1];
        for (int i = 0; i < songs.length; i++) {
            for (int j = 1; j <= duration; j++) {
                dp[1][j] = Math.max(taken(dp, 1, j, songs[i]), dp[0][j]);
            }
            int[] temp = dp[0];
            dp[0] = dp[1];
            dp[1] = temp;
        }
        return dp[0][duration];
    }

    private static int maximiseSongsDPBestSpaceOptimised(int[] songs, int duration) {
        int[] dp = new int[duration + 1];
        int[] selectedSongs = new int[songs.length];
        for (int i = 0; i < songs.length; i++) {
            for (int j = songs[i]; j <= duration; j++) {

                int taken = songs[i] == j ? 1 : (dp[j - songs[i]] > 0 ? 1 + dp[j - songs[i]] : 0);
                dp[j] = Math.max(taken, dp[j]);

                //enable printing of final selected songs
                if (taken > dp[j])
                    selectedSongs[i]++;
            }
        }
        for (int s : selectedSongs)
            System.out.print(s + " ");

        return dp[duration];
    }

    private static void test1() {
//        int[] input = {5, 2, 2, 5, 3, 2, 1};
        int[] input = {3, 4};
        int duration = 6;
        System.out.println(maximiseSongsDP(input, duration));
        System.out.println(maximiseSongsDPSpaceOptimised(input, duration));
        System.out.println(maximiseSongsDPBestSpaceOptimised(input, duration));
    }

    private static void regression() {
        for (int i = 0; i < 100; i++) {
            int numSongs = AssortedMethods.randomInt(20);
            int songMinDuration = AssortedMethods.randomInt(3) + 1;
            int songMaxDuration = AssortedMethods.randomInt(7);
            int[] input = AssortedMethods.randomArray(numSongs, songMinDuration, songMaxDuration);
            int duration = AssortedMethods.randomInt(numSongs * (songMaxDuration + songMinDuration) / 2);
            for (int in : input)
                System.out.print(in + " ");
            System.out.println("duration: " + duration);

//            int t1 = maximiseSongsDP(input, duration);
//            int t2 = maximiseSongsDPSpaceOptimised(input, duration);
            int t3 = maximiseSongsDPBestSpaceOptimised(input, duration);

//            if (t1 != t2 || t1 != t3) {
//                System.out.println("------panic--------" + "t1=" + t1 + " t2=" + t2 + " t3=" + t3);
//            }
            System.out.println("number of songs picked=" + t3);
        }
    }

    public static void main(String[] args) {
        test1();
//        regression();
    }
}
