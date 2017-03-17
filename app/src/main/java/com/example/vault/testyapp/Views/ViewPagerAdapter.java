package com.example.vault.testyapp.Views;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;

import com.example.vault.testyapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by vault on 15/03/2017.
 */


// FragmentPagerAdapter : permet de charger plusieurs fragments entre lesquels l'utilisateur peut swipe
public class ViewPagerAdapter extends FragmentPagerAdapter {
    protected Set<String> prefValues;
    protected SharedPreferences userPrefs;


    public ViewPagerAdapter(AppCompatActivity activity) {
        super(activity.getSupportFragmentManager());
        userPrefs = PreferenceManager.getDefaultSharedPreferences(activity);

        prefValues = userPrefs.getStringSet("key_prefs", null);

        if (prefValues == null) {
            String[] newPrefs = activity.getResources().getStringArray(R.array.categories_values);
            prefValues = new HashSet<String>(Arrays.asList(newPrefs));
        }


    }

    @Override
    public int getCount() {
        return (prefValues.size()+1);
    }

    @Override
    public Fragment getItem(int position) {
        String[] categories;
        String choosenCateg;

        if (position == 0) {
            return new ArticlesListFragment().newInstance("recent");
        }

        else {
            // on récupère la catégorie sélectionnée
            categories = prefValues.toArray(new String[0]);
            choosenCateg = categories[position];

            // on appelle le fragment en lui passant la catégorie en paramètre
            return new ArticlesListFragment().newInstance(choosenCateg);
        }
    }

    @Override
    public String getPageTitle(int position) {
        if (position == 0) {
            return "Recent";
        }

        List<String> prefList = new ArrayList<String>(prefValues);
        String title = prefList.get(position-1);
        return title;
    }
}