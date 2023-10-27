package com.onetechsol.ipayment.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.InstructionItemBinding;
import com.onetechsol.ipayment.pojo.InstructionModel;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseRecyclerViewAdapter;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseViewHolder;

import java.util.List;


public class InstructionAdapter extends BaseRecyclerViewAdapter<InstructionItemBinding, InstructionModel, InstructionAdapter.InstructionViewHolder> {


    private List<InstructionModel> instructionList;

    public InstructionAdapter() {
        super();
    }


    @Override
    public long getItemId(int i) {
        return 0;
    }


    public List<InstructionModel> instructionList() {
        return instructionList;
    }

    public InstructionAdapter setInstructionList(List<InstructionModel> instructionList) {
        this.instructionList = instructionList;
        notifyDataSetChanged();
        return this;
    }

    @NonNull
    @Override
    public InstructionAdapter.InstructionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        InstructionItemBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.instruction_item, parent, false);

        return new InstructionViewHolder(inflate);

    }


    @Override
    public void onBindViewHolder(InstructionViewHolder holder, int position) {

        holder.onBind(instructionList.get(position));
    }


    @Override
    public int getItemCount() {
        return instructionList != null && instructionList.size() > 0 ? instructionList.size() : 0;
    }


    public static class InstructionViewHolder extends BaseViewHolder<InstructionItemBinding, InstructionModel> {

        public InstructionViewHolder(InstructionItemBinding instructionItemBinding) {
            super(instructionItemBinding);

        }

        @Override
        public void onBind(InstructionModel instructionModel) {
            Glide.with(binding().getRoot()).load(instructionModel.image()).apply(RequestOptions.circleCropTransform().error(R.drawable.hdfc_content)).into(binding().ivInstructionImg);
            binding().setInstructionModel(instructionModel);
            binding().executePendingBindings();
        }
    }

}
