package sean.bostonhacks;

/**
 * Created by Sean on 10/21/15.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomListAdapterAnnouncements extends ArrayAdapter<Announcements> {

    Context context;

    public CustomListAdapterAnnouncements(Context context, int textViewResourceId, List<Announcements> objects) {
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
        Announcements rowItem = getItem(position);

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

        convertView.setClickable(false);
        convertView.setEnabled(false);
        return convertView;
    }

}