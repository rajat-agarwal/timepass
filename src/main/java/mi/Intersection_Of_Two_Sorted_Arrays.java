package mi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Intersection_Of_Two_Sorted_Arrays {
    public static List<Integer> intersect(final List<Integer> a, final List<Integer> b) {
        List<Integer> ret = new ArrayList<>();
        if (a.size() == 0 || b.size() == 0)
            return ret;

        for (int i = 0, j = 0; i < a.size() && j < b.size(); ) {
            if (a.get(i).compareTo(b.get(j)) == 0) {
                ret.add(a.get(i));
                i++;
                j++;
            } else if (a.get(i).compareTo(b.get(j)) > 0) {
                j++;
            } else {
                i++;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        List<Integer> input1 = Arrays.asList(2, 3, 3, 4, 4, 6, 8, 8, 9, 9, 10, 10, 11, 14, 14, 15, 16, 18, 20, 21, 23, 23, 24, 25, 28, 29, 33, 33, 35, 35, 37, 38, 38, 40, 41, 42, 42, 44, 44, 47, 47, 49, 49, 52, 53, 56, 58, 61, 62, 62, 63, 64, 65, 65, 66, 67, 67, 67, 68, 69, 72, 74, 76, 76, 79, 80, 82, 82, 83, 83, 83, 84, 85, 85, 85, 85, 87, 91, 93, 94, 94, 94, 95, 96, 101, 101);
        List<Integer> input2 = Arrays.asList(12, 12, 20, 21, 27, 29);
        System.out.println(intersect(input1, input2));
    }
}
