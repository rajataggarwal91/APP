package hackathon.android.com.app;

import android.app.Activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;


public class login extends Activity
{
    EditText username;
    EditText passWord;
    Button login;
    private UsersDataSource datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.editText);
        passWord = (EditText) findViewById(R.id.editText2);
        login = (Button)findViewById(R.id.button);
        login.setOnClickListener(loginListener);
        datasource = new UsersDataSource(this);
        datasource.open();

    }

    protected void onResume() {
        datasource.open();
        super.onResume();
    }

    public View.OnClickListener loginListener = new View.OnClickListener() {
        @Override
        public void onClick(View view)
        {
//            System.out.println("Hello World "+username.getText()+passWord.getText());
//            datasource.createUser(username.getText().toString(),passWord.getText().toString());
//            System.out.println("Done!");
            List<User> li=datasource.getAllUsers();


        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onPause() {
        datasource.close();
        super.onPause();
    }
}
