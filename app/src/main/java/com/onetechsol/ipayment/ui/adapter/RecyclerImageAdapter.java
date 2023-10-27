package com.onetechsol.ipayment.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.OfferItemClickListener;
import com.onetechsol.ipayment.databinding.OfferListItemBinding;
import com.onetechsol.ipayment.pojo.ImageItem;
import com.onetechsol.ipayment.utils.GlideImageLoader;

import java.util.List;

import kotlin.jvm.JvmStatic;

public class RecyclerImageAdapter extends RecyclerView.Adapter<RecyclerImageAdapter.ImageHolder> {

    private List<ImageItem> imageItemList;

    public void setImageItemList(List<ImageItem> imageItemList) {
        this.imageItemList = imageItemList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        OfferListItemBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.offer_list_item, parent, false);
        return new ImageHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageHolder imageHolder, int position) {

        ImageItem academyItem = imageItemList.get(position);
        imageHolder.bind(academyItem);

    }

    @Override
    public int getItemCount() {
        return imageItemList.size();
    }

    public static class ImageHolder extends RecyclerView.ViewHolder implements OfferItemClickListener {

        private OfferListItemBinding offerListItemBinding;

        public ImageHolder(OfferListItemBinding offerListItemBinding) {
            super(offerListItemBinding.getRoot());
            this.offerListItemBinding = offerListItemBinding;
        }

        @JvmStatic
        @BindingAdapter("android:src")
        public void setImageUrl(AppCompatImageView appCompatImageView, ImageItem imageItem) {

            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.error_image)
                    .priority(Priority.HIGH);

            new GlideImageLoader(appCompatImageView,
                    offerListItemBinding.progressOfferLoader).load(imageItem.imgUrl(), options);

        }

        public void bind(ImageItem imageItem) {
            setImageUrl(offerListItemBinding.ivOfferImgItem, imageItem);
            offerListItemBinding.setImageItem(imageItem);
            offerListItemBinding.setItemClickListener(this);
            offerListItemBinding.executePendingBindings();
        }

        @Override
        public void selectOfferItem(ImageItem imageItem) {

        }
    }
}
