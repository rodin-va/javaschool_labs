package org.javaschool.lab1;

import java.util.*;
import java.io.*;

public class Solution {
    public static void main(String[] argv) throws IOException {
        ArrayList<Integer> m = new ArrayList<Integer>();

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int a = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < a; i++) {
            Integer b = Integer.parseInt(st.nextToken());
            m.add(b);
        }

        for (int j = 0; j < 2; j++) {
            Integer max = m.get(0);
            for (int i = 0; i < a; i++) {
                if (max.compareTo(m.get(i)) < 0) {
                    max = (Integer) m.get(i);
                }
            }

            for (int i = 0; i < a; i++) {
                if (max.equals(m.get(i)))
                    m.set(i, m.get(i) / 2);
            }
        }
        for (int i = 0; i < a; i++) {
            System.out.print(m.get(i));
            System.out.print(" ");
        }
    }

    private static void task2001() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = a + b;

        System.out.println(c);
    }

    private static void task2002() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int a = Integer.parseInt(st.nextToken());
        int s = 0;
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < a; i++) {
            int b = Integer.parseInt(st.nextToken());
            s += b;
        }

        System.out.println(s);
    }

    private static void task2003() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int a = Integer.parseInt(st.nextToken());
        int s = 0;
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < a; i++) {
            int b = Integer.parseInt(st.nextToken());
            s += b * (i % 2 == 0 ? 1 : -1);
        }

        System.out.println(s);
    }

    private static void task2021() throws IOException {
        ArrayList<Integer> m = new ArrayList<Integer>();

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int a = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < a; i++) {
            Integer b = Integer.parseInt(st.nextToken());
            m.add(b);
        }

        for (int j = 0; j < 2; j++) {
            Integer max = m.get(0);
            for (int i = 0; i < a; i++) {
                if (max.compareTo(m.get(i)) < 0) {
                    max = (Integer) m.get(i);
                }
            }

            for (int i = 0; i < a; i++) {
                if (max.equals(m.get(i)))
                    m.set(i, m.get(i) / 2);
            }
        }
        for (int i = 0; i < a; i++) {
            System.out.print(m.get(i));
            System.out.print(" ");
        }
    }
}