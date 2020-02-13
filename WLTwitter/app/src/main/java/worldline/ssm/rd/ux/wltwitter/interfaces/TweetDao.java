package worldline.ssm.rd.ux.wltwitter.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;

@Dao
public interface TweetDao {
    @Query("SELECT * FROM Tweet")
    List<Tweet> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Tweet tweet);
}
