package worldline.ssm.rd.ux.wltwitter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import worldline.ssm.rd.ux.wltwitter.utils.Constants;
import worldline.ssm.rd.ux.wltwitter.utils.PreferenceUtils;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class WLTwitterActivity extends AppCompatActivity {
    private SharedPreferences myPreferences ;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myPreferences = getPreferences(MODE_PRIVATE);
        editor=myPreferences.edit();

        Intent fromIntent = getIntent();
        String Username="";
        if (fromIntent != null) {
            if(fromIntent.getExtras()!=null) {
                Username = fromIntent.getExtras().getString(Constants.Login.EXTRA_LOGIN);
            }
                getSupportActionBar().setTitle(Username);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_settings:
                logout();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void logout(){
        String username=myPreferences.getString(getString(R.string.username),"");
        String password=myPreferences.getString(getString(R.string.password),"");
        editor.putString((getString(R.string.username)),"");
        editor.commit();
        editor.putString((getString(R.string.password)),"");
        editor.commit();
        finish();
        setContentView(R.layout.activity_login);
        return;
    }

    private Intent getHoIntent(String userName){
        final Intent homeIntent = new Intent(this, WLTwitterActivity.class);
        final Bundle extras = new Bundle();

        extras.putString(Constants.Login.EXTRA_LOGIN,userName);
        homeIntent.putExtras(extras);
        return homeIntent;
    }
}
