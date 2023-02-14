package comp2402a4;

import java.util.Iterator;

public interface RevengeOfSparrow extends Iterable<Integer> {
  public void push(int x);
  public Integer pop();
  public Integer get(int i);
  public Integer set(int i, int x);
  public Integer max();
  public long ksum(int k);
  public int size();
  public Iterator<Integer> iterator();
}
