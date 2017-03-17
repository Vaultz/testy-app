package com.example.vault.testyapp.Views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.vault.testyapp.Article;
import com.example.vault.testyapp.R;

/**
 * Created by vault on 14/03/2017.
 */

// Activité de zoom sur un article
public class WebviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_html);

        Article clickedArticle = getIntent().getParcelableExtra("article");

        WebviewFragment wbf = WebviewFragment.newInstance(clickedArticle);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.webview, wbf)
                .commitAllowingStateLoss();

    }

}
