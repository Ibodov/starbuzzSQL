package com.example.starbuzz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.widget.SimpleCursorAdapter;

public class DrinkCategoryActivity extends ListActivity {

//Эти приватные переменные добавляются для того, чтобы базу данных и курсор можно
//было закрыть в методе onDestroy().
    private SQLiteDatabase db;
    private Cursor cursor;


    @Override
    //Создаётся базовая сетевая активность DrinkCategoryActivity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // не нужно назначать макет используя setContentView()
        //setContentView(R.layout.activity_drink_category);

//        ListView listDrinks = getListView(); //перем listDrink с мет getListView
//        ArrayAdapter<Drink> listAdapter = new ArrayAdapter<Drink>(
//                this, //текущая активность
//                android.R.layout.simple_expandable_list_item_1, //Встроенный ресурс макета. -
//                // - приказывает адаптеру массива отображать каждый элемент массива в виде надписи.
//                Drink.drinks); // Сам массив drinks из класса Drink
//        listDrinks.setAdapter(listAdapter); //Назначить слушателя для спискового представления.

        ListView listDrinks = getListView();
        try {
            SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
            db = starbuzzDatabaseHelper.getReadableDatabase(); //Получить ссылку на базу данных.

            //Создать курсор
            cursor = db.query("DRINK",
                                   new String[]{"_id", "NAME"},
                                   null, null, null, null, null );

            CursorAdapter listAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_expandable_list_item_1,
                                                                      cursor,
                                                                      new String[]{"NAME"},
                                                                      new int[]{android.R.id.text1},
                                                                      0);
            listDrinks.setAdapter(listAdapter); //В этой версии также используется адаптер — но на этот раз адаптер курсора.

        } catch (SQLiteException e) {
            //Вывести сообщение для пользователя, если будет выдано исключение SQLiteException.
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
//База данных и курсор закрываются в методе onDestroy() активности.
//Курсор остается открытым до того момента, когда он перестает быть нужным адаптеру.
    public void onDestroy(){
        super.onDestroy();
        cursor.close();
        db.close();
    }

    @Override
    public void onListItemClick( ListView listView,
                                 View itemView,
                                 int position,
                                 long id)
    {

        Intent intent = new Intent(DrinkCategoryActivity.this, DrinkActivity.class);
        intent.putExtra(DrinkActivity.EXTRA_DRINKNO, (int)id);
        startActivity(intent);
    }

}