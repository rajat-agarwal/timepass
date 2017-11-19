package mi;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by rajat.agarwal on 13/07/17.
 */

// https://gist.github.com/thoroc/75a8555fca480345c5c4
public class Find_First_Common_Manager {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine().trim());
        String firstE = sc.nextLine().trim();
        String secondE = sc.nextLine().trim();
        Map<String, String> reporteeChain = new HashMap<>();

        for (int i = 2; i <= N; i++) {
            String[] tok = sc.nextLine().trim().split(" ");
            reporteeChain.put(tok[1], tok[0]);
        }

        Set<String> managersEmployee1 = new HashSet<>();
        do {
            managersEmployee1.add(firstE);
        } while ((firstE = reporteeChain.get(firstE)) != null);

        while (!managersEmployee1.contains(secondE)) {
            secondE = reporteeChain.get(secondE);
        }
        System.out.println(secondE);
    }
}
