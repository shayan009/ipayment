package com.onetechsol.ipayment.ui.adapter;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.app.MainApp;
import com.onetechsol.ipayment.databinding.SellEarnItemBinding;
import com.onetechsol.ipayment.databinding.SellEarnItemClickListener;
import com.onetechsol.ipayment.pojo.SellEarnModel;
import com.onetechsol.ipayment.pojo.SellEarnType;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseRecyclerViewAdapter;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseViewHolder;
import com.onetechsol.ipayment.ui.screen.insurance.InsuranceActivity;
import com.onetechsol.ipayment.ui.screen.product.OtherProductActivity;
import com.onetechsol.ipayment.ui.screen.product.ProductActivity;

import java.util.ArrayList;
import java.util.List;


public class HomeSellEarnAdapter extends BaseRecyclerViewAdapter<SellEarnItemBinding, SellEarnModel, HomeSellEarnAdapter.HomeSellViewHolder> {


    int[] arr = {R.drawable.icon_creditcard, R.drawable.icon_savings, R.drawable.icon_investment, R.drawable.icon_demat};


    public HomeSellEarnAdapter() {
        super();
    }

    @Override
    public List<SellEarnModel> getItems() {
        return super.getItems();
    }

    @NonNull
    @Override
    public HomeSellViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SellEarnItemBinding sellEarnItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.sell_earn_item, parent, false);
        return new HomeSellViewHolder(sellEarnItemBinding, getItems());
    }

    public class HomeSellViewHolder extends BaseViewHolder<SellEarnItemBinding, SellEarnModel> implements SellEarnItemClickListener {

        List<SellEarnModel> items = new ArrayList<>();

        public HomeSellViewHolder(SellEarnItemBinding sellEarnItemBinding, List<SellEarnModel> items) {
            super(sellEarnItemBinding);
            this.items = items;
        }


        @Override
        public void onBind(SellEarnModel sellEarnModel) {
            Log.d("homeItemList", sellEarnModel.toString());

            if (sellEarnModel.amount() > 0) {
                binding().mcvReturn.setVisibility(View.VISIBLE);
                binding().tvSEAmount.setText("Up to ₹" + sellEarnModel.amount());
            } else {
                binding().mcvReturn.setVisibility(View.INVISIBLE);
            }

            Uri uri = Uri.parse(sellEarnModel.imagePath());
            if (getLayoutPosition() <= 3) {
                uri = Uri.parse("android.resource://" + MainApp.getContext().getPackageName() + "/" + arr[getLayoutPosition()]);
            }

            Glide.with(binding().getRoot()).load(uri).into(binding().ivSellItemIcon);

            binding().setSellEarnModel(sellEarnModel);
            binding().setSellEarnItemClickListener(this);
            binding().executePendingBindings();


        }

        @Override
        public void onClickItem(View view, SellEarnModel sellEarnModel) {
            Log.d("SellEarnType:", sellEarnModel.sellEarnType().toString());
            ////Toast.makeText(view.getContext(), sellEarnModel.id(), //Toast.LENGTH_SHORT).show();

            if (sellEarnModel.sellEarnType().equals(SellEarnType.INSURANCE)) {

                Intent intent = new Intent(view.getContext(), InsuranceActivity.class);
                intent.putExtra("sellEarnProductId", sellEarnModel.id());
                view.getContext().startActivity(new Intent(view.getContext(), InsuranceActivity.class));

            } else if (sellEarnModel.sellEarnType().equals(SellEarnType.CREDIT_CARD) || sellEarnModel.sellEarnType().equals(SellEarnType.DEMAT_ACC) || sellEarnModel.sellEarnType().equals(SellEarnType.SAVINGS_ACC)) {

                Intent intent = new Intent(view.getContext(), ProductActivity.class);
                intent.putExtra("sellEarnProductId", sellEarnModel.id());
                view.getContext().startActivity(intent);

            } else {
                Intent intent = new Intent(view.getContext(), OtherProductActivity.class);
                intent.putExtra("sellEarnProductId", sellEarnModel.id());
                intent.putParcelableArrayListExtra("affiliateList", new ArrayList<>(items));
                view.getContext().startActivity(intent);
            }
        }
    }

}
