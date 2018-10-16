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

    public boolean isEmpty() {
      if (N == 0) { return true; }
      else { return false; }
    }

    public int size() { return this.N; }

    public String toString() {}

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

    private int level() { return Math.log(this.N) / Math.log(2); }

    private void setLast(int N) {
        if (N == 1) {
            this.last = new Node(null, /* pointer to parent */, null, null);
        } else {

        }
    }

    public static void main(String[] args) {}

}
