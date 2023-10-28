package com.onetechsol.ipayment.ui.screen.productdetails.content;

import static com.onetechsol.ipayment.app.MainApp.getContext;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.bumptech.glide.Glide;
import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.FragmentShareContentBinding;
import com.onetechsol.ipayment.pojo.BenefitModel;
import com.onetechsol.ipayment.pojo.ContentModel;
import com.onetechsol.ipayment.pojo.GetAffiliateProductDetailData;
import com.onetechsol.ipayment.ui.adapter.ShareContentAdapter;
import com.onetechsol.ipayment.ui.basefiles.BaseFragment;
import com.onetechsol.ipayment.ui.screen.productdetails.ProductDetailViewModel;
import com.onetechsol.ipayment.utils.ApiConstant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;


public class ShareContentFragment extends BaseFragment<ProductDetailViewModel, FragmentShareContentBinding> implements ShareContentAdapter.AdapterCallback {


    private static final String imageContent = "imageContent";
    private static final String videoContent = "videoContents";
    private static final String benefitModel = "benefitModels";
    private static final String productData = "data";

    private ArrayList<ContentModel> imageContents;
    private ArrayList<ContentModel> videoContents;
    private ArrayList<BenefitModel> benefitModels;

    private GetAffiliateProductDetailData data;

    public ShareContentFragment() {
        // Required empty public constructor
    }

    public static ShareContentFragment newInstance(ArrayList<ContentModel> imageContents, ArrayList<ContentModel> videoContents, ArrayList<BenefitModel> benefitModels, GetAffiliateProductDetailData data) {
        ShareContentFragment fragment = new ShareContentFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(imageContent, imageContents);
        args.putParcelableArrayList(videoContent, videoContents);
        args.putParcelableArrayList(benefitModel, benefitModels);
        args.putParcelable(productData,data);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            imageContents = getArguments().getParcelableArrayList(imageContent);
            videoContents = getArguments().getParcelableArrayList(videoContent);
            benefitModels = getArguments().getParcelableArrayList(benefitModel);
            data = getArguments().getParcelable(productData);

        }
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_share_content;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        ShareContentAdapter shareContentAdapter = new ShareContentAdapter();
        shareContentAdapter.setCallback(this);
        shareContentAdapter.setItems(imageContents);
        viewBinding().rvShareImage.setLayoutManager(new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false));
        viewBinding().rvShareImage.setAdapter(shareContentAdapter);

        ShareContentAdapter shareVideoContentAdapter = new ShareContentAdapter();
        shareVideoContentAdapter.setCallback(this);
        shareVideoContentAdapter.setItems(videoContents);
        viewBinding().rvShareVideo.setLayoutManager(new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false));
        viewBinding().rvShareVideo.setAdapter(shareVideoContentAdapter);

    }

    @Override
    public ProductDetailViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(ProductDetailViewModel.class);
    }

    @Override
    public FragmentShareContentBinding setupViewBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onItemClicked(ContentModel ContentModel) {

    }

    @Override
    public void shareWhatsApp(ContentModel ContentModel) {

    }

    @Override
    public void shareDirectLink(ContentModel contentModel) {
        shareLink(contentModel);
    }

    private void shareLink(ContentModel contentModel) {



        String contentImage = ApiConstant.BASE_URL_IMAGE_SERVICE + contentModel.url();


        StringBuilder out = new StringBuilder();

        if (benefitModels != null && benefitModels.size() > 0) {

            List<String> collect = benefitModels.stream().map(BenefitModel::name).collect(Collectors.toList());

            for (String o : collect) {
                out.append(o);
                out.append("\n");
            }

        }


        String input = "https://partner.ipayments.in/share-link?service=" + data.id() + "&company=" + prefManager.getUserSession().companyVersion() + "&referral=" + prefManager.getUserSession().userName();

        out.append(input);
        out.append("\n");


            io.reactivex.disposables.Disposable subscribe1 = Observable.just(contentImage)
                    .subscribeOn(Schedulers.io())
                    .map(s -> Glide
                            .with(getContext())
                            .asBitmap()
                            .load(s)
                            .submit().get())
                    .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                    .subscribe(bitmap -> {

                        Intent share = new Intent(Intent.ACTION_SEND);

                        String s = MediaStore.Images.Media.insertImage(requireActivity().getContentResolver(), bitmap, data.id(), data.id());

                        share.putExtra(Intent.EXTRA_STREAM, Uri.parse(s));
                        share.putExtra(Intent.EXTRA_TEXT, out.toString());
                        share.setType("text/*");
                        share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                        startActivity(Intent.createChooser(share, "Share"));

                    }, throwable -> {

                        //Toast.makeText(this, throwable.getMessage(), //Toast.LENGTH_SHORT).show();

                    });


    }

    private boolean whatsappInstalledOrNot(String uri) {
        PackageManager pm = requireActivity().getPackageManager();
        boolean app_installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }

    private void openWhatsApp(String smsNumber, String text) {
        try {

            Intent sendIntent = new Intent("android.intent.action.MAIN");
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.setType("text/plain");
            sendIntent.putExtra(Intent.EXTRA_TEXT, text);
            sendIntent.putExtra("jid", smsNumber + "@s.whatsapp.net"); //phone number without "+" prefix
            sendIntent.setPackage("com.whatsapp");
            startActivity(sendIntent);
        } catch (Exception e) {
            //Toast.makeText(getContext(), "Error/n" + e.toString(), //Toast.LENGTH_SHORT).show();
        }

    }
}