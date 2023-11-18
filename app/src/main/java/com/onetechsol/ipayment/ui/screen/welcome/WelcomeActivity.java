package com.onetechsol.ipayment.ui.screen.welcome;

import static android.Manifest.permission.ACCESS_BACKGROUND_LOCATION;
import static android.Manifest.permission.READ_MEDIA_IMAGES;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.ActivityWelcomeBinding;
import com.onetechsol.ipayment.databinding.CommonEventHandler;
import com.onetechsol.ipayment.pojo.CheckLoginRequest;
import com.onetechsol.ipayment.pojo.PageList;
import com.onetechsol.ipayment.pojo.StatusList;
import com.onetechsol.ipayment.pojo.SupportList;
import com.onetechsol.ipayment.session.UserLoginSession;
import com.onetechsol.ipayment.ui.basefiles.BaseActivity;
import com.onetechsol.ipayment.ui.screen.home.HomeActivity;
import com.onetechsol.ipayment.ui.screen.login_singup.LoginSignupActivity;
import com.onetechsol.ipayment.utils.ApiConstant;

import java.util.Calendar;

import io.reactivex.disposables.Disposable;


public class WelcomeActivity extends BaseActivity<WelcomeViewModel, ActivityWelcomeBinding> implements CommonEventHandler {


