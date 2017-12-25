package mi;

public class Compare_Complexities {
    public static void main(String[] args) {
        for (int i=1;i<1000;i++){
            int logn = (int)(Math.log(i)/Math.log(2));
            System.out.println("log: " + logn + " sqrt: " + Math.sqrt(i));
        }
    }
}
