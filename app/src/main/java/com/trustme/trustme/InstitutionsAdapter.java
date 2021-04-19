package com.trustme.trustme;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class InstitutionsAdapter extends RecyclerView.Adapter <InstitutionsAdapter.ViewHolder> {

    private ArrayList<Institutions> list;
    private InstitutionsAdapter.OnItemClickListener listener;

    public InstitutionsAdapter(ArrayList<Institutions> list, OnItemClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_institutions,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Institutions institutions = list.get(position);

        holder.txtNameInstitution.setText(institutions.getNameInstitution());
        holder.txtHoursInstitution.setText(institutions.getHoursInstitution());
        holder.txtPhoneInstitution.setText(institutions.getPhoneInstitution().toString());
        holder.txtAddress.setText(institutions.getAddress());

        holder.onClick(listener, institutions);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtNameInstitution;
        private TextView txtHoursInstitution;
        private TextView txtPhoneInstitution;
        private TextView txtAddress;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNameInstitution = itemView.findViewById(R.id.txtNameInstituton);
            txtHoursInstitution = itemView.findViewById(R.id.txtHoursInstitution);
            txtPhoneInstitution = itemView.findViewById(R.id.txtPhoneInstitution);
            txtAddress = itemView.findViewById(R.id.txtAddressInstitution);
        }

        public void onClick(OnItemClickListener listener, Institutions institutions) {
            itemView.setOnClickListener(v-> listener.onItemClick(institutions));
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Institutions institutions);
    }

    public void addInstitutions(Institutions institutions) {
        list.add(institutions);
        notifyDataSetChanged();
    }
}
