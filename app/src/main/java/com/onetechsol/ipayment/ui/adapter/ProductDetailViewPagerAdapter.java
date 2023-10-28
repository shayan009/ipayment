package com.onetechsol.ipayment.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.onetechsol.ipayment.pojo.BenefitModel;
import com.onetechsol.ipayment.pojo.ContentModel;
import com.onetechsol.ipayment.pojo.GetAffiliateProductDetailData;
import com.onetechsol.ipayment.pojo.InstructionModel;
import com.onetechsol.ipayment.pojo.TermsConditionsModel;
import com.onetechsol.ipayment.pojo.WhomToSellModel;
import com.onetechsol.ipayment.ui.screen.productdetails.content.ShareContentFragment;
import com.onetechsol.ipayment.ui.screen.productdetails.detail.DetailsFragment;
import com.onetechsol.ipayment.ui.screen.productdetails.earning.EarningFragment;

import java.util.ArrayList;

public class ProductDetailViewPagerAdapter extends FragmentStateAdapter {


    private DetailsFragment detailsFragment = null;
    private ShareContentFragment shareContentFragment = null;
    private EarningFragment earningFragment = null;

    public ProductDetailViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, ArrayList<BenefitModel> benefitModels, ArrayList<WhomToSellModel> whomToSellModels, ArrayList<InstructionModel> instructionModels, ArrayList<TermsConditionsModel> termsConditionsModels, ArrayList<ContentModel> contentImages, ArrayList<ContentModel> contentVideos, GetAffiliateProductDetailData product) {
        super(fragmentManager, lifecycle);

        detailsFragment = DetailsFragment.newInstance(benefitModels, whomToSellModels, instructionModels, termsConditionsModels);
        shareContentFragment = ShareContentFragment.newInstance(contentImages, contentVideos,product.benefitModels(),product);
        earningFragment = EarningFragment.newInstance();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return detailsFragment;
            case 1:
                return shareContentFragment;
            case 2:
                return earningFragment;
        }
        return detailsFragment;
    }


    @Override
    public int getItemCount() {
        return 3;
    }
}
