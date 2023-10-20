import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*     
Создать справочник сотрудников
Необходимо:
Создать класс справочник сотрудников, который содержит внутри коллекцию сотрудников - каждый сотрудник должен иметь следующие атрибуты:
Табельный номер
Номер телефона
Имя
Стаж
Добавить метод, который ищет сотрудника по стажу (может быть список)
Добавить метод, который выводит номер телефона сотрудника по имени (может быть список)
Добавить метод, который ищет сотрудника по табельному номеру
Добавить метод добавление нового сотрудника в справочник */
public class Contacts {
    private int count_id;
    private Map<Integer, Person> tabel = new HashMap<>();

    /**
     * метод добавление нового сотрудника в справочник
     */
    public void addPerson(int phone, String name, int level) {
        tabel.put(++count_id, new Person(phone, name, level));
    }

    /**
     * метод, который ищет сотрудника по стажу (может быть список)
     * 
     * @param search_experience - стаж
     * @return List<String>
     */
    public List<String> showExperience(int search_experience) {
        List<String> out = new LinkedList<>();
        for (Map.Entry<Integer, Person> element : tabel.entrySet()) {
            if (element.getValue().getLevel() == search_experience) {
                out.add(String.format("id \"%d\" \"%s\"", element.getKey(), element.getValue().info()));
            }
        }
        return out;
    }

    /**
     * метод, который ищет сотрудника по табельному номеру
     * 
     * @param name - искомое имя
     * @return List<String>
     */
    public List<String> showPhone(String name) {
        List<String> out = new LinkedList<>();
        for (Map.Entry<Integer, Person> element : tabel.entrySet()) {
            if (element.getValue().getName() == name) {
                out.add(String.valueOf(element.getValue().getPhone()));
            }
        }
        return out;
    }

    /**
     * метод, который ищет сотрудника по табельному номеру
     * 
     * @param id - id человека
     */
    public void showId(int id) {
        try {
            System.out.println(tabel.get(id).info());
        } catch (NullPointerException e) {
            System.out.printf("id %d несуществует\n", id);
        }
    }

    private class Person {
        private String name;
        private int level;
        private int phone;

        public int getLevel() {
            return level;
        }

        public int getPhone() {
            return phone;
        }

        public String getName() {
            return name;
        }

        Person(int phone, String name, int level) {
            this.phone = phone;
            this.name = name;
            this.level = level;
        }

        private String info() {
            return new String(String.format("телефон: %d имя: %s стаж: %d",
                    getPhone(), getName(), getLevel()));
        }

    }
}
