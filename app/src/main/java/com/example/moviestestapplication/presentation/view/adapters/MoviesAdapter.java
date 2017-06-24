package com.example.moviestestapplication.presentation.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.moviestestapplication.R;
import com.example.moviestestapplication.presentation.model.MovieModel;
import com.squareup.picasso.Picasso;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;


public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder>{

    public interface OnItemClickListener {
        void onMovieItemClicked(MovieModel movieModel);
    }

    private static final String PICASSO_BASE_URL = "http://image.tmdb.org/t/p/w92";

    private List<MovieModel> movies;
    private final Context context;
    private final SimpleDateFormat dateFormat;

    private OnItemClickListener onItemClickListener;

    public MoviesAdapter(Context context, List<MovieModel> movies) {
        this.context = context;
        this.movies = movies;
        dateFormat = new SimpleDateFormat("d MMM yy", Locale.ENGLISH);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MovieModel movie = movies.get(position);
        holder.tvTitle.setText(movie.getTitle());
        holder.tvVoteAverage.setText(String.valueOf(movie.getVoteAverage()));
        holder.tvDate.setText(dateFormat.format(movie.getReleaseDate()));
        holder.tvOverview.setText(movie.getOverview());
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onMovieItemClicked(movie);
            }
        });
        loadPoster(movie.getPosterPath(),holder.ivPoster);

    }

    private void loadPoster(String posterPath, ImageView imageView){
        String picassoBaseUrl = context.getString(R.string.picasso_base_url);
        Picasso.with(context).load(picassoBaseUrl + "/w92" + posterPath)
                .fit()
                .into(imageView);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public List<MovieModel> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieModel> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    public void addMovies(List<MovieModel> data) {
        this.movies.addAll(data);
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

            this.ivPoster = (ImageView) itemView.findViewById(R.id.ivPoster);
            this.tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            this.tvVoteAverage = (TextView) itemView.findViewById(R.id.tvVoteAverage);
            this.tvDate = (TextView) itemView.findViewById(R.id.tvDate);
            this.tvOverview = (TextView) itemView.findViewById(R.id.tvOverview);
        }
    }
}
