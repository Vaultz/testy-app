package com.example.vault.testyapp.Events;

import com.example.vault.testyapp.Article;

/**
 * Created by vault on 15/03/2017.
 */

public interface OnListItemClickListener {
    void onHeaderClicked(Article article);
    void onItemClicked(Article article);
}
