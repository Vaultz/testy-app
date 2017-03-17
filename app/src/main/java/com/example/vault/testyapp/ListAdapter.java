package com.example.vault.testyapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vault.testyapp.Events.OnListItemClickListener;
import com.squareup.picasso.Picasso;

/**
 * Created by vault on 14/03/2017.
 */


public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Article[] values;
    private final OnListItemClickListener listener;

    public ListAdapter(Article[] values, OnListItemClickListener listener) {
        this.values = values;
        this.listener = listener;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new MyViewHolder(inflater.inflate(R.layout.cardview_regular, parent, false), listener);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MyViewHolder myHolder = (MyViewHolder) holder;
        myHolder.bindValue(values[position]);
    }

    public int getCount() {
        return values.length;
    }


    @Override
    public int getItemCount() {
        return values.length;
    }




    // Le ViewHolder est un objet qui attend les données à afficher
    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title;
        private TextView content;
        private final OnListItemClickListener listener;

        MyViewHolder(View itemView, OnListItemClickListener l) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            content = (TextView) itemView.findViewById(R.id.content);

            itemView.setOnClickListener(this);
            listener = l;
        }

        void bindValue(Article article) {
            // on récupère l'image en ligne
            ImageView img = (ImageView) itemView.findViewById(R.id.imagewatch);

            if (article.attachments != null && article.attachments.length >= 1) {
                Picasso.with(itemView.getContext())
                        .load(article.attachments[0])
                        .into(img);
            }

            else {
                img.setImageResource(R.drawable.pigface);
            }


            itemView.setTag(article);
            title.setText(article.title);
            if(!article.featured) content.setText(article.content);
        }

        public void onClick(View view) {

            listener.onHeaderClicked((Article) itemView.getTag());
        }

    }
}
