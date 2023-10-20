
public class Main {
    public static void main(String[] args) {
        Contacts book = new Contacts();
        // Добавить метод добавление нового сотрудника в справочник
        book.addPerson(65798798, "Анна", 3);
        book.addPerson(34645634, "Игорь", 2);
        book.addPerson(123234634, "Михаил", 2);
        book.addPerson(2345522, "Юрий", 3);
        book.addPerson(12312355, "Анна", 1);
        // Добавить метод, который ищет сотрудника по табельному номеру
        book.showId(0);
        book.showId(2);
        book.showId(4);
        System.out.println();
        // Добавить метод, который выводит номер телефона сотрудника по имени
        book.showPhone("Анна").forEach((s) -> System.out.println(s));        
        System.out.println();
        // Добавить метод, который ищет сотрудника по стажу
        book.showExperience(1).forEach((s) -> System.out.println(s));
        book.showExperience(2).forEach((s) -> System.out.println(s));
    }

}
