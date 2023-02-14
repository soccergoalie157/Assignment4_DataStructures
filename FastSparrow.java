package comp2402a4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FastSparrow implements RevengeOfSparrow {
  // TODO: Your data structures go here

  class both {
    public int max;
    public long sum;
  }
  public ArrayList<ArrayList<both>> nodes;
  int totalSize = 0;
  public FastSparrow() {
    // TODO: Your code goes here
    nodes = new ArrayList<ArrayList<both>>();
  }

  
  public void push(int x) {
    // TODO: Your code goes here
    ArrayList<both> temp = new ArrayList<both>();
    both t = new both();
    if (size() == 0) {
      t.max = x;
      t.sum = x;
      temp.add(t);
      nodes.add(temp);
    } 
    else {
      if ((1 << nodes.size()-1) == size()) {
        for (int i = 0; i < nodes.size(); i++) {
          t = new both();
          t.max = x;
          t.sum = x;
          nodes.get(i).add(t);
          for (int y = nodes.get(i).size(); y < (1 << (nodes.size()-i)); y++) {
            t = new both();
            t.max = 0;
            t.sum = 0;
            nodes.get(i).add(t);
          }
        }
        t = new both();
        both slast = nodes.get(nodes.size()-1).get(0);
        both last = nodes.get(nodes.size()-1).get(1);
        t.sum = slast.sum + last.sum;
        if (slast.max < last.max) {
          t.max = last.max;
        } else {
          t.max = slast.max;
        }
        temp = new ArrayList<both>();
        temp.add(t);
        nodes.add(temp);
      }
      else {
        both b = nodes.get(0).get(size());
        b.sum = x;
        b.max = x;
        int size = size()+1;
        if (size % 2 != 0) {
          size++;
        }
        for (int i = 1; i < nodes.size(); i++) {
          if (size == nodes.get(0).size()) {
            b = nodes.get(i).get(nodes.get(i).size()-1);
            b.sum += x;
            if (b.max < x) {
              b.max = x;
            }
          }
          else {
            int index = (int)Math.ceil((size / (double)(1 << i))-1);
            b = nodes.get(i).get(index);
            b.sum += x;
            if (b.max < x) {
              b.max = x;
            } 
          }
        }
      } 
    }
    totalSize++;
  }   
  // [1, 2, 3, 4] size = 4
  // pop = 4, size 
  public Integer pop() {
    // TODO: Your code goes here
    int value = 0;
    if(size() == 0) {
      return null;
    } else {
      if (size()-1 == nodes.get(0).size()/2) {
        value = nodes.get(0).get(size()-1).max;
        nodes.remove(nodes.size()-1);
        for (int i = 0; i < nodes.size(); i++) {
          int size = nodes.get(i).size();
          for (int y = size-1; y >= size/2; y--) {
            nodes.get(i).remove(y);
          }
        }
        totalSize--;
      }
      else {
        int size = size();
        if (size % 2 != 0) {
          size++;
        }
        both node = nodes.get(0).get(size()-1);
        value = node.max;
        node.max = 0;
        for (int i = 1; i < nodes.size(); i++) {
          int index = (int)Math.ceil((size / (double)(1 << i))-1);
          both b = nodes.get(i).get(index);
          b.sum -= nodes.get(0).get(size()-1).sum;
          ArrayList<both> temp = nodes.get(i-1);
          if (temp.get(index*2).max <= temp.get((index*2)+1).max) {
            b.max = temp.get((index*2)+1).max;
          } else {
            b.max = temp.get(index*2).max;
          }
        } 
        node.sum = 0;
        totalSize--;
      }
      return value;
    }
  }


  public Integer set(int i, int x) {
    // TODO: Your code goes here
    if(i < 0 || i >= size()) {
      return null;
    }
    else {
      int size = i+1;
      if (size % 2 != 0 && i != 0) {
        size++;
      }
      both t = nodes.get(0).get(i);
      long diff = (long)x - t.sum;
      int value = t.max;
      t.sum = x;
      t.max = x;

      for (int y = 1; y < nodes.size(); y++) {
        int index = (int)Math.ceil((size / (double)(1 << y))-1);
        both b = nodes.get(y).get(index);
        b.sum += diff;
        ArrayList<both> temp = nodes.get(y-1);
        if (temp.get(index*2).max <= temp.get((index*2)+1).max) {
          b.max = temp.get((index*2)+1).max;
        } else {
          b.max = temp.get(index*2).max;
        }
      }
      return value;
    }
  }


  public long ksum(int k) {
    // TODO: Your code goes here
    long total = 0;
    if (k <= 0 || size() == 0) {
      return 0;
    } else if (k >= size()) {
      return nodes.get(nodes.size()-1).get(0).sum;
    } else if (k == 1) {
      return nodes.get(0).get(size()-1).max;
    } 
    else {
      int realK = k + (nodes.get(0).size() - size());
      int nums = realK;
      int index = 0;
      int z = nodes.get(0).size();
      for (int i = nodes.size()-2; i >= 0; i--) {
        int is = nodes.get(i).size();
        if (z / is <= nums){
          index = i;
          total += nodes.get(index).get(nodes.get(index).size()-1).sum;
          nums -= z / is;
          index--;
          break;
        }
      }
      while (index >= 0) {
        ArrayList<both> temp = nodes.get(index);
        if (z / temp.size() <= nums) {
          total += temp.get((temp.size() - 1) - ((realK-nums) / (z / temp.size()))).sum;
          nums -= z / temp.size();
        } else {
          index--;
        }
      }
      return total;
    }
  }

  public Integer get(int i) {
    // TODO: Your code goes here
    if(i < 0 || i >= size())
      return null;
    return nodes.get(0).get(i).max;
  }

  public Integer max() {
    // TODO: Your code goes here
    if (size()==0) {
      return null;
    } else {
      return nodes.get(nodes.size()-1).get(0).max;
    }
  }

  public int size() {
    // TODO: Your code goes here
    return totalSize;
  }

  public Iterator<Integer> iterator() {
    // TODO: Your code goes here
    Iterator<Integer> whole = new Iterator<Integer>() {
      int index = 0;
      @Override
      public boolean hasNext() {
        if (index <= size()-1) {
          return true;
        }
        return false;
      }
      @Override
      public Integer next() {
        ArrayList<both> z = nodes.get(0);
        return z.get(index++).max; 
      }
    };
    return whole;
  }

  /* 
  public void printSums() {
    System.out.print("[");
    for (int i = 0; i < nodes.size(); i++) {
        System.out.print("[");
        for (int y = 0; y < nodes.get(i).size(); y++) {
            System.out.print(nodes.get(i).get(y).sum + ", ");
        }
        System.out.print("]");
    }
    System.out.println("]");
  }

  public void printMaxs() {
    System.out.print("[");
    for (int i = 0; i < nodes.size(); i++) {
        System.out.print("[");
        for (int y = 0; y < nodes.get(i).size(); y++) {
            System.out.print(nodes.get(i).get(y).max + ", ");
        }
        System.out.print("]");
    }
    System.out.println("]");
  }
*/
}