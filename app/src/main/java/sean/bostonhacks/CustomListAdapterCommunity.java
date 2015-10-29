package sean.bostonhacks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

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
        ImageButton twitter;
        ImageButton email;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        final Community rowItem = getItem(position);

        int viewType = getItemViewType(position);

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.communitylayout, null);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.role = (TextView) convertView.findViewById(R.id.role);
            holder.twitter = (ImageButton) convertView.findViewById(R.id.twitter_btn);
            holder.email = (ImageButton) convertView.findViewById(R.id.mail_btn);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.name.setText(rowItem.getName());
        holder.role.setText(rowItem.getRole());
        if(rowItem.getTwitterHandle()==null)
            holder.twitter.setImageResource(R.drawable.ic_twitter_inactive);
        else
            holder.twitter.setImageResource(R.drawable.ic_twitter_active);
        holder.email.setImageResource(R.drawable.ic_email_active);

        holder.twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rowItem.getTwitterHandle() != null) {
                    Intent tweetIntent = new Intent(Intent.ACTION_VIEW);
                    tweetIntent.putExtra(Intent.EXTRA_TEXT, "@" + rowItem.getTwitterHandle() + " ");
                    tweetIntent.setType("text/plain");

                    PackageManager packManager = context.getPackageManager();
                    List<ResolveInfo> resolvedInfoList = packManager.queryIntentActivities(tweetIntent, PackageManager.MATCH_DEFAULT_ONLY);

                    boolean resolved = false;
                    for (ResolveInfo resolveInfo : resolvedInfoList) {
                        if (resolveInfo.activityInfo.packageName.startsWith("com.twitter.android")) {
                            tweetIntent.setClassName(
                                    resolveInfo.activityInfo.packageName,
                                    resolveInfo.activityInfo.name);
                            resolved = true;
                            break;
                        }
                    }
                    if (resolved) {
                        context.startActivity(tweetIntent);
                    } else {
                        Intent i = new Intent();
                        i.putExtra(Intent.EXTRA_TEXT, "");
                        i.setAction(Intent.ACTION_VIEW);
                        i.setData(Uri.parse("https://twitter.com/intent/tweet?text=@" + rowItem.getTwitterHandle()));
                        context.startActivity(i);
                    }
                }
            }
        });
        holder.email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SENDTO);
                Uri data = Uri.parse("mailto:" + rowItem.getEmail());
                i.setData(data);
                context.startActivity(Intent.createChooser(i, "Send mail..."));

            }
        });

        return convertView;
    }



}