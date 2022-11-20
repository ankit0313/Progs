import java.util.Scanner;

public class Bankers {
    private int need[][], alloc[][], max[][], avail[][], np, nr;

    private void input() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the number of process");
        np = s.nextInt();
        System.out.print("\nEnter the number of resources");
        nr = s.nextInt();
        need = new int[np][nr];
        alloc = new int[np][nr];
        max = new int[np][nr];
        avail = new int[1][nr];

        System.out.println("Enter the allocation matrix-->");
        for (int i = 0; i < np; i++) {
            for (int j = 0; j < nr; j++) {
                alloc[i][j] = s.nextInt();
            }
        }

        System.out.println("Enter the max matrix-->");
        for (int i = 0; i < np; i++) {
            for (int j = 0; j < nr; j++) {
                max[i][j] = s.nextInt();
            }
        }

        System.out.println("Enter the available matrix");
        for (int i = 0; i < nr; i++) {
            avail[0][i] = s.nextInt();
        }

        s.close();

    }

    private int[][] cal_need() {
        for (int i = 0; i < np; i++) {
            for (int j = 0; j < nr; j++) {
                need[i][j] = max[i][j] - alloc[i][j];
            }
        }
        return need;
    }

    private boolean check(int i) {
        for (int j = 0; j < nr; j++) {
            if (need[i][j] > avail[0][j]) {
                return false;
            }

        }
        return true;

    }

    private void isSafe() {
        input();
        cal_need();
        boolean done[] = new boolean[np];
        boolean allocated = false;
        int j = 0;
        while (j < np) {
            System.out.println("Safe Sequence");
            for (int i = 0; i < np; i++) {
                if (!done[i] && check(i)) {
                    for (int k = 0; k < nr; k++) {
                        avail[0][k] = avail[0][k] + alloc[i][k];

                    }
                    System.out.println("Allocated process " + i);
                    allocated = done[i] = true;
                    j++;
                }

            }
            if (!allocated)
                break;
        }

        if (j == np)
            System.out.println("Safetly allocated");
        else
            System.out.println("Not all are allocated safetly");
    }

    public static void main(String[] args) {
        Bankers b = new Bankers();
        b.isSafe();

    }
}