    @RequiresApi(api = Build.VERSION_CODES.Q)
    public static String[] storage_permissions_26 = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_BACKGROUND_LOCATION,
            Manifest.permission.CAMERA,
            Manifest.permission.READ_PHONE_STATE
    };
    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    public String[] storage_permissions_33 = {
            READ_MEDIA_IMAGES,
            Manifest.permission.POST_NOTIFICATIONS,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_BACKGROUND_LOCATION,
            Manifest.permission.CAMERA,
            Manifest.permission.READ_PHONE_STATE
    };
    public String[] storage_permissions_other = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.CAMERA,
            Manifest.permission.READ_PHONE_STATE
    };
    int i = 0;
    private Disposable rxDisposable;
    private io.reactivex.rxjava3.disposables.Disposable subscribe2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setWelcomeDetails();

        checkPermission();


    }

    private void startActionPostPermAccepted() {
        UserLoginSession userSession = viewModel().prefManager().getUserSession();

        if (userSession != null) {

            String bearerAuth = "Bearer " + userSession.token();
            String userName = userSession.userName();
            loginCheck(bearerAuth, userName);

        } else {
            loginCheck("", "");
        }
    }

    public void checkPermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, ACCESS_BACKGROUND_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED
        ) {

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU &&
                    ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) &&
                    ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.INTERNET) &&
                    ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_NETWORK_STATE) &&
                    ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_PHONE_STATE)
            ) {
                startActionPostPermAccepted();
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
                    ActivityCompat.shouldShowRequestPermissionRationale(this, READ_MEDIA_IMAGES) &&
                    ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_MEDIA_AUDIO) &&
                    ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_MEDIA_VIDEO) &&
                    ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.INTERNET) &&
                    ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_NETWORK_STATE) &&
                    ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_PHONE_STATE)
            ) {
                startActionPostPermAccepted();
            } else {


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    requestPermission(storage_permissions_33[i]);
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    requestPermission(storage_permissions_26[i]);
                } else {
                    requestPermission(storage_permissions_other[i]);
                }

            }


        } else {
            startActionPostPermAccepted();

        }


    }

    public void requestPermission(String reqPermission) {
        compositeDisposableRxJava().add(rxPermissions().request(reqPermission).subscribe(aBoolean -> {
            if (aBoolean) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

                    if (i == 6) {
                        startActionPostPermAccepted();
                    }
                    requestPermission(storage_permissions_33[++i]);
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

                    if (i == 6) {
                        startActionPostPermAccepted();
                    }

                    requestPermission(storage_permissions_26[++i]);
                } else {

                    if (i == 6) {
                        startActionPostPermAccepted();
                    }
                    requestPermission(storage_permissions_other[++i]);
                }
            } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, reqPermission)) {
                showAlertDialog("Permission Alert", "Some or more permissions are not granted. Please grant them for smoth funtion of the application", false)
                        .setPositiveButton("OK", (dialogInterface, j) -> {
                            dialogInterface.dismiss();
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                requestPermission(storage_permissions_33[i]);
                            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                                requestPermission(storage_permissions_26[i]);
                            } else {
                                requestPermission(storage_permissions_other[i]);
                            }
                        })
                        .setNegativeButton("Cancel", (dialogInterface, i) -> {
                            showSettingDialog();
                        })
                        .show();
            } else {
                showSettingDialog();
            }
        }, throwable -> {

        }));
    }

    private void showSettingDialog() {
        new MaterialAlertDialogBuilder(this, com.google.android.material.R.style.MaterialAlertDialog_Material3)
                .setTitle("Notification Permission")
                .setMessage("Notification permission is required, Please allow notification permission from setting")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + getPackageName())));

                    }
                }).setNegativeButton("Cancel", null).show();
    }


    private void setWelcomeDetails() {

        String timeEventCk = "";
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        if (timeOfDay < 12) {
            timeEventCk = "Good Morning";
        } else if (timeOfDay < 16) {
            timeEventCk = "Good Afternoon";
        } else if (timeOfDay < 24) {
            timeEventCk = "Good Evening";
        }

        String finalTimeEventCk = viewModel().prefManager().getWelcomeDetail();

        if (!timeEventCk.equals(finalTimeEventCk)) {
            if (!finalTimeEventCk.equals("")) {
                //Toast.makeText(this, "Welcome to our " + ApiConstant.BASIC_VERSION, //Toast.LENGTH_SHORT).show();
                //LightVoiceMsg.s(this.getApplicationContext(), finalTimeEventCk + " Welcome to our " + ApiConstant.BASIC_VERSION);
            }
            viewModel().prefManager().setWelcomeDetails(finalTimeEventCk);
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (rxDisposable != null) {
            rxDisposable.dispose();
        }

        compositeDisposable().clear();

    }

    public void loginCheck(String bearerAuth, String userName) {

        CheckLoginRequest checkLoginRequest = new CheckLoginRequest(ApiConstant.BASIC_VERSION);

        compositeDisposable().add(viewModel().checkLogin(checkLoginRequest)
                .subscribe(checkLoginResponse -> {
                    String status = checkLoginResponse.status();
                    String message = checkLoginResponse.message();

                    StatusList statusList = checkLoginResponse.statusList();
                    PageList page_list = checkLoginResponse.pageList();
                    SupportList support_list = checkLoginResponse.supportList();

                    viewModel().prefManager().setUserConfigPrefData(checkLoginResponse);

                    if (status.equals("1")) {


                           /* SharedPreferences.Editor editor = prefs_login.edit();
                            editor.putString("status_list", status_list);
                            editor.putString("page_list", page_list);
                            editor.putString("support_list", support_list);
                            editor.apply();*/
                        String kycCk = String.valueOf(checkLoginResponse.kycStatus);
                        if (kycCk.equals("1")) {
                            Intent gomai = new Intent(WelcomeActivity.this, HomeActivity.class);
                            overridePendingTransition(0, 0);
                            startActivity(gomai);

                        }
                        if (kycCk.equals("0")) {
                            //*SharedPreferences prefs_register=getApplicationContext().getSharedPreferences("Register Details", Context.MODE_PRIVATE);
                                /*SharedPreferences.Editor edit=prefs_register.edit();
                                edit.clear();
                                edit.commit();
                                Intent gomai = new Intent(Welcome.this, ActivityUploadDoc.class);
                                overridePendingTransition(0, 0);
                                startActivity(gomai);
                                finish();*/
                        }
                        if (kycCk.equals("2")) {
                            //String kyc_msg = checkLoginResponse.kycMessage();
                                /*blockInfo = new Dialog(Welcome.this, R.style.AppBaseTheme);
                                blockInfo.setContentView(R.layout.block_user_information);
                                TextView kyc_msg_txt = (TextView) blockInfo.findViewById(R.id.kyc_msg);
                                kyc_msg_txt.setText(kyc_msg);*/
                               /* Button exit = (Button) blockInfo.findViewById(R.id.exit);
                                exit.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        blockInfo.dismiss();
                                        SharedPreferences prefs_register = getSharedPreferences(
                                                "Register Details", Context.MODE_PRIVATE);
                                        System.exit(0);
                                    }
                                });
                                blockInfo.setCancelable(false);
                                blockInfo.show();*/
                        }
                        if (kycCk.equals("3")) {
                               /* Intent gomai = new Intent(Welcome.this, ActivityUploadDoc.class);
                                overridePendingTransition(0, 0);
                                startActivity(gomai);
                                finish();*/
                        }

                    } else if (status.equals("3")) {

                        Intent intent = new Intent(WelcomeActivity.this, LoginSignupActivity.class);
                        overridePendingTransition(0, 0);
                        startActivity(intent);
                        finish();

                            /*String status_list = response.getString("status_list");
                            String page_list = response.getString("page_list");
                            String support_list = response.getString("support_list");
                            SharedPreferences.Editor editor = prefs_login.edit();
                            editor.putString("status_list", status_list);
                            editor.putString("page_list", page_list);
                            editor.putString("support_list", support_list);
                            editor.apply();

                            SharedPreferences prefs_register=getApplicationContext().getSharedPreferences(
                                    "Register Details", Context.MODE_PRIVATE);
                            SharedPreferences.Editor edit=prefs_register.edit();
                            edit.clear();
                            edit.commit();
                            Intent gomainActivity=new Intent(Welcome.this,LoginSignupActivity.class);
                            overridePendingTransition(0,0);
                            startActivity(gomainActivity);
                            finish();*/
                    } else {
                               /*  SharedPreferences prefs_register=getApplicationContext().getSharedPreferences(
                                    "Register Details", Context.MODE_PRIVATE);
                            SharedPreferences.Editor edit=prefs_register.edit();
                            edit.clear();
                            edit.commit();*/

                        //Toast.makeText(getApplicationContext(), message, //Toast.LENGTH_LONG).show();
                    }


                }, Throwable::printStackTrace));


        /*String bearerAuth = "Bearer " + LOGIN_TOKEN;
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("Version", Config.basicVersion);
        } catch (JSONException e) {
            e.printStackTrace();
        }



        *//*timeEventSave = prefs_welcome.getString("time_event","");
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        if(timeOfDay >= 0 && timeOfDay < 12){
            timeEventCk = "Good Morning";
        }else if(timeOfDay >= 12 && timeOfDay < 16){
            timeEventCk = "Good Afternoon";
        }else if(timeOfDay >= 16 && timeOfDay < 21){
            timeEventCk = "Good Evening";
        }else if(timeOfDay >= 21 && timeOfDay < 24){
            timeEventCk = "";
        }*//*

        if(!timeEventSave.equals(timeEventCk)){
            if(!timeEventCk.equals("")){
                LightVoiceMsg.s(this.getApplicationContext(),timeEventCk + " Welcome to our " + Config.basicAppVoice);
            }

            userPrefDataStore().setPrefData()
            SharedPreferences.Editor editor = prefs_welcome.edit();
            editor.putString("time_event", timeEventCk);
            editor.apply();
        }


        AndroidNetworking.post(Config.LOGIN_CHECK)
                .addJSONObjectBody(jsonObject)
                .addHeaders("Authorization", bearerAuth)
                .addHeaders("Username", USER_NAME)
                .addHeaders("Version", Config.basicVersion)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("Response log ck", response.toString());
                            String status=response.getString("status");
                            String message=response.getString("message");
                            if(status.equals("1")) {
                                String status_list = response.getString("status_list");
                                String page_list = response.getString("page_list");
                                String support_list = response.getString("support_list");
                                SharedPreferences.Editor editor = prefs_login.edit();
                                editor.putString("status_list", status_list);
                                editor.putString("page_list", page_list);
                                editor.putString("support_list", support_list);
                                editor.apply();

                                kycCk = response.getString("kyc_status");
                                if (kycCk.equals("1")) {
                                    Intent gomai = new Intent(Welcome.this, MainActivity.class);
                                    overridePendingTransition(0, 0);
                                    startActivity(gomai);
                                    finish();
                                }
                                if (kycCk.equals("0")) {
                            *//*SharedPreferences prefs_register=getApplicationContext().getSharedPreferences("Register Details", Context.MODE_PRIVATE);
                            SharedPreferences.Editor edit=prefs_register.edit();
                            edit.clear();
                            edit.commit();*//*
                                    Intent gomai = new Intent(Welcome.this, ActivityUploadDoc.class);
                                    overridePendingTransition(0, 0);
                                    startActivity(gomai);
                                    finish();
                                }
                                if (kycCk.equals("2")) {
                                    String kyc_msg = response.getString("kyc_message");
                                    blockInfo = new Dialog(Welcome.this, R.style.AppBaseTheme);
                                    blockInfo.setContentView(R.layout.block_user_information);
                                    TextView kyc_msg_txt = (TextView) blockInfo.findViewById(R.id.kyc_msg);
                                    kyc_msg_txt.setText(kyc_msg);
                                    Button exit = (Button) blockInfo.findViewById(R.id.exit);
                                    exit.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            blockInfo.dismiss();
                                            SharedPreferences prefs_register = getSharedPreferences(
                                                    "Register Details", Context.MODE_PRIVATE);
                                            System.exit(0);
                                        }
                                    });
                                    blockInfo.setCancelable(false);
                                    blockInfo.show();
                                }
                                if (kycCk.equals("3")) {
                                    Intent gomai = new Intent(Welcome.this, ActivityUploadDoc.class);
                                    overridePendingTransition(0, 0);
                                    startActivity(gomai);
                                    finish();
                                }
                            }else if(status.equals("3")){
                                String status_list = response.getString("status_list");
                                String page_list = response.getString("page_list");
                                String support_list = response.getString("support_list");
                                SharedPreferences.Editor editor = prefs_login.edit();
                                editor.putString("status_list", status_list);
                                editor.putString("page_list", page_list);
                                editor.putString("support_list", support_list);
                                editor.apply();

                                SharedPreferences prefs_register=getApplicationContext().getSharedPreferences(
                                        "Register Details", Context.MODE_PRIVATE);
                                SharedPreferences.Editor edit=prefs_register.edit();
                                edit.clear();
                                edit.commit();
                                Intent gomainActivity=new Intent(Welcome.this,Login.class);
                                overridePendingTransition(0,0);
                                startActivity(gomainActivity);
                                finish();
                            }
                            else {


                                SharedPreferences prefs_register=getApplicationContext().getSharedPreferences(
                                        "Register Details", Context.MODE_PRIVATE);
                                SharedPreferences.Editor edit=prefs_register.edit();
                                edit.clear();
                                edit.commit();

                                //Toast.makeText(getApplicationContext(),  message, //Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            //Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), //Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });*/
    }


    @Override
    public ActivityWelcomeBinding setupViewBinding(LayoutInflater inflater) {
        return ActivityWelcomeBinding.inflate(inflater);
    }

    @Override
    public WelcomeViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(WelcomeViewModel.class);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_welcome;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void goBack() {
        onBackPressed();
    }

    @Override
    public void onRefresh() {

    }


}