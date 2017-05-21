import java.math.*;
import javax.naming.CommunicationException;

public class Complexity {
    
    /**
     * Problem 1
     * @param arr: an array of doubles
     * @param eps: a double value
     * @param d1 : a double value
     * @return   : true if there is a element d2 in the arr such that 
     *             |d1 - d2| < eps 
     */
    public static boolean contains(double[] arr, double eps, double d1) {
        for (int i = 0; i < arr.length; i++) {
            if (Math.abs(d1 - arr[i]) < eps) {
                return true;
            }
        }
        return false;
    }

    /**
     * Problem 2
     * @param x: an integer
     * @param y: an integer
     * @return : x^y
     */
    public static double fastExp(int x, int y) {
        if (y == 0) {
            return 1.0;
        } else if (y == -1) {
          return 1.0 / x;
        } else if (y % 2 == 0) {
            return fastExp(x * x, y / 2);
        } else {
            return x * fastExp(x * x, (y - 1) / 2);
        }
    }

    /**
     * Problem 3
     * @param arr: an integer array
     * @return   : a new array of type Pair of all possible pairs of 
     *             elements from the input array
     */
    public static Pair[] allPairs(int[] arr) {
        if(arr == null) {
            throw new IllegalArgumentException();
        } else {
            Pair[] resultArr = new Pair[arr.length * arr.length];
            int k = 0;
            for(int i = 0; i < arr.length; i++) {
                for(int j = 0; j < arr.length; j++) {
                    resultArr[k] = new Pair(arr[i], arr[j]);
                    k++;
                }
            }
            return resultArr;
        }
    }

    /**
     * Problem 4
     * @param arr: an array of strings
     * @param n  : a positive integer
     * @return   : a single string that is the result of replicating
     *             the strings in the array n times and then concatenating
     *             them all together
     */
    public static String concatAndReplicateAll(String[] arr, int n) {
        if(arr == null) {
            throw new IllegalArgumentException("test");
        } else{
            String str = "";
            for(int i = 0; i < arr.length; i++) {
                for(int j = 0; j < n; j++) {
                    str += arr[i];
                }
            }
            return str;
        }
    }

    /**
     * Problem 5
     * @param arr1: an array of integers
     * @param arr2: an array of integers
     * @return    : a third array that is the result of interleaving the
     *              first array with the second array
     */
    public static int[] interleave(int[] arr1, int[] arr2) {
        int[] resultArr = new int[arr1.length + arr2.length];
        if(arr1.length > arr2.length){
            int lenDiff = arr1.length - arr2.length;
            for(int i = 0; i < arr2.length; i++) {
                resultArr[2 * i] = arr1[i];
            }
            for(int i = 0; i < arr2.length; i++) {
                resultArr[2 * i + 1] = arr2[i];
            }
            for(int i = 0; i < lenDiff; i++) {
                resultArr[2 * arr2.length + i] = arr1[arr2.length + i];
            }
        } else if (arr1.length == arr2.length){
            for(int i = 0; i < arr1.length; i++) {
                resultArr[2 * i] = arr1[i];
            }
            for(int i = 0; i < arr2.length; i++) {
                resultArr[2 * i + 1] = arr2[i];
            }
        } else {
            int lenDiff = arr2.length - arr1.length;
            for(int i = 0; i < arr1.length; i++) {
                resultArr[2 * i] = arr1[i];
            }
            for(int i = 0; i < arr1.length; i++) {
                resultArr[2 * i + 1] = arr2[i];
            }
            for(int i = 0; i < lenDiff; i++) {
                resultArr[2 * arr1.length + i] = arr2[arr1.length + i];
            }
        }
        return resultArr;
    }

    public static void main(String[] args) {
        double[] arr1 = {0.1, 0.2, 0.3};
        System.out.println(contains(arr1, 0.05, 0.12));
        System.out.println(fastExp(2, -2));
        int[] arr = { 3, 5, 9};
        Pair[] resultArr = allPairs(arr);
        for(int i = 0; i < resultArr.length; i++) {
             System.out.print(resultArr[i].getFst());
             System.out.print(resultArr[i].getSnd());
             System.out.println("");
        }
        String[] arr2 = {"Hello", "World", "!"};
        System.out.println(concatAndReplicateAll(arr2, 3));
        int[] test1 = {0, 1, 2};
        int[] test2 = {3, 4, 5, 6, 7, 8};
        for(int i = 0; i < 9; i++) {
            System.out.println(interleave(test1, test2)[i]);
        }
    }
}
