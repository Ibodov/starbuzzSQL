package com.example.starbuzz;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;

public class FoodCategoryActivity extends ListActivity {
    @Override
    //Создаётся базовая сетевая активность FoodCategoryActivity
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListView listFoods = getListView(); //перем listFoods с мет getListView
        ArrayAdapter<Food> listAdapter = new ArrayAdapter<Food>(
                this, //текущая активность
                android.R.layout.simple_expandable_list_item_1, //Встроенный ресурс макета. -
                // - приказывает адаптеру массива отображать каждый элемент массива в виде надписи.
                Food.foods); // Сам массив foods из класса Food
        listFoods.setAdapter(listAdapter); //Назначить слушателя для спискового представления.

    }
}
