package com.trustme.trustme;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private ArrayList<History> list;
    private OnItemClickListener listener;

    public HistoryAdapter(ArrayList<History> list, OnItemClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        History history = list.get(position);

        holder.txtUserStory.setText(history.getUserStory());
        holder.txtDateStory.setText(history.getDateStory());
        holder.txtDegree.setText(history.getDegree());
        holder.txtCategory.setText(history.getCategory());
        holder.txtTitleStory.setText(history.getTitleStory());
        holder.txtTextStory.setText(history.getTextHistory());
        holder.btnCommentsOneHistory.setText(history.getCommentsOneHistory());

        holder.onClick(listener, history);
    }


    @Override
    public int getItemCount() {return list.size(); }

    public interface OnItemClickListener {
        void onItemClick(History history);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtUserStory;
        TextView txtDateStory;
        TextView txtDegree;
        TextView txtCategory;
        TextView txtTitleStory;
        TextView txtTextStory;
        Button btnCommentsOneHistory;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtUserStory = itemView.findViewById(R.id.txtUserStory);
            txtDateStory = itemView.findViewById(R.id.txtDateStory);
            txtDegree = itemView.findViewById(R.id.txtDegree);
            txtCategory = itemView.findViewById(R.id.txtCategory);
            txtTitleStory = itemView.findViewById(R.id.txtTitleStory);
            txtTextStory = itemView.findViewById(R.id.txtTextHistory);
            btnCommentsOneHistory = itemView.findViewById(R.id.btnCommentsOneHistory);
        }
        public void onClick(OnItemClickListener listener, History history) {
            itemView.setOnClickListener(v-> listener.onItemClick(history));
        }

    }

    public void addHistory(History history) {
        list.add(history);
        notifyDataSetChanged();
    }

}
