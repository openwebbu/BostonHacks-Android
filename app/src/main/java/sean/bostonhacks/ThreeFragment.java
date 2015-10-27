package sean.bostonhacks;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
public class ThreeFragment extends ListFragment {
    private List<Community> communityMembers;

    private CustomListAdapterCommunity mAdapter;
    private Handler handler = new Handler();

    public ThreeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        communityMembers = new ArrayList<Community>();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_three, container, false);

        //create adapter
        mAdapter = new CustomListAdapterCommunity(getActivity(), android.R.id.list, communityMembers);

        // inflate custom header and attach it to the list
        View header = (View) inflater.inflate(R.layout.header, null, false);

        ListView mList = (ListView) rootView.findViewById(android.R.id.list);
        mList.addHeaderView(header, null, false);

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
                        Community team = new Community(post.getObjectId(), post.getString("name"), post.getString("role"), post.getString("twitter"),post.getString("email"));
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
