package com.patrickdurke.trackremindtodo.ui.track.area;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.patrickdurke.trackremindtodo.R;

//"CollectionDemoFragment" https://developer.android.com/guide/navigation/navigation-swipe-view-2
public class AreaTabFragment extends Fragment {
    // When requested, this adapter returns a DemoObjectFragment,
    // representing an object in the collection.
    AreaFragmentStateAdapter areaFragmentStateAdapter; //"DemoCollectionAdapter"
    ViewPager2 viewPager;
    private final String[] titles = new String[]{"Records", "Graph"};

    private int selectedAreaId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectedAreaId = getArguments().getInt(getString(R.string.selectedAreaId));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.track_area_tab_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        areaFragmentStateAdapter = new AreaFragmentStateAdapter(this, selectedAreaId);
        viewPager = view.findViewById(R.id.pager);
        viewPager.setAdapter(areaFragmentStateAdapter);

        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(titles[position])
        ).attach();

    }
}

