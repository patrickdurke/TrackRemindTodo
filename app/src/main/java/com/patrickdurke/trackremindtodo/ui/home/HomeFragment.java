package com.patrickdurke.trackremindtodo.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.patrickdurke.trackremindtodo.R;
import com.patrickdurke.trackremindtodo.ui.track.area.AreaTabFragmentDirections;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.home_fragment, container, false);

        CardView cardViewTrack = root.findViewById(R.id.card_home_track);
        CardView cardViewRemind = root.findViewById(R.id.card_home_remind);
        CardView cardViewTodo = root.findViewById(R.id.card_home_todo);

        cardViewTrack.setOnClickListener(v -> Navigation.findNavController(v).navigate(HomeFragmentDirections.actionNavHomeToNavTrack()));
        cardViewRemind.setOnClickListener(v -> Navigation.findNavController(v).navigate(HomeFragmentDirections.actionNavHomeToNavRemind()));
        cardViewTodo.setOnClickListener(v -> Navigation.findNavController(v).navigate(HomeFragmentDirections.actionNavHomeToNavTodo()));

        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), s -> textView.setText(s));

        ((FloatingActionButton) this.getActivity().findViewById(R.id.fab)).hide();
        
        return root;
    }
}