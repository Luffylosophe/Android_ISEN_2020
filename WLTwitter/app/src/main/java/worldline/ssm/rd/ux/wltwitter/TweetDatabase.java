package worldline.ssm.rd.ux.wltwitter;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import worldline.ssm.rd.ux.wltwitter.interfaces.TweetDao;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;

@Database(entities = {Tweet.class},version = 1,exportSchema = false)
public abstract class TweetDatabase extends RoomDatabase {

    public abstract TweetDao tweetDao();

}
