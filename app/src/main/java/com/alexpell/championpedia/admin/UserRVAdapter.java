package com.alexpell.championpedia.admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.alexpell.championpedia.DB.AllDAO;
import com.alexpell.championpedia.DB.AppDataBase;
import com.alexpell.championpedia.R;

import java.util.ArrayList;

public class UserRVAdapter extends RecyclerView.Adapter<UserRVAdapter.MyViewHolder> {

    Context context;
    ArrayList<UserModel> userModels;
    AllDAO allDAO;

    public UserRVAdapter(Context context, ArrayList<UserModel> commentModels) {
        this.context = context;
        this.userModels = commentModels;
        allDAO = Room.databaseBuilder(context, AppDataBase.class, AppDataBase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build()
                .getAllDAO();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.user_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        UserModel userModel = userModels.get(position);
        holder.id.setText(String.valueOf(userModel.getId()));
        holder.name.setText(userModel.getName());
        holder.comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Comments", Toast.LENGTH_SHORT).show();
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userModel.getId() == context.getSharedPreferences("com.alexpell.championpedia", Context.MODE_PRIVATE).getInt("userId", 42)) {
                    Toast.makeText(context, "You cannot delete yourself", Toast.LENGTH_SHORT).show();
                    return;
                }
                allDAO.deleteUser(userModel.getId());
                userModels.remove(position);
                notifyItemRemoved(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id, name;
        Button comments, delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.userId);
            name = itemView.findViewById(R.id.username);
            comments = itemView.findViewById(R.id.viewCommentsButton);
            delete = itemView.findViewById(R.id.deleteButton);
        }
    }
}
