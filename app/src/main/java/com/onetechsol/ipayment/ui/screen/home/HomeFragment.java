package com.onetechsol.ipayment.ui.screen.home;

import static com.onetechsol.ipayment.utils.ApiConstant.BASE_URL_IMAGE_SERVICE;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.app.MainApp;
import com.onetechsol.ipayment.databinding.FragmentHomeBinding;
import com.onetechsol.ipayment.databinding.HomeItemCLickListener;
import com.onetechsol.ipayment.pojo.AcademyItem;
import com.onetechsol.ipayment.pojo.AffiliateModel;
import com.onetechsol.ipayment.pojo.DepartmentModel;
import com.onetechsol.ipayment.pojo.ImageSliderItem;
import com.onetechsol.ipayment.pojo.ReportTypeItem;
import com.onetechsol.ipayment.pojo.ReviewItem;
import com.onetechsol.ipayment.pojo.SellEarnModel;
import com.onetechsol.ipayment.pojo.SellEarnType;
import com.onetechsol.ipayment.pojo.ServiceModel;
import com.onetechsol.ipayment.pojo.TutorialItem;
import com.onetechsol.ipayment.pojo.WalletList;
import com.onetechsol.ipayment.pojo.WalletType;
import com.onetechsol.ipayment.session.UserLoginSession;
import com.onetechsol.ipayment.ui.adapter.AcademyAdapter;
import com.onetechsol.ipayment.ui.adapter.AffiliateDepartmentAdapter;
import com.onetechsol.ipayment.ui.adapter.GromoReviewAdapter;
import com.onetechsol.ipayment.ui.adapter.GromoTutorialAdapter;
import com.onetechsol.ipayment.ui.adapter.ScreenSlidePagerAdapter;
import com.onetechsol.ipayment.ui.adapter.ServiceAdapter;
import com.onetechsol.ipayment.ui.basefiles.BaseFragment;
import com.onetechsol.ipayment.ui.screen.addFund.BalanceRequestBottomSheet;
import com.onetechsol.ipayment.ui.screen.dashboard.DashboardActivity;
import com.onetechsol.ipayment.ui.screen.fund_transfer.FundTransferBottomSheet;
import com.onetechsol.ipayment.ui.screen.login_singup.LoginSignupActivity;
import com.onetechsol.ipayment.ui.screen.mydiary.GromoDiaryActivity;
import com.onetechsol.ipayment.ui.screen.offerswallet.OfferWalletActivity;
import com.onetechsol.ipayment.ui.screen.report.ReportActivity;
import com.onetechsol.ipayment.ui.screen.report.ReportCategoryBottomSheet;
import com.onetechsol.ipayment.ui.screen.service.ServiceFragment;
import com.onetechsol.ipayment.ui.tracking.TrackingService;
import com.onetechsol.ipayment.utils.Constant;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import io.reactivex.rxjava3.disposables.Disposable;

public class HomeFragment extends BaseFragment<HomeFragmentViewModel, FragmentHomeBinding> implements HomeItemCLickListener, ReportCategoryBottomSheet.AdapterCallback {


