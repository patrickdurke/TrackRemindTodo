package com.patrickdurke.trackremindtodo.ui.track;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.patrickdurke.trackremindtodo.R;

public class TrackFragment extends Fragment {

    private TrackViewModel trackViewModel;
    private RecyclerView recyclerViewItemList;
    private AreaListAdapter areaListAdapter;

    private FloatingActionButton fab;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        areaListAdapter = new AreaListAdapter();

        trackViewModel = new ViewModelProvider(this).get(TrackViewModel.class);
        trackViewModel.init();

        //set observer on repository data and ensure update adapter data on changed repository data
        trackViewModel.getTrackingAreaListLiveData().observe(this, trackingAreaList -> {
            if (trackingAreaList != null) {
                areaListAdapter.setAreaList(trackingAreaList);
            }
        });

        fab = this.getActivity().findViewById(R.id.fab);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        trackViewModel =
                new ViewModelProvider(this).get(TrackViewModel.class);
        View root = inflater.inflate(R.layout.track_fragment, container, false);

        // create RecyclerView
        recyclerViewItemList = root.findViewById(R.id.track_recyclerview);
        recyclerViewItemList.setHasFixedSize(true);
        recyclerViewItemList.setLayoutManager(new GridLayoutManager(root.getContext(), 2));
        recyclerViewItemList.setAdapter(areaListAdapter);

        return root;
    }

   @Override
   public void onResume(){
       super.onResume();

       fab.show();
       setOnclickListener(fab);
   }

    private void setOnclickListener(FloatingActionButton fab){

        fab.setOnClickListener(v -> {
            TrackFragmentDirections.ActionNavTrackToTrackAreaFragment action
                    = TrackFragmentDirections.actionNavTrackToTrackAreaFragment(-1);
            fab.setOnClickListener(null);

            assert getParentFragment() != null;
            NavHostFragment.findNavController(getParentFragment()).navigate(action);
        });
    }



}