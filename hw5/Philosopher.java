package hw5;

public class Philosopher extends Thread {
    private String name;
    private int countThink;
    private volatile PhStates currentState, previousState;
    private Plate spaghetti;
    private Fork leftfFork, rightFork;

    public Philosopher(String name, Fork left, Fork right) {
        this.name = name;
        currentState = PhStates.THINK;
        previousState = PhStates.THINK;
        this.leftfFork = left;
        this.rightFork = right;
        spaghetti = new Plate();
    }

    public PhStates getStatus() {
        return currentState;
    }

    private synchronized void eat() throws InterruptedException {

        leftfFork.setFree(false);
        rightFork.setFree(false);
        System.out.printf("%s взял вилки №%d №%d\n",name,leftfFork.getId(),rightFork.getId());
        currentState = PhStates.EAT;
        System.out.println(name + " ЕСТ");
        spaghetti.decrease();

        Thread.sleep(1000);
        currentState = PhStates.THINK;
        previousState = PhStates.EAT;
        leftfFork.setFree(true);
        rightFork.setFree(true);
        countThink = 0;
    }

    @Override
    public void run() {

        try {

            while (spaghetti.getSize() > 0) {

                if ((currentState == PhStates.THINK) && (previousState == PhStates.EAT)) {
                    countThink++;
                    System.out.println(name + " РАЗМЫШЛЯЕТ");
                }

                // if ((leftfFork.isFree() & rightFork.isFree() == true) && (previousState == PhStates.THINK)) {
                if ((leftfFork.isFree()) && (previousState == PhStates.THINK)) {

                    synchronized (leftfFork) {
                        //Thread.sleep(10);
                        if (rightFork.isFree()) {
                            synchronized (rightFork) {
                                eat();
                            }
                        }
                    }
                }

                if (currentState == PhStates.THINK && countThink > 0) {
                    previousState = PhStates.THINK;
                }

            }
            currentState=PhStates.WELL;
            System.out.println(name + " НАЕЛСЯ");

        } catch (InterruptedException e) {
            // currentState = PhStates.THINK;
            e.printStackTrace();
        }
        // System.exit(0);
    }

}
