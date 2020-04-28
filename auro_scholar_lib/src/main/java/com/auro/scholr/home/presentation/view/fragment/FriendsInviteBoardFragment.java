package com.auro.scholr.home.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.auro.scholr.R;
import com.auro.scholr.core.application.AuroApp;
import com.auro.scholr.core.application.base_component.BaseFragment;
import com.auro.scholr.core.application.di.component.ViewModelFactory;
import com.auro.scholr.databinding.FriendsInviteLayoutBinding;
import com.auro.scholr.databinding.FriendsLeoboardLayoutBinding;
import com.auro.scholr.home.presentation.view.adapter.LeaderBoardAdapter;
import com.auro.scholr.home.presentation.viewmodel.FriendsInviteViewModel;
import com.auro.scholr.home.presentation.viewmodel.FriendsLeaderShipViewModel;

import javax.inject.Inject;
import javax.inject.Named;


public class FriendsInviteBoardFragment extends BaseFragment implements View.OnClickListener {

    @Inject
    @Named("FriendsInviteBoardFragment")
    ViewModelFactory viewModelFactory;


    FriendsInviteLayoutBinding binding;
    FriendsInviteViewModel viewModel;

    LeaderBoardAdapter leaderBoardAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayout(), container, false);
        AuroApp.getAppComponent().doInjection(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(FriendsInviteViewModel.class);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }


    @Override
    protected void init() {
        binding.headerParent.cambridgeHeading.setVisibility(View.GONE);
        binding.toolbarLayout.backArrow.setOnClickListener(this);
        setLeaderBoard();
    }


    @Override
    protected void setToolbar() {

    }

    @Override
    protected void setListener() {

    }


    @Override
    protected int getLayout() {
        return R.layout.friends_invite_layout;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            // dashboardResModel = getArguments().getParcelable(AppConstant.DASHBOARD_RES_MODEL);

        }
        init();
        setToolbar();
        setListener();
    }

    private void setLeaderBoard() {
        binding.friendsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.friendsList.setHasFixedSize(true);
        leaderBoardAdapter = new LeaderBoardAdapter(viewModel.homeUseCase.makeListForFriendsLeaderBoard(false));
        binding.friendsList.setAdapter(leaderBoardAdapter);
    }


    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onClick(View v) {
        getActivity().getSupportFragmentManager().popBackStack();
    }
}
