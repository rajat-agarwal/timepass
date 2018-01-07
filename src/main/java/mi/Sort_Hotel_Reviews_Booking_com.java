package mi;

import java.util.*;

public class Sort_Hotel_Reviews_Booking_com {
    private static Set<String> getKeywordDictionary(String keywords) {
        Set<String> dictionary = new HashSet<>();
        String[] tok = keywords.split(" ", -1);
        for (String s : tok)
            dictionary.add(s.trim().toLowerCase());
        return dictionary;
    }

    private static Map<Integer, Integer> getHotelsByPopularityCount(Set<String> dictionary, int[] hotel_ids, String[] reviews) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int i = 0; i < reviews.length; i++) {
            int hotelId = hotel_ids[i];
            int popularity = counts.containsKey(hotelId) ? counts.get(hotelId) : 0;
            String[] reviewWords = reviews[i].split(" |\\.|,", -1);
            for (String w : reviewWords) {
                if (dictionary.contains(w.trim().toLowerCase()))
                    popularity++;
            }
            if (popularity > 0)
                counts.put(hotelId, popularity);
        }
        return counts;
    }

    static int[] sort_hotels(String keywords, int[] hotel_ids, String[] reviews) {
        Set<String> keywordDictionary = getKeywordDictionary(keywords);
        Map<Integer, Integer> popularityCounts = getHotelsByPopularityCount(keywordDictionary, hotel_ids, reviews);
        Map<Integer, Set<Integer>> treemap = new TreeMap<>();
        int counts = 0;
        for (Map.Entry<Integer, Integer> e : popularityCounts.entrySet()){
            if (!treemap.containsKey(e.getValue())){
                treemap.put(e.getValue(), new TreeSet<>());
            }
            treemap.get(e.getValue()).add(e.getKey());
            counts++;
        }

        int[] ret = new int[counts];
        int i = 0;
        for (Set<Integer> e : treemap.values()) {
            for (int v : e)
                ret[i++] = v;
        }
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
