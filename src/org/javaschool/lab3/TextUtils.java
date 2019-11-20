package org.javaschool.lab3;

import sun.awt.SunHints;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.Key;
import java.util.*;
import java.util.function.Consumer;

public class TextUtils {

    private final static class ReverseListIterator<E> implements Iterator<E> {

        private List<E> list;
        private Integer currentIndex;

        private ReverseListIterator(List<E> list, Integer currentIndex) throws NoSuchElementException {
            if (list == null || currentIndex < 0 || currentIndex >= list.size()) {
                throw new NoSuchElementException();
            }

            this.list = list;
            this.currentIndex = currentIndex;
        }

        public ReverseListIterator(List<E> list) throws NoSuchElementException {
            this(list, list.size() - 1);
        }

        @Override
        public boolean hasNext() {
            return this.list != null && this.currentIndex >= 0;
        }

        @Override
        public E next() throws NoSuchElementException {
            E currentItem = list.get(this.currentIndex);
            if (this.hasNext()) {
                this.currentIndex--;
                return currentItem;
            } else {
                throw new NoSuchElementException();
            }

        }

        @Override
        public void remove() throws IllegalStateException {
            if (this.list != null && this.currentIndex < this.list.size()) {
                this.list.remove(this.currentIndex);
            } else {
                throw new IllegalStateException();
            }
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            Objects.requireNonNull(action);
            while (this.hasNext()) {
                E currentItem = this.next();
                action.accept(currentItem);
            }

        }
    }

    private final static Comparator<String> customComparator = (String firstString, String secondString) -> {
        int lengthCompare = Integer.compare(firstString.length(), secondString.length());
        if (lengthCompare == 0) {
            return firstString.compareTo(secondString);
        } else {
            return lengthCompare;
        }
    };

    private final static ArrayList<String> strings = new ArrayList<String>();

    static {
        strings.add("Long string");
        strings.add("LongLong string");
        strings.add("short string");
        strings.add("short zz");
        strings.add("just another string");
        strings.add("short string");
        strings.add("LongLong string");
        strings.add("string");
        strings.add("my string");
    }

    public static void main(String[] args) {
        // 0
        System.out.println("-----Before all:-----");
        strings.forEach(System.out::println);

        // 1
        LinkedHashSet<String> uniqueStrings = new LinkedHashSet<String>(strings);
        System.out.println("Count unique : " + uniqueStrings.size());

        // 2
        ArrayList<String> uniqueStringsSorted = new ArrayList<String>(uniqueStrings);
        uniqueStringsSorted.sort(customComparator);
        System.out.println("-----After soft:-----");
        uniqueStringsSorted.forEach(System.out::println);

        // 3
        HashMap<String, Integer> countString = new HashMap<String, Integer>();
        strings.forEach(s -> {
            Integer count = countString.get(s);
            if (count == null) {
                countString.put(s, 1);
            } else {
                countString.replace(s, count + 1);
            }
        });
        System.out.println("-----Counting strings:-----");
        countString.forEach((k, v) -> System.out.println(k + " : " + v));

        // 4
        ArrayList<String> reverseList = new ArrayList<String>(strings);
        Collections.reverse(reverseList);
        System.out.println("-----Reverse list:-----");
        reverseList.forEach(System.out::println);

        // 5
        System.out.println("-----Reverse iterator for list:-----");
        ArrayList<String> customIteratorList = new ArrayList<String>(strings);
        ReverseListIterator<String> reverseIterator = new ReverseListIterator(customIteratorList, 5);
        System.out.println("current position = 5");
        System.out.println("HasNext:" + reverseIterator.hasNext());
        System.out.println("Next:" + reverseIterator.next());
        System.out.println("forEachRemaining:");
        reverseIterator.forEachRemaining(System.out::println);

        // 6
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        try {
            System.out.println("------------------------------------------------------");
            System.out.println("Enter list of element's indexes (whitespace-separated)");
            System.out.print(">");
            st = new StringTokenizer(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (st != null && st.hasMoreTokens()) {

            String token;

            try {
                token = st.nextToken();

                Integer a = Integer.parseInt(token);
                if (a < 0 || a >= strings.size()) {
                    System.out.println(a + " : invalid index");
                } else {
                    System.out.println(a + " : " + strings.get(a));
                }
            } catch (NoSuchElementException e) {
                System.out.println("Конец пользовательского ввода");
            } catch (NumberFormatException e) {
                System.out.println("Не удалось привести к целому числу");
            }
        }
    } //public static void main(String[] args) {
}
