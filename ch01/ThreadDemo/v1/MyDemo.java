public class MyDemo {

    public static void main(String[] args) {
        Thread thread = Thread.currentThread();
        new Thread(() -> thread.interrupt()).start();
        try {
            Thread.sleep(3000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
    
}