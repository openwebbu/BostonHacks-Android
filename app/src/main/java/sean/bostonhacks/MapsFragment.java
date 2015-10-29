package sean.bostonhacks;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;


public class MapsFragment extends Fragment{

    public MapsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Maps");
        TabLayout tabLayout = (TabLayout) (getActivity()).findViewById(R.id.tabs);
        Drawable announcements = getActivity().getResources().getDrawable(R.drawable.ic_announcement_white_24dp);
        announcements.setAlpha(100);
        Drawable events = getActivity().getResources().getDrawable(R.drawable.ic_event_note_white_24dp);
        events.setAlpha(100);
        Drawable maps = getActivity().getResources().getDrawable(R.drawable.ic_map_white_24dp);
        maps.setAlpha(255);
        Drawable connect = getActivity().getResources().getDrawable(R.drawable.ic_people_white_24dp);
        connect.setAlpha(100);
        tabLayout.getTabAt(0).setIcon(announcements);
        tabLayout.getTabAt(1).setIcon(events);
        tabLayout.getTabAt(2).setIcon(maps);
        tabLayout.getTabAt(3).setIcon(connect);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_maps, container, false);

    }

}