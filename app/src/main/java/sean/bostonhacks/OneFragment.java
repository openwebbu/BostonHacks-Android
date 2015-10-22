package sean.bostonhacks;

import android.support.v4.app.Fragment;
import android.app.ListActivity;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;
import java.util.List;



public class OneFragment extends ListFragment implements SwipeRefreshLayout.OnRefreshListener {
    private List<Schedule> posts;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private CustomListAdapterSchedule mAdapter;


    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        posts = new ArrayList<Schedule>();

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //create swiperefresh
//        mSwipeRefreshLayout = (SwipeRefreshLayout) mSwipeRefreshLayout.findViewById((R.id.swipeSchedule));
//        mSwipeRefreshLayout.setOnRefreshListener(this);

        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_one,container,false);
        //create adapter
        mAdapter = new CustomListAdapterSchedule(getActivity(), android.R.id.list, posts);
                //bind adapter to listfragment
        setListAdapter(mAdapter);
        refreshPostList();
        return rootView;

    }
    private void refreshPostList() {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Events");

        query.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> postList, ParseException e) {
                if (e == null) {
                    // If there are results, update the list of posts
                    // and notify the adapter
                    posts.clear();
                    for (ParseObject post : postList) {
                        Schedule note = new Schedule(post.getObjectId(), post.getString("title"), post.getString("location"),post.getDate("date"));
                        posts.add(note);
                    }
                    ((ArrayAdapter<Schedule>) getListAdapter()).notifyDataSetChanged();
                } else {
                    Log.d(getClass().getSimpleName(), "Error: " + e.getMessage());
                }
            }
        });
    }

    @Override
    public void onRefresh() {

    }
}

