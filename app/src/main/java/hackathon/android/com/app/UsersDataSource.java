package hackathon.android.com.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jayant on 9/21/14.
 */
public class UsersDataSource
{
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID , MySQLiteHelper.COLUMN_USERNAMES , MySQLiteHelper.COLUMN_PASSWORDS };

    public UsersDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public User createUser(String userName, String password)
    {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_USERNAMES, userName);
        values.put(MySQLiteHelper.COLUMN_PASSWORDS, password);

        long insertId = database.insert(MySQLiteHelper.TABLE_USERS, null, values);

        Cursor cursor = database.query(MySQLiteHelper.TABLE_USERS,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        User newUser = cursorToUser(cursor);
        cursor.close();
        return newUser;
    }

    public void deleteUser(User user) {
        long id = user.getId();
        System.out.println("User deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_USERS, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_USERS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            User user = cursorToUser(cursor);
            users.add(user);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return users;
    }

    public User getUser(String userName,String password )
    {
        String[]  userNames = {userName};
        Cursor cursor = database.query(MySQLiteHelper.TABLE_USERS, allColumns , MySQLiteHelper.COLUMN_USERNAMES,userNames,null,null,null);
        cursor.moveToFirst();
        User newUser = cursorToUser(cursor);
        cursor.close();
        return newUser;
    }

    private User cursorToUser(Cursor cursor) {
        User user = new User();
        user.setId(cursor.getLong(0));
        user.setUserName(cursor.getString(1));
        user.setPassword(cursor.getString(2));
        return user;
    }

}
