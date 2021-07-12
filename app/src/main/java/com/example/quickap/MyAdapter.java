package com.example.quickap;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class MyAdapter extends FirebaseRecyclerAdapter<Patient,MyAdapter.myViewHolder>{

    public MyAdapter(@NonNull FirebaseRecyclerOptions<Patient> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Patient patient) {
        holder.nameview.setText(patient.getName());
        holder.surnameview.setText(patient.getSurname());
        holder.genderview.setText(patient.getGender());
        holder.ageview.setText(patient.getAge());
        holder.dateview.setText(patient.getDate());

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView nameview,surnameview,genderview,ageview,dateview;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            nameview=(TextView)itemView.findViewById(R.id.nametextView);
            surnameview=(TextView)itemView.findViewById(R.id.surnametextView);
            genderview=(TextView)itemView.findViewById(R.id.gendertextView);
            ageview=(TextView)itemView.findViewById(R.id.agetextView);
            dateview=(TextView)itemView.findViewById(R.id.datetextView);
        }
    }
}