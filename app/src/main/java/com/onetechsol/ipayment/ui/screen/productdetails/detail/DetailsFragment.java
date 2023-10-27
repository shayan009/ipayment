package com.onetechsol.ipayment.ui.screen.productdetails.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.FragmentDetailsBinding;
import com.onetechsol.ipayment.pojo.BenefitModel;
import com.onetechsol.ipayment.pojo.InstructionModel;
import com.onetechsol.ipayment.pojo.TermsConditionsModel;
import com.onetechsol.ipayment.pojo.WhomToSellModel;
import com.onetechsol.ipayment.ui.adapter.BenefitAdapter;
import com.onetechsol.ipayment.ui.adapter.InstructionAdapter;
import com.onetechsol.ipayment.ui.adapter.TermsConditionAdapter;
import com.onetechsol.ipayment.ui.adapter.WhomToSellAdapter;
import com.onetechsol.ipayment.ui.basefiles.BaseFragment;
import com.onetechsol.ipayment.ui.screen.productdetails.ProductDetailViewModel;

import java.util.ArrayList;


public class DetailsFragment extends BaseFragment<ProductDetailViewModel, FragmentDetailsBinding> {


    private static final String benefitModel = "benefitModel";
    private static final String whomToSellModel = "whomToSellModel";
    private static final String instructionModel = "instructionModel";
    private static final String termsConditionsModel = "termsConditionsModels";

    private ArrayList<BenefitModel> benefitModels;
    private ArrayList<WhomToSellModel> whomToSellModels;
    private ArrayList<InstructionModel> instructionModels;
    private ArrayList<TermsConditionsModel> termsConditionsModels;

    public DetailsFragment() {
        // Required empty public constructor
    }


    public static DetailsFragment newInstance(ArrayList<BenefitModel> benefitModels, ArrayList<WhomToSellModel> whomToSellModels, ArrayList<InstructionModel> instructionModels, ArrayList<TermsConditionsModel> termsConditionsModels) {

        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(benefitModel, benefitModels);
        args.putParcelableArrayList(whomToSellModel, whomToSellModels);
        args.putParcelableArrayList(instructionModel, instructionModels);
        args.putParcelableArrayList(termsConditionsModel, termsConditionsModels);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            benefitModels = getArguments().getParcelableArrayList(benefitModel);
            whomToSellModels = getArguments().getParcelableArrayList(whomToSellModel);
            instructionModels = getArguments().getParcelableArrayList(instructionModel);
            termsConditionsModels = getArguments().getParcelableArrayList(termsConditionsModel);

        }
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_details;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        BenefitAdapter benefitAdapter = new BenefitAdapter();
        benefitAdapter.setBenefitList(benefitModels);
        viewBinding().setBenefitAdapter(benefitAdapter);


        InstructionAdapter instructionAdapter = new InstructionAdapter();
        instructionAdapter.setInstructionList(instructionModels);
        viewBinding().setInstructionAdapter(instructionAdapter);


        WhomToSellAdapter whomToSellAdapter = new WhomToSellAdapter();
        whomToSellAdapter.setWhomToSellModels(whomToSellModels);
        viewBinding().setWhomToSellAdapter(whomToSellAdapter);

        TermsConditionAdapter termsConditionAdapter = new TermsConditionAdapter();
        termsConditionAdapter.setTermsConditionsModels(termsConditionsModels);
        viewBinding().setTermsConditionAdapter(termsConditionAdapter);


    }

    @Override
    public ProductDetailViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(ProductDetailViewModel.class);
    }

    @Override
    public FragmentDetailsBinding setupViewBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
    }

    @Override
    public void onRefresh() {

    }
}