package comp2402a4;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class SlowSparrow implements RevengeOfSparrow {
  List<Integer> ds;

  public SlowSparrow() {
    ds = new ArrayList<Integer>();
  }

  public void push(int x) {
      ds.add(x);
  }

  public Integer pop() {
    if(ds.size() == 0)
      return null;
    return ds.remove(ds.size()-1);
  }


  public Integer get(int i) {
    if(i < 0 || i >= ds.size())
      return null;
    return ds.get(i);
  }

  public Integer set(int i, int x) {
    if(i < 0 || i >= ds.size())
      return null;
    return ds.set(i, x);
  }

  public Integer max() {
    Integer m = null;
    for (int x : ds) {
      if (m == null || x > m) {
        m = x;
      }
    }
    return m;
  }

  public long ksum(int k) {
    long sum = 0;
    for(int i=0; i<k && i<ds.size(); i++)
      sum += ds.get(ds.size() - 1 - i);
    return sum;
  }

  public int size() {
    return ds.size();
  }

  public Iterator<Integer> iterator() {
    return ds.iterator();
  }
}
