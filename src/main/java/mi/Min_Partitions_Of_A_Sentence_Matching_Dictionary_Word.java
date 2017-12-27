package mi;

import java.util.*;

/*
* Given a sentence without space and a dictionary of word, see if the sentence can be partitioned into a valid sentence of dictionary words.
* Print minimum number of partitions to transform input into valid sentence.
*/
public class Min_Partitions_Of_A_Sentence_Matching_Dictionary_Word {
    private static void minPartitionsLength(String input, Set<String> dictionary) {
        int[] dp = new int[input.length() + 1];
        List<Integer> partitions = new ArrayList<>(); //store partition indexes
        partitions.add(input.length()); //base case

        for (int i = input.length() - 1; i >= 0; i--) {
            int minPartitions = Integer.MAX_VALUE;
            for (int j : partitions) {
                if (dictionary.contains(input.substring(i, j))) {
                    minPartitions = Math.min(minPartitions, 1 + dp[j]);
                }
            }
            if (minPartitions != Integer.MAX_VALUE) {
                partitions.add(i);
                dp[i] = minPartitions;
            }
        }

        printBuffers(dp, partitions);
        System.out.println("Is input partitionable:" + (dp[0] > 0));
    }

    private static void printBuffers(int[] dp, List<Integer> partitions){
        System.out.print("dp buffer is ");
        for (int i : dp)
            System.out.print(i + " ");
        System.out.println("partitions are " + partitions);
    }

    public static void main(String[] args) {
        Set<String> dictionary = new HashSet<>(Arrays.asList("i", "like", "to", "il", "iketo"));
        String word = "iliketo";
        minPartitionsLength(word, dictionary);
    }
}
