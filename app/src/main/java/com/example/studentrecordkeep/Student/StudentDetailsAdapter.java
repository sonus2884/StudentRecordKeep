package com.example.studentrecordkeep.Student;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.studentrecordkeep.Admin.AddingHall;
import com.example.studentrecordkeep.Admin.AddingStudent;
import com.example.studentrecordkeep.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class StudentDetailsAdapter extends RecyclerView.Adapter<StudentDetailsAdapter.MyViewHolder> {

    private Context mContext;
    private List<AddingStudent> studentList;
    private DatabaseReference mReference;

    public StudentDetailsAdapter(Context mContext, List<AddingStudent> studentList) {
        this.mContext = mContext;
        this.studentList = studentList;
       // mReference = FirebaseDatabase.getInstance().getReference().child("add hall details").child("A_253");;
        //mReference

    }


    @NonNull
    @Override
    public StudentDetailsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_stu_details_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final StudentDetailsAdapter.MyViewHolder holder, int i) {

        AddingStudent st = studentList.get(i);
        holder.stu_name.setText(st.getName());
        holder.stu_sem.setText(st.getSemester());
        holder.stu_branch.setText(st.getDepName());
        holder.stu_year.setText(st.getYear());
        holder.stu_regNo.setText(st.getRegNo());
        holder.stu_email.setText(st.getEmail());
        holder.stu_room_no.setText(st.getRoomNumber());
        holder.stu_block_name.setText(st.getBlockName());

        mReference=FirebaseDatabase.getInstance().getReference().child("add hall details")
                .child(st.getBlockName()+"_"+st.getRoomNumber());
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                AddingHall hall = dataSnapshot.getValue(AddingHall.class);
                if (hall.getStaffName() != null) {
                    Log.i("_na", hall.getStaffName());
                    holder.staffName.setText(hall.getStaffName());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
        private TextView stu_block_name;
        private TextView stu_room_no;
        private TextView staffName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            stu_branch = itemView.findViewById(R.id.stud_branch);
            stu_name = itemView.findViewById(R.id.stud_name);
            stu_sem = itemView.findViewById(R.id.stud_sem);
            stu_email = itemView.findViewById(R.id.stud_email);
            stu_regNo = itemView.findViewById(R.id.stud_RegNo);
            stu_year = itemView.findViewById(R.id.stud_year);
            stu_block_name = itemView.findViewById(R.id.stud_block_name);
            stu_room_no = itemView.findViewById(R.id.stud_room_number);
            staffName = itemView.findViewById(R.id.staff_Name);

        }
    }
}
