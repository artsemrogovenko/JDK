package ex1;

public class Friend {
    private String name;

    public Friend(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public synchronized void bow(Friend friend){
        System.out.println(friend.getName()+"я" + " поклонился "+ name+"e");
        bowBack(friend);
    }

    private synchronized void bowBack(Friend friend){
        System.out.println(name+"я"+ " кланяется в ответ " + friend.getName()+"e");
    }
}
