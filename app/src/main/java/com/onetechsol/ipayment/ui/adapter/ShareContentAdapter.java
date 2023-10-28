package com.onetechsol.ipayment.ui.adapter;

import android.media.MediaPlayer;
import android.media.session.MediaController;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.ShareContentItemBinding;
import com.onetechsol.ipayment.databinding.ShareContentOnClickListener;
import com.onetechsol.ipayment.pojo.ContentModel;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseRecyclerViewAdapter;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseViewHolder;


public class ShareContentAdapter extends BaseRecyclerViewAdapter<ShareContentItemBinding, ContentModel, ShareContentAdapter.ShareContentViewHolder> {


    private AdapterCallback callback;

    public ShareContentAdapter() {
        super();
    }

    public AdapterCallback callback() {
        return callback;
    }

    public ShareContentAdapter setCallback(AdapterCallback callback) {
        this.callback = callback;
        return this;
    }

    @NonNull
    @Override
    public ShareContentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ShareContentItemBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.share_content_item, parent, false);

        return new ShareContentViewHolder(inflate);

    }

    public interface AdapterCallback {
        void onItemClicked(ContentModel ContentModel);
        void shareWhatsApp(ContentModel ContentModel);
        void shareDirectLink(ContentModel ContentModel);
    }

    public class ShareContentViewHolder extends BaseViewHolder<ShareContentItemBinding, ContentModel> implements ShareContentOnClickListener {


        public ShareContentViewHolder(ShareContentItemBinding shareContentItemBinding) {
            super(shareContentItemBinding);
        }

        @Override
        public void onBind(ContentModel contentModel) {

            if(contentModel.type() == 1) {
                binding().ivContent.setVisibility(View.VISIBLE);
                binding().videoContent.setVisibility(View.INVISIBLE);
                Glide.with(binding().getRoot()).load(contentModel.url()).into(binding().ivContent);
            } else {
                binding().ivContent.setVisibility(View.INVISIBLE);
                binding().videoContent.setVisibility(View.VISIBLE);
                VideoView videoView = binding().videoContent;
                ;

                videoView.setOnClickListener(view -> {
                    Uri video = Uri.parse(contentModel.url());
                    videoView.setVideoURI(video);
                    videoView.setOnPreparedListener(mp -> {
                        mp.setLooping(true);
                        videoView.start();
                    });
                });

            }
            binding().setContentModel(contentModel);
            binding().setShareContentOnClickListener(this);
            binding().executePendingBindings();
        }

        @Override
        public void onClickItem(ContentModel ContentModel) {
            if (callback != null)
                callback.onItemClicked(ContentModel);
        }

        @Override
        public void shareWhatsApp(ContentModel contentModel) {
            if (callback != null)
                callback.shareWhatsApp(contentModel);
        }

        @Override
        public void shareDirectLink(ContentModel contentModel) {
            if (callback != null)
                callback.shareDirectLink(contentModel);
        }
    }

}
