package com.example.android.menuapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //share button, cool way !
    private ShareActionProvider shareActionProvider;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView navigationView;
    private DrawerLayout mDrawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);//esto parece que termina llamando onCreateOptionsMenu y onOptionsItemSelected


        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        // configuracion de nav drawer
        mDrawerLayout = (DrawerLayout)
                findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                R.string.drawer_open,
                R.string.drawer_close
        );
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.menu_item_share);
        shareActionProvider= (ShareActionProvider) MenuItemCompat.getActionProvider(item);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "http://www.lslutnfra.com");

        shareActionProvider.setShareIntent(intent);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_settings: {
                Log.d("item menu", "action setting");
                break;
            }
            case R.id.act1: {
                Log.d("item menu", "act1");
                break;
            }
            case R.id.act2: {
                Log.d("item menu", "act2");
                break;
            }
            case android.R.id.home:{
                Log.d("item menu", "back!");
                break;

            }
        }

        return super.onOptionsItemSelected(item);//la idea es que pase por aca solo cuando no lo intercepto
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void setContenido(int numeroContenido)
    {
        Fragment fragment;

        Bundle bundle = new Bundle();
        bundle.putInt("idPlaneta",numeroContenido);

        if(numeroContenido%2==0)
            fragment = new Contenido1();
        else
            fragment = new Contenido2();

        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.contenedor, fragment)
                .commit();

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        menuItem.setChecked(true);
        switch(menuItem.getItemId())
        {
            case R.id.drawer_item_mercurio:
                setContenido(0);
                break;
            case R.id.drawer_item_venus:
                setContenido(1);
                break;
            case R.id.drawer_item_tierra:
                break;
            case R.id.drawer_item_marte:
                break;
            case R.id.drawer_item_jupiter:
                break;
        }
        mDrawerLayout.closeDrawers();
        return false;
    }

}
