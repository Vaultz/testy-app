package com.example.vault.testyapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.vault.testyapp.DB.DatabaseHelper;
import com.example.vault.testyapp.Preferences.PreferenceActivity;
import com.example.vault.testyapp.Views.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ouverture de la BD
        //SQLiteDatabase db = new DatabaseHelper(getContext()).getWritableDatabase();
        DatabaseHelper.getInstance(getApplicationContext());

        // récupération du FrameLayout vide
        setContentView(R.layout.articles_fragment);

        // création d'un ViewPager
        ViewPager viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(new ViewPagerAdapter(this));


        //db.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuPrefs) {
            Intent intent = new Intent(this, PreferenceActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


