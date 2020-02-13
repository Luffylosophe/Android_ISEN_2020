package worldline.ssm.rd.ux.wltwitter.pojo;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.google.gson.annotations.SerializedName;

@Entity
public class Tweet {
	@PrimaryKey() @NonNull
	private  String id;

	@ColumnInfo()
	private String text;

	@SerializedName("created_at")
	public String dateCreated;

	@SerializedName("in_reply_to_status_id")
	public String inReplyToStatusId;

	@SerializedName("in_reply_to_user_id")
	public String inReplyToUserId;

	@SerializedName("in_reply_to_screen_name")
	public String inReplyToScreenName;

	@SerializedName("user")
	public TwitterUser user;


	public Tweet() {
	}

	@Override
	public String toString() {
		return text;
	}
	
	public long getDateCreatedTimestamp(){
		final SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZZZ yyyy", Locale.ENGLISH);
		dateFormat.setLenient(false);
		try {
			final Date created = dateFormat.parse(dateCreated);
			return created.getTime();
		} catch (Exception e) {
			return 0;
		}
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String id) {
		this.text = id;
	}
	
}