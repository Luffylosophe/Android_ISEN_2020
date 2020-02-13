package worldline.ssm.rd.ux.wltwitter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.room.Room;

import worldline.ssm.rd.ux.wltwitter.async.RetrieveTweetsAsyncTask;
import worldline.ssm.rd.ux.wltwitter.interfaces.TweetListener;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;
import worldline.ssm.rd.ux.wltwitter.ui.fragments.TweetsFragment;
import worldline.ssm.rd.ux.wltwitter.utils.Constants;
import worldline.ssm.rd.ux.wltwitter.utils.PreferenceUtils;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WLTwitterActivity extends AppCompatActivity implements TweetListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wltwitter);

        final Intent intent = getIntent();
        if(null!= intent){
            final Bundle extras = intent.getExtras();
            if(null!= extras )
            {
                final String  login = extras.getString(Constants.Login.EXTRA_LOGIN);
                getSupportActionBar().setSubtitle(login);

            }
        }
        if(savedInstanceState==null)
        {
            getSupportFragmentManager().beginTransaction().add(R.id.container,new TweetsFragment()).commit();
        }

        final TweetDatabase db = Room.databaseBuilder(this,
                TweetDatabase.class, "ma_bdd.db").build();
        ExecutorService executor= Executors.newSingleThreadExecutor();
        executor.submit(new Runnable() {
            @Override
            public void run() {
                Tweet tweet=new Tweet();
                tweet.setId("564");
                tweet.setText(" 1,2,3,viva Os");

                db.tweetDao().insert(tweet);

            }
        });

        executor.submit(new Runnable() {
            @Override
            public void run() {
                List<Tweet> tweets = db.tweetDao().getAll();
                for(Tweet var:tweets){
                    Log.d("BDD",var.getText());

                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.wltwitter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //action de logout
        if (id == R.id.actionLogout) {
            PreferenceUtils.setLogin(null);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRetweet(Tweet tweet) {
        Toast.makeText(this,tweet.getText(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onViewTweet(Tweet tweet) {
        Toast.makeText(this,"OnViewTweet"+tweet.getText(),Toast.LENGTH_SHORT).show();
    }
}
