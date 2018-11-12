package com.casasw.bankapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class CurrencyFragment extends Fragment {

    CurrencyAdapter mCurrencyAdapter;


    public static CurrencyFragment newInstance() {
        return new CurrencyFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.currency_fragment, container, false);
        Bundle args = getArguments();

        if (args != null) {
            LoginViewModel viewModel = args.getParcelable("EXTRA_LOGIN");
            ViewHolder viewHolder = new ViewHolder(rootView);
            viewHolder.mUserName.setText(viewModel.getName());

            viewHolder.mImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().onBackPressed();
                }
            });

            String account = viewModel.getAgency()+"";
            account = String.format("%1$s.%2$s-%3$s", account.substring(0,1), account.substring(2,account.length()-2), account.substring(account.length()-1));
            viewHolder.mAccount.setText(getString(R.string.format_agency_account,
                    viewModel.getBankAccount(), account));

            DecimalFormat df = new java.text.DecimalFormat("###,####,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
            NumberFormat nf =  NumberFormat.getCurrencyInstance();
            String balance = df.format(viewModel.getBalance())+"";


            viewHolder.mBalance.setText(getString(R.string.format_balance,balance));

            viewHolder.mRecent.setText(getString(R.string.recent_text   ));

            CurrencyViewModel currencyViewModel = args.getParcelable("EXTRA_STATEMENTS");
            mCurrencyAdapter = new CurrencyAdapter(getContext(),currencyViewModel.getListOfStatements());
            viewHolder.mList.setAdapter(mCurrencyAdapter);


        }

        return rootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private class ViewHolder {
        private TextView mUserName;
        private ImageView mImage;
        private TextView mAccount;
        private TextView mBalance;
        private TextView mRecent;
        private ListView mList;

        ViewHolder(View view) {
            mUserName = view.findViewById(R.id.user_name_text);
            mImage = view.findViewById(R.id.logout);
            mAccount = view.findViewById(R.id.account_number_text);
            mBalance = view.findViewById(R.id.balance_number_text);
            mRecent = view.findViewById(R.id.recent_text);
            mList = view.findViewById(R.id.list_currency);
        }
    }

    private class CurrencyAdapter extends ArrayAdapter<CurrencyModel> {
        public CurrencyAdapter(@NonNull Context context, @NonNull ArrayList<CurrencyModel> models) {
            super(context, 0, models);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            CurrencyModel viewModel = getItem(position);
            DecimalFormat df = new java.text.DecimalFormat("###,####,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
            if (convertView == null) {
                convertView =
                        LayoutInflater.from(
                                getContext()).inflate(
                                        R.layout.list_item, parent, false);
            }
            if (viewModel != null) {
                ((TextView) convertView.findViewById(R.id.title_text)).setText(viewModel.getTitle());
                ((TextView) convertView.findViewById(R.id.date_text)).setText(viewModel.getDate());
                ((TextView) convertView.findViewById(R.id.desc_text)).setText(viewModel.getDesc());
                double balance = viewModel.getBalance();
                String editedBalance;
                if (balance<0) {
                    balance*=-1;
                    editedBalance = "-"+df.format(balance);
                }
                else
                    editedBalance = df.format(balance);
                ((TextView) convertView.findViewById(R.id.balance_text))
                        .setText(editedBalance);
            }
            return convertView;
        }
    }



}
