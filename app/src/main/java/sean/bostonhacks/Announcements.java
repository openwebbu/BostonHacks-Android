package sean.bostonhacks;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Announcements {

    private String id;
    private String title;
    private String content;
    private Date timestamp;

    Announcements(String noteId, String noteTitle, String noteContent, Date createdAt) {
        id = noteId;
        title = noteTitle;
        content = noteContent;
        timestamp = createdAt;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getTimeStamp(){
        String amPM = "";
        Calendar cal = Calendar.getInstance();
        cal.setTime(timestamp);
        if(cal.get(Calendar.AM_PM) ==0){
            amPM = "AM";
        }
        else{
            amPM = "PM";
        }
        String curTime = String.format("%02d:%02d", cal.get(cal.HOUR),cal.get(cal.MINUTE));
        return cal.getDisplayName(cal.DAY_OF_WEEK, cal.SHORT, Locale.US) + " " + curTime + amPM;
    }
    public void setTimeStamp(Date createdAt){
        this.timestamp = createdAt;
    }
    @Override
    public String toString() {
        return this.getTitle();
    }

}
