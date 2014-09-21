package hackathon.android.com.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by jayant on 9/20/14.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_USERS = "users";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PASSWORDS = "passwords";
    public static final String COLUMN_USERNAMES = "username";



    private static final String DATABASE_NAME = "app.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String USERS_CREATE = "create table "
            + TABLE_USERS + "(" + COLUMN_ID
            + " integer primary key autoincrement, "
            + COLUMN_USERNAMES + " text not null,"
            + COLUMN_PASSWORDS+ " text not null);";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(USERS_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

}