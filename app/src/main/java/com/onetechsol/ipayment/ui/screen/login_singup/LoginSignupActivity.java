package com.onetechsol.ipayment.ui.screen.login_singup;

import static com.onetechsol.ipayment.utils.Utilities.adjustKeyboard;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.ViewModelProvider;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.ActivityLoginBinding;
import com.onetechsol.ipayment.ui.basefiles.BaseActivity;
import com.onetechsol.ipayment.ui.screen.login_singup.login.LoginFragment;
import com.onetechsol.ipayment.ui.screen.login_singup.signup.SignupFragment;

public class LoginSignupActivity extends BaseActivity<LoginSignupViewModel, ActivityLoginBinding> implements LoginSignUpClickEvents {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NestedScrollView nsvLoginRoot = viewBinding().msvLoginSignupRoot;
        RelativeLayout container = viewBinding().rlLoginSignup;

        adjustKeyboard(nsvLoginRoot, container);

        viewBinding().executePendingBindings();
        viewBinding().setLoginSignUpClickEvents(this);
        viewBinding().btLogin.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.palete)));
        viewBinding().btSignUp.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.float_transparent)));
        onAttachFragment(R.id.flLogInSignup, new LoginFragment(), LoginFragment.class.getName());
    }


    @Override
    public ActivityLoginBinding setupViewBinding(LayoutInflater inflater) {
        return ActivityLoginBinding.inflate(inflater);
    }

    @Override
    public LoginSignupViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(LoginSignupViewModel.class);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void openLogin(View view) {
        viewBinding().btLogin.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.palete)));
        viewBinding().btSignUp.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.float_transparent)));
        onAttachFragment(R.id.flLogInSignup, new LoginFragment(), LoginFragment.class.getName());
    }

    @Override
    public void openSignUp(View view) {
        viewBinding().btLogin.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.float_transparent)));
        viewBinding().btSignUp.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.palete)));
        onAttachFragment(R.id.flLogInSignup, new SignupFragment(), SignupFragment.class.getName());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        onDetachedFromWindow();
       /* onDetachFragment(SignupFragment.class.getName());
        onDetachFragment(LoginFragment.class.getName());*/
    }
}