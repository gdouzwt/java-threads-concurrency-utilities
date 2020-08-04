package v1;

public class MyDemo {

    public static void main(String[] args) {
        Thread thread = Thread.currentThread();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        thread.interrupt();
    }
}
