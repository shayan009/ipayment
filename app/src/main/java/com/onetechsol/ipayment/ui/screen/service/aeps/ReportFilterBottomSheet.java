package com.onetechsol.ipayment.ui.screen.service.aeps;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.util.Pair;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.chip.Chip;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointBackward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.ReportClickListener;
import com.onetechsol.ipayment.databinding.ReportFilterClickListener;
import com.onetechsol.ipayment.databinding.ReportFilterSheetBinding;
import com.onetechsol.ipayment.widgets.CurvedBottomSheetDialogFragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class ReportFilterBottomSheet extends CurvedBottomSheetDialogFragment<ReportFilterSheetBinding, ReportFilterViewModel> implements ReportFilterClickListener {


    private ReportClickListener reportClickListener;

    @Override
    public int getLayoutRes() {
        return R.layout.report_filter_sheet;
    }

    @Override
    public ReportFilterViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(ReportFilterViewModel.class);
    }

    public ReportClickListener reportClickListener() {
        return reportClickListener;
    }

    public ReportFilterBottomSheet setReportClickListener(ReportClickListener reportClickListener) {
        this.reportClickListener = reportClickListener;
        return this;
    }

    @Override
    public ReportFilterSheetBinding setupViewBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewBinding().setReportFilterClickListener(this);

        /*  */
        SimpleDateFormat monthFormatter = new SimpleDateFormat("MMM, yyyy", Locale.ENGLISH);


        Calendar instance = Calendar.getInstance();

        List<String> list = new ArrayList<>();
        int month = instance.get(Calendar.MONTH);
        instance.add(Calendar.MONTH, -month);

        for (int i = 0; i <= month; i++) {
            String format1 = monthFormatter.format(instance.getTimeInMillis());
            list.add(format1);

            Chip chip = new Chip(getContext());
            chip.setId(i);
            chip.setText(format1);
            viewBinding().chipGroup.addView(chip);
            instance.add(Calendar.MONTH, 1);
        }


        List<String[]> strings = new ArrayList<>();
        viewBinding().chipGroup.setOnCheckedStateChangeListener((group, checkedIds) -> {

            if (checkedIds.size() > 0) {

                for (int i = 0; i < checkedIds.size(); i++) {
                    Chip chip = viewBinding().chipGroup.findViewById(checkedIds.get(i));
                    String[] dateRange = getFormatedMonth(chip.getId(), Locale.ENGLISH);

                    if (chip.isChecked()) {
                        Log.d("--------------------", "");
                        Log.d("DateFrom : ->", dateRange[0]);
                        Log.d("DateTo : ->", dateRange[1]);
                        Log.d("--------------------", "");
                        reportClickListener.onDateClickListener(dateRange);
                    }
                }
            }

        });


    }

    public String[] getFormatedMonth(int month, Locale locale) {

        String[] dateRange = new String[2];

        DateFormat formatter = new SimpleDateFormat(getString(R.string.yyyy_mm_dd), locale);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.MONTH, month);
        dateRange[0] = formatter.format(calendar.getTime());
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        dateRange[1] = formatter.format(calendar.getTime());
        return dateRange;
    }

    @Override
    public void hideKeyboard(View view) {

    }

    @Override
    public void closeBottomSheet() {
        dismiss();

    }

    @Override
    public void openCalenderFromDate() {

        showDateRange();
    }

    private void showDateRange() {
        CalendarConstraints.Builder builder = new CalendarConstraints.Builder()
                .setValidator(DateValidatorPointBackward.now());

        MaterialDatePicker<Pair<Long, Long>> materialDatePicker = MaterialDatePicker.Builder.dateRangePicker()
                .setTitleText("Select dates")
                .setCalendarConstraints(builder.build())
                .setTheme(R.style.ThemeOverlay_App_DatePicker)

                .build();
        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Pair<Long, Long>>() {
            @Override
            public void onPositiveButtonClick(Pair<Long, Long> selection) {

                Long startDate = selection.first;
                Long endDate = selection.second;

                // user has selected date range
                // format the date and set the text of the input box to be the selected date
                // right now this format is hard-coded, this will change
                ;
                // Get the offset from our timezone and UTC.
                TimeZone timeZoneUTC = TimeZone.getDefault();
                // It will be negative, so that's the -1
                int offsetFromUTC = timeZoneUTC.getOffset(new Date().getTime()) * -1;

                // Create a date format, then a date object with our offset
                SimpleDateFormat simpleFormat = new SimpleDateFormat(getString(R.string.yyyy_mm_dd), Locale.ENGLISH);
                Date startFormatDate = new Date(startDate + offsetFromUTC);
                Date endFormatDate = new Date(endDate + offsetFromUTC);

                viewBinding().tvFromDate.setText(simpleFormat.format(startFormatDate));
                viewBinding().tvToDate.setText(simpleFormat.format(endFormatDate));
                String[] dateRange = new String[2];
                dateRange[0] = viewBinding().tvFromDate.getText().toString();
                dateRange[1] = viewBinding().tvToDate.getText().toString();
                reportClickListener.onDateClickListener(dateRange);
            }
        });
        materialDatePicker.show(getParentFragmentManager(), "tag");
    }

    @Override
    public void openCalenderToDate() {
        showDateRange();
    }

    @Override
    public void apply() {
        dismiss();
    }

    @Override
    public void clear() {
        reportClickListener.onDateClickListener(new String[2]);
    }

    @Override
    public boolean isConnectedToNetwork() {
        return false;
    }
}
