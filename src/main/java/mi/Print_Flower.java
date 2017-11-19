package mi;
/**
 * Created by rajat.agarwal on 12/03/17.
 */
public class Print_Flower {

    private static void addPetals(String petal, int times, StringBuilder sb) {
        while (times-- > 0) {
            sb.append(petal);
        }
        sb.append("\n");
    }

    public static void main(String[] args) {
        String fedge = "..0..";
        String fmid = "0.o.0";
        int row = 2;
        int col = 2;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < row; i++) {
            addPetals(fedge, col, sb);
            addPetals(fmid, col, sb);
            addPetals(fedge, col, sb);
        }

        System.out.println(sb.toString());

    }
}
