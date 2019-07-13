package in.royelectricals.securetracx;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SessionMgt session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Session class instance
        session = new SessionMgt(getApplicationContext());

        Toast.makeText(getApplicationContext(),
                "User Login Status: " + session.isUserLoggedIn(),
                Toast.LENGTH_LONG).show();

        // Check user login (this is the important point)
        // If User is not logged in , This will redirect user to LoginActivity
        // and finish current activity from activity stack.
        if(session.checkLogin())
            finish();

        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // get email
        String email = user.get(SessionMgt.KEY_EMAIL);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (findViewById(R.id.container) != null) {
                    FeedbackFragment feedbackFragment = new FeedbackFragment();
                    feedbackFragment.setArguments(getIntent().getExtras());
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, feedbackFragment)
                            .addToBackStack(null)
                            .commit();
                }

            }
        });

        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.container, new DashboardFragment());
        tx.commit();

    /*    if (findViewById(R.id.container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create an instance of ExampleFragment
            DashboardFragment dashboardFragment = new DashboardFragment();

            // In case this activity was started with special instructions from an Intent,
            // pass the Intent's extras to the fragment as arguments
            dashboardFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, dashboardFragment)
                    .addToBackStack(null)
                    .commit();
        }
*/
        //Drawer==============
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }else if(id == R.id.action_profile){
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
        }else if(id == R.id.action_logout){
            session.logout();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_dashboard) {
            if (findViewById(R.id.container) != null) {
                DashboardFragment dashboardFragment = new DashboardFragment();
                dashboardFragment.setArguments(getIntent().getExtras());
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, dashboardFragment)
                        .addToBackStack(null)
                        .commit();
            }

        } else if (id == R.id.nav_location) {
            if (findViewById(R.id.container) != null) {
                LocationFragment locationFragment = new LocationFragment();
                locationFragment.setArguments(getIntent().getExtras());
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, locationFragment)
                        .addToBackStack(null)
                        .commit();
            }

        } else if (id == R.id.nav_location_poi) {
            if (findViewById(R.id.container) != null) {
                LocationHistoryFragment locationHistoryFragment = new LocationHistoryFragment();
                locationHistoryFragment.setArguments(getIntent().getExtras());
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, locationHistoryFragment)
                        .addToBackStack(null)
                        .commit();
            }

        } else if (id == R.id.nav_report) {
            if (findViewById(R.id.container) != null) {
                ReportFragment reportFragment = new ReportFragment();
                reportFragment.setArguments(getIntent().getExtras());
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, reportFragment)
                        .addToBackStack(null)
                        .commit();
            }

        } else if (id == R.id.nav_alert) {
            if (findViewById(R.id.container) != null) {
                AlertFragment alertFragment = new AlertFragment();
                alertFragment.setArguments(getIntent().getExtras());
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, alertFragment)
                        .addToBackStack(null)
                        .commit();
            }

        } else if (id == R.id.nav_feedback) {
            if (findViewById(R.id.container) != null) {
                FeedbackFragment feedbackFragment = new FeedbackFragment();
                feedbackFragment.setArguments(getIntent().getExtras());
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, feedbackFragment)
                        .addToBackStack(null)
                        .commit();
            }

        }else if (id == R.id.nav_about) {
            if (findViewById(R.id.container) != null) {
                AboutFragment aboutFragment = new AboutFragment();
                aboutFragment.setArguments(getIntent().getExtras());
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, aboutFragment)
                        .addToBackStack(null)
                        .commit();
            }

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
