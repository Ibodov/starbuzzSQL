package com.example.starbuzz;

public class Food {

    private String name;
    private String description;
    private int imageResourceId;

    //Создаем массив
    public static final Food[] foods = {
//        new Food("Osh", "National food", R.drawable.plov),
//        new Food("Shurbo", "National food", R.drawable.shurpa),
//        new Food("Qurutob", "National food", R.drawable.qurutob)
    };


    //создаем конструктор
    public Food(String name, String description, int imageResourceId) {
        this.name = name;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }

    //Создаем геттеры
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
    public String toString() {
        return this.name;
    }
}
