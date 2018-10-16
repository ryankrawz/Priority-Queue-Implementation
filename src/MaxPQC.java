/*
Max Priority Queue ADT
Nick Hawk & Ryan Krawczyk
 */

import java.util.*;
import java.lang.*;

public class MaxPQC<T> implements MaxPQ<T> {

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
        T item;
        if (this.isEmpty()) {
            throw new NoSuchElementException("EMPTY QUEUE");
        } else if (this.size() == 1) {
            item = this.first.info;
            this.first = null;
            this.last = null;
        }
        else {
            item = this.first.info;
            this.first.info = this.last.info;
            this.setLast(this.first, --this.N, 0);
        }
        return item;
    }

    public void insert(T key) {
        this.setLast(this.first, ++this.N, 1);
        this.last.info = key;
    }

    public boolean isEmpty() { return true; }

    public int size() { return this.N; }

    public String toString() { return ""; }

    private void sink(Node key) {}

    private void swim(Node key) {}

    private static double level(int N) { return Math.log(N) / Math.log(2); }

    private T setLast(Node root, int N, int tell) {
        int nodeCount = 0;
        for (int i = 0; i < level(N); i++) {
            nodeCount += Math.pow(2, i);
        }
        int bottomLeaves = N - nodeCount;

        if (bottomLeaves == 1) {
            if (tell == 1) { root.left = new Node(null, root, null, null); }
            this.last = root.left;
        } else if (bottomLeaves == 2) {
            if (tell == 1) { root.right = new Node(null, root, null, null); }
            this.last = root.right;
        } else {
            if (bottomLeaves <= Math.pow(2, level(N) - 1)) {
                return setLast(root.left, N / 2, tell);
            } else {
                return setLast(root.right, N / 2, tell);
            }
        }
        T fake = root.info;
        return fake;
    }

    public static void main(String[] args) {}

}