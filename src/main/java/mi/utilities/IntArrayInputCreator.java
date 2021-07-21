package mi.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class IntArrayInputCreator {
    public static ArrayList<int[]> createIntArrays() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("D:/Git/rAjAT/timepass/InputIntArrays.txt"));
        ArrayList<int[]> inputArrlist = new ArrayList();
        while (sc.hasNextLine()){
            String[] tok = sc.nextLine().trim().split(",", -1);
            int[] inputarr = new int[tok.length];
            inputArrlist.add(inputarr);
            for (int i=0;i<tok.length;i++){
                inputarr[i] = Integer.parseInt(tok[i].trim());
            }
        }
        return inputArrlist;
    }
}
