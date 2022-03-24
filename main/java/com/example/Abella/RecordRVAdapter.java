package com.example.Abella;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecordRVAdapter extends RecyclerView.Adapter<RecordRVAdapter.ViewHolder> {

    private ArrayList<ModalClass> recordModalArrayList;
    private Context context;

    public RecordRVAdapter(ArrayList<ModalClass> recordModalArrayList, Context context) {
        this.recordModalArrayList = recordModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ModalClass modal = recordModalArrayList.get(position);
        holder.recordNameTV.setText(modal.getName());
        holder.recordStatusTV.setText(modal.getStatus());
        holder.recordAgeTV.setText(modal.getAge());
        holder.recordGenderTV.setText(modal.getGender());
        holder.recordAddressTV.setText(modal.getAddress());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, UpdateRecordActivity.class);

                i.putExtra("user_name", modal.getName());
                i.putExtra("user_age", modal.getAge());
                i.putExtra("user_gender", modal.getGender());
                i.putExtra("user_address", modal.getAddress());
                i.putExtra("user_status", modal.getStatus());

                context.startActivity(i);
            }
        });
    }
    @Override
    public int getItemCount(){
        return recordModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView recordNameTV, recordAgeTV, recordGenderTV, recordStatusTV, recordAddressTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            recordNameTV = itemView.findViewById(R.id.idTVName);
            recordStatusTV = itemView.findViewById(R.id.idTVStatus);
            recordAgeTV = itemView.findViewById(R.id.idTVAge);
            recordGenderTV = itemView.findViewById(R.id.idTVGender);
            recordAddressTV = itemView.findViewById(R.id.idTVAddress);
        }
    }
}
