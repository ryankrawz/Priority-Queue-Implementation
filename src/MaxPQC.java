/*
Max Priority Queue ADT
Nick Hawk & Ryan Krawczyk
 */

import java.util.*;
import java.lang.*;

public class MaxPQC<T extends Comparable<T>> implements MaxPQ<T> {

    private Node first, last;
    private int N;

    private class Node {
        T info;
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

    /* The algorithm accounts for three cases. If there is no maximum, hence no items,
    to evict, an exception is thrown. If there is only one item remaining, the maximum
    is evicted and all fields of MaxPQ are set to null. Otherwise, the info field of
    this.first is evicted and the info field of this.last is exchanged for it.
     */
    public T evictMax() {
        T item;
        if (this.isEmpty()) {
            throw new NoSuchElementException("EMPTY QUEUE");
        }
        else if (this.size() == 1) {
            item = this.first.info;
            this.first = null;
            this.last = null;
        }
        else {
            item = this.first.info;
            this.first.info = this.last.info;
            this.setLast(this.first, --this.N);
            sink(this.first);
        }
        return item;
    }

    public void insert(T key) {
        this.setLast(this.first, ++this.N);
        this.last.info = key;
        swim(this.last);
    }

    public boolean isEmpty() { return this.N == 0; }

    public int size() { return this.N; }

    public String toString() {
    StringBuilder ans = new StringBuilder();
    ans.append("{" + first.info);
    for (int i = 2; i <= this.size(); i++) {
        ans.append(", " + nodeAt(i).info);
    }
    ans.append("}");
    return ans.toString();
    }

    private void exchange(Node key1, Node key2) {
        T temp = key1.info;
        key1.info = key2.info;
        key2.info = temp;
    }

    private void sink(Node key) {
        while (!key.left.equals(null)) {
            Node greatest;
            if ((!key.right.equals(null)) && (key.right.info > key.left.info)) {
                greatest = key.right;
            }
            else {
                greatest = key.left;
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

    private static int level(int N) {
        int level = (int) Math.round(Math.log(N) / Math.log(2));
        return level;
    }

    /* Recursively walks through the tree by continuously locating the parent node.
    Required for the toString() method.
     */
    private Node nodeAt(int m) {
        if (m == 1) return first;
        if (m % 2 == 0) return nodeAt(m / 2).left;
        else return nodeAt(m / 2).right;
    }

    /* Algorithm for reassigning the this.last pointer. If it is passed this.size() - 1
    in the case of evictMax(), this.last becomes the second to last leaf. If it is passed
    this.size() + 1, this.last becomes a new leaf immediately following the last leaf.
    The algorithm locates the Nth leaf by recursively passing back a left or right child
    based on the number of leaves on the bottom level.
     */
    private T setLast(Node root, int N) {
        int nodeCount = 0;
        for (int i = 0; i < level(N); i++) {
            nodeCount += Math.pow(2, i);
        }
        int bottomLeaves = N - nodeCount;

        if (bottomLeaves == 1) {
            if (root.left.equals(null)) { root.left = new Node(null, root, null, null); }
            this.last = root.left;
        } else if (bottomLeaves == 2) {
            if (root.right.equals(null)) { root.right = new Node(null, root, null, null); }
            this.last = root.right;
        } else {
            if (bottomLeaves <= Math.pow(2, level(N) - 1)) {
                return setLast(root.left, N / 2);
            } else {
                return setLast(root.right, N / 2);
            }
        }
        T fake = root.info;
        return fake;
    }

    public static void main(String[] args) {
        /* Unit Testing */
        MaxPQ<Integer> testPQ = new MaxPQC<Integer>();
        ArrayList<Integer> nums = new ArrayList<Integer>();
        nums.add(3);
        nums.add(114);
        nums.add(56);
        nums.add(94);
        nums.add(169);
        nums.add(8);

        // Inserting
        System.out.format("%n");
        for (int i = 0; i < nums.size(); i++) {
            testPQ.insert(nums.get(i));
            System.out.format("After inserting %d: %s%n", nums.get(i), testPQ.toString());
        }
        System.out.format("Size of priority queue: %d%n%n", testPQ.size());

        // Evicting
        for (int j = 0; j < nums.size(); j++) {
            System.out.format("After evicting %d: %s%n", testPQ.evictMax(), testPQ.toString());
        }
        System.out.format("Size of priority queue: %d%n%n", testPQ.size());
    }

}
