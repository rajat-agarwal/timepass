package mi;

import java.util.*;

public class Sort_Hotel_Reviews_Booking_com {
    private static class Node implements Comparable<Node> {
        int id;
        int count;

        Node(int id, int count) {
            this.id = id;
            this.count = count;
        }

        @Override
        public int compareTo(Node o) {
            int v = o.count - this.count;
            return v == 0 ? this.id - o.id : v;
        }
    }

    static int[] sort_hotels(String keywords, int[] hotel_ids, String[] reviews) {
        Set<String> keywordsSet = new HashSet<>();
        String[] keyTok = keywords.split(" ", -1);
        for (String s : keyTok)
            keywordsSet.add(s.trim().toLowerCase());

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < reviews.length; i++) {
            String review = reviews[i];
            int s = 0, e = 0;
            int rating = 0;
            while (e < review.length()) {
                if (review.charAt(e) == ' ' || review.charAt(e) == '.' || review.charAt(e) == ',') {
                    String word = review.substring(s, e);
                    s = e = review.charAt(e) == ' ' ? e + 1 : e + 2;
                    word = word.charAt(0) > 'z' ? word.toLowerCase() : word;
                    if (keywordsSet.contains(word))
                        rating++;
                }
                e++;
            }
            if (map.containsKey(hotel_ids[i]))
                map.put(hotel_ids[i], map.get(hotel_ids[i]) + rating);
            else
                map.put(hotel_ids[i], rating);
        }

        Queue<Node> pqueue = new PriorityQueue<>(map.size());
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            pqueue.add(new Node(e.getKey(), e.getValue()));
        }
        int i = 0;
        int[] ret = new int[pqueue.size()];
        while (!pqueue.isEmpty())
            ret[i++] = pqueue.poll().id;

        return ret;
    }

    public static void main(String[] args) {
        String keywords = "breakfast beach citycenter location metro view staff price";
        int[] hotelIds = {1, 2, 1, 1, 2};
        String[] reviews = {
                "This hotel has a nice view of the citycenter. The location is perfect.",
                "The breakfast is ok. Regarding location, it is quite far from citycenter but price is cheap so it is worth.",
                "Location is excellent, 5 minutes from citycenter. There is also a metro station very close to the hotel.",
                "They said I couldn't take my dog and there were other guests with dogs! That is not fair.",
                "Very friendly staff and good cost-benefit ratio. Location is a bit far from citycenter."
        };
        int[] out = sort_hotels(keywords, hotelIds, reviews);
        for (int i : out) {
            System.out.println(i);
        }

    }
}
