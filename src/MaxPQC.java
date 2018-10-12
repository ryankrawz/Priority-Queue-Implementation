/*
Max Priority Queue ADT
Nick Hawk & Ryan Krawczyk
 */

import java.util.*;

public class MaxPQC<T> implements MaxPQ<T extends Comparable<T>> {

    private Node first, last;
    private int N;

    private class Node {
        T info;
        int place;
        Node parent;
        Node left;
        Node right;
        Node(T info, Node parent, Node left, Node right) {
            this.info = info;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }
    }

    public MaxPQC() {
        this.first = null;
        this.last = null;
        this.N = 0;
    }

    public T evictMax() {}

    public void insert(T key) {}

    public boolean isEmpty() {}

    public int size() {}

    public String toString() {}

    private void exch(Node key1, Node key2) {}

    private void sink(Node key) {}

    private void swim(Node key) {}

    public static void main(String[] args) {}

}