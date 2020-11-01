package com.example.smartfintest.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.smartfintest.PostCkeck
import java.util.*

class DBHelperCH constructor(context: Context): SQLiteOpenHelper(context,"history.db",null,1) {
    val CONTACTS_COLUMN_NAME = "name"
    val CONTACTS_COLUMN_PRICE = "price"
    val CONTACTS_COLUMN_SIZE = "size"
    val CONTACTS_COLUMN_NUMBER = "numper"


    override fun onCreate(db: SQLiteDatabase) {
        // TODO Auto-generated method stub
        db.execSQL(
            "create table contacts " +
                    "(id integer primary key, name text,price text,size text,numper integer)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS contacts")
        onCreate(db)
    }

    fun insertCheck(postCheck:PostCkeck,number: Int) {
        val db: SQLiteDatabase = this.writableDatabase
        val contentValues = ContentValues()

            contentValues.put(CONTACTS_COLUMN_NAME, postCheck.name)
            contentValues.put(CONTACTS_COLUMN_PRICE, postCheck.price.toString())
            contentValues.put(CONTACTS_COLUMN_SIZE, postCheck.size.toString())
            contentValues.put(CONTACTS_COLUMN_NUMBER, number)
            db.insert("contacts", null, contentValues)

        db.close()
    }


    fun getCount(): Int {
        var COUNT=0
        val db: SQLiteDatabase = this.readableDatabase
        val cursor = db.rawQuery("select * from contacts", null)
        cursor.moveToFirst()
        val mol=cursor.count
        if (mol!=0){
            cursor.moveToLast()
            COUNT = cursor.getInt(cursor.getColumnIndex(CONTACTS_COLUMN_NUMBER))}
        cursor.close()
        return COUNT
    }


}