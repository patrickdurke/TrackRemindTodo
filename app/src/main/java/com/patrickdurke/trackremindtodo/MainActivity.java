package com.patrickdurke.trackremindtodo;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.patrickdurke.trackremindtodo.ui.track.area.AreaFragment;
import com.patrickdurke.trackremindtodo.ui.track.area.AreaFragmentDirections;
import com.patrickdurke.trackremindtodo.ui.track.area.parameter.ParameterFragment;
import com.patrickdurke.trackremindtodo.ui.track.area.record.RecordFragment;
import com.patrickdurke.trackremindtodo.ui.track.area.record.RecordFragmentDirections;
import com.patrickdurke.trackremindtodo.ui.track.area.record.entry.EntryFragment;
import com.patrickdurke.trackremindtodo.ui.track.area.record.entry.EntryFragmentDirections;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
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

}
