public class City {
    private final int id;
    private String name;

    // конструктор со всеми полями
    public City(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // геттеры и сеттеры для полей класса
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
