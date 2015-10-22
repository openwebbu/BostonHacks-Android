package sean.bostonhacks;

/**
 * Created by Sean on 10/21/15.
 */
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListAdapterSchedule extends ArrayAdapter<Schedule> {

    Context context;

    public CustomListAdapterSchedule(Context context, int textViewResourceId, List<Schedule> objects) {
        super(context, textViewResourceId, objects);
        // TODO Auto-generated constructor stub
        this.context = context;
    }

    /*private view holder class*/
    private class ViewHolder {
        TextView scheduleTitle;
        TextView scheduleContent;
        TextView scheduleTimeStamp;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        Schedule rowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.rowlayout, null);
            holder = new ViewHolder();
            holder.scheduleTitle = (TextView) convertView.findViewById(R.id.title);
            holder.scheduleContent = (TextView) convertView.findViewById(R.id.content);
            holder.scheduleTimeStamp = (TextView) convertView.findViewById(R.id.timestamp);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.scheduleTitle.setText(rowItem.getTitle());
        holder.scheduleContent.setText(rowItem.getContent());
        holder.scheduleTimeStamp.setText(rowItem.getTimeStamp());

        return convertView;
    }


}