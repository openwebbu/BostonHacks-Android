package sean.bostonhacks;

/**
 * Created by Sean on 10/21/15.
 */
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomListAdapterSchedule extends ArrayAdapter<Schedule> {

    Context context;
    String theDay = "";
    boolean setDivider = false;

    public CustomListAdapterSchedule(Context context, int textViewResourceId, List<Schedule> objects) {
        super(context, textViewResourceId, objects);
        // TODO Auto-generated constructor stub
        this.context = context;
    }

    public int getItemType(Schedule scheduleItem) {
        if(scheduleItem.getTitle().equals("Registration")){
            theDay="Saturday";
            setDivider = false;
            return 0;
        }
        else if(scheduleItem.getTitle().equals("Midnight Snack")){
            theDay="Sunday";
            setDivider = true;
            return 0;
        }
        else {
            return 1;
        }
    }

    /*private view holder class*/
    private class ViewHolder {
        TextView divider;
        TextView day;
        TextView scheduleTitle;
        TextView scheduleContent;
        TextView scheduleTimeStamp;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        Schedule rowItem = getItem(position);
        int rowType = getItemType(rowItem);
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            holder = new ViewHolder();

            switch(rowType) {
                case 0:
                    convertView = mInflater.inflate(R.layout.schedule_with_header, null);
                    holder.day = (TextView) convertView.findViewById(R.id.day);
                    if(setDivider)
                        convertView.findViewById(R.id.divider).setVisibility(View.VISIBLE);
                    else
                        convertView.findViewById(R.id.divider).setVisibility(View.GONE);
                    holder.scheduleTitle = (TextView) convertView.findViewById(R.id.title);
                    holder.scheduleContent = (TextView) convertView.findViewById(R.id.content);
                    holder.scheduleTimeStamp = (TextView) convertView.findViewById(R.id.timestamp);
                    break;
                case 1:
                    convertView = mInflater.inflate(R.layout.schedule_layout, null);
                    holder.scheduleTitle = (TextView) convertView.findViewById(R.id.title);
                    holder.scheduleContent = (TextView) convertView.findViewById(R.id.content);
                    holder.scheduleTimeStamp = (TextView) convertView.findViewById(R.id.timestamp);
                    break;
            }
            convertView.setTag(holder);

        } else
            holder = (ViewHolder) convertView.getTag();
        if(rowType == 0) {
            holder.day.setText(theDay);
        }
        holder.scheduleTitle.setText(rowItem.getTitle());
        holder.scheduleContent.setText(rowItem.getContent());
        holder.scheduleTimeStamp.setText(rowItem.getTimeStamp());

        convertView.setClickable(false);
        convertView.setEnabled(false);
        return convertView;
    }


}