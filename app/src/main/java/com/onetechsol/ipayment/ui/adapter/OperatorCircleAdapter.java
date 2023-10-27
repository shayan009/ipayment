package com.onetechsol.ipayment.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.OpCircleItemClickListener;
import com.onetechsol.ipayment.databinding.OperatorCircleItemBinding;
import com.onetechsol.ipayment.pojo.OpCircleItemDto;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseRecyclerViewAdapter;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseViewHolder;

import java.util.List;


public class OperatorCircleAdapter extends BaseRecyclerViewAdapter<OperatorCircleItemBinding, OpCircleItemDto, OperatorCircleAdapter.OpCircleViewHolder> {


    private List<OpCircleItemDto> opCircleItemDtoList;

    private AdapterCallback callback;


    @Override
    public long getItemId(int i) {
        return 0;
    }

    public OperatorCircleAdapter setOpCircleItemDtoList(List<OpCircleItemDto> opCircleItemDtoList) {
        this.opCircleItemDtoList = opCircleItemDtoList;
        notifyDataSetChanged();
        return this;
    }

    public List<OpCircleItemDto> opCircleItemDtoList() {
        return opCircleItemDtoList;
    }

    public AdapterCallback callback() {
        return callback;
    }

    public OperatorCircleAdapter setCallback(AdapterCallback callback) {
        this.callback = callback;
        return this;
    }

    @NonNull
    @Override
    public OpCircleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        OperatorCircleItemBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.operator_circle_item, parent, false);

        return new OpCircleViewHolder(inflate);

    }

    @Override
    public void onBindViewHolder(OpCircleViewHolder holder, int position) {

        holder.onBind(opCircleItemDtoList.get(position));
    }

    @Override
    public int getItemCount() {
        return opCircleItemDtoList != null && opCircleItemDtoList.size() > 0 ? opCircleItemDtoList.size() : 0;
    }


    public interface AdapterCallback {
        void onItemClicked(OpCircleItemDto opCircleItemDto);
    }

    public class OpCircleViewHolder extends BaseViewHolder<OperatorCircleItemBinding, OpCircleItemDto> implements OpCircleItemClickListener {


        public OpCircleViewHolder(OperatorCircleItemBinding operatorCircleItemBinding) {
            super(operatorCircleItemBinding);

        }

        @Override
        public void onBind(OpCircleItemDto opCircleItemDto) {

            Log.d("opCircleItemDto", opCircleItemDto.toString());

            //Glide.with(binding().getRoot()).load(Uri.parse(opCircleItemDto.image())).into(binding().ivOperatorCircleIcon);

            // new GlideImageLoader(binding().ivOperatorCircleIcon,null).load(tutorialItem.imageUrl(),options);

            binding().setOpCircleItemDto(opCircleItemDto);
            binding().setOpCircleItemClickListener(this);
            binding().executePendingBindings();
        }

        @Override
        public void onOpItemClick(OpCircleItemDto opCircleItemDto) {
            if (callback != null)
                callback.onItemClicked(opCircleItemDto);
        }
    }

}
