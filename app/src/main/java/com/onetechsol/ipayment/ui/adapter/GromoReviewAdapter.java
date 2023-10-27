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
import com.onetechsol.ipayment.databinding.GromoReviewItemBinding;
import com.onetechsol.ipayment.databinding.ReviewItemClickListener;
import com.onetechsol.ipayment.pojo.ReviewItem;
import com.onetechsol.ipayment.utils.GlideImageLoader;

import java.util.List;

import kotlin.jvm.JvmStatic;

public class GromoReviewAdapter extends RecyclerView.Adapter<GromoReviewAdapter.ReviewItemHolder> implements ReviewItemClickListener {


    private List<ReviewItem> reviewItemList;

    public GromoReviewAdapter() {
    }

    public void setReviewItemList(List<ReviewItem> reviewItemList) {
        this.reviewItemList = reviewItemList;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public ReviewItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        GromoReviewItemBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.gromo_review_item, parent, false);
        return new ReviewItemHolder(inflate);
    }


    @Override
    public void onBindViewHolder(@NonNull ReviewItemHolder tutorialItemHolder, int position) {

        ReviewItem reviewItem = reviewItemList.get(position);
        tutorialItemHolder.bind(reviewItem);
        tutorialItemHolder.gromoReviewItemBinding.setItemClickListener(this);
    }

    @Override
    public int getItemCount() {
        return reviewItemList.size();
    }


    @Override
    public void openReviewVid(ReviewItem reviewItem) {

        // //Toast.makeText(MainApp.getContext(), "Clicked Review with id: "+reviewItem.id(), //Toast.LENGTH_SHORT).show();

    }


    public static class ReviewItemHolder extends RecyclerView.ViewHolder {

        private GromoReviewItemBinding gromoReviewItemBinding = null;

        public ReviewItemHolder(GromoReviewItemBinding gromoReviewItemBinding) {
            super(gromoReviewItemBinding.getRoot());
            this.gromoReviewItemBinding = gromoReviewItemBinding;
        }

        @JvmStatic
        @BindingAdapter("android:src")
        public void setImageUrl(AppCompatImageView appCompatImageView, ReviewItem reviewItem) {

            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.error_image)
                    .priority(Priority.HIGH);

            new GlideImageLoader(appCompatImageView,
                    gromoReviewItemBinding.progressReviewLoader).load(reviewItem.imageUrl(), options);

        }

        public void bind(ReviewItem reviewItem) {
            setImageUrl(gromoReviewItemBinding.ivGromoReviewImg, reviewItem);
            gromoReviewItemBinding.setReviewItem(reviewItem);
        }
    }

}
