package com.onetechsol.ipayment.ui.screen.report;

import static com.onetechsol.ipayment.utils.Utilities.get7DaysRangeBeforeDate;

import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.FragmentReportBinding;
import com.onetechsol.ipayment.databinding.ReportClickListener;
import com.onetechsol.ipayment.pojo.ReportItem;
import com.onetechsol.ipayment.pojo.ServiceCategoryModel;
import com.onetechsol.ipayment.session.UserLocation;
import com.onetechsol.ipayment.session.UserLoginSession;
import com.onetechsol.ipayment.ui.adapter.ReportAdapter;
import com.onetechsol.ipayment.ui.basefiles.BaseFragment;
import com.onetechsol.ipayment.ui.screen.service.aeps.ReportFilterBottomSheet;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ReportFragment extends BaseFragment<ReportViewModel, FragmentReportBinding> implements ReportClickListener, ReportAdapter.AdapterCallback {


    String txnStatus = "";
    private ReportFilterBottomSheet reportFilterBottomSheet;
    private ReportAdapter reportAdapter;
    private List<ReportItem> aeps1OriginalReportItemList;
    private UsbManager mUsbManager;
    private UserLocation userLocation;
    private ServiceCategoryModel serviceCategoryModel;
    private String categoryId;
    private String bearerAuth, userName;


    public static ReportFragment newInstance(String categoryId) {
        Bundle args = new Bundle();
        args.putString("categoryId", categoryId);
        ReportFragment fragment = new ReportFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_report;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        UserLoginSession userSession = viewModel().prefManager().getUserSession();

        if (userSession != null) {

            bearerAuth = "Bearer " + viewModel().prefManager().getLoginToken();
            userName = viewModel().prefManager().getUsername();
            categoryId = getArguments().getString("categoryId");

            reportAdapter = new ReportAdapter();
            reportAdapter.setCallback(this);

            viewBinding().setReportAdapter(reportAdapter);
            viewBinding().setReportClickListener(this);

            getInitial7DaysReport();

            viewBinding().chipGroup.setOnCheckedStateChangeListener((ChipGroup.OnCheckedStateChangeListener) (chipGroup, i) -> {


                if (i.size() > 0) {
                    Chip chip = chipGroup.findViewById(i.get(0));
                    if (chip.isChecked())
                        txnStatus = String.valueOf(chip.getText());
                } else {
                    List<ReportItem> reportItemListNew = new ArrayList<>(aeps1OriginalReportItemList);
                    reportAdapter.setReportItemList(reportItemListNew);
                }


                if (aeps1OriginalReportItemList != null && aeps1OriginalReportItemList.size() > 0) {

                    List<ReportItem> reportItemListNew = new ArrayList<>();
                    for (ReportItem reportItem : aeps1OriginalReportItemList) {
                        if (reportItem.txnStatus().equalsIgnoreCase(txnStatus)) {
                            reportItemListNew.add(reportItem);
                        }
                    }

                    reportAdapter.setReportItemList(reportItemListNew);

                }

            });


        }


    }

    void getInitial7DaysReport() {
        List<String> rangeBeforeDate = get7DaysRangeBeforeDate("yyyy-MM-dd");
        String fromDate = rangeBeforeDate.get(0);
        String toDate = rangeBeforeDate.get(1);

        getAEPS1Report(categoryId, fromDate, toDate, "", true);

    }


    private void getAEPS1Report(String categoryId, String fromDate, String toDate, String txnStatus, boolean reset) {


        String bearerAuth = "Bearer " + viewModel().prefManager().getLoginToken();
        String userName = viewModel().prefManager().getUsername();

        //String categoryId = "AEPS 1";
        String walletType = "";
        String txnType = "";
        String subTxnType = "";
        String page = "0";

        onShowLoading();
        compositeDisposable().add(viewModel().getAEPS1Report(categoryId, fromDate, toDate, txnType, subTxnType, walletType, txnStatus, page)
                .subscribe(reportResponse -> {

                    Log.d("aeps2ReportResponse ->", reportResponse.toString());


                    if (reportResponse.data().txnList() != null && reportResponse.data().txnList().size() > 0) {

                        List<ReportItem> finalReportItemList = new ArrayList<>();
                        List<ReportItem> reportItemsNew = reportResponse.data().txnList();

                        if (!reset) {

                            if (reportAdapter.reportItemList() != null && reportAdapter.reportItemList().size() > 0) {

                                List<ReportItem> reportItemsOld = reportAdapter.reportItemList();
                                finalReportItemList.addAll(reportItemsOld);

                                for (int i = 0; i < reportItemsNew.size(); i++) {
                                    ReportItem reportItemNew = reportItemsNew.get(i);
                                    for (int j = 0; j < reportItemsOld.size(); j++) {
                                        ReportItem reportItemOld = reportItemsOld.get(j);

                                        if (!Objects.equals(reportItemNew.txn(), reportItemOld.txn())) {
                                            finalReportItemList.add(reportItemNew);
                                        }
                                    }

                                }
                            } else {
                                finalReportItemList.addAll(reportItemsNew);
                            }

                        } else {
                            finalReportItemList.addAll(reportItemsNew);
                        }

                        aeps1OriginalReportItemList = finalReportItemList;
                        reportAdapter.setReportItemList(finalReportItemList);
                    } else {
                        showAlertDialog("Report Details", reportResponse.data().pageMsg(), true)
                                .setPositiveButton("OK", (dialogInterface, i) -> {

                                    dialogInterface.dismiss();

                                }).show();
                    }

                    onHideLoading();
                }, throwable -> {
                    onHideLoading();
                    showAlertDialog("Report Error", throwable.getMessage(), true)
                            .setPositiveButton("OK", (dialogInterface, i) -> {

                                dialogInterface.dismiss();

                            }).show();
                }));
    }

    @Override
    public ReportViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(ReportViewModel.class);
    }

    @Override
    public FragmentReportBinding setupViewBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
    }

    @Override
    public void openFilter() {
        reportFilterBottomSheet = new ReportFilterBottomSheet();
        reportFilterBottomSheet.show(getActivity().getSupportFragmentManager(), ReportFilterBottomSheet.class.getName());
        reportFilterBottomSheet.setReportClickListener(this);
    }

    @Override
    public void onDateClickListener(String[] strings) {

        if (StringUtils.isNotBlank(strings[0]) && StringUtils.isNotBlank(strings[1]))
            getAEPS1Report(categoryId, strings[0], strings[1], txnStatus, false);
        else
            getInitial7DaysReport();
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (reportFilterBottomSheet != null) {
            reportFilterBottomSheet.dismiss();
        }

        if (compositeDisposable() != null) {
            compositeDisposable().clear();
        }
    }

    @Override
    public void onItemClicked(ReportItem reportItem) {

    }
}