package worldline.ssm.rd.ux.wltwitter.async;

import android.graphics.Bitmap;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import worldline.ssm.rd.ux.wltwitter.helpers.TwitterHelper;


public class GetImages extends AsyncTask<String, Void, Bitmap> {

    private ImageView mImage;

    public GetImages(ImageView img) {
        mImage = img;
    }

    @Override
    protected Bitmap doInBackground(String... url) {
        if (url == null || url.length == 0) {
            return null;
        }
        try {
            return TwitterHelper.getTwitterUserImage(url[0]);
        } catch (Exception e) {
            Log.e("GetImages", e.toString());
            return null;
        }

    }


    @Override
    protected void onPostExecute(Bitmap bm) {
        super.onPostExecute(bm);
        if (bm != null) {
            mImage.setImageBitmap(bm);
        }
    }

}
