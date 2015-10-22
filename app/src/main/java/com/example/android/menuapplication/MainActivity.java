package com.example.android.menuapplication;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    //share button, cool way !
    private ShareActionProvider shareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);//esto parece que termina llamando onCreateOptionsMenu y onOptionsItemSelected


        //para meter el back neesitamos este codigo
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.menu_item_share);
        shareActionProvider= (ShareActionProvider) MenuItemCompat.getActionProvider(item);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,"http://www.lslutnfra.com");

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
}
