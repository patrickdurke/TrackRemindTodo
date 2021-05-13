package com.patrickdurke.trackremindtodo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.patrickdurke.trackremindtodo.ui.SignInActivity;
import com.patrickdurke.trackremindtodo.ui.track.Area;

import androidx.annotation.NonNull;

import androidx.core.view.GravityCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    FloatingActionButton fab;
    NavigationView navigationView;
    private MainActivityViewModel viewModel;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        checkIfSignedIn();
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView textViewUserEmail = headerView.findViewById(R.id.text_user_email);
        TextView textViewUserName = headerView.findViewById(R.id.text_user_name);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        textViewUserEmail.setText(currentUser.getEmail());
        textViewUserName.setText(currentUser.getDisplayName());

        fab = findViewById(R.id.fab);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_track, R.id.nav_remind, R.id.nav_todo)
                .setOpenableLayout(drawer) //https://stackoverflow.com/questions/62386279/setdrawerlayoutandroidx-drawerlayout-widget-drawerlayout-is-deprecated
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);


        //https://stackoverflow.com/questions/60072986/onnavigationitemselected-not-calling-when-item-is-selected
        navigationView.setNavigationItemSelectedListener(item -> {
            boolean handled = NavigationUI.onNavDestinationSelected(item, navController);

            if(!handled) {
                int id = item.getItemId();
                if (id == R.id.nav_logout) {
                    viewModel.signOut();
                    return true;
                }
            }

            drawer.closeDrawer(GravityCompat.START);
            return handled;
        });
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //       // Default
        return super.onOptionsItemSelected(item);
        //       /*When you successfully handle a menu item, return true.
        //       If you don't handle the menu item, you should call the superclass implementation of onOptionsItemSelected()
        //       (the default implementation returns false).
        //       https://developer.android.com/guide/topics/ui/menus*/
    }

    private void checkIfSignedIn() {
        viewModel.getCurrentUser().observe(this, user -> {
            if (user == null) {
                startLoginActivity();
            }
        });
    }

    private void startLoginActivity() {
        startActivity(new Intent(this, SignInActivity.class));
        finish();
    }

    private void setupNavigationView(NavigationView navigationView) {
    }

}
