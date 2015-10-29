package sean.bostonhacks;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.common.collect.Lists;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;



public class TwoFragment extends ListFragment implements SwipeRefreshLayout.OnRefreshListener {
    private List<Announcements> announcements;
    private CustomListAdapterAnnouncements mAdapter;

    private SwipeRefreshLayout swipeLayout;

    public TwoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        announcements = new ArrayList<Announcements>();

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Announcements");

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


        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_two,container,false);

        //refresh thing
        swipeLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(this);

        //create adapter
        mAdapter = new CustomListAdapterAnnouncements(getActivity(), android.R.id.list, announcements);
        //bind adapter to listfragment
        setListAdapter(mAdapter);        //bind adapter to listfragment
        refreshAnnouncementsList();
        return rootView;

    }

    /**
     * This method is called when swipe refresh is pulled down
     */
    @Override
    public void onRefresh() {
        refreshAnnouncementsList();
        swipeLayout.setColorSchemeResources(R.color.colorPrimary);
    }

    private void refreshAnnouncementsList() {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Announcements");

        query.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> postList, ParseException e) {
                swipeLayout.setRefreshing(false);
                if (e == null) {
                    // If there are results, update the list of posts
                    // and notify the adapter
                    swipeLayout.setRefreshing(true);
                    announcements.clear();
                    for (ParseObject post : Lists.reverse(postList)) {
                        Announcements note = new Announcements(post.getObjectId(), post.getString("title"), post.getString("description"), post.getCreatedAt());
                        announcements.add(note);
                    }
                    ((ArrayAdapter<Announcements>) getListAdapter()).notifyDataSetChanged();
                    swipeLayout.setRefreshing(false);
                } else {
                    Log.d(getClass().getSimpleName(), "Error: " + e.getMessage());
                }
                swipeLayout.setRefreshing(false);
            }
        });
    }


}

