import java.util.Scanner;
import java.security.SecureRandom;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class MyThread{
    public static void main(String[] args){
        int x,y;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter thread1 sleeptime:");
        x = scanner.nextInt();
        System.out.println("Enter thread2 sleeptime:");
        y = scanner.nextInt();
        Threads t1 = new Threads("Thread1",x);
        Threads t2 = new Threads("Thread2",y);
        System.out.println("two threads will start now");
        ExecutorService e1 = Executors.newCachedThreadPool();
        e1.execute(t1);
        e1.execute(t2);
        System.out.printf("threads started, main is ending%n");
        e1.shutdown();
    }
}
class Threads implements Runnable{
    private static final SecureRandom generator = new SecureRandom();
    private final String tname;
    private final int time;
    public Threads(String name, int t){
        this.tname = name;
        this.time = generator.nextInt(t);
    }
    public void run(){
        System.out.printf("%s%s%s%n%n",tname,tname,tname);
        try
        {
            Thread.sleep(time);
        }
        catch(InterruptedException exception)
        {
            exception.printStackTrace();
            Thread.currentThread().interrupt();
        }
        System.out.printf("%s done sleeping for %d milliseconds%n", tname,time);
    }
}