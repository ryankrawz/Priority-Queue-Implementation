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

    public T evictMax() {
        if (this.N == 0) { throw new NoSuchElementException("EMPTY QUEUE"); }
        else if (this.N == 1) {
            T item = this.first.info;
            this.first = null;
            this.last = null;
        } else {
            T item = this.first.info;
            this.last.left = this.first.left;
            this.last.right = this.first.right;
            if (this.N % 2 == 0) { this.last.parent.right = null; }
            else { this.last.parent.left = null; }
            this.last.parent = null;
            this.first = this.last;
            if () {
                // This is for the case of a single node remaining on the last level.
            } else {

            }
        }
        this.N--;
        return item;
    }

    public void insert(T key) {}

    public boolean isEmpty() {}

    public int size() {}

    public String toString() {}

    private void exch(Node key1, Node key2) {}

    private void sink(Node key) {}

    private void swim(Node key) {}

    private int level() { return Math.log(this.N) / Math.log(2); }

    public static void main(String[] args) {}

}