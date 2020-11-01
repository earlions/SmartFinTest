package com.example.smartfintest.db

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.smartfintest.Post
import java.io.*
import java.sql.SQLException
import java.util.ArrayList
import kotlin.jvm.Throws

class DBHelper constructor(val context: Context): SQLiteOpenHelper(context,"range.db",null,1) {
    val DB_NAME = "range.db"
    val DB_PATH:String
    val TABLE = "range" // название таблицы в бд
    var mPosts: MutableList<Post> = ArrayList()
    // названия столбцов
    val COLUMN_ID = "_id"
    val COLUMN_NAME = "name"
    val COLUMN_PRICE = "price"
    val COLUMN_IMAGE = "image"
    val COLUMN_CITY = "city"



    init {
       DB_PATH =context.filesDir.path + DB_NAME
    }
    override fun onCreate(db: SQLiteDatabase?) {

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
    fun create_db(){


        val myInput: InputStream
        val myOutput:OutputStream
        try {
            val file = File(DB_PATH);
            if (!file.exists()) {
                this.readableDatabase;
                //получаем локальную бд как поток
                myInput = context.assets.open(DB_NAME);
                // Путь к новой бд
                val outFileName = DB_PATH
                // Открываем пустую бд
                myOutput = FileOutputStream(outFileName);

                // побайтово копируем данные
                val buffer = ByteArray(1024)
                var length:Int = myInput.read(buffer)
                while (length > 0) {
                    myOutput.write(buffer, 0, length)
                    length = myInput.read(buffer)
                }

                myOutput.flush();
                myOutput.close();
                myInput.close();
            }
        }
        catch(ex: IOException){
            Log.d("DatabaseHelper", ex.message.toString())
        }
    }

    @Throws(SQLException::class)
    fun open(): SQLiteDatabase {
        return SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE)
    }
    fun getData(select:String):MutableList<Post>{
        val db: SQLiteDatabase = open()
        var cursor: Cursor
        cursor = db.rawQuery("select * from $TABLE "+ " where city = '$select'", null)
        cursor.moveToFirst()
        val counter = cursor.count
        mPosts.clear()
        for (i in 1..counter){
            val name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
            val price = cursor.getString(cursor.getColumnIndex(COLUMN_PRICE))
            val image = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE))
            val city = cursor.getString(cursor.getColumnIndex(COLUMN_CITY))
            val post = Post(name,city,price.toFloat(),image)
            mPosts.add(post)
            cursor.moveToNext()
        }

        cursor.close()
    return mPosts
    }
    fun getDataAll():MutableList<Post>{
        val db: SQLiteDatabase = open()
        var cursor: Cursor
        cursor = db.rawQuery("select * from $TABLE ", null)
        cursor.moveToFirst()
        val counter = cursor.count
        mPosts.clear()
        for (i in 1..counter){
            val name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
            val price = cursor.getString(cursor.getColumnIndex(COLUMN_PRICE))
            val image = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE))
            val city = cursor.getString(cursor.getColumnIndex(COLUMN_CITY))
            val post = Post(name,city,price.toFloat(),image)
            mPosts.add(post)
            cursor.moveToNext()
        }

        cursor.close()
        return mPosts
    }
}
// where city=$select