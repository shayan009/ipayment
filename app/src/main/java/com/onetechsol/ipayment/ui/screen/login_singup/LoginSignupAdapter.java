package com.onetechsol.ipayment.ui.screen.login_singup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.onetechsol.ipayment.ui.screen.login_singup.login.LoginFragment;
import com.onetechsol.ipayment.ui.screen.login_singup.signup.SignupFragment;

public class LoginSignupAdapter extends FragmentStateAdapter {

    public LoginSignupAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new LoginFragment();
            case 1:
                return new SignupFragment();
        }
        return null;
    }


    @Override
    public int getItemCount() {
        return 2;
    }
}
