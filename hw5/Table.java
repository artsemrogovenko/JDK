package hw5;

public class Table {

    private static final String[] randomnames = { "Аристотель", "Иммануил_Кант", "Платон", "Конфуций", "Сократ",
            "Рене_Декарт", "Никколо_Макиавелли", "Джон_Локк", "Диоген", "Фома_Аквинский", "Лао-Цзы", "Вольтер",
            "Барон_де_Монтескьё", "Жан-Жак_Руссо", "Джордж_Беркли", "Айн_Рэнд", "Сунь-Цзы" };

    private Fork[] forks;
    private Philosopher[] philosophers;

    public Table(int peoples) {

        philosophers = new Philosopher[peoples];
        forks = new Fork[peoples];

        // создаю вилки с уникальными id
        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Fork(i);
        }

        // создаю философа, назначаю вилку справа и слева
        for (int i = 0; i < philosophers.length; i++) {
            try {
                philosophers[i] = new Philosopher(randomnames[i], forks[i], forks[i + 1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                philosophers[i] = new Philosopher(randomnames[i], forks[i], forks[0]);
            }
        }

        for (Philosopher element : philosophers) {
            element.start();
        }

    }

}
