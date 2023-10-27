package com.onetechsol.ipayment.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.AcademyItemClickListener;
import com.onetechsol.ipayment.databinding.GromoAcademyItemBinding;
import com.onetechsol.ipayment.pojo.AcademyItem;

import java.util.List;


public class AcademyAdapter extends RecyclerView.Adapter<AcademyAdapter.AcademyHolder> implements AcademyItemClickListener {


    private List<AcademyItem> academyItemList;

    public AcademyAdapter() {
    }

    public void setAcademyItemList(List<AcademyItem> academyItemList) {
        this.academyItemList = academyItemList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AcademyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        GromoAcademyItemBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.gromo_academy_item, parent, false);
        return new AcademyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull AcademyHolder academyHolder, int position) {

        AcademyItem academyItem = academyItemList.get(position);
        academyHolder.bind(academyItem);
        academyHolder.bind(academyItem);
        academyHolder.gromoAcademyItemBinding.setItemClickListener(this);
    }

    @Override
    public int getItemCount() {
        return academyItemList.size();
    }

    @Override
    public void registerNow(AcademyItem f) {

        ////Toast.makeText(MainApp.getContext(), "Academny : "+f.name()+ " registered.", //Toast.LENGTH_SHORT).show();
    }


    public static class AcademyHolder extends RecyclerView.ViewHolder {

        private GromoAcademyItemBinding gromoAcademyItemBinding = null;

        public AcademyHolder(GromoAcademyItemBinding gromoAcademyItemBinding) {
            super(gromoAcademyItemBinding.getRoot());
            this.gromoAcademyItemBinding = gromoAcademyItemBinding;
        }

        public void bind(AcademyItem academyItem) {
            gromoAcademyItemBinding.setAcademyItem(academyItem);
            gromoAcademyItemBinding.executePendingBindings();
        }

    }

}
