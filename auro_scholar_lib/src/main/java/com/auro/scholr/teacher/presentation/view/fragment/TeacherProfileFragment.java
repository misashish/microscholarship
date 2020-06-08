package com.auro.scholr.teacher.presentation.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.auro.scholr.R;
import com.auro.scholr.core.application.AuroApp;
import com.auro.scholr.core.application.base_component.BaseFragment;
import com.auro.scholr.core.application.di.component.ViewModelFactory;
import com.auro.scholr.core.common.Status;
import com.auro.scholr.databinding.FragmentTeacherProfileBinding;
import com.auro.scholr.teacher.data.model.common.DistrictDataModel;
import com.auro.scholr.teacher.data.model.common.StateDataModel;
import com.auro.scholr.teacher.presentation.view.adapter.DistrictSpinnerAdapter;
import com.auro.scholr.teacher.presentation.view.adapter.StateSpinnerAdapter;
import com.auro.scholr.teacher.presentation.viewmodel.TeacherProfileViewModel;
import com.auro.scholr.util.AppUtil;
import com.auro.scholr.util.TextUtil;
import com.auro.scholr.util.ViewUtil;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;


public class TeacherProfileFragment extends BaseFragment {

    @Inject
    @Named("TeacherProfileFragment")
    ViewModelFactory viewModelFactory;
    TeacherProfileViewModel viewModel;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    FragmentTeacherProfileBinding binding;
    private String mParam1;
    private String mParam2;
    boolean isStateRestore;
    List<StateDataModel> stateDataModelList;
    List<DistrictDataModel> districtDataModels;

    public TeacherProfileFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static TeacherProfileFragment newInstance(String param1, String param2) {
        TeacherProfileFragment fragment = new TeacherProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (binding != null) {
            isStateRestore = true;
            return binding.getRoot();
        }
        binding = DataBindingUtil.inflate(inflater, getLayout(), container, false);
        AuroApp.getAppComponent().doInjection(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TeacherProfileViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setTeacherProfileViewModel(viewModel);
        setRetainInstance(true);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();

    }

    @Override
    protected void init() {
       binding.headerTopParent.cambridgeHeading.setVisibility(View.GONE);
        if (viewModel != null && viewModel.serviceLiveData().hasObservers()) {
            viewModel.serviceLiveData().removeObservers(this);
        } else {
            observeServiceResponse();
        }
        viewModel.getStateListData();
        viewModel.getDistrictListData();
    }

    @Override
    protected void setToolbar() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_teacher_profile;
    }

    private void observeServiceResponse() {

        viewModel.serviceLiveData().observeForever(responseApi -> {
            switch (responseApi.status) {

                case LOADING:
                    break;

                case SUCCESS:
                    if (responseApi.apiTypeStatus == Status.KYC_RESULT_PATH) {
                    }
                    break;

                case FAIL:
                case NO_INTERNET:

                    showSnackbarError((String) responseApi.data);
                    break;


                /*For state list*/
                case STATE_LIST_ARRAY:
                    stateDataModelList = (List<StateDataModel>) responseApi.data;
                    stateSpinner();
                    break;

                case DISTRICT_LIST_DATA:
                    districtDataModels = (List<DistrictDataModel>) responseApi.data;
                    districtSpinner();
                    break;

                default:
                    showSnackbarError(getString(R.string.default_error));
                    break;
            }

        });
    }
    private void showSnackbarError(String message) {
        ViewUtil.showSnackBar(binding.getRoot(), message);
    }

    private void stateSpinner() {
        if (!TextUtil.checkListIsEmpty(stateDataModelList)) {
            StateSpinnerAdapter stateSpinnerAdapter = new StateSpinnerAdapter(stateDataModelList);
            binding.stateSpinner.setAdapter(stateSpinnerAdapter);
            binding.stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position != 0) {
                        viewModel.getStateDistrictData(stateDataModelList.get(position).getState_code());
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }


    }

    private void districtSpinner() {
        if (!TextUtil.checkListIsEmpty(districtDataModels)) {
            binding.cityTitle.setVisibility(View.VISIBLE);
            binding.citySpinner.setVisibility(View.VISIBLE);
            DistrictSpinnerAdapter stateSpinnerAdapter = new DistrictSpinnerAdapter(districtDataModels);
            binding.citySpinner.setAdapter(stateSpinnerAdapter);
            binding.citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else {
            binding.cityTitle.setVisibility(View.GONE);
            binding.citySpinner.setVisibility(View.GONE);
        }
    }

}