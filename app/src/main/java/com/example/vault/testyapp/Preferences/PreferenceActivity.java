package com.example.vault.testyapp.Preferences;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.vault.testyapp.R;


/**
 * Created by vault on 15/03/2017.
 */

public class PreferenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.preference_fragment);

        PreferenceFragment pf = new PreferenceFragment();

        getFragmentManager().beginTransaction()
                .replace(R.id.preferencelayout, pf)
                .commitAllowingStateLoss();

    }

}
