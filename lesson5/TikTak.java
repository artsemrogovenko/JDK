
public class TikTak implements Runnable{

private final String bracket;
private final Object monitor;


public TikTak(String bracket) {
    this.bracket = bracket;
    this.monitor = TikTak.class;
}




@Override
public void run() {
while(true){
    synchronized (monitor){
        System.out.print(bracket);   
    }
    try {
        Thread.sleep(300);
        monitor.notify();
        monitor.wait();     
    } catch (Exception e) {
        // TODO: handle exception
    }
}   
}


}