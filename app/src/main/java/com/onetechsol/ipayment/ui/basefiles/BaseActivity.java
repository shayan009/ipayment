package com.onetechsol.ipayment.ui.basefiles;

import static android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
import static android.view.WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewbinding.ViewBinding;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.onetechsol.ipayment.data.locale.pref.PrefManager;
import com.onetechsol.ipayment.widgets.ProgressLoaderDialog;
import com.onetechsol.ipayment.widgets.ToastAlertDialog;
import com.tbruyelle.rxpermissions3.RxPermissions;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import io.reactivex.disposables.CompositeDisposable;


public abstract class BaseActivity<VM extends ViewModel, VB extends ViewBinding> extends AppCompatActivity implements IBaseView, SwipeRefreshLayout.OnRefreshListener, HasSupportFragmentInjector {

    @Inject
    public DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;
    @Inject
    public PrefManager prefManager;
    public ProgressLoaderDialog progressLoaderDialog = null;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    io.reactivex.rxjava3.disposables.CompositeDisposable compositeDisposableRxJava = new io.reactivex.rxjava3.disposables.CompositeDisposable();
    private Context context;
    private Snackbar snackbar;
    private RxPermissions rxPermissions;
    @NotNull
    private VB viewBinding;
    @NotNull
    private VM viewModel;

    public io.reactivex.rxjava3.disposables.CompositeDisposable compositeDisposableRxJava() {
        return compositeDisposableRxJava;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        viewBinding = setupViewBinding(getLayoutInflater());
        setContentView(viewBinding.getRoot());
        context = this;
        hideSystemUi();
        viewModel = setUpViewModel(new ViewModelProvider(this));
        rxPermissions = new RxPermissions(this);
        setProgressDialog(viewBinding.getRoot().getContext());


    }


    @Override
    public void onAttachFragment(int id, Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(id, fragment, tag);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    public CompositeDisposable compositeDisposable() {
        return compositeDisposable;
    }

    @Override
    public void onAttachFragment(int id, @NonNull Fragment fragment, @NonNull String tag, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(id, fragment, tag);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

        if (addToBackStack)
            fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();
    }

    @Override
    public void onDetachFragment(String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(fragment);
    }

    @Override
    public void removeAllBackStackFragments() {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    @NotNull
    @Override
    public VB viewBinding() {
        return viewBinding;
    }


    @Override
    public void setProgressDialog(Context context) {
        progressLoaderDialog = new ProgressLoaderDialog(context);
        progressLoaderDialog.setCancelable(false);
        progressLoaderDialog.setCanceledOnTouchOutside(false);
    }

    @NotNull
    @Override
    public VM viewModel() {
        return viewModel;
    }

    public RxPermissions rxPermissions() {
        return rxPermissions;
    }

    public abstract VB setupViewBinding(LayoutInflater inflater);

    public abstract VM setUpViewModel(ViewModelProvider viewModelProvider);


    @Override
    public MaterialAlertDialogBuilder showAlertDialog(String title, String desc, boolean isCancel) {
        return new MaterialAlertDialogBuilder(this, com.google.android.material.R.style.MaterialAlertDialog_Material3_Animation)
                .setTitle(title)
                .setMessage(desc)
                .setCancelable(isCancel);
    }

    @Override
    public ToastAlertDialog showToastAlertDialog(String title, String desc, boolean isCancel) {

        ToastAlertDialog toastAlertDialog = new ToastAlertDialog();
        toastAlertDialog.setTitle(title);
        toastAlertDialog.setShowCancel(isCancel);
        toastAlertDialog.setSubTitle(desc);
        return toastAlertDialog;
    }

    public void hideSystemUi() {

        int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS |
                SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        this.getWindow().getDecorView().setSystemUiVisibility(flags);
        // getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

    }


    @Override
    public void onShowLoading() {
        if (progressLoaderDialog != null)
            progressLoaderDialog.show();
    }

    @Override
    public void onHideLoading() {
        if (progressLoaderDialog != null)
            progressLoaderDialog.dismiss();
    }

    @Override
    public void onFailed(@NonNull String message) {
        //Toast.makeText(context, message, //Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(@NonNull String error) {
        //Toast.makeText(context, error, //Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(@NonNull String message) {
        //Toast.makeText(context, message, //Toast.LENGTH_SHORT).show();
    }

    @Override
    public void initializeSnackBar(View view) {
        snackbar = Snackbar.make(view, "Please try again", Snackbar.LENGTH_LONG);
    }

    @Override
    public void showSnackBarMessage(String message) {
        try {
            snackbar.setText(message);
            snackbar.setAction("Dismiss", view -> {

            });
            snackbar.show();
        } catch (NullPointerException e) {
//            //Toast.makeText(
//                    context,
//                    "Please initialize the snackbar before showing. call initializeSnackBar(View view)",
//                    //Toast.LENGTH_SHORT
//            ).show();
        }
    }

    @Override
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public Bundle getBundleData() {
        return getIntent() != null ? getIntent().getExtras() : null;
    }

    @Override
    public boolean isConnectedToNetwork() {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = null;
        if (cm != null) {
            activeNetwork = cm.getActiveNetworkInfo();
        }
        return (activeNetwork != null && activeNetwork.isConnected());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    public TextView changeToolbarFont(Toolbar toolbar) {
        for (int i = 0; i < toolbar.getChildCount(); i++) {
            View view = toolbar.getChildAt(i);
            if (view instanceof TextView) {
                TextView tv = (TextView) view;
                if (tv.getText().equals(toolbar.getTitle())) {
                    return tv;
                }
            }
        }
        return null;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    public abstract int getLayoutRes();


}
