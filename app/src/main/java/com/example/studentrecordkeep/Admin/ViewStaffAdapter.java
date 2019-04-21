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

public class ViewStaffAdapter extends RecyclerView.Adapter<ViewStaffAdapter.MyViewHolder> {

    private Context mContext;
    private List<AddingStaff> staffList;

    public ViewStaffAdapter() {

    }

    public ViewStaffAdapter(Context mContext, List<AddingStaff> staffList) {
        this.mContext = mContext;
        this.staffList = staffList;
    }

    @NonNull
    @Override
    public ViewStaffAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_staff_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewStaffAdapter.MyViewHolder holder, int i) {

        AddingStaff staff = staffList.get(i);

        holder.staffName.setText(staff.getName());
        holder.staffDep.setText(staff.getDepName());
        holder.staffEmail.setText(staff.getEmail());

    }

    @Override
    public int getItemCount() {

        return staffList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView staffName;
        private TextView staffDep;
        private TextView staffEmail;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            staffName = itemView.findViewById(R.id.staff_name);
            staffDep = itemView.findViewById(R.id.staff_branch);
            staffEmail = itemView.findViewById(R.id.staff_email);
        }
    }
}
