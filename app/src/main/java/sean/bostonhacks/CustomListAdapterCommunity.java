package sean.bostonhacks;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CustomListAdapterCommunity  extends ArrayAdapter<Community> {
    private ArrayList<String> list = new ArrayList<String>();
    private Context context;


    public CustomListAdapterCommunity(Context context, int textViewResourceId, List<Community> objects) {
        super(context, textViewResourceId, objects);
        // TODO Auto-generated constructor stub
        this.context = context;
    }

    /*private view holder class*/
    private class ViewHolder {
        TextView name;
        TextView role;
        Button twitter;
        Button email;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        Community rowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.communitylayout, null);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.role = (TextView) convertView.findViewById(R.id.role);
            holder.twitter = (Button) convertView.findViewById(R.id.twitter_btn);
            holder.email = (Button) convertView.findViewById(R.id.mail_btn);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.name.setText(rowItem.getName());
        holder.role.setText(rowItem.getRole());
        holder.twitter.setText(rowItem.getTwitterHandle());
        holder.email.setText(rowItem.getEmail());

        holder.twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
                notifyDataSetChanged();
            }
        });
        holder.email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

}