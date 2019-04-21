package com.example.studentrecordkeep.Admin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.studentrecordkeep.R;

import java.util.List;

public class ViewExamHallAdapter extends RecyclerView.Adapter<ViewExamHallAdapter.MyViewHolder> {

    private Context mContext;
    private List<AddingHall> examHallList;

    public ViewExamHallAdapter() {
    }

    public ViewExamHallAdapter(Context mContext, List<AddingHall> examHallList) {
        this.mContext = mContext;
        this.examHallList = examHallList;
    }

    @NonNull
    @Override
    public ViewExamHallAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_exam_hall_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewExamHallAdapter.MyViewHolder holder, int i) {

        AddingHall hall = examHallList.get(i);
        holder.examHallName.setText(hall.getHallName());
        holder.examBlockName.setText(hall.getBlockNumber());
        holder.examRoomNumber.setText(hall.getRoomNumber());
        holder.examStudentNumber.setText(hall.getStudentNumber());

    }

    @Override
    public int getItemCount() {
        return examHallList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView examHallName;
        private TextView examBlockName;
        private TextView examRoomNumber;
        private TextView examStudentNumber;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            examHallName = itemView.findViewById(R.id.exam_hall_name);
            examBlockName = itemView.findViewById(R.id.exam_block_name);
            examRoomNumber = itemView.findViewById(R.id.exam_room_number);
            examStudentNumber = itemView.findViewById(R.id.exam_student_number);
        }
    }
}
