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
    private List<Descriptions> itemsList;
    private Context context;
    private OnRowClickListener listener;

    public RecyclerViewAdapter(List<Descriptions> itemsList, Context context, OnRowClickListener clickListener) {
        this.itemsList = itemsList;
        this.context = context;
        this.listener = clickListener;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.lost_n_found_row, parent, false);
        return new ViewHolder(itemView, listener);
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewDescriptionRow;
        TextView textViewLorFRow;
        public OnRowClickListener onRowClickListener;
        public ViewHolder(@NonNull View itemView, OnRowClickListener onRowClickListener) {
            super(itemView);
            textViewDescriptionRow = itemView.findViewById(R.id.textViewDescriptionRow);
            textViewLorFRow = itemView.findViewById(R.id.textViewLorFRow);
            this.onRowClickListener = onRowClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onRowClickListener.onItemClick(getAdapterPosition());
        }
    }

    public interface OnRowClickListener {
        void onItemClick (int position);
    }
}
