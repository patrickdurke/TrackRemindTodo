package com.patrickdurke.trackremindtodo.ui.track.area;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class AreaFragmentStateAdapter extends FragmentStateAdapter {

    //private List<Fragment> fragments = new ArrayList<>();
    private int selectedAreaId;

    public AreaFragmentStateAdapter(Fragment fragment, int selectedAreaId) {
        super(fragment);

        this.selectedAreaId = selectedAreaId;
        //fragments.add(new AreaFragment(selectedAreaId));
        //fragments.add(new AreaFragment(-1)); //TODO: Graph fragment instead here
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Return a NEW fragment instance in createFragment(int)
        //Fragment fragment = new DemoObjectFragment();
        Fragment fragment = new AreaFragment();

        Bundle args = new Bundle();
        args.putInt(AreaFragment.ARG_SELECTED_AREA_ID, selectedAreaId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
