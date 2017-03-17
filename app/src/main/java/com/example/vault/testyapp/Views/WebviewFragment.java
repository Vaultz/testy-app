package com.example.vault.testyapp.Views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.vault.testyapp.Article;
import com.example.vault.testyapp.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;

/**
 * Created by vault on 15/03/2017.
 */


// WebviewFragment : Fragment appelé par la Webview lors d'un clic sur le titre d'un article, dont le rôle est de charger le HTML
public class WebviewFragment extends Fragment {


    public static WebviewFragment newInstance(Article clickedArticle) {
        WebviewFragment fragment = new WebviewFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("article", clickedArticle);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.display_html, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Article article = getArguments().getParcelable("article");

        WebView webView = (WebView) view.findViewById(R.id.webview);
//        webview.loadData("file:///android_asset/template.html", "text/html", null);
        webView.loadDataWithBaseURL("file:///android_asset/", getPostContent(article), "text/html", "utf-8", "");
    }


    @Nullable
    public String getPostContent(@Nullable Article article) {
        if (article == null || article.getContent() == null) {
            return null;
        }

        String htmlTemplate = loadAssetTextAsString("html/template.html");
        if (htmlTemplate == null) {
            throw new NullPointerException();
        }

        return String.format(Locale.getDefault(),
                htmlTemplate,
                article.getContent());
    }

    @Nullable
    private String loadAssetTextAsString(@NonNull String fileName) {
        BufferedReader in = null;
        try {
            StringBuilder buf = new StringBuilder();
            InputStream is = getContext().getAssets().open(fileName);
            in = new BufferedReader(new InputStreamReader(is));

            String str;
            boolean isFirst = true;
            while ((str = in.readLine()) != null) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    buf.append('\n');
                }
                buf.append(str);
            }
            return buf.toString();
        } catch (IOException ignored) {
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ignored) {}
            }
        }

        return null;
    }
}

