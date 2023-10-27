package com.onetechsol.ipayment.widgets;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.sidesheet.SideSheetCallback;
import com.google.android.material.sidesheet.SideSheetDialog;
import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.SideSheetDataOnClickListener;
import com.onetechsol.ipayment.pojo.SideSheetItem;
import com.onetechsol.ipayment.ui.adapter.SideSheetItemListAdapter;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class CustomSideSheetSpinner extends SideSheetDialog implements SideSheetDataOnClickListener {

    private Context context;

    private List<SideSheetItem> sideSheetItems;
    private SideSpinnerCallback sideSpinnerCallback;
    private SideSheetDataOnClickListener sideSheetDataOnClickListener;
    private SideSheetItemListAdapter sideSheetItemListAdapter;

    public CustomSideSheetSpinner(@NonNull Context context, List<SideSheetItem> sideSheetItems) {
        super(context);
        this.context = context;
        this.sideSheetItems = sideSheetItems;
    }

    public CustomSideSheetSpinner(@NonNull Context context, int theme) {
        super(context, theme);
    }

    public CustomSideSheetSpinner setSideSheetDataOnClickListener(SideSheetDataOnClickListener sideSheetDataOnClickListener) {
        this.sideSheetDataOnClickListener = sideSheetDataOnClickListener;
        return this;
    }

    public CustomSideSheetSpinner setSideSheetItems(List<SideSheetItem> sideSheetItems) {
        this.sideSheetItems = sideSheetItems;
        sideSheetItemListAdapter.setSideSheetItemList(sideSheetItems);
        return this;
    }

    public CustomSideSheetSpinner setSideSpinnerCallback(SideSpinnerCallback sideSpinnerCallback) {
        this.sideSpinnerCallback = sideSpinnerCallback;
        return this;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getBehavior().addCallback(new SideSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View sheet, int newState) {

            }

            @Override
            public void onSlide(@NonNull View sheet, float slideOffset) {

            }
        });
        setCancelable(false);
        setCanceledOnTouchOutside(true);
        View inflate = getLayoutInflater().inflate(R.layout.sidesheet_spinner, null);
        setContentView(inflate);

        RecyclerView rvSpinnerList = inflate.findViewById(R.id.rvSpinnerList);
        EditText etSearchQuery = inflate.findViewById(R.id.etSearchQuery);
        etSearchQuery.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                if (StringUtils.isNoneEmpty(charSequence) && charSequence.length() > 2) {
                    if (sideSpinnerCallback != null) {
                        sideSpinnerCallback.onItemSearched(String.valueOf(charSequence));
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        rvSpinnerList.setLayoutManager(new LinearLayoutManager(context));
        sideSheetItemListAdapter = new SideSheetItemListAdapter(sideSheetItems);


        sideSheetItemListAdapter.setStateOnClickListener(this);
        rvSpinnerList.setAdapter(sideSheetItemListAdapter);


    }

    @Override
    public void selectSideSheetItem(SideSheetItem stateList) {

        for (SideSheetItem sideSheetItem1 : sideSheetItems) {
            sideSheetItem1.setSelected(sideSheetItem1.id() == stateList.id());
        }

        sideSheetItemListAdapter.setSideSheetItemList(sideSheetItems);
        sideSheetDataOnClickListener.selectSideSheetItem(stateList);
    }


    public interface SideSpinnerCallback {
        void onItemSearched(String search);
    }
}
