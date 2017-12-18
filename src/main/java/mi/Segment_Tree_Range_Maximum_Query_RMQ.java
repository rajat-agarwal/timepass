package mi;

public class Segment_Tree_Range_Maximum_Query_RMQ {

    // Program for range minimum query using segment tree
    static int[] st; //array to store segment tree

    // A utility function to get the middle index from corner
    // indexes.
    private static int getMid(int s, int e) {
        return s + (e - s) / 2;
    }

    /*  A recursive function to get the minimum value in a given
        range of array indexes. The following are parameters for
        this function.

        st    --> Pointer to segment tree
        index --> Index of current node in the segment tree. Initially
                   0 is passed as root is always at index 0
        ss & se  --> Starting and ending indexes of the segment
                     represented by current node, i.e., st[index]
        qs & qe  --> Starting and ending indexes of query range */
    private static int RMQUtil(int ss, int se, int qs, int qe, int index) {
        // If segment of this node is a part of given range, then
        // return the min of the segment
        if (qs <= ss && qe >= se)
            return st[index];

        // If segment of this node is outside the given range
        if (se < qs || ss > qe)
            return Integer.MAX_VALUE;

        // If a part of this segment overlaps with the given range
        int mid = getMid(ss, se);
        return Math.min(RMQUtil(ss, mid, qs, qe, 2 * index + 1),
                RMQUtil(mid + 1, se, qs, qe, 2 * index + 2));
    }

    // Return minimum of elements in range from index qs (quey
    // start) to qe (query end).  It mainly uses RMQUtil()
    private static int RMQ(int n, int qs, int qe) {
        // Check for erroneous input values
        if (qs < 0 || qe > n - 1 || qs > qe) {
            System.out.println("Invalid Input");
            return -1;
        }

        return RMQUtil(0, n - 1, qs, qe, 0);
    }

    // A recursive function that constructs Segment Tree for
    // array[ss..se]. si is index of current node in segment tree st
    private static int constructSTUtil(int arr[], int ss, int se, int si) {
        // If there is one element in array, store it in current
        //  node of segment tree and return
        if (ss == se) {
            st[si] = arr[ss];
            return arr[ss];
        }

        // If there are more than one elements, then recur for left and
        // right subtrees and store the minimum of two values in this node
        int mid = getMid(ss, se);
        st[si] = Math.min(constructSTUtil(arr, ss, mid, si * 2 + 1),
                constructSTUtil(arr, mid + 1, se, si * 2 + 2));
        return st[si];
    }

    /* Function to construct segment tree from given array. This function
       allocates memory for segment tree and calls constructSTUtil() to
       fill the allocated memory */
    private static void constructST(int arr[], int n) {
        // Allocate memory for segment tree

        //Height of segment tree
        int x = (int) (Math.ceil(Math.log(n) / Math.log(2)));

        //Maximum size of segment tree
        int max_size = 2 * (int) Math.pow(2, x) - 1;
        st = new int[max_size]; // allocate memory

        // Fill the allocated memory st
        constructSTUtil(arr, 0, n - 1, 0);
    }

//    private static void checkHeightVsNaturalSizeTest(){
//        for (int i=1;i<100;i++){
//            int d = (int)Math.ceil(Math.log(i)/Math.log(2));
//            int s = 2 * (int)Math.pow(2,d) -1;
//            System.out.println(s + " for i=" + i);
//        }
//    }
    // Driver program to test above functions
    public static void main(String args[]) {
        int arr[] = {1, 3, 2, 7, 9, 11};
//        int arr[] = {1, 3, 2};
        int n = arr.length;

//        checkHeightVsNaturalSizeTest();
        // Build segment tree from given array
        constructST(arr, n);

        int qs = 0;  // Starting index of query range
        int qe = 1;  // Ending index of query range

        for (int i : st)
            System.out.print(i + " ");

        // Print minimum value in arr[qs..qe]
        System.out.println("Minimum of values in range [" + qs + ", "
                + qe + "] is = " + RMQ(n, qs, qe));
    }
}
