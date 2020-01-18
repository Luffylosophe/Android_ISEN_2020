package worldline.ssm.rd.ux.wltwitter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import worldline.ssm.rd.ux.wltwitter.utils.Constants;

public class loginActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton=findViewById(R.id.button);
        loginButton.setOnClickListener(this);
    }

    @SuppressLint("ShowToast")
    @Override
    public void onClick(View v) {
        EditText usernameEditText = findViewById(R.id.usernameEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);

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

    }

