package com.onetechsol.ipayment.utils;

import static android.content.Context.WINDOW_SERVICE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Path;
import android.graphics.Rect;
import android.net.Uri;
import android.telephony.SmsManager;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.FragmentManager;

import com.miguelbcr.ui.rx_paparazzo2.entities.FileData;
import com.onetechsol.ipayment.app.MainApp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class Utilities {

    public static final String TAG = Utilities.class.getSimpleName();

    @SuppressLint("SimpleDateFormat")
    public static SimpleDateFormat getSdfsTime() {
        return new SimpleDateFormat("hh a");
    }

    public static void adjustKeyboard(NestedScrollView nsvLoginRoot, RelativeLayout relativeLayout) {
        nsvLoginRoot.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            Rect r = new Rect();
            relativeLayout.getWindowVisibleDisplayFrame(r);
            int screenHeight = relativeLayout.getRootView().getHeight();

            // r.bottom is the position above soft keypad or device button.
            // if keypad is shown, the r.bottom is smaller than that before.
            int keypadHeight = screenHeight - r.bottom;

            //Log.d("TAG", "keypadHeight = " + keypadHeight);

            if (keypadHeight > screenHeight * 0.15) { // 0.15 ratio is perhaps enough to determine keypad height.
                // keyboard is opened
                FrameLayout.LayoutParams relativeParams = (FrameLayout.LayoutParams) relativeLayout.getLayoutParams();
                relativeParams.setMargins(0, 0, 0, keypadHeight + 150);  // left, top, right, bottom
                relativeLayout.setLayoutParams(relativeParams);
                //showNaigation()
            } else {
                // keyboard is closed
                FrameLayout.LayoutParams relativeParams = (FrameLayout.LayoutParams) relativeLayout.getLayoutParams();
                relativeParams.setMargins(0, 0, 0, 0);  // left, top, right, bottom
                relativeLayout.setLayoutParams(relativeParams);
                //hideSystemUi()
            }
        });
    }

    public static void hideKeyboard(AppCompatActivity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static String getDrawableUrl(int resId) {

        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + MainApp.getContext().getResources().getResourcePackageName(resId) + '/' + MainApp.getContext().getResources().getResourceTypeName(resId)
                + '/' + MainApp.getContext().getResources().getResourceEntryName(resId)).toString();
    }

    public static String getBase64Image(FileData fileData) {
        try {
            return Base64.encodeToString(loadFile(fileData.getFile()), Base64.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void sendSms(String smsBody, Context context) {
        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.putExtra("sms_body", smsBody);
        sendIntent.setType("vnd.android-dir/mms-sms");
        context.startActivity(sendIntent);
    }

    public static void sendDirectSms(final String receiverNumber, final String smsBody) {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(receiverNumber, null, smsBody, null, null);
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static void getKeyHash(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(),
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                //   //Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }
 /*   public static Bitmap getBitmap(String url) {
        Bitmap bitmap = null;
        MediaMetadataRetriever mediaMetadataRetriever = null;
        try
        {
            mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(url, new HashMap<String, String>());
            //   mediaMetadataRetriever.setDataSource(videoPath);
            bitmap = mediaMetadataRetriever.getFrameAtTime();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (mediaMetadataRetriever != null) {
                mediaMetadataRetriever.release();
            }
        }
        return bitmap;
    }*/

    public static void shareData(String title, String s) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, s);
        //  MainApp.getContext().startActivity(Intent.createChooser(shareIntent, title).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    // Custom method to get screen width in dp/dip using Context object
    public static int getScreenWidthInDPs(Context context) {

        DisplayMetrics dm = new DisplayMetrics();

        WindowManager windowManager = (WindowManager) context.getSystemService(WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        int widthInDP = Math.round(dm.widthPixels / dm.density);
        return widthInDP;
    }

    // Custom method to get screen height in dp/dip using Context object
    public static int getScreenHeightInDPs(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        /*
            In this example code we converted the float value
            to nearest whole integer number. But, you can get the actual height in dp
            by removing the Math.round method. Then, it will return a float value, you should
            also make the necessary changes.
        */

        /*
            public int heightPixels
                The absolute height of the display in pixels.

            public float density
             The logical density of the display.
        */
        int heightInDP = Math.round(dm.heightPixels / dm.density);
        return heightInDP;
    }

    public static void showToast(String text) {
        //   MainApp.getToast().message(text).show();
    }

    public static int getComplexUnitDp(Context context, int value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.getResources().getDisplayMetrics());
    }

    public static void getWidthHeight() {

    }

    private static byte[] loadFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        long length = file.length();
        byte[] bytes = new byte[(int) length];

        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }

        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }

        is.close();
        return bytes;
    }

    public static String getFormattedDateTime(Date startDate, String format) {
        return new SimpleDateFormat(format).format(startDate);

    }

    public static String dateFormater(String dateFromJSON, String expectedFormat, String oldFormat) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(oldFormat);
        Date date = null;
        String convertedDate = null;
        try {
            date = dateFormat.parse(dateFromJSON);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(expectedFormat);
            convertedDate = simpleDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return convertedDate;
    }

    public static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

   /* public static RecyclerItemDivider getItemDecorator(Context context, int dimen) {
        TypedArray a = context.obtainStyledAttributes(new int[]{android.R.attr.listDivider});
        Drawable divider = a.getDrawable(0);
        int inset = context.getResources().getDimensionPixelSize(dimen);
        InsetDrawable insetDivider = new InsetDrawable(divider, inset, 0, inset, 0);
        a.recycle();
        RecyclerItemDivider itemDecoration = new RecyclerItemDivider(context, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(insetDivider);
        return itemDecoration;
    }*/

    public static String getTodayDate(String format) {
        return new SimpleDateFormat(format, Locale.ENGLISH).format(new Date());
    }

    public static List<String> get7DaysRangeBeforeDate(String format) {


        List<String> dateRange = new ArrayList<>();
        Date toDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.ENGLISH);
        String formattedToDate = dateFormat.format(toDate);

        Date fromDate = new Date(toDate.getTime() - 604800000L);
        String formattedFromDate = dateFormat.format(fromDate);
        dateRange.add(formattedFromDate);
        dateRange.add(formattedToDate);
        return dateRange;
    }

    public static void main(String[] args) {
        System.out.println(getTimeDifference("14:40:00", "10:40:00"));
    }

    /**
     * Finds the angle between two points in the plane (x1,y1) and (x2, y2)
     * The angle is measured with 0/360 being the X-axis to the right, angles
     * increase counter clockwise.
     *
     * @param x1 the x position of the first point
     * @param y1 the y position of the first point
     * @param x2 the x position of the second point
     * @param y2 the y position of the second point
     * @return the angle between two points
     */
    public static double getAngle(float x1, float y1, float x2, float y2) {

        double rad = Math.atan2(y1 - y2, x2 - x1) + Math.PI;
        return (rad * 180 / Math.PI + 180) % 360;
    }

    public static String getTimeDifference(String time1, String time2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        Date startDate = null;
        try {
            startDate = simpleDateFormat.parse(time1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date endDate = null;
        try {
            endDate = simpleDateFormat.parse(time2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long difference = endDate.getTime() - startDate.getTime();
        System.out.println(TAG + "getTimeDifference: " + difference);
        if (difference < 0) {
            Date dateMax = null;
            try {
                dateMax = simpleDateFormat.parse("24:00:00");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date dateMin = null;
            try {
                dateMin = simpleDateFormat.parse("00:00:00");
            } catch (ParseException e) {
            }
            difference = (dateMax.getTime() - startDate.getTime()) + (endDate.getTime() - dateMin.getTime());
        }
        int days = (int) (difference / (1000 * 60 * 60 * 24));
        int hours = (int) ((difference - (1000 * 60 * 60 * 24 * days)) / (1000 * 60 * 60));
        int min = (int) (difference - (1000 * 60 * 60 * 24 * days) - (1000 * 60 * 60 * hours)) / (1000 * 60);
        System.out.println("log_tag" + "Hours: " + hours + ", Mins: " + min);

        return ((hours * 60) + min) + " Mins"; // updated value every1 second
    }

    static Type getParameterUpperBound(int index, ParameterizedType type) {
        Type[] types = type.getActualTypeArguments();
        if (index < 0 || index >= types.length) {
            throw new IllegalArgumentException(
                    "Index " + index + " not in range [0," + types.length + ") for " + type);
        }
        Type paramType = types[index];
        if (paramType instanceof WildcardType) {
            return ((WildcardType) paramType).getUpperBounds()[0];
        }
        return paramType;
    }

    private void setListViewHeight(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    /**
     * Override this method. The Direction enum will tell you how the user swiped.
     */
    public boolean onSwipe(Path.Direction direction) {
        return false;
    }
//    public static String getBase64Image(FileData fileData){
//        try {
//            return Base64.encodeToString(loadFile(fileData.getFile()), Base64.DEFAULT);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public void removeAllBackStackFragments(Context context) {
        ((AppCompatActivity) context).getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    /*public static void authfailure(Context context){
        Intent intent = new Intent(context, ConnectDeviceActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|
                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
       // showToast("Authentication Failed");
      //  MainApp.getPrefManager().clearData();
        context.startActivity(intent);
        ((AppCompatActivity)context).finish();
    }*/
}