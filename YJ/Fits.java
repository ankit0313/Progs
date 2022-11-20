import java.util.Scanner;

class first_fit {
    public first_fit(int memory_blocks[], int m, int process_num[], int n) {
        int allocation[] = new int[n];
        for (int i = 0; i < allocation.length; i++) {
            allocation[i] = -1;

        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (memory_blocks[j] >= process_num[i]) {
                    allocation[i] = j;
                    memory_blocks[j] = memory_blocks[j] - process_num[i];
                    break;
                }

            }

        }

        System.out.println("Process no. \tProcess size \tBlock no.");
        for (int i = 0; i < n; i++) {
            if (allocation[i] != -1) {
                System.out.println((i + 1) + "\t\t" + process_num[i] + "\t\t" + (allocation[i] + 1));
            } else {
                System.out.println((i + 1) + "\t\t" + process_num[i] + "\t\t" + "Not allocated");
            }

        }

    }
}

class best_fit {
    best_fit(int memory_blocks[], int m, int process_num[], int n) {
        int allocation[] = new int[n];
        for (int i = 0; i < allocation.length; i++) {
            allocation[i] = -1;
        }

        for (int i = 0; i < n; i++) {
            int bestIdx = -1;
            for (int j = 0; j < m; j++) {
                if (memory_blocks[j] >= process_num[i]) {
                    if (bestIdx == -1) {
                        bestIdx = j;
                    } else if (memory_blocks[bestIdx] > memory_blocks[j]) {
                        bestIdx = j;
                    }

                }
            }

            if (bestIdx != -1) {
                allocation[i] = bestIdx;
                memory_blocks[bestIdx] = memory_blocks[bestIdx] - process_num[i];

            }
        }
        System.out.println("Process no. \tProcess size \tBlock no.");
        for (int i = 0; i < n; i++) {
            if (allocation[i] != -1) {
                System.out.println((i + 1) + "\t\t" + process_num[i] + "\t\t" + (allocation[i] + 1));
            } else {
                System.out.println((i + 1) + "\t\t" + process_num[i] + "\t\t" + "Not allocates");
            }

        }

    }
}

class worst_fit {
    public worst_fit(int memory_blocks[], int m, int process_num[], int n) {
        int allocation[] = new int[n];
        for (int i = 0; i < allocation.length; i++) {
            allocation[i] = -1;
        }
        for (int i = 0; i < n; i++) {
            int worstIdx = -1;
            for (int j = 0; j < m; j++) {
                if (memory_blocks[j] >= process_num[i]) {
                    if (worstIdx == -1) {
                        worstIdx = j;
                    } else if (memory_blocks[worstIdx] < memory_blocks[j]) {        //focus on sign in difference between worst and best fit
                        worstIdx = j;
                    }
                }
            }
            if (worstIdx != -1) {
                allocation[i] = worstIdx;
                memory_blocks[worstIdx] -= process_num[i];

            }
        }
        System.out.println("Process no. \tProcess Size \tBlock no.");
        for (int j = 0; j < n; j++) {
            if (allocation[j] != -1) {
                System.out.println((j + 1) + "\t\t" + process_num[j] + "\t\t" + (allocation[j] + 1));
            } else {
                System.out.println((j + 1) + "\t\t" + process_num[j] + "\t\t" + "Not allocated");
            }

        }
    }
}
class nextfit{
    public nextfit(int memory_blocks[], int m, int process_num[], int n) {
        int allocation[] = new int[n];
        for (int i = 0; i < allocation.length; i++) {
            allocation[i] = -1;

        }
        int x=0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                while(x<m){
                if (memory_blocks[j] >= process_num[i]) {
                    allocation[i] = j;
                    memory_blocks[j] = memory_blocks[j] - process_num[i];
                    break;
                }
                
            }
            x++;
        }

    }

        System.out.println("Process no. \tProcess size \tBlock no.");
        for (int i = 0; i < n; i++) {
            if (allocation[i] != -1) {
                System.out.println((i + 1) + "\t\t" + process_num[i] + "\t\t" + (allocation[i] + 1));
            } else {
                System.out.println((i + 1) + "\t\t" + process_num[i] + "\t\t" + "Not allocated");
            }

        }

    }
}

public class Fits {
    public static void main(String[] args) {
        int m, n;
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the number of memory blocks ");
        m = s.nextInt();
        System.out.println("Number of memory blocks " + m);
        int[] memory_blocks = new int[m];
        System.out.println("Enter the memory blocks");
        for (int i = 0; i < m; i++) {
            memory_blocks[i] = s.nextInt();

        }
        System.out.println("Enter process number you want");
        n = s.nextInt();
        System.out.println("Number of processes " + n);
        int[] process_num = new int[n];
        System.out.println("Enter the process size");
        for (int i = 0; i < n; i++) {
            process_num[i] = s.nextInt();

        }
        int ch;
        System.out.println("Enter your choice \n1.FirstFit\n2.BestFit\n3.WorstFit\n4.NextFit");
        ch = s.nextInt();
        switch (ch) {
            case 1:
                System.out.println("Firstfit");
                first_fit fobj = new first_fit(memory_blocks, m, process_num, n);
                break;
            case 2:
                System.out.println("BestFit");
                best_fit bobj = new best_fit(memory_blocks, m, process_num, n);
                break;
            case 3:
                System.out.println("WorstFit");
                worst_fit wobj = new worst_fit(memory_blocks, m, process_num, n);
                break;
            case 4:
                System.out.println("NextFit");
                nextfit nef = new nextfit(memory_blocks, m, process_num, n);
                break;
            default:
                System.out.println("Wrong choice");

        }
        s.close();

    }

}