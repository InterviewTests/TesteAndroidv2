package com.resourceit.app.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.resourceit.app.Adaptera.StatmentsAdapter;
import com.resourceit.app.R;
import com.resourceit.app.holders.StatmentHolder;
import com.resourceit.app.models.StatmentModel;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class StatementsFragment extends Fragment {

    @BindView(R.id.statments)
    RecyclerView statments;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statements, container, false);
        ButterKnife.bind(this, view);
        statments.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        statments.setLayoutManager(mLayoutManager);

        StatmentsAdapter mAdapter = new StatmentsAdapter(new ArrayList<StatmentHolder>(0));
        statments.setAdapter(mAdapter);
        for(int i = 1;i<=50;i++){
            StatmentModel st = new StatmentModel();
            st.setDate("1111-11-11");
            st.setTitle("khalid "+i);
            st.setDesc("teste desc "+i);
            st.setValue(i*0.3);
            mAdapter.insertItem(st);
        }
        return view;
    }
}
