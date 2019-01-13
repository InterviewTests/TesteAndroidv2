package com.resourceit.app.views;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.resourceit.app.Adaptera.StatmentsAdapter;
import com.resourceit.app.R;
import com.resourceit.app.holders.StatmentHolder;
import com.resourceit.app.models.StatmentModel;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatementsFragment extends Fragment {

    @BindView(R.id.statments)
    RecyclerView statments;
    private MainActivity activity;
    private List<StatmentModel> statmentsList;
    StatmentsAdapter mAdapter;
    private Boolean working = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_statements, container, false);
        ButterKnife.bind(this, view);
        activity = (MainActivity) getActivity();
        activity.Loading(false);
        statments.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        statments.setLayoutManager(mLayoutManager);

        mAdapter = new StatmentsAdapter(new ArrayList<StatmentHolder>(0));
        statments.setAdapter(mAdapter);
        activity.runOnUiThread(UpdateStatments);
        activity.Loading(false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @OnClick(R.id.logout)
    void Logout() {
        activity.updateFragment(new LoginFragment(), "LOGIN");
    }

        Runnable UpdateStatments = new Runnable() {
        @Override
        public void run() {
            if(!working) {
                working = true;
                try {
                    Call<StatmentModel> statments = activity.API.GetStatments("1");
                    final Gson gson = new Gson();

                    statments.enqueue(new Callback<StatmentModel>() {
                        @Override
                        public void onResponse(Call<StatmentModel> call, Response<StatmentModel> response) {
                            StatmentModel statments = response.body();
                            Log.w("statments:sucess:: ", gson.toJson(statments.getStatementList()));
                            statmentsList = statments.getStatementList();

                            if(statmentsList!=null) {
                                for (StatmentModel statment : statmentsList) {
                                    StatmentModel st = new StatmentModel();
                                    st.setDate(statment.getDate());
                                    st.setTitle(statment.getTitle());
                                    st.setDesc(statment.getDesc());
                                    st.setValue(statment.getValue());
                                    mAdapter.insertItem(st);
                                }
                            }
                            working = false;
                        }

                        @Override
                        public void onFailure(Call<StatmentModel> call, Throwable t) {
                            Log.w("statments:error:: ", t.toString());
                            activity.Loading(false);
                            statmentsList = null;
                            working = false;
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                    working = false;
                }
            }
        }
    };
}
