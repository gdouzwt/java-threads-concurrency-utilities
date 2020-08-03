import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountingThreads {
    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                int count = 0;
                while (true)
                    System.out.println(name + ": " + count++);
            }
        };
        ExecutorService es = Executors.newFixedThreadPool(2);
        es.submit(r);
        es.submit(r);
    }
}
