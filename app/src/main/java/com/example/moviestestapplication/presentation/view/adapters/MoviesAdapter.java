package com.example.moviestestapplication.presentation.view.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpDelegate;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.moviestestapplication.R;
import com.example.moviestestapplication.domain.Movie;
import com.example.moviestestapplication.presentation.di.components.DaggerMoviesAdapterComponent;
import com.example.moviestestapplication.presentation.di.components.HasMoviesAdapterDepenedencies;
import com.example.moviestestapplication.presentation.model.MovieModel;
import com.example.moviestestapplication.presentation.view.HasComponent;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;


public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> implements MoviesAdapterView{

    private List<Movie> movies;
    private final Activity activity;
    private final SimpleDateFormat dateFormat;
    private MvpDelegate<? extends MoviesAdapter> mMvpDelegate;

    @InjectPresenter
    @Inject
    MoviesAdapterPresenter presenter;

    @ProvidePresenter
    public MoviesAdapterPresenter providePresenter(){
        return presenter;
    }

    public MoviesAdapter(Activity activity, List<Movie> movies) {
        this.activity = activity;
        this.movies = movies;
        dateFormat = new SimpleDateFormat("d MMM yy", Locale.ENGLISH);
//        buildGraph();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.tvTitle.setText(movie.getTitle());
        holder.tvVoteAverage.setText(String.valueOf(movie.getVoteAverage()));
        holder.tvDate.setText(getFormateDateString(movie.getReleaseDate()));
        holder.tvOverview.setText(movie.getOverview());
        holder.itemView.setOnClickListener(v -> {
//            presenter.movieClicked(movie.getId());
        });
        loadPoster(movie.getPosterPath(),holder.ivPoster);
    }

    private String getFormateDateString(String releaseDate) {
        String formatedDateString = "";
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH).parse(releaseDate);
            formatedDateString = dateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatedDateString;
    }

    private void loadPoster(String posterPath, ImageView imageView){
        String picassoBaseUrl = activity.getString(R.string.picasso_base_url);
        Picasso.with(activity).load(picassoBaseUrl + "/w92" + posterPath)
                .fit()
                .into(imageView);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        final ImageView ivPoster;
        final TextView tvTitle;
        final TextView tvVoteAverage;
        final TextView tvDate;
        final TextView tvOverview;


        ViewHolder(View itemView) {
            super(itemView);

            this.ivPoster = itemView.findViewById(R.id.ivPoster);
            this.tvTitle = itemView.findViewById(R.id.tvTitle);
            this.tvVoteAverage = itemView.findViewById(R.id.tvVoteAverage);
            this.tvDate = itemView.findViewById(R.id.tvDate);
            this.tvOverview = itemView.findViewById(R.id.tvOverview);
        }
    }
}
