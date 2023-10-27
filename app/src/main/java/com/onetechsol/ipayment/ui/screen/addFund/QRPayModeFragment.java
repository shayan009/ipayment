package com.onetechsol.ipayment.ui.screen.addFund;

import static android.content.Context.CLIPBOARD_SERVICE;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.FragmentQRPayBinding;
import com.onetechsol.ipayment.databinding.QRCodeClickListener;
import com.onetechsol.ipayment.pojo.FundReqAccount;
import com.onetechsol.ipayment.ui.basefiles.BaseFragment;
import com.onetechsol.ipayment.utils.ApiConstant;


public class QRPayModeFragment extends BaseFragment<BalanceRequestViewModel, FragmentQRPayBinding> implements QRCodeClickListener {


    private static final String ARG_PARAM1 = "fundReqAccount";
    private FundReqAccount fundReqAccount;

    public QRPayModeFragment() {
        // Required empty public constructor
    }

    public static QRPayModeFragment newInstance(FundReqAccount fundReqAccount) {
        QRPayModeFragment fragment = new QRPayModeFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, fundReqAccount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            fundReqAccount = getArguments().getParcelable(ARG_PARAM1);
        }
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_q_r_pay;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewBinding().setFundReqAccount(fundReqAccount);
        Glide.with(view.getContext()).load(ApiConstant.BASE_URL_IMAGE_SERVICE + fundReqAccount.img()).into(viewBinding().ivQRCode);

    }

    @Override
    public BalanceRequestViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(BalanceRequestViewModel.class);
    }

    @Override
    public FragmentQRPayBinding setupViewBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void copyQR() {


        ClipboardManager clipboard = (ClipboardManager) requireActivity().getSystemService(CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("label", fundReqAccount.account());
        clipboard.setPrimaryClip(clip);
        //Toast.makeText(requireContext(), fundReqAccount.account(), //Toast.LENGTH_SHORT).show();
    }
}