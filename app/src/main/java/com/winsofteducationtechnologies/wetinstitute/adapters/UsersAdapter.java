package com.winsofteducationtechnologies.wetinstitute.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.winsofteducationtechnologies.wetinstitute.R;
import com.winsofteducationtechnologies.wetinstitute.model.User;

import java.util.List;

/*
        class              :       SignUp
        description        :       Used to insert the input fields and register the valid user
        arguments          :       
        return type        :
*/public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder> {

    private Context mctx;
    private List<User> userlist;

    public UsersAdapter(Context mctx, List<User> userlist) {
        this.mctx = mctx;
        this.userlist = userlist;
    }

    @NonNull

    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_user, parent, false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder usersViewHolder, int position) {
        User user = userlist.get(position);
        usersViewHolder.textViewName.setText(user.getName());
        usersViewHolder.textViewEmail.setText(user.getEmail());
        usersViewHolder.textViewRole.setText(user.getRole());
        usersViewHolder.textViewGender.setText(user.getGender());
    }

    @Override
    public int getItemCount() {
        return userlist.size();
    }

    public class UsersViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewName;
        public TextView textViewEmail;
        public TextView textViewRole;
        public TextView textViewGender;

        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = (TextView)itemView.findViewById(R.id.textViewName);
            textViewEmail = (TextView)itemView.findViewById(R.id.textViewEmail);
            textViewRole = (TextView)itemView.findViewById(R.id.textViewRole);
            textViewGender = (TextView)itemView.findViewById(R.id.textViewGender);

        }
    }
}
