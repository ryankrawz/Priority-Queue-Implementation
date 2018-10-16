/*
Max Priority Queue ADT
Nick Hawk & Ryan Krawczyk
 */

import java.util.*;
import java.lang.*;

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
        if (this.isEmpty()) {
            throw new NoSuchElementException("EMPTY QUEUE");
        }
        else {
            T item = this.first.info;
            this.first.info = this.last.info;
            this.setLast(--this.N);
        }
        return item;
    }

    public void insert(T key) {
        this.setLast(++this.N);
        this.last.info = key;
    }

    public boolean isEmpty() {}

    public int size() { return this.N; }

    public String toString() {}

    private void sink(Node key) {}

    private void swim(Node key) {}

    private int level() { return Math.log(this.N) / Math.log(2); }

    private void setLast(int N) {
        if (N == 1) {
            this.last = new Node(null, /* pointer to parent */, null, null);
        } else {
            
        }
    }

    public static void main(String[] args) {}

}