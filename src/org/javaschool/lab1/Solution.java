package org.javaschool.lab1;

import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;

public class Solution {

    public static void main(String[] args) throws Exception {
        Solution solution = new Solution();
        solution.run();
    }

    private void run() throws Exception {
        Scanner scan = new Scanner(System.in);
        PrintWriter writer = new PrintWriter(System.out);
        int n = scan.nextInt();
        int m = scan.nextInt();
        writer.print(m + n);
        writer.close();
    }
}