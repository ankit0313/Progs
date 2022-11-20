
import java.util.*;
import java.util.concurrent.Semaphore;

class SharedResource{
    static int count=0;
}

class Mythread extends Thread{
    Semaphore semaphore;
    String threadName;

    public Mythread(Semaphore semaphore, String threadName){
        super(threadName);
        this.semaphore=semaphore;
        this.threadName=threadName;

    }

    public void run(){
        if(this.getName().equals("Thead1"))
        {
            System.out.println("Starting thread "+threadName);
            try{

                System.out.println(threadName + " is waiting for permit");

                semaphore.acquire();

                System.out.println(threadName+" got the permit" );

                //accessing shareResource
                for (int i = 0; i < 5; i++) {
                    SharedResource.count++;
                    System.out.println(threadName +":" + SharedResource.count);
                }

                Thread.sleep(10);

            }
            catch(InterruptedException exc)
            {
                System.out.println(exc);
            }
            //closing the thread;
            System.out.println("Releasing thread "+threadName);
            semaphore.release();
        }
        else 
        {
            System.out.println("Starting thread "+threadName);
            try{

                System.out.println(threadName + " is waiting for permit");

                semaphore.acquire();

                System.out.println(threadName+" got the permit" );

                //accessing shareResource
                for (int i = 5; i > 0; i--) {
                    SharedResource.count++;
                    System.out.println(threadName +":" + SharedResource.count);
                }

                Thread.sleep(10);

            }
            catch(InterruptedException exc)
            {
                System.out.println(exc);
            }
            //closing the thread;
            System.out.println("Releasing thread "+threadName);
            semaphore.release();
        }

    }

}



public class SemaYash {
    public static void main(String[] args) throws InterruptedException {
       
        Semaphore semaphore = new Semaphore(1);
        Mythread t1 = new Mythread(semaphore, "Thread1");
        Mythread t2 = new Mythread(semaphore, "Thread2");
        t1.start();
        t1.join();
        t2.start();
        t2.join();

       
        

    }
    
}
