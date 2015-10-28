package sean.bostonhacks;

import android.util.Log;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Schedule {

    private String id;
    private String title;
    private String content;
    private Date timestamp;

    Schedule(String noteId, String noteTitle, String noteContent, Date createdAt) {
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
        String amPM = "AM";
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        cal.setTime(timestamp);
//        Log.v("time", cal.get(cal.HOUR_OF_DAY) + "");
        switch(cal.get(cal.HOUR_OF_DAY)){
            case 0:
                cal.set(Calendar.HOUR_OF_DAY,12);
                break;
            case 12:
                amPM="PM";
                break;
            case 13:
                cal.set(Calendar.HOUR_OF_DAY, 1);
                amPM = "PM";
                break;
            case 14:
                cal.set(Calendar.HOUR_OF_DAY, 2);
                amPM = "PM";
                break;
            case 15:
                cal.set(Calendar.HOUR_OF_DAY, 3);
                amPM = "PM";
                break;
            case 16:
                cal.set(Calendar.HOUR_OF_DAY, 4);
                amPM = "PM";
                break;
            case 17:
                cal.set(Calendar.HOUR_OF_DAY, 5);
                amPM = "PM";
                break;
            case 18:
                cal.set(Calendar.HOUR_OF_DAY, 6);
                amPM = "PM";
                break;
            case 19:
                cal.set(Calendar.HOUR_OF_DAY, 7);
                amPM = "PM";
                break;
            case 20:
                cal.set(Calendar.HOUR_OF_DAY, 8);
                amPM = "PM";
                break;
            case 21:
                cal.set(Calendar.HOUR_OF_DAY, 9);
                amPM = "PM";
                break;
            case 22:
                cal.set(Calendar.HOUR_OF_DAY, 10);
                amPM = "PM";
                break;
            case 23:
                cal.set(Calendar.HOUR_OF_DAY, 11);
                amPM = "PM";
                break;
            case 24:
                cal.set(Calendar.HOUR_OF_DAY, 12);
                amPM = "PM";
                break;

        }

        String curTime = String.format("%d:%02d", cal.get(cal.HOUR_OF_DAY),cal.get(cal.MINUTE));
        return curTime + amPM;
    }
    public void setTimeStamp(Date createdAt){
        this.timestamp = createdAt;
    }
    @Override
    public String toString() {
        return this.getTitle();
    }

}
