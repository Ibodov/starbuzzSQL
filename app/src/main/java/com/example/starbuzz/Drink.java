package com.example.starbuzz;
// in klass digar lozim nest. shumo az DB ma`lumotro girifta istodaed
public class Drink {
    private String name;
    private String description;
    private int imageResourceId;

    //drinks - массив с элементами Drink
    public static final Drink[] drinks = {
            new Drink("Latte", "coffee with milk", R.drawable.latte),
            new Drink("Cappuccino", "hot milk and a steamed milk foam", R.drawable.cappuccino),
            new Drink("Filter", "Highest quality beans roasted and brewed fresh", R.drawable.filter)
    };

    //Конструктор
    private Drink(String name, String description, int imageResourceId) {
        this.name = name;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }

    //геттеры - методы для приватных переменных
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public int getImageResourceId() { return imageResourceId;}

    //Строковое представления Drink использует названия напитка.
    public String toString() {
        return this.name;
    }
}
