public interface MaxPQ<Key extends Comparable<Key>> {
  Key delMax();
  void insert(Key key);
  boolean isEmpty();
  int size();
  String toString();
}
