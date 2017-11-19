package mi;

public class Three_Stacks_In_One_Array {
    public static void main(String[] args) {
        continuousAllocation(10);
    }

    private static void continuousAllocation(int size) {
        int[] buffer = new int[size];
        int topA = -1, bottomB = -1, topB = -1, topC = size;

        add('A', 1, buffer, topA, bottomB, topB, topC);
    }

    private static void add(char stack, int data, int[] buffer, int topA, int bottomB, int topB, int topC) {
        switch (stack) {
            case 'A':
                int rightLimit = topB > bottomB ? bottomB - Math.abs(topB - bottomB) - 1 : topB;
                if (rightLimit == -1) {
                    rightLimit = topC;
                }
                if (topA < rightLimit - 1) {
                    buffer[++topA] = data;
                }
                break;

            case 'B':
                int stretch = Math.abs(topB - bottomB);
                int newPos = Math.abs(topB - bottomB);
                if (topB == -1) {
                    newPos = (topC - topA) / 2 > 0 ? topA + (topC - topA) / 2 : -1;
                }
                break;

            case 'C':
                int leftLimit = topB < bottomB ? bottomB + Math.abs(bottomB - topB) : topB;
                if (leftLimit == -1) {
                    leftLimit = topA;
                }
                if (topC > leftLimit + 1) {
                    buffer[--topC] = data;
                }
                break;
        }
    }
}
