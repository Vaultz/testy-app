package com.example.vault.testyapp.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vault.testyapp.API.API;
import com.example.vault.testyapp.API.PostResponse;
import com.example.vault.testyapp.Article;
import com.example.vault.testyapp.ListAdapter;
import com.example.vault.testyapp.Events.OnListItemClickListener;
import com.example.vault.testyapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by vault on 15/03/2017.
 */

/* ArticlesListFragment : fragment affichage une liste d'articles */
public class ArticlesListFragment extends Fragment implements OnListItemClickListener {
    protected Article[] articles;

    // newInstance() sert à générer le fragment en lui passant des paramètres
    // en l'occurrence, le nom de la catégorie choisie
    public static ArticlesListFragment newInstance(String category) {
        Bundle args = new Bundle();
        args.putString("category", category);
        ArticlesListFragment fragment = new ArticlesListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recycler, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        final RecyclerView rView = (RecyclerView) view.findViewById(R.id.myRecycler);
        rView.setLayoutManager(new GridLayoutManager(getActivity(), getResources().getInteger(R.integer.grid)));


        // Retrofit est une bibliothèque de requêtes API
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.goglasses.fr/")
                .addConverterFactory(
                        JacksonConverterFactory.create())
                .build();
        API api = retrofit.create(API.class);


        // on récupère l'éventuelle catégorie
        String choosenCategory = getArguments().getString("category");
        Call<PostResponse> call;

        if (choosenCategory == "recent") {
            call = api.getRecentPosts();
        }

        else {
            call = api.getCategoryPosts(choosenCategory);
        }


        // On reçoit la requête
        call.enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                Article[] articlesList = response.body().articles;
                rView.setAdapter(new ListAdapter(articlesList, ArticlesListFragment.this));
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {
                t.printStackTrace();
            }

        });

        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onHeaderClicked(Article article) {
        Intent intent = new Intent(getActivity(), WebviewActivity.class);
        intent.putExtra("article", article);
        startActivity(intent);
    }

    @Override
    public void onItemClicked(Article article) {

    }

}
