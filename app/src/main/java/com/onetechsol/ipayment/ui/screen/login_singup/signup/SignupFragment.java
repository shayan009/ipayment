package com.onetechsol.ipayment.ui.screen.login_singup.signup;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.FragmentSignupBinding;
import com.onetechsol.ipayment.di.binders.ImageViewBinder;
import com.onetechsol.ipayment.di.binders.TextViewBindingAdapter;
import com.onetechsol.ipayment.ui.basefiles.BaseFragment;
import com.onetechsol.ipayment.ui.screen.signup.RegisterActivity;

import org.apache.commons.lang3.StringUtils;

import io.reactivex.disposables.CompositeDisposable;

public class SignupFragment extends BaseFragment<SignupViewModel, FragmentSignupBinding> implements SignupClickEvent {


    private CompositeDisposable mDisposable = new CompositeDisposable();

    @Override
    public void clearMobileField(View view) {
        TextViewBindingAdapter.setText(viewBinding().etSignupMobile, "");
        ImageViewBinder.setVisibility((ImageView) view, View.INVISIBLE);

    }


    @Override
    public int getLayoutRes() {
        return R.layout.fragment_signup;
    }

    @Override
    public SignupViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(SignupViewModel.class);
    }

    @Override
    public FragmentSignupBinding setupViewBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewBinding().setSignupClickEvent(this);

        viewBinding().etSignupMobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    ImageViewBinder.setVisibility(viewBinding().ivClearSignup, View.VISIBLE);
                } else {
                    ImageViewBinder.setVisibility(viewBinding().ivClearSignup, View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void signUp(View view) {

        onShowLoading();
        String mobile = viewBinding().etSignupMobile.getText().toString();

        if (StringUtils.isEmpty(mobile)) {
            //Toast.makeText(getActivity(), "Mobile number cannot be empty.", //Toast.LENGTH_SHORT).show();
            return;
        } else if (StringUtils.length(mobile) != 10) {
            //Toast.makeText(getActivity(), "Mobile number must be of 10 digits.", //Toast.LENGTH_SHORT).show();
            return;
        }

        mDisposable.add(viewModel().checkSignUp(viewBinding().etSignupMobile.getText().toString()).subscribe(checkSignupResponse -> {

            new MaterialAlertDialogBuilder(getContext())
                    .setTitle("Signup")
                    .setMessage(checkSignupResponse.message)
                    .setPositiveButton("Ok", (dialogInterface, i) -> {

                        ////Toast.makeText(getContext(), checkSignupResponse.message, //Toast.LENGTH_SHORT).show();
                        if (checkSignupResponse.status.equals("1")) {

                            onHideLoading();
                            startActivity(new Intent(getActivity(), RegisterActivity.class));
                            requireActivity().overridePendingTransition(R.animator.fade_in, R.animator.fade_out);
                        }

                    }).show();

        }, throwable -> {
            //Toast.makeText(getActivity(), throwable.getMessage(), //Toast.LENGTH_SHORT).show();
        }));

    }
}