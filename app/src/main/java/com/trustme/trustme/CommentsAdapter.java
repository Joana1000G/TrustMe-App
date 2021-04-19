package com.trustme.trustme;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {

    private ArrayList<Comments> list;
    private OnItemClickListener listener;

    public CommentsAdapter(ArrayList<Comments> list, CommentsAdapter.OnItemClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.fragment_item_comments_detail_dialog_list_dialog, parent,
                        false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comments comments = list.get(position);

        holder.txtUser.setText(comments.getUser());
        holder.txtDate.setText(comments.getDate());
        holder.txtCommentary.setText(comments.getCommentary());

        holder.onClick(listener, comments);
    }

    @Override
    public int getItemCount() {return  list.size();}

    public interface OnItemClickListener {
        void onItemClick(Comments comments);
    }

    public class  ViewHolder extends RecyclerView.ViewHolder {

        TextView txtUser;
        TextView txtDate;
        TextView txtCommentary;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtUser = itemView.findViewById(R.id.txtUser);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtCommentary = itemView.findViewById(R.id.txtCommentary);
        }

        public void onClick(OnItemClickListener listener, Comments comments) {
            itemView.setOnClickListener(v-> listener.onItemClick(comments));
        }
    }

    public void addComments(Comments comments) {
        list.add(comments);
        notifyDataSetChanged();
    }
}
