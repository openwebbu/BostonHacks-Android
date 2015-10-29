package sean.bostonhacks;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.common.collect.Lists;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConnectFragment extends ListFragment {
    private List<Community> communityMembers;
    private Button connect_button;

    private CustomListAdapterCommunity mAdapter;
    private Handler handler = new Handler();

    public ConnectFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Connect");

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        communityMembers = new ArrayList<Community>();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (AppStatus.getInstance(this.getActivity()).isOnline()) {
            this.getActivity().findViewById(R.id.loadingPanel).setVisibility(View.GONE);
        } else {
            Toast.makeText(this.getActivity(), "No internet connection found. Please reconnect and refresh the page.", Toast.LENGTH_LONG).show();
//            Log.v("Home", "############################You are not online!!!!");
        }

        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_connect, container, false);

        //create adapter
        mAdapter = new CustomListAdapterCommunity(getActivity(), android.R.id.list, communityMembers);

        // inflate custom header and attach it to the list
        View header = (View) inflater.inflate(R.layout.header, null, false);
        ListView mList = (ListView) rootView.findViewById(android.R.id.list);
        mList.addHeaderView(header, null, false);

        View connectButton = rootView.findViewById(R.id.connect_button);
        connectButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Uri uri = Uri.parse("https://bostonhacks.slack.com");

                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        //bind adapter to listfragment
        setListAdapter(mAdapter);
        refreshPostList();

        return rootView;
    }

    private void refreshPostList() {


        ParseQuery<ParseObject> query = ParseQuery.getQuery("People");

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> postList, ParseException e) {
                if (e == null) {
                    // If there are results, update the list of posts
                    // and notify the adapter
                    communityMembers.clear();
                    for (ParseObject post : Lists.reverse(postList)) {
                        Community team = new Community(post.getObjectId(), post.getString("name"), post.getString("role"), post.getString("twitter"), post.getString("email"));
                        communityMembers.add(team);
                    }
                    ((ArrayAdapter<Schedule>) getListAdapter()).notifyDataSetChanged();

                } else {

                    Log.d(getClass().getSimpleName(), "Error: " + e.getMessage());
                }
            }
        });

    }
}
