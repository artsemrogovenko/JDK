package hw5;

/** вилка  */
public class Fork {
    private volatile boolean free;
    private int id;

    public Fork(int id) {
        this.free = true;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean status) {
        this.free = status;
    }

}
