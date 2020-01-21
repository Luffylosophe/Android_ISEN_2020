package worldline.ssm.rd.ux.wltwitter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import worldline.ssm.rd.ux.wltwitter.utils.Constants;

public class loginActivity extends AppCompatActivity implements View.OnClickListener {
    private CheckBox checkbox_meat;

    private SharedPreferences myPreferences ;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        checkbox_meat=(CheckBox)findViewById(R.id.checkbox_meat);

        Button loginButton=findViewById(R.id.button);
        loginButton.setOnClickListener(this);

        myPreferences = getPreferences(MODE_PRIVATE);
        editor=myPreferences.edit();

        checkPreferences();
    }

    @SuppressLint("ShowToast")
    @Override
    public void onClick(View v) {

        EditText usernameEditText = findViewById(R.id.usernameEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);
        if(checkbox_meat.isChecked()){
            editor.putString((getString(R.string.checkbox)),"True");
            editor.commit();

            String username=usernameEditText.getText().toString();
            editor.putString((getString(R.string.username)),username);
            editor.commit();

            String password=passwordEditText.getText().toString();
            editor.putString((getString(R.string.password)),username);
            editor.commit();
        }
        else {
            editor.putString((getString(R.string.checkbox)),"remember me");
            editor.commit();

            editor.putString((getString(R.string.username)),"");
            editor.commit();

            editor.putString((getString(R.string.password)),"");
            editor.commit();

        }

        if (TextUtils.isEmpty(usernameEditText.getText())) {
            Toast.makeText(getApplicationContext(), getText(R.string.ERRORUsernameEmpty), Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(passwordEditText.getText())) {
            Toast.makeText(getApplicationContext(), getText(R.string.ERRORPasswordEmpty), Toast.LENGTH_LONG).show();
            return;
        }
    startActivity(getHoIntent(usernameEditText.getText().toString()));

    }

    private Intent getHoIntent(String userName){
            final Intent homeIntent = new Intent(this, WLTwitterActivity.class);
            final Bundle extras = new Bundle();

            extras.putString(Constants.Login.EXTRA_LOGIN,userName);
            homeIntent.putExtras(extras);
            return homeIntent;
    }

    private void checkPreferences(){
        EditText usernameEditText = findViewById(R.id.usernameEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);

        String checkbox=myPreferences.getString(getString(R.string.checkbox),"remember me");
        String username=myPreferences.getString(getString(R.string.username),"");
        String password=myPreferences.getString(getString(R.string.password),"");

        usernameEditText.setText(username);
        passwordEditText.setText(password);
        if(checkbox.equals("True")){
            checkbox_meat.setText("True");
        }
        else{
            checkbox_meat.setText("remember me");
        }
    }



}

