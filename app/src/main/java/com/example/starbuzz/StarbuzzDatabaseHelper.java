package com.example.starbuzz;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StarbuzzDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "starbuzz"; // Имя базы данных
    private static final int DB_VERSION = 1; // Версия базы данных

    StarbuzzDatabaseHelper(Context context) {
//Вызываем конструктор суперкласса SQLiteOpenHelper и передаем ему имя
// и версию базы данных
        super(context, DB_NAME, null, DB_VERSION); //null используется для работы с курсорами
    }
//Конструктор задает информацию о базе данных, но сама
//база данных в этой точке не создается. Помощник SQLite
//ожидает, пока приложение обратится к базе данных, и со-
//здает базу данных в этой точке.


//Метод onCreate() вызывается при создании базы данных;
//мы используем его для создания таблицы и вставки данных.
    @Override
    public void onCreate(SQLiteDatabase db) {
//Вместо того, чтобы создавать здесь таблицу DRINK, мы создадим
//ее в методе updateMyDatabase().
        updateMyDatabase(db, 0, DB_VERSION);

    }


    @Override
    //oldVersion - Текущая версия базы данных.
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, oldVersion, newVersion);
        //newVersion Новая версия из кода помощника SQlite.
        if (oldVersion == 1) {
//Код, выполняемый для версии 1
        }
        if (oldVersion < 3) {
//Код, выполняемый для версии 1 или 2
        }
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion < 1) {
            //Команда CREATE TABLE сообщает, какие столбцы должны присутствовать в таблице,
//и данные какого типа должны в этих столбцах храниться

//Команда создает пустую таблицу DRINK — но что делать,
//если таблицу потребуется заполнить исходными данными?
            db.execSQL("CREATE TABLE DRINK (" //Создать таблицу DRINK
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, " //Столбец _id является первичным ключом.
                    + "NAME TEXT, "
                    + "DESCRIPTION TEXT, "
                    + "IMAGE_RESOURCE_ID INTEGER);");
//Вставить данные каждого напитка в отдельную строку.
            insertDrink(db, "Latte", "Espresso and steamed milk", R.drawable.latte);
            insertDrink(db, "Cappuccino", "Espresso, hot milk and steamed-milk foam",
                    R.drawable.cappuccino);
            insertDrink(db, "Filter", "Our best drip coffee", R.drawable.filter);

//            insertDrink((db, "Filter", "Кофеи фильтр шуда", R.drawable.filter);
            //insertDrink(db, "Filter", "Our best drip coffee", R.drawable.filter);

        }
        if (oldVersion < 2) {
            //Код добавления нового столбца  - Этот код будет выполняться в том случае, если у
            // пользователя уже установлена версия 1 базы данных.

            db.execSQL("ALTER TABLE DRINK ADD COLUMN FAVORITE NUMERIC;"); //Добавить числовой столбец FAVORITE в таблицу DRINK

        }


    }

//Так как нужно добавить несколько напитков, мы создаем отдельный метод
private static void insertDrink(SQLiteDatabase db, String name,
                                String description, int resourceId) {
    ContentValues drinkValues = new ContentValues();
    drinkValues.put("NAME", name);
    drinkValues.put("DESCRIPTION", description);
    drinkValues.put("IMAGE_RESOURCE_ID", resourceId);
    db.insert("DRINK", null, drinkValues);
}
}
