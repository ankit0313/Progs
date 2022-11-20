import java.util.*;
import java.util.Queue;

public class PageReplacement {
    public static void lru(int n, int pages[], int frames) {
        HashSet<Integer> s = new HashSet<>(frames);
        HashMap<Integer, Integer> indexes = new HashMap<>();
        int pagefault = 0;
        for (int i = 0; i < n; i++) {
            if (s.size() < frames) {
                if (!s.contains(pages[i])) {
                    s.add(pages[i]);
                    pagefault++;
                }
                indexes.put(pages[i], i);
            } else {
                if (!s.contains(pages[i])) {
                    int lru = Integer.MAX_VALUE;
                    int val = Integer.MIN_VALUE;
                    Iterator<Integer> itr = s.iterator();
                    while (itr.hasNext()) {
                        int temp = itr.next();
                        if (indexes.get(temp) < lru) {
                            lru = indexes.get(temp);
                            val = temp;
                        }
                    }
                    s.remove(val);
                    indexes.remove(val);
                    s.add(pages[i]);
                    pagefault++;

                }
                indexes.put(pages[i], i);
            }

        }
        System.out.println("Pagefault :" + pagefault);
        System.out.println("PageHit :" + (n - pagefault));
    }

    public static void fifo(int n, int pages[], int frames) {
        HashSet<Integer> s = new HashSet<>(frames);
        Queue queue = new LinkedList<>();
        int pagefault = 0;
        for (int i = 0; i < n; i++) {
            if (s.size() < frames) {
                if (!s.contains(pages[i])) {
                    s.add(pages[i]);
                    pagefault++;
                    queue.add(pages[i]);

                }
            } else {
                if (!s.contains(pages[i])) {
                    int val = (int) queue.peek();
                    queue.poll();
                    s.remove(val);
                    s.add(pages[i]);
                    queue.add(pages[i]);
                    pagefault++;
                }
            }

        }
        System.out.println("PageFault :" + pagefault);
        System.out.println("PageHits :" + (n - pagefault));

    }

    public static boolean search(int key, int frames[]) {
        for (int i = 0; i < frames.length; i++) {
            if (frames[i] == key) {
                return true;
            }
        }
        return false;
    }

    public static int predict(int pages[], int frames[], int n, int index) {
        int res = -1, farthest = index;
        for (int i = 0; i < frames.length; i++) {
            int j;
            for (j = index; j < n; j++) {
                if (frames[i] == pages[j]) {
                    if (j > farthest) {
                        farthest = j;
                        res = i;
                    }
                    break;
                }
            }
            if (j == n)
                return i;
        }
        return (res == -1) ? 0 : res;
    }

    public static void optimal(int n, int pages[], int fr) {
        int hit = 0;
        int index = 0;
        int frames[] = new int[fr];
        for (int i = 0; i < n; i++) {
            if (search(pages[i], frames)) {
                hit++;
                continue;
            }
            if (index < fr) {
                frames[index++] = pages[i];
            } else {
                int j = predict(pages, frames, n, i + 1);
                frames[j] = pages[i];
            }

        }
        System.out.println("PageFault :" + (n - hit));
        System.out.println("PageHits :" + hit);
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n;

        System.out.println("Enter the number of pages ");
        n = s.nextInt();
        int string[] = new int[n];
        for (int i = 0; i < string.length; i++) {
            string[i] = s.nextInt();
        }
        System.out.println("Enter the no. of frames");
        int frames = s.nextInt();
        System.out.println(" \n1.LRU \n2.FIFO \n3.Optimal \nEnter your choice");
        int ch = s.nextInt();
        switch (ch) {
            case 1:
                lru(n, string, frames);
                break;
            case 2:
                fifo(n, string, frames);
                break;
            case 3:
                optimal(n, string, frames);
                break;
            default:
                System.out.println("Wrong choice");
        }
        s.close();

    }
}