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

    public boolean isEmpty() {
      if (N == 0) { return true; }
      else { return false; }
    }

    public int size() { return this.N; }

    public String toString() { return ""; }

    public void exchange(Node key1, Node key2) {
      T temp = key1.info;
      key1.info = key2.info;
      key2.info = temp;
    }

    private void sink(Node key) {
      while (!key.left.equals(null)) {
        if ((!key.right.equals(null)) && (key.right.info > key.left.info)) {
          Node greatest = key.right;
        }
        else {
          Node greatest = key.left;
        }
        if (greatest.info > key.info) {
          exchange(greatest, key);
          key = greatest;
          }
        else { break; }
      }
    }

    private void swim(Node key) {
      while (!key.parent.equals(null) && key.info > key.parent.info) {
        exchange(key, key.parent);
        key = key.parent;
      }

    }

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
