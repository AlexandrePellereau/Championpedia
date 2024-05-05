package com.alexpell.championpedia.comment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.alexpell.championpedia.DB.AllDAO;
import com.alexpell.championpedia.DB.AppDataBase;
import com.alexpell.championpedia.R;

import java.util.ArrayList;

public class CommentRVAdapter extends RecyclerView.Adapter<CommentRVAdapter.MyViewHolder>{

    Context context;
    ArrayList<CommentModel> commentModels;
    boolean isAdmin = false;

    public CommentRVAdapter(Context context, ArrayList<CommentModel> commentModels) {
        this.context = context;
        this.commentModels = commentModels;
        isAdmin = context.getSharedPreferences("com.alexpell.championpedia", Context.MODE_PRIVATE).getBoolean("isAdmin", false);
    }

    @NonNull
    @Override
    public CommentRVAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.comment_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentRVAdapter.MyViewHolder holder, int position) {
        CommentModel commentModel = commentModels.get(position);
        holder.name.setText(commentModel.getName());
        holder.date.setText(commentModel.getDate());
        holder.content.setText(commentModel.getContent());
        holder.image.setImageResource(commentModel.getImage());

        if (isAdmin) {
            holder.delete.setVisibility(View.VISIBLE);
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AllDAO allDAO = Room.databaseBuilder(context, AppDataBase.class, AppDataBase.DATABASE_NAME)
                            .allowMainThreadQueries()
                            .build()
                            .getAllDAO();
                    allDAO.deleteComment(commentModels.get(position).getId());
                    commentModels.remove(position);
                    notifyItemRemoved(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return commentModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, date, content;
        ImageView image;
        Button delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            date = itemView.findViewById(R.id.date);
            content = itemView.findViewById(R.id.content);
            image = itemView.findViewById(R.id.avatar);
            delete = itemView.findViewById(R.id.deleteButton);
        }
    }
}
