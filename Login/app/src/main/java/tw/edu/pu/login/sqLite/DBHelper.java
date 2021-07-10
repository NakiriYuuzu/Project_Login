package tw.edu.pu.login.sqLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Login.db";

    public DBHelper(@Nullable Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table users(username TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists users");
    }

    public boolean insertData(String username, String password) {
        SQLiteDatabase myDb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("username", username);
        contentValues.put("password", password);

        long result = myDb.insert("users", null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean checkUsername(String username) {
        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor cursor = myDb.rawQuery("Select * from users where username = ?", new String[] {username});

        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public boolean checkUserPassword(String username, String password) {
        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor cursor = myDb.rawQuery("Select * from users where username = ? and password = ?", new String[] {username, password});

        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
}
