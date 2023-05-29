package com.tmannapps.lostnfound;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tmannapps.lostnfound.model.User;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    public RecyclerViewAdapter(List<Descriptions> itemsList, Context context) {
        this.itemsList = itemsList;
        this.context = context;
    }

    private List<Descriptions> itemsList;
    private Context context;

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.lost_n_found_row, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
    // need a method here to delete or update list after db changes...
        holder.textViewLorFRow.setText(itemsList.get(position).getLorF());
        holder.textViewDescriptionRow.setText(itemsList.get(position).getDescriptions());
    }

    @Override
    public int getItemCount()
    {
        return itemsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewDescriptionRow;
        TextView textViewLorFRow;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewDescriptionRow = itemView.findViewById(R.id.textViewDescriptionRow);
            textViewLorFRow = itemView.findViewById(R.id.textViewLorFRow);
        }
    }
}
