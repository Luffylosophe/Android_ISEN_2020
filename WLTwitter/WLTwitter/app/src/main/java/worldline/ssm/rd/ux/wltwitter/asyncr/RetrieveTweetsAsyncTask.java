package worldline.ssm.rd.ux.wltwitter.asyncr;

import android.os.AsyncTask;
import android.util.Log;

import worldline.ssm.rd.ux.wltwitter.WLTwitterApplication;
import worldline.ssm.rd.ux.wltwitter.helpers.TwitterHelper;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;
import worldline.ssm.rd.ux.wltwitter.interfaces.TweetChangeListener;
import java.util.List;

public class RetrieveTweetsAsyncTask extends AsyncTask<String, Void, List<Tweet>> {
    private TweetChangeListener mListener;

    public RetrieveTweetsAsyncTask(TweetChangeListener mListener) {
        this.mListener = mListener;
    }

    protected List<Tweet> doInBackground(String...strings){
        if(null!=strings && strings.length>0){
            return TwitterHelper.getTweetsOfUser(strings[0]);
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Tweet> tweets) {
        super.onPostExecute(tweets);
        mListener.onTweetRetrieved(tweets);
    }
}
