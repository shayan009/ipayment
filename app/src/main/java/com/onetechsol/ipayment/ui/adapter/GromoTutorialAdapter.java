package com.onetechsol.ipayment.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.GromoTutorialItemBinding;
import com.onetechsol.ipayment.databinding.TutorialItemClickListener;
import com.onetechsol.ipayment.pojo.TutorialItem;
import com.onetechsol.ipayment.utils.GlideImageLoader;

import java.util.List;

import kotlin.jvm.JvmStatic;

public class GromoTutorialAdapter extends RecyclerView.Adapter<GromoTutorialAdapter.TutorialItemHolder> implements TutorialItemClickListener {


    private List<TutorialItem> tutorialItemList;

    public GromoTutorialAdapter() {
    }

    public void setTutorialItemList(List<TutorialItem> tutorialItemList) {
        this.tutorialItemList = tutorialItemList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TutorialItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        GromoTutorialItemBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.gromo_tutorial_item, parent, false);
        return new TutorialItemHolder(inflate);
    }


    @Override
    public void onBindViewHolder(@NonNull TutorialItemHolder tutorialItemHolder, int position) {

        TutorialItem academyItem = tutorialItemList.get(position);
        tutorialItemHolder.bind(academyItem);
        tutorialItemHolder.gromoTutorialItemBinding.setItemClickListener(this);
    }

    @Override
    public int getItemCount() {
        return tutorialItemList.size();
    }

    @Override
    public void openTutorial(TutorialItem tutorialItem) {
        ////Toast.makeText(MainApp.getContext(), "Clicked Tutorial with id: "+tutorialItem.id(), //Toast.LENGTH_SHORT).show();

    }


    public static class TutorialItemHolder extends RecyclerView.ViewHolder {

        private GromoTutorialItemBinding gromoTutorialItemBinding = null;

        public TutorialItemHolder(GromoTutorialItemBinding gromoTutorialItemBinding) {
            super(gromoTutorialItemBinding.getRoot());
            this.gromoTutorialItemBinding = gromoTutorialItemBinding;
        }

        @JvmStatic
        @BindingAdapter("android:src")
        public void setImageUrl(AppCompatImageView appCompatImageView, TutorialItem tutorialItem) {

            Glide.with(appCompatImageView.getContext()).load(tutorialItem.imageUrl()).apply(RequestOptions.placeholderOf(R.drawable.academy).error(R.drawable.ic_bank)).into(appCompatImageView);

            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.error_image)
                    .priority(Priority.HIGH);

            new GlideImageLoader(appCompatImageView,
                    gromoTutorialItemBinding.progressTutorialLoader).load(tutorialItem.imageUrl(), options);

        }

        public void bind(TutorialItem tutorialItem) {
            setImageUrl(gromoTutorialItemBinding.ivGromoTutImg, tutorialItem);
            gromoTutorialItemBinding.setTutorialItem(tutorialItem);
        }
    }

}
