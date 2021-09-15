package com.dentag.animalstorage.cursor

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.dentag.App
import com.dentag.animalstorage.DATABASE_NAME
import com.dentag.animalstorage.DATABASE_VERSION
import com.dentag.animalstorage.TABLE_NAME
import com.dentag.animalstorage.room.Animal

class AnimalSQLiteOpenHelper : SQLiteOpenHelper(
    App.instance,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_SQL)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    private fun getCursor(): Cursor {
        return readableDatabase.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }

    fun getListOfAnimals(): List<Animal> {
        val listOfAnimals = mutableListOf<Animal>()
        val cursor = getCursor()
        if (cursor.moveToFirst()) {
            do {
                val name = cursor.getString(cursor.getColumnIndex(NAME_COLUMN))
                val age = cursor.getInt(cursor.getColumnIndex(AGE_COLUMN))
                val breed = cursor.getString(cursor.getColumnIndex(BREED_COLUMN))
                val animal = Animal(name, age, breed)
                listOfAnimals.add(animal)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return listOfAnimals
    }

    fun update(animal: Animal) {
        readableDatabase.execSQL(
            "UPDATE $TABLE_NAME " +
                "SET ($AGE_COLUMN, $BREED_COLUMN) = ('${animal.age}', '${animal.breed}') " +
                "WHERE $NAME_COLUMN = '${animal.name}'"
        )
    }

    fun remove(animal: Animal) {
        readableDatabase.execSQL(
            "DELETE FROM $TABLE_NAME WHERE $NAME_COLUMN = '${animal.name}'"
        )
    }

    fun add(animal: Animal) {
        readableDatabase.execSQL("INSERT INTO $TABLE_NAME($NAME_COLUMN, $AGE_COLUMN, $BREED_COLUMN) VALUES ('${animal.name}','${animal.age}','${animal.breed}')")
    }

    companion object {
        private const val NAME_COLUMN = "name"
        private const val AGE_COLUMN = "age"
        private const val BREED_COLUMN = "breed"
        private const val CREATE_TABLE_SQL =
            "CREATE TABLE IF NOT EXISTS $TABLE_NAME ($NAME_COLUMN TEXT PRIMARY KEY, $AGE_COLUMN INTEGER, $BREED_COLUMN TEXT);"
    }
}
