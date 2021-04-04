package com.example.starbuzz;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import android.widget.Toast;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DrinkActivity extends Activity {
    public static final String EXTRA_DRINKNO = "drinkNo";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        //Получить напиток из данных интента
        int drinkNo = (Integer)getIntent().getExtras().get(EXTRA_DRINKNO);

        // аз ин чо хатоги буд. дринк ид 3 аз интент омад , лекин массив 0,1,2 ҳаст. Шумо массив
        // 3 эелементаша талаб карда истодаед (3 ум элемент нест , танхо 0,1,2 хаст дар массив)
        // ин код решения бе истифодаи БД буд. бояд инро удалить мекардед.
//        Drink drink = Drink.drinks[drinkNo]; // Использовать drinkNo для получения информации о напитке, выбранном пользователем.

        //Создание курсора
        try {
            SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
            SQLiteDatabase db = starbuzzDatabaseHelper.getReadableDatabase();
//Создать курсор для получения из таблицы DRINK столбцов NAME, DESCRIPTION и IMAGE_
//RESOURCE_ID тех записей, у которых значение _id равно drinkNo.
            Cursor cursor = db.query("DRINK",
                    new String[]{"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID"},
                    "_id = ?",
                    new String[]{Integer.toString(drinkNo)},
                    null, null, null);

//Переход к первой записи в курсоре
            if (cursor.moveToFirst()) {
//Получение данных напитка из курсора
                String nameText = cursor.getString(0);
                String descriptionText = cursor.getString(1);
                int photoId = cursor.getInt(2);


                //Заполнение названия напитка
                TextView name = (TextView) findViewById(R.id.name);
//        name.setText(drink.getName());
                name.setText(nameText); //Использовать данные, полученные из курсора, для заполнения представлений

                //Заполнение описания напитка
                TextView description = (TextView) findViewById((R.id.description));
//        description.setText(drink.getDescription());
                description.setText(descriptionText); //Использовать данные, полученные из курсора, для заполнения представлений

                //Заполнение изображение напитка
                ImageView photo = (ImageView) findViewById(R.id.photo);
//        photo.setImageResource(drink.getImageResourceId());
                //photo.setImageResource(drink.getImageResourceId());
//        photo.setContentDescription(drink.getName());
                photo.setImageResource(photoId); //Использовать данные, полученные из курсора, для заполнения представлений
                photo.setContentDescription(nameText);
            }
            //Закрыть курсор и базу данных.
            cursor.close();
            db.close();
        } catch (SQLiteException e) {
//Если будет выдано исключение SQLiteException, значит, при работе с базой данных возникли проблемы. В этом случае объект Toast
//используется для выдачи сообщения для пользователя
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    };

}
