package com.example.starbuzz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class TopLevelActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_level);


        //OnItemClickListener - вложенный класс по отношению к классу AdapterView. ListView - Субкласс(дочерный) AdapterView
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> listView, //Представление, на котором был сделан щелчок
                                    //Дополнительная информация о варианте спискового представления -
                                    // - например представление и его позиция :
                                    View v,
                                    int position,
                                    long id) {
                if (position == 0) { //Drinks - первый вариант в списковом прредставлении находится в позиции 0.

                    //TopLevelActivity.this - Интент выдается - TopLevelActivity, Должен запускать DrinkCategoryActivity.class
                    Intent intent = new Intent(TopLevelActivity.this, DrinkCategoryActivity.class);
                    startActivity(intent);
                }
                else if  (position == 1) {
                    Intent intent1 = new Intent(TopLevelActivity.this, FoodCategoryActivity.class);
                    startActivity(intent1);
                }
            }
        };

        //Добавление слушателя к списковому представлению.
        ListView listView = (ListView) findViewById(R.id.list_options);
        listView.setOnItemClickListener(itemClickListener);

    }
}