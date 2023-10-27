package com.onetechsol.ipayment.di.binders;

import android.os.Build;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;

public class TextViewBindingAdapter {
    @SuppressWarnings("unused")
    public static final int INTEGER = 0x01;
    public static final int SIGNED = 0x03;
    public static final int DECIMAL = 0x05;
    private static final String TAG = TextViewBindingAdapter.class.getName();

    @BindingAdapter("android:text")
    public static void setText(TextView view, CharSequence text) {
        final CharSequence oldText = view.getText();
        if (text == oldText || (text == null && oldText.length() == 0)) {
            return;
        }
        if (text instanceof Spanned) {
            if (text.equals(oldText)) {
                return; // No change in the spans, so don't set anything.
            }
        } else if (!haveContentsChanged(text, oldText)) {
            return; // No content changes, so don't set anything.
        }
        view.setText(text);
    }

    @InverseBindingAdapter(attribute = "android:text", event = "android:textAttrChanged")
    public static String getTextString(TextView view) {
        return view.getText().toString();
    }

    @BindingAdapter("android:visibility")
    public static void setVisibility(TextView view, int visibility) {
        view.setVisibility(visibility);
    }

    @InverseBindingAdapter(attribute = "android:visibility", event = "android:textAttrChanged")
    public static int getVisibility(TextView view) {
        return view.getVisibility();
    }


    @BindingAdapter({"android:imeActionLabel"})
    public static void setImeActionLabel(TextView view, CharSequence value) {
        view.setImeActionLabel(value, view.getImeActionId());
    }

    @BindingAdapter({"android:imeActionId"})
    public static void setImeActionLabel(TextView view, int value) {
        view.setImeActionLabel(view.getImeActionLabel(), value);
    }

    @BindingAdapter({"android:inputMethod"})
    @SuppressWarnings("ClassNewInstance")
    public static void setInputMethod(TextView view, CharSequence inputMethod) {
        try {
            Class<?> c = Class.forName(inputMethod.toString());
            view.setKeyListener((KeyListener) c.newInstance());
        } catch (ClassNotFoundException e) {
            Log.e(TAG, "Could not create input method: " + inputMethod, e);
        } catch (InstantiationException e) {
            Log.e(TAG, "Could not create input method: " + inputMethod, e);
        } catch (IllegalAccessException e) {
            Log.e(TAG, "Could not create input method: " + inputMethod, e);
        }
    }


    @BindingAdapter({"android:maxLength"})
    public static void setMaxLength(TextView view, int value) {
        InputFilter[] filters = view.getFilters();
        if (filters == null) {
            filters = new InputFilter[]{
                    new InputFilter.LengthFilter(value)
            };
        } else {
            boolean foundMaxLength = false;
            for (int i = 0; i < filters.length; i++) {
                InputFilter filter = filters[i];
                if (filter instanceof InputFilter.LengthFilter) {
                    foundMaxLength = true;
                    boolean replace = true;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        replace = ((InputFilter.LengthFilter) filter).getMax() != value;
                    }
                    if (replace) {
                        filters[i] = new InputFilter.LengthFilter(value);
                    }
                    break;
                }
            }
            if (!foundMaxLength) {
                // can't use Arrays.copyOf -- it shows up in API 9
                InputFilter[] oldFilters = filters;
                filters = new InputFilter[oldFilters.length + 1];
                System.arraycopy(oldFilters, 0, filters, 0, oldFilters.length);
                filters[filters.length - 1] = new InputFilter.LengthFilter(value);
            }
        }
        view.setFilters(filters);
    }

    @BindingAdapter({"android:password"})
    public static void setPassword(TextView view, boolean password) {
        if (password) {
            view.setTransformationMethod(PasswordTransformationMethod.getInstance());
        } else if (view.getTransformationMethod() instanceof PasswordTransformationMethod) {
            view.setTransformationMethod(null);
        }
    }


    @BindingAdapter({"android:textSize"})
    public static void setTextSize(TextView view, float size) {
        view.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
    }

    private static boolean haveContentsChanged(CharSequence str1, CharSequence str2) {
        if ((str1 == null) != (str2 == null)) {
            return true;
        } else if (str1 == null) {
            return false;
        }
        final int length = str1.length();
        if (length != str2.length()) {
            return true;
        }
        for (int i = 0; i < length; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                return true;
            }
        }
        return false;
    }

    @BindingAdapter(value = {"android:beforeTextChanged", "android:onTextChanged",
            "android:afterTextChanged", "android:textAttrChanged"}, requireAll = false)
    public static void setTextWatcher(TextView view, final BeforeTextChanged before,
                                      final OnTextChanged on, final AfterTextChanged after,
                                      final InverseBindingListener textAttrChanged) {
        final TextWatcher newValue;
        if (before == null && after == null && on == null && textAttrChanged == null) {
            newValue = null;
        } else {
            newValue = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    if (before != null) {
                        before.beforeTextChanged(s, start, count, after);
                    }
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (on != null) {
                        on.onTextChanged(s, start, before, count);
                    }
                    if (textAttrChanged != null) {
                        textAttrChanged.onChange();
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (after != null) {
                        after.afterTextChanged(s);
                    }
                }
            };
        }
    }

    public interface AfterTextChanged {
        void afterTextChanged(Editable s);
    }

    public interface BeforeTextChanged {
        void beforeTextChanged(CharSequence s, int start, int count, int after);
    }

    public interface OnTextChanged {
        void onTextChanged(CharSequence s, int start, int before, int count);
    }
}
