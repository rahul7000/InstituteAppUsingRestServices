package com.winsofteducationtechnologies.wetinstitute.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.winsofteducationtechnologies.wetinstitute.R;
import com.winsofteducationtechnologies.wetinstitute.adapters.UsersAdapter;
import com.winsofteducationtechnologies.wetinstitute.api.RetrofitClient;
import com.winsofteducationtechnologies.wetinstitute.model.User;
import com.winsofteducationtechnologies.wetinstitute.model.UsersResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
        class              :       SignUp
        description        :       Used to insert the input fields and register the valid user
        arguments          :       
        return type        :
*/public class LocationFragment extends Fragment {

    private RecyclerView recyclerView;
    private UsersAdapter adapter;

    private List<User> users;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_location, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Home");

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewUser);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Call<UsersResponse> call = RetrofitClient.getInstance().getAPI().getUser();
        call.enqueue(new Callback<UsersResponse>() {

            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                users = response.body().getUser();
                adapter = new UsersAdapter(getActivity(),users);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {

            }
        });
    }
}
