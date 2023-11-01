package com.onetechsol.ipayment.ui.adapter;

import android.graphics.Typeface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.app.MainApp;
import com.onetechsol.ipayment.databinding.SideMenuItemBinding;
import com.onetechsol.ipayment.databinding.SideSheetDataOnClickListener;
import com.onetechsol.ipayment.databinding.SideSheetItemClickListener;
import com.onetechsol.ipayment.databinding.SpinnerItemBinding;
import com.onetechsol.ipayment.pojo.ServiceModel;
import com.onetechsol.ipayment.pojo.SideSheetItem;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseRecyclerViewAdapter;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseViewHolder;

import java.util.List;

public class SideSheetItemListAdapter extends BaseRecyclerViewAdapter<SpinnerItemBinding, SideSheetItem, SideSheetItemListAdapter.SideSheetItemHolder> {

    private static SideSheetDataOnClickListener sideSheetDataOnClickListener;


    public void setStateOnClickListener(SideSheetDataOnClickListener sideSheetDataOnClickListener) {
        SideSheetItemListAdapter.sideSheetDataOnClickListener = sideSheetDataOnClickListener;
    }


    @NonNull
    @Override
    public SideSheetItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SpinnerItemBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.spinner_item, parent, false);
        return new SideSheetItemHolder(inflate);
    }


    public static class SideSheetItemHolder extends BaseViewHolder<SpinnerItemBinding, SideSheetItem> implements SideSheetItemClickListener {


        public SideSheetItemHolder(SpinnerItemBinding spinnerItemBinding) {
            super(spinnerItemBinding);
        }


        @Override
        public void onBind(SideSheetItem sideSheetItem) {
            Typeface typeface = null;

            if (sideSheetItem.selected()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    typeface = MainApp.getContext().getResources().getFont(R.font.balsamiqsansbold);
                }
                binding().tvServiceText.setTextColor(MainApp.getContext().getResources().getColor(R.color.colorPrimary_Deep));
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    typeface = MainApp.getContext().getResources().getFont(R.font.balsamiqsansregular);
                }
                binding().tvServiceText.setTextColor(MainApp.getContext().getResources().getColor(R.color.deep_black));
            }

            binding().tvServiceText.setTypeface(typeface);
            binding().setSideSheetItem(sideSheetItem);
            binding().setSideSheetItemClickListener(this);
            binding().executePendingBindings();
        }


        @Override
        public void onClickItem(SideSheetItem stateList) {
            sideSheetDataOnClickListener.selectSideSheetItem(stateList);
        }
    }
}
