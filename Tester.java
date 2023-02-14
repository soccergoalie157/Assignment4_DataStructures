package comp2402a4;

import java.util.Iterator;
import java.util.Random;

public class Tester {

    static <T> void showContents(Iterable<Integer> ds) {
        System.out.print("[");
        Iterator<Integer> it = ds.iterator();
        while (it.hasNext()) {
            System.out.print(it.next());
            if (it.hasNext()) {
                System.out.print(",");
            }
        }
        System.out.println("]");
    }
    
    static void sparrowTest(RevengeOfSparrow rs, int n) {
        System.out.println(rs.getClass().getName());

        Random rand = new Random();
        for (int j = 0; j < n; j++) {
            int x = rand.nextInt(3*n/2);
            System.out.println("push(" + x + ")");
            rs.push(x);
            showContents(rs);
            int i = rand.nextInt(rs.size());
            System.out.println("get("+ i +") = " + rs.get(i));
            System.out.println("max() = " + rs.max());
            int k = rand.nextInt(rs.size()+1);
            System.out.println("ksum("+ k +") = " + rs.ksum(k));
        }

        for (int j = 0; j < n/2; j++) {
            int i = rand.nextInt(rs.size());
            int x = rand.nextInt(3*n/2);
            System.out.println("set(" + i + ", " + x + ") = " + rs.set(i, x));
            showContents(rs);
            i = rand.nextInt(rs.size());
            System.out.println("get("+ i +") = " + rs.get(i));
            System.out.println("max() = " + rs.max());
            int k = rand.nextInt(rs.size()+1);
            System.out.println("ksum("+ k +") = " + rs.ksum(k));
        }
        while (rs.size() > 0) {
            System.out.println("pop() = " + rs.pop());
            showContents(rs);
            if(rs.size() > 0){
                int i = rand.nextInt(rs.size());
                System.out.println("get("+ i +") = " + rs.get(i));
                System.out.println("max() = " + rs.max());
                int k = rand.nextInt(rs.size()+1);
                System.out.println("ksum("+ k +") = " + rs.ksum(k));
            }
        }
    }

    public static void main(String[] args) {
        //sparrowTest(new SlowSparrow(), 20);
        sparrowTest(new FastSparrow(), 8);
    }
}