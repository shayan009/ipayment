package com.onetechsol.ipayment.ui.screen.signup;

import static com.onetechsol.ipayment.utils.Utilities.adjustKeyboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.sidesheet.SideSheetDialog;
import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.ActivityRegisterBinding;
import com.onetechsol.ipayment.databinding.RegisterClickListener;
import com.onetechsol.ipayment.databinding.SideSheetDataOnClickListener;
import com.onetechsol.ipayment.pojo.SideSheetItem;
import com.onetechsol.ipayment.pojo.StateList;
import com.onetechsol.ipayment.ui.basefiles.BaseActivity;
import com.onetechsol.ipayment.ui.screen.login_singup.LoginSignupActivity;
import com.onetechsol.ipayment.widgets.CustomSideSheetSpinner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class RegisterActivity extends BaseActivity<RegisterViewModel, ActivityRegisterBinding> implements RegisterClickListener, SideSheetDataOnClickListener {

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    private SideSheetDialog sideSheetDialog;
    private String label = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewBinding().setRegisterClickListener(this);

        adjustKeyboard(viewBinding().nsvRegisterRoot, viewBinding().rlRegister);

        compositeDisposable().add(viewModel().appSetup().subscribe(appSetupResponse -> {

            List<SideSheetItem> sideSheetItems = new ArrayList<>();

            for (int i = 1; i < appSetupResponse.data().stateList().size(); i++) {
                StateList stateList = appSetupResponse.data().stateList().get(i);
                sideSheetItems.add(new SideSheetItem(stateList.label(), true, (i - 1), false));
            }

            intSideSheet(sideSheetItems);

        }, throwable -> {
            //Toast.makeText(this, throwable.getMessage(), //Toast.LENGTH_SHORT).show();

        }));
    }

    private void intSideSheet(List<SideSheetItem> sideSheetItems) {

        sideSheetDialog = new CustomSideSheetSpinner(this, sideSheetItems);
        ((CustomSideSheetSpinner) sideSheetDialog).setSideSheetDataOnClickListener(this);
    }


    @Override
    public ActivityRegisterBinding setupViewBinding(LayoutInflater inflater) {
        return ActivityRegisterBinding.inflate(inflater);
    }

    @Override
    public RegisterViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(RegisterViewModel.class);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_register;
    }

    @Override
    public void goBack() {
        onBackPressed();
    }

    @Override
    public void openSpinner() {
        if (sideSheetDialog != null)
            sideSheetDialog.show();
    }

    @Override
    public void register(String referral, String fullName, String email, String address, String pinCode, String district, String state) {

        onShowLoading();

        compositeDisposable().add(viewModel().register(referral, fullName, email, address, pinCode, district, state)
                .subscribe(registerUserResponse -> {

                    onHideLoading();
                    new MaterialAlertDialogBuilder(getApplicationContext())
                            .setTitle("Signup")
                            .setMessage(registerUserResponse.message())
                            .setPositiveButton("Ok", (dialogInterface, i) -> {

                                ////Toast.makeText(getContext(), checkSignupResponse.message, //Toast.LENGTH_SHORT).show();
                                if (registerUserResponse.status.equals("1")) {

                                    startActivity(new Intent(this, LoginSignupActivity.class));
                                    overridePendingTransition(0, 0);
                                    finish();
                                    dialogInterface.dismiss();

                                }

                            }).show();

                }, throwable -> {
                    onHideLoading();
                }));

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable().clear();
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void selectSideSheetItem(SideSheetItem sideSheetItem) {
        label = sideSheetItem.name();
        sideSheetDialog.dismiss();
        viewBinding().tvStateSignup.setText(sideSheetItem.name());
        ////Toast.makeText(this, sideSheetItem.name(), ////Toast.LENGTH_SHORT).show();

    }
}