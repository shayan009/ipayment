package com.onetechsol.ipayment.ui.screen.productdetails.content;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.FragmentShareContentBinding;
import com.onetechsol.ipayment.pojo.ContentModel;
import com.onetechsol.ipayment.ui.basefiles.BaseFragment;
import com.onetechsol.ipayment.ui.screen.productdetails.ProductDetailViewModel;

import java.util.ArrayList;


public class ShareContentFragment extends BaseFragment<ProductDetailViewModel, FragmentShareContentBinding> {


    private static final String imageContent = "imageContent";
    private static final String videoContent = "videoContents";

    private ArrayList<ContentModel> imageContents;
    private ArrayList<ContentModel> videoContents;

    public ShareContentFragment() {
        // Required empty public constructor
    }

    public static ShareContentFragment newInstance(ArrayList<ContentModel> imageContents, ArrayList<ContentModel> videoContents) {
        ShareContentFragment fragment = new ShareContentFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(imageContent, imageContents);
        args.putParcelableArrayList(videoContent, videoContents);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            imageContents = getArguments().getParcelableArrayList(imageContent);
            videoContents = getArguments().getParcelableArrayList(videoContent);

        }
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_share_content;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


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
}