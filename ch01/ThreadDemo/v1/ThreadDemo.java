public class ThreadDemo
{
   public static void main(String[] args)
   {
      boolean isDaemon = args.length != 0;  // 如果命令行参数长度不为零，则将 isDaemon 设为 true
      Runnable r = new Runnable()  // 新建一个 Runnable
                   {
                      @Override
                      public void run()  // 线程跑的代码
                      {
                         Thread thd = Thread.currentThread();  // 获取当前线程引用存在 thd
                         while (true)
                            System.out.printf("%s is %salive and in %s " +
                                              "state%n",
                                              thd.getName(), 
                                              thd.isAlive() ? "" : "not ", 
                                              thd.getState());
                      }
                   };
      Thread t1 = new Thread(r, "thd1");  // 用上边的 Runnable 构建一个新线程
      if (isDaemon)  // 如果命令行参数长度不为零
         t1.setDaemon(true); // 将新建的 t1 线程设为 daemon thread
      System.out.printf("%s is %salive and in %s state%n",
                        t1.getName(), 
                        t1.isAlive() ? "" : "not ", 
                        t1.getState()); // 刚创建是 NEW state
      Thread t2 = new Thread(r);
      t2.setName("thd2");
      if (isDaemon)
         t2.setDaemon(true);
      System.out.printf("%s is %salive and in %s state%n",
                        t2.getName(), 
                        t2.isAlive() ? "" : "not ", 
                        t2.getState());
      t1.start(); // 调用 start() 之后变为 RUNNABLE state， 但如果是 daemon thread，主线程结束后它们就退出了，没机会变成 RUNNING　state
      t2.start();
   }
}