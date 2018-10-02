package com.example.ovi.rxjavaandroidone.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ovi.rxjavaandroidone.R;
import com.example.ovi.rxjavaandroidone.models.Content;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesHolder> {

    List<Content> movieList;
    Context context;

    public MoviesAdapter(List<Content> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @Override
    public MoviesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_movies,parent,false);
        MoviesHolder mh = new MoviesHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(MoviesHolder holder, int position) {

        holder.txtTitle.setText(movieList.get(position).getTitle());
        holder.txtBody.setText(movieList.get(position).getBody());

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MoviesHolder extends RecyclerView.ViewHolder {

        TextView txtTitle,txtBody;

        public MoviesHolder(View v) {
            super(v);
            txtTitle = (TextView) v.findViewById(R.id.txtTitle);
            txtBody = (TextView) v.findViewById(R.id.txtBody);
        }
    }
}