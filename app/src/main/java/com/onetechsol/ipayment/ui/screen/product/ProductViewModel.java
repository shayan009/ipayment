package com.onetechsol.ipayment.ui.screen.product;

import com.onetechsol.ipayment.pojo.ApprovalRatingType;
import com.onetechsol.ipayment.pojo.BenefitModel;
import com.onetechsol.ipayment.pojo.FetchProductListRequest;
import com.onetechsol.ipayment.pojo.GoalModel;
import com.onetechsol.ipayment.pojo.ProductModel;
import com.onetechsol.ipayment.pojo.SellEarnType;
import com.onetechsol.ipayment.ui.basefiles.BaseViewModel;
import com.onetechsol.ipayment.utils.ApiConstant;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class ProductViewModel extends BaseViewModel {


    public Observable<ArrayList<ProductModel>> getAffiliateServiceList(String sellEarnProductId) {
        return iModelRepository().getAffiliateProductList(new FetchProductListRequest(sellEarnProductId)).map(m -> {

            ArrayList<ProductModel> productModelList = new ArrayList<>();

            m.data().productModelList().forEach(product -> {

                SellEarnType sellEarnType = SellEarnType.get(product.sellEarnId());
                List<BenefitModel> benefitModels = new ArrayList<>();
                List<GoalModel> goalModels = new ArrayList<>();

                product.benefitModels().forEach(benefit -> {
                    benefitModels.add(new BenefitModel(benefit.id(), benefit.name(), "", ApiConstant.BASE_URL_IMAGE_SERVICE + benefit.image()));
                });

                if (sellEarnType == SellEarnType.DEMAT_ACC) {
                    goalModels = product.goalModels();
                }

                productModelList.add(new ProductModel(product.id(), product.type(), sellEarnType, Double.parseDouble(product.minAccountBalance()), Integer.parseInt(product.accountCreationTime()), product.link(), product.name(), benefitModels, goalModels, product.joinFee(), product.annualFee(), ApprovalRatingType.EXCELLANT.name(), product.maxEarn(), ApiConstant.BASE_URL_IMAGE_SERVICE + product.iconUrl(), product.shortName(), "#EADDFF", "#46297B", "#46297B"));

            });

            return productModelList;
        }).observeOn(AndroidSchedulers.mainThread());
    }

}
