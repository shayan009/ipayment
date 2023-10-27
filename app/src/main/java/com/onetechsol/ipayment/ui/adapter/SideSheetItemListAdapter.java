package com.onetechsol.ipayment.ui.adapter;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.app.MainApp;
import com.onetechsol.ipayment.databinding.SideSheetDataOnClickListener;
import com.onetechsol.ipayment.databinding.SideSheetItemClickListener;
import com.onetechsol.ipayment.databinding.SpinnerItemBinding;
import com.onetechsol.ipayment.pojo.SideSheetItem;

import java.util.List;

public class SideSheetItemListAdapter extends RecyclerView.Adapter<SideSheetItemListAdapter.SideSheetItemHolder> {

    private static SideSheetDataOnClickListener sideSheetDataOnClickListener;
    private List<SideSheetItem> sideSheetItems;


    public SideSheetItemListAdapter(List<SideSheetItem> stateListList) {
        this.sideSheetItems = stateListList;
    }

    public void setStateOnClickListener(SideSheetDataOnClickListener sideSheetDataOnClickListener) {
        SideSheetItemListAdapter.sideSheetDataOnClickListener = sideSheetDataOnClickListener;
    }

    public void setSideSheetItemList(List<SideSheetItem> stateListList) {
        this.sideSheetItems = stateListList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SideSheetItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SpinnerItemBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.spinner_item, parent, false);
        return new SideSheetItemHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull SideSheetItemHolder cardHolder, int position) {
        SideSheetItem stateList = sideSheetItems.get(position);
        stateList.setOdd(position % 2 == 0);
        cardHolder.bind(stateList);

    }

    @Override
    public int getItemCount() {
        return sideSheetItems.size();
    }


    public static class SideSheetItemHolder extends RecyclerView.ViewHolder implements SideSheetItemClickListener {

        private SpinnerItemBinding spinnerItemBinding;

        public SideSheetItemHolder(SpinnerItemBinding spinnerItemBinding) {
            super(spinnerItemBinding.getRoot());
            this.spinnerItemBinding = spinnerItemBinding;
        }


        public void bind(SideSheetItem sideSheetItem) {
            Typeface typeface = null;

            if (sideSheetItem.selected()) {
                typeface = MainApp.getContext().getResources().getFont(R.font.balsamiqsansbold);
                spinnerItemBinding.tvServiceText.setTextColor(MainApp.getContext().getResources().getColor(R.color.colorPrimary_Deep));
            } else {
                typeface = MainApp.getContext().getResources().getFont(R.font.balsamiqsansregular);
                spinnerItemBinding.tvServiceText.setTextColor(MainApp.getContext().getResources().getColor(R.color.deep_black));
            }

            spinnerItemBinding.tvServiceText.setTypeface(typeface);
            spinnerItemBinding.setSideSheetItem(sideSheetItem);
            spinnerItemBinding.setSideSheetItemClickListener(this);
            spinnerItemBinding.executePendingBindings();
        }


        @Override
        public void onClickItem(SideSheetItem stateList) {
            sideSheetDataOnClickListener.selectSideSheetItem(stateList);
        }
    }
}
