import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        var perte = 0;
        var max = -1;
        for (int i = 0; i < n; i++) {
            int v = in.nextInt();
            if(v>max){
                max = v;
            }
            if(v-max<perte){
                perte = v - max;
            }
        }

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(perte);
    }
}