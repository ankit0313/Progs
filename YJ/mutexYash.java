import java.lang.*;
public class mutexYash {
  public  final static int numb=3;
    static int sharedData1=0;
    static int sharedData2=0;

    static class theLock extends Object
    {

    }
    static public theLock lockObject = new theLock();

    static class theThread extends Thread
    {
        public void run()
        {
            System.out.println("Starting thread"+getName());

            synchronized(lockObject)
            {
                System.out.println("Thread "+getName()+" start critical section in synchornized block ");
                --sharedData1;
                ++sharedData2;
                System.out.println("Thread "+getName()+" end critical section leave synchornized block ");
            }
        }
    }

    public static void main(String[] args) {
        theThread mythread[]= new theThread[numb];
        System.out.println("Entered the test case\n");

        System.out.println("Synchronize to prevent access to shared resource");
        synchronized(lockObject)
        {
            for (int i = 0; i < numb; i++) {
                mythread[i]= new theThread();
                mythread[i].start();
                
            }
    
            System.out.println("Wait a bit until we're done with sharedData/n");
    
            try{
                Thread.sleep(3000);
            }
            catch(Exception e)
            {
                
            }
            System.out.println("Unlock shared data");
        }
            
        

        System.out.println("Wait for the thread to complete");

        try{
            for (int i = 0; i < numb; i++) {
                mythread[i].join();
                System.out.println("Testcasecompleted");
                
            }
            
        }
        catch(InterruptedException exv)
            {
                
            }
    
    }
}
