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

public class ViewStudentAdapter extends RecyclerView.Adapter<ViewStudentAdapter.MyViewHolder> {

    private Context mContext;
    private List<AddingStudent> studentList;

    public ViewStudentAdapter() {
    }

    public ViewStudentAdapter(Context mContext, List<AddingStudent> studentList) {
        this.mContext = mContext;
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public ViewStudentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_students_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewStudentAdapter.MyViewHolder holder, int i) {

        AddingStudent st = studentList.get(i);
        holder.stu_name.setText(st.getName());
        holder.stu_sem.setText(st.getSemester());
        holder.stu_branch.setText(st.getDepName());
        holder.stu_year.setText(st.getYear());
        holder.stu_regNo.setText(st.getRegNo());
        holder.stu_email.setText(st.getEmail());




    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView stu_name;
        private TextView stu_branch;
        private TextView stu_sem;
        private TextView stu_regNo;
        private TextView stu_year;
        private TextView stu_email;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            stu_branch = itemView.findViewById(R.id.stu_branch);
            stu_name = itemView.findViewById(R.id.stu_name);
            stu_sem = itemView.findViewById(R.id.stu_sem);
            stu_email = itemView.findViewById(R.id.stu_email);
            stu_regNo = itemView.findViewById(R.id.stu_RegNo);
            stu_year = itemView.findViewById(R.id.stu_year);


        }
    }
}
