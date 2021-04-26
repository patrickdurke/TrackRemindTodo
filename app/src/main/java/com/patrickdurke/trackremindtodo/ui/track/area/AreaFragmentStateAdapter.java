package com.patrickdurke.trackremindtodo.ui.track.area;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class AreaFragmentStateAdapter extends FragmentStateAdapter {

    private List<Fragment> fragments = new ArrayList<>();
    private int selectedAreaId;

    public AreaFragmentStateAdapter(Fragment fragment, int selectedAreaId) {
        super(fragment);

        this.selectedAreaId = selectedAreaId;
        fragments.add(new AreaFragment());
        fragments.add(new AreaChartFragment());
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = fragments.get(position);

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