    private ReportCategoryBottomSheet reportCategoryBottomSheet;
    private Disposable subscribe2;
    private Disposable subscribe1;
    private FundTransferBottomSheet fundTransferBottomSheet;
    private BalanceRequestBottomSheet balanceRequestBottomSheet;
    private ServiceAdapter serviceAdapter;
    //private ActivityResultLauncher<Intent> pweActivityResultLauncher;


    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    public HomeFragmentViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(HomeFragmentViewModel.class);
    }


    @Override
    public FragmentHomeBinding setupViewBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ContextCompat.startForegroundService(requireContext(), new Intent(requireActivity(), TrackingService.class));

        UserLoginSession userSession = viewModel().prefManager().getUserSession();

        if (userSession != null) {

            serviceAdapter = new ServiceAdapter();

            String bearerAuth = "Bearer " + viewModel().prefManager().getLoginToken();
            String userName = viewModel().prefManager().getUsername();

            viewBinding().tvTitle.setText(viewModel().prefManager().getUserSession().loginName());
            viewBinding().tvLocation.setText(viewModel().prefManager().getUserSession().address());

            getServiceList();

            setWalletDetails(bearerAuth, userName);

            viewBinding().rvServices.setAdapter(serviceAdapter);


           /* List<AcademyItem> academyItems = new ArrayList<>();
            academyItems.add(new AcademyItem(1, "First step to sell Insurance", "Insurance", "09 Feb, 09:00AM"));
            academyItems.add(new AcademyItem(2, "Learn how to earn 1 Lakh/Month", "Onboarding", "09 Feb, 09:00AM"));

            AcademyAdapter academyAdapter = new AcademyAdapter();
            academyAdapter.setAcademyItemList(academyItems);
            viewBinding().setAcademyAdapter(academyAdapter);*/


           /* List<ImageSliderItem> imageSliderItems = new ArrayList<>();
            imageSliderItems.add(new ImageSliderItem(1, "https://fastly.picsum.photos/id/273/200/200.jpg?hmac=q1g4PnYVQHWkGBWnLmy3VaiQHuPGrZXnpZK986TwkFg"));
            imageSliderItems.add(new ImageSliderItem(2, "https://fastly.picsum.photos/id/273/200/200.jpg?hmac=q1g4PnYVQHWkGBWnLmy3VaiQHuPGrZXnpZK986TwkFg"));

            ScreenSlidePagerAdapter screenSlidePagerAdapter = new ScreenSlidePagerAdapter(requireActivity());
            screenSlidePagerAdapter.setImageSliderItems(imageSliderItems);*/


            /*viewBinding().viewPager2.setAdapter(screenSlidePagerAdapter);
            TabLayout tabLayout = viewBinding().tabLayout;
            ViewPager2 viewPager2 = viewBinding().viewPager2;

            new TabLayoutMediator(tabLayout, viewPager2,
                    (tab, position) -> {
                        viewPager2.setCurrentItem(position, true);
                    }
            ).attach();

            List<TutorialItem> tutorialItemList = new ArrayList<>();
            tutorialItemList.add(new TutorialItem(1, "https://fastly.picsum.photos/id/273/200/200.jpg?hmac=q1g4PnYVQHWkGBWnLmy3VaiQHuPGrZXnpZK986TwkFg", "Introduction to MyGroMo"));
            tutorialItemList.add(new TutorialItem(2, "https://fastly.picsum.photos/id/273/200/200.jpg?hmac=q1g4PnYVQHWkGBWnLmy3VaiQHuPGrZXnpZK986TwkFg", "Make your first sale"));

            GromoTutorialAdapter gromoTutorialAdapter = new GromoTutorialAdapter();
            gromoTutorialAdapter.setTutorialItemList(tutorialItemList);
            viewBinding().setGromoTutorialAdapter(gromoTutorialAdapter);

            List<ReviewItem> reviewItemList = new ArrayList<>();
            reviewItemList.add(new ReviewItem(1, "https://fastly.picsum.photos/id/273/200/200.jpg?hmac=q1g4PnYVQHWkGBWnLmy3VaiQHuPGrZXnpZK986TwkFg"));
            reviewItemList.add(new ReviewItem(2, "https://fastly.picsum.photos/id/273/200/200.jpg?hmac=q1g4PnYVQHWkGBWnLmy3VaiQHuPGrZXnpZK986TwkFg"));

            GromoReviewAdapter gromoReviewAdapter = new GromoReviewAdapter();
            gromoReviewAdapter.setReviewItemList(reviewItemList);
            viewBinding().setGromoReviewAdapter(gromoReviewAdapter);
*/

            // viewBinding().rvSellEarn.setLayoutManager(new GridLayoutManager(getContext(), 4, LinearLayoutManager.VERTICAL, false));


            viewBinding().setHomeItemCLick(this);

            reportCategoryBottomSheet = new ReportCategoryBottomSheet();
            reportCategoryBottomSheet.setAdapterCallback(this);


        }

    }

    private void getServiceList() {

        compositeDisposable().add(viewModel().getServiceList()
                .subscribe(serviceListResponse -> {
                    onHideLoading();

                    ArrayList<ServiceModel> serviceModelList = serviceListResponse.serviceModelList();

                    if (serviceListResponse.status().equals("1")) {


                        Log.d("serviceModelList", String.valueOf(serviceModelList.size()));


                        serviceAdapter.setItems(serviceModelList);

                        serviceAdapter.setCallback(serviceModel -> {

                            Log.d("serviceModel.HomeClick", serviceModel.toString());

                            ServiceFragment serviceFragment = ServiceFragment.newInstance(serviceModelList, serviceModel.id());
                            onAttachFragment(R.id.frameLayout, serviceFragment, ServiceFragment.class.getName(), true);
                        });

                    } else if (serviceListResponse.status().equals("0")) {
                        showAlertDialog("Service alert!", serviceListResponse.message(), true).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                dialogInterface.dismiss();

                            }
                        }).show();
                    }


                }, throwable -> {
                    onHideLoading();
                    showAlertDialog("Error alert!", throwable.getMessage(), true).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {


                            dialogInterface.dismiss();

                        }
                    }).show();
                }));
    }

    private void getAffiliateAPi() {

        viewBinding().shimmerAffiliateServices.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));
        AffiliateDepartmentAdapter affiliateDepartmentAdapter = new AffiliateDepartmentAdapter();
        viewBinding().shimmerAffiliateServices.setAdapter(affiliateDepartmentAdapter);
        viewBinding().shimmerAffiliateServices.showShimmerAdapter();

        compositeDisposable().add(viewModel().getDepartmentList()
                .subscribe(getDepartmentListResponse -> {

                    affiliateDepartmentAdapter.setItems(getDepartmentListResponse.data.departmentList());
                    viewBinding().shimmerAffiliateServices.hideShimmerAdapter();
                }, throwable -> {
                    viewBinding().shimmerAffiliateServices.hideShimmerAdapter();
                })
        );

    }

    private void setWalletDetails(String bearerAuth, String userName) {


        compositeDisposable().add(viewModel().getWalletList(bearerAuth, userName).subscribe(walletListResponse -> {


            if (walletListResponse.status().equals("1")) {


                for (int i = 0; i < walletListResponse.data().waletList().size(); i++) {
                    WalletList walletList = walletListResponse.data().waletList().get(i);
                    BigDecimal bigDecimal = StringUtils.isNotBlank(walletList.amt()) ? new BigDecimal(walletList.amt()) : BigDecimal.ZERO;

                    if (WalletType.get(walletList.label()) == WalletType.COMMISSION) {
                        viewBinding().tvCommissionBal.setText("" + bigDecimal);
                    } else if (WalletType.get(walletList.label()) == WalletType.SETTLEMENT) {
                        viewBinding().tvMoveFundBal.setText("₹ " + bigDecimal);
                    } else if (WalletType.get(walletList.label()) == WalletType.MAIN) {
                        viewBinding().tvAddFundBal.setText("₹ " + bigDecimal);
                    }
                }
                viewBinding().tvTotalBalance.setText(walletListResponse.data().totalWalet());
                fundTransferBottomSheet = new FundTransferBottomSheet();
                fundTransferBottomSheet.setRetailerStatus(walletListResponse.data().retailerStatus());

                balanceRequestBottomSheet = new BalanceRequestBottomSheet();

                prefManager.setUpgradeAmount(walletListResponse.data().learnerAmt());

            }

            getAffiliateAPi();

        }, throwable -> {
            onHideLoading();
            getAffiliateAPi();
        }));
    }

    private void logout(String message) {
        new MaterialAlertDialogBuilder(requireContext())
                .setTitle("Logout")
                .setMessage(message)
                .setIcon(R.drawable.error_image)
                .setPositiveButton("OK", (dialogInterface, i) -> {
                    viewModel().prefManager().clearLoginData();
                    Intent intent = new Intent(getActivity(), LoginSignupActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    dialogInterface.dismiss();
                    requireActivity().finish();
                }).show();
    }

    @Override
    public void onRefresh() {

    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void openDashboard() {
        startActivity(new Intent(getActivity(), DashboardActivity.class));
    }

    @Override
    public void openNotification(View view) {

    }

    @Override
    public void openSupport(View view) {

    }

    @Override
    public void openDiary(View view) {
        //startActivity(new Intent(getActivity(), GromoDiaryActivity.class));
    }

    @Override
    public void openOfferWallet(View view) {
        startActivity(new Intent(getActivity(), OfferWalletActivity.class));
    }

    @Override
    public void openSellEarnMore() {
       /* if (homeItemList != null && !homeItemList.isEmpty()) {
            SellEarnBottomSheet sellEarnBottomSheet = new SellEarnBottomSheet();
            sellEarnBottomSheet.setHomeItemList(homeItemList);
            sellEarnBottomSheet.show(getParentFragmentManager(), SellEarnBottomSheet.class.getName());
        }*/

    }

    @Override
    public void openServices() {
        Intent intent = new Intent("seeMoreService");
        LocalBroadcastManager.getInstance(requireActivity()).sendBroadcast(intent);

    }

    @Override
    public void startFundTransfer() {
        viewBinding().mcbMoveFund.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorbtnGreen)));
        viewBinding().tvMoveFund.setTextColor(getResources().getColor(R.color.white));
        viewBinding().tvMoveFundBal.setTextColor(getResources().getColor(R.color.white));
        Uri selectedImg = Uri.parse("android.resource://" + MainApp.getContext().getPackageName() + "/" + R.drawable.icon_add_fund);
        Glide.with(requireContext()).load(selectedImg).apply(RequestOptions.circleCropTransform()).into(viewBinding().ivMoveFund);

        viewBinding().mcbAddFund.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
        viewBinding().tvAddFund.setTextColor(getResources().getColor(R.color.text_black));
        viewBinding().tvAddFundBal.setTextColor(getResources().getColor(R.color.text_black));

        Uri unSselectedImg = Uri.parse("android.resource://" + MainApp.getContext().getPackageName() + "/" + R.drawable.icon_move_fund);
        Glide.with(requireContext()).load(unSselectedImg).apply(RequestOptions.circleCropTransform()).into(viewBinding().ivAddFund);

        if (fundTransferBottomSheet != null)
            fundTransferBottomSheet.show(getParentFragmentManager(), FundTransferBottomSheet.class.getName());
    }

    @Override
    public void startBalanceRequest() {

        modifyButtonColor();

        if (balanceRequestBottomSheet != null)
            balanceRequestBottomSheet.show(getParentFragmentManager(), BalanceRequestBottomSheet.class.getName());

    }

    @Override
    public void openTransactionList() {
        if (null != reportCategoryBottomSheet)
            reportCategoryBottomSheet.show(getParentFragmentManager(), ReportCategoryBottomSheet.class.getName());
    }

    private void modifyButtonColor() {
        viewBinding().mcbMoveFund.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
        viewBinding().tvMoveFund.setTextColor(getResources().getColor(R.color.text_black));
        viewBinding().tvMoveFundBal.setTextColor(getResources().getColor(R.color.text_black));
        Uri unSselectedImg = Uri.parse("android.resource://" + MainApp.getContext().getPackageName() + "/" + R.drawable.icon_move_fund);
        Glide.with(requireContext()).load(unSselectedImg).apply(RequestOptions.circleCropTransform()).into(viewBinding().ivMoveFund);


        viewBinding().mcbAddFund.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorbtnGreen)));
        viewBinding().tvAddFund.setTextColor(getResources().getColor(R.color.white));
        viewBinding().tvAddFundBal.setTextColor(getResources().getColor(R.color.white));

        Uri selectedImg = Uri.parse("android.resource://" + MainApp.getContext().getPackageName() + "/" + R.drawable.icon_add_fund);
        Glide.with(requireContext()).load(selectedImg).apply(RequestOptions.circleCropTransform()).into(viewBinding().ivAddFund);
    }

    @Override
    public void selectReportCategory(ReportTypeItem reportTypeItem) {
        if (null != reportCategoryBottomSheet)
            reportCategoryBottomSheet.dismiss();

        Log.d("reportTypeItem", reportTypeItem.toString());
        Intent intent = new Intent(requireActivity(), ReportActivity.class);
        intent.putExtra("categoryId", reportTypeItem.id());
        startActivity(intent);

    }
}