package com.onetechsol.ipayment.ui.screen.service.dmt;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.RemitterCheckClickListener;
import com.onetechsol.ipayment.databinding.RemitterCheckSheetBinding;
import com.onetechsol.ipayment.pojo.RecentRemitterItem;
import com.onetechsol.ipayment.ui.adapter.RecentRemitterAdapter;
import com.onetechsol.ipayment.widgets.CurvedBottomSheetDialogFragment;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Random;

import io.reactivex.rxjava3.disposables.Disposable;

public class RemitterCheckBottomSheet extends CurvedBottomSheetDialogFragment<RemitterCheckSheetBinding, DMTViewModel> implements RemitterCheckClickListener {

    boolean otpRequired = false;
    private String remHelp = "";
    private String remMob = "";
    private Disposable subscribe;
    private Disposable subscribe1;
    private Disposable subscribe2;

    @Override
    public int getLayoutRes() {
        return R.layout.remitter_check_sheet;
    }

    @Override
    public DMTViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(DMTViewModel.class);
    }


    @Override
    public RemitterCheckSheetBinding setupViewBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewBinding().setRemitterCheckClickListener(this);


         /*subscribe2 = getRecentRemitter().subscribe(paginatedResultGraphQLResponse -> {

             List<RecentRemitterItem> recentRemitterItems = new ArrayList<>();


             paginatedResultGraphQLResponse.getData().forEach(recentRemitter -> {
                 recentRemitterItems.add(new RecentRemitterItem(recentRemitter.getId(),recentRemitter.getMobile(),recentRemitter.getName(),recentRemitter.getTime().format()));
             });

             List<RecentRemitterItem> recentFinalRemitterItems = new ArrayList<>();

             HashSet<String> strings = new HashSet<>();
             for (int i = 0; i < recentRemitterItems.size(); i++) {
                 RecentRemitterItem recentRemitterItem = recentRemitterItems.get(i);
                 if(!strings.contains(recentRemitterItem.remMobile())) {
                     recentFinalRemitterItems.add(recentRemitterItem);
                     strings.add(recentRemitterItem.remMobile());
                 }
             }

             initRecentRemitterList(recentFinalRemitterItems);

        }, th -> {

        });*/


    }

    public void initRecentRemitterList(List<RecentRemitterItem> recentRemitterItems) {

        RecentRemitterAdapter recentRemitterAdapter = new RecentRemitterAdapter();
        recentRemitterAdapter.setRecentRemitterItems(recentRemitterItems);
        recentRemitterAdapter.setCallback(recentRemitterItem -> {
            viewBinding().etRemitterMobile.setText(recentRemitterItem.remMobile());
            checkRemitter(recentRemitterItem.remMobile());
        });
        viewBinding().setRecentRemitterAdapter(recentRemitterAdapter);

    }

    @Override
    public void hideKeyboard(View view) {

    }

    @Override
    public void onResume() {
        super.onResume();

        if (otpRequired) {
            viewBinding().mcvRemitterName.setVisibility(View.VISIBLE);
            viewBinding().mcvRemitterOtp.setVisibility(View.VISIBLE);
        } else {
            viewBinding().mcvRemitterName.setVisibility(View.GONE);
            viewBinding().mcvRemitterOtp.setVisibility(View.GONE);
        }
    }

    @Override
    public void closeBottomSheet() {
        dismiss();

    }

    @Override
    public void submit(String number) {

        //Toast.makeText(getContext(), "number : "+number, //Toast.LENGTH_SHORT).show();

        if (StringUtils.isEmpty(number)) {
            //Toast.makeText(getContext(), "Mobile number cannot be empty", //Toast.LENGTH_SHORT).show();
            return;
        }

        if (!otpRequired) {

            checkRemitter(number);

        } else {

            if (StringUtils.isNotEmpty(remHelp) && StringUtils.isNotEmpty(remMob)) {


                compositeDisposable().add(viewModel().submitOtpRemitter(remMob, viewBinding().etName.getText().toString(), viewBinding().etOtp.getText().toString(), remHelp)
                        .subscribe(submitRemitterOtpResponse -> {
                            showAlertDialog("DMT Details", submitRemitterOtpResponse.message(), true)
                                    .setPositiveButton("OK", (dialogInterface, i) -> {
                                        dialogInterface.dismiss();

                                        if (submitRemitterOtpResponse.status().equals("1") && submitRemitterOtpResponse.txnStatus().equals("1")) {

                                            Intent intent = new Intent(getContext(), DMTActivity.class);
                                            intent.putExtra("remMobile", remMob);
                                            startActivity(intent);
                                            requireActivity().overridePendingTransition(0, 0);
                                            dismiss();

                                        }

                                    });

                        }, th -> {

                        }));


            }


        }


    }

    private void checkRemitter(String number) {

        compositeDisposable().add(viewModel().checkRemitter(number)
                .subscribe(checkRemitterResponse ->
                        showAlertDialog("DMT Details", checkRemitterResponse.message(), true)
                                .setPositiveButton("OK", (dialogInterface, i) -> {

                                    dialogInterface.dismiss();


                                    if (checkRemitterResponse.status().equals("1") && checkRemitterResponse.txnStatus().equals("3")) {

                                        remMob = checkRemitterResponse.data().mobile();
                                        remHelp = checkRemitterResponse.data().remHelp();
                                        otpRequired = true;
                                        viewBinding().mcvRemitterName.setVisibility(View.VISIBLE);
                                        viewBinding().mcvRemitterOtp.setVisibility(View.VISIBLE);


                                    } else if (checkRemitterResponse.status().equals("1") && checkRemitterResponse.txnStatus().equals("1")) {


                                        Random random = new Random(99999);
                                        int i1 = random.nextInt();

                                        Intent intent = new Intent(getContext(), DMTActivity.class);
                                        intent.putExtra("remMobile", checkRemitterResponse.data().mobile());
                                        startActivity(intent);
                                        requireActivity().overridePendingTransition(0, 0);
                                        dismiss();


                                    }

                                }).show(), th ->
                {

                }));
    }


    @Override
    public boolean isConnectedToNetwork() {
        return false;
    }
}
