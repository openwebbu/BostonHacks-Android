package sean.bostonhacks;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
/**
 * Created by Claire on 10/18/2015.
 */
@ParseClassName("announcements")
public class announcements extends ParseObject{

    public String getTitle() {
        return getString("title");
    }

    public void setTitle(String title) {
        put("title", title);
    }
/*
    public ParseUser getAuthor() {
        return getParseUser("author");
    }

    public void setAuthor(ParseUser currentUser) {
        put("author", currentUser);
    }

    public boolean isDraft() {
        return getBoolean("isDraft");
    }

    public void setDraft(boolean isDraft) {
        put("isDraft", isDraft);
    }*/

    public void setUuidString() {
        UUID uuid = UUID.randomUUID();
        put("uuid", uuid.toString());
    }

    public String getUuidString() {
        return getString("uuid");
    }

    public static ParseQuery<Todo> getQuery() {
        return ParseQuery.getQuery(announcements.class);
    }

}
