package hackathon.android.com.app;

/**
 * Created by jayant on 9/21/14.
 */
public class User
{
    private long id;
    private String userName;
    private String password;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return id + " " + userName + " " + password;
    }
}
