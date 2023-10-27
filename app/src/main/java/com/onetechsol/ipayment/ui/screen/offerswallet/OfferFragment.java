package com.onetechsol.ipayment.ui.screen.offerswallet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.FragmentOfferBinding;
import com.onetechsol.ipayment.pojo.ImageItem;
import com.onetechsol.ipayment.ui.adapter.RecyclerImageAdapter;
import com.onetechsol.ipayment.ui.basefiles.BaseFragment;

import java.util.ArrayList;
import java.util.List;


public class OfferFragment extends BaseFragment<OfferViewModel, FragmentOfferBinding> {

    private RecyclerImageAdapter recyclerImageAdapter;

    public OfferFragment() {
        // Required empty public constructor
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_offer;
    }

    @Override
    public OfferViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(OfferViewModel.class);
    }


    @Override
    public FragmentOfferBinding setupViewBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        recyclerImageAdapter = new RecyclerImageAdapter();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<ImageItem> imageItemList = new ArrayList<>();
        imageItemList.add(new ImageItem(1, "https://fastly.picsum.photos/id/273/200/200.jpg?hmac=q1g4PnYVQHWkGBWnLmy3VaiQHuPGrZXnpZK986TwkFg"));
        imageItemList.add(new ImageItem(2, "https://fastly.picsum.photos/id/273/200/200.jpg?hmac=q1g4PnYVQHWkGBWnLmy3VaiQHuPGrZXnpZK986TwkFg"));
        imageItemList.add(new ImageItem(3, "https://fastly.picsum.photos/id/273/200/200.jpg?hmac=q1g4PnYVQHWkGBWnLmy3VaiQHuPGrZXnpZK986TwkFg"));
        imageItemList.add(new ImageItem(4, "https://fastly.picsum.photos/id/273/200/200.jpg?hmac=q1g4PnYVQHWkGBWnLmy3VaiQHuPGrZXnpZK986TwkFg"));
        recyclerImageAdapter.setImageItemList(imageItemList);
        viewBinding().setRecyclerImageAdapter(recyclerImageAdapter);
    }


    @Override
    public void onRefresh() {

    }

}