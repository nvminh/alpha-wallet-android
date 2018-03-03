package com.wallet.crypto.alphawallet.ui;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.wallet.crypto.alphawallet.R;
import com.wallet.crypto.alphawallet.entity.NetworkInfo;
import com.wallet.crypto.alphawallet.entity.Wallet;
import com.wallet.crypto.alphawallet.viewmodel.NewSettingsViewModel;
import com.wallet.crypto.alphawallet.viewmodel.NewSettingsViewModelFactory;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class NewSettingsFragment extends Fragment {
    @Inject
    NewSettingsViewModelFactory newSettingsViewModelFactory;
    private NewSettingsViewModel viewModel;

    Wallet wallet;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        viewModel = ViewModelProviders.of(this, newSettingsViewModelFactory).get(NewSettingsViewModel.class);
        viewModel.defaultWallet().observe(this, this::onDefaultWallet);

        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        LinearLayout layoutWalletAddress = view.findViewById(R.id.layout_wallet_address);

        layoutWalletAddress.setOnClickListener(v -> {
            viewModel.showMyAddress(getContext());
        });

        return view;
    }

    private void onDefaultWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.prepare();
    }
}
