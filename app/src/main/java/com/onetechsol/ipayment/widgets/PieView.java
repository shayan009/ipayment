package com.onetechsol.ipayment.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import com.onetechsol.ipayment.utils.Utilities;

import java.util.ArrayList;

public class PieView extends View {

    public static final int NO_SELECTED_INDEX = -999;
    private final int[] DEFAULT_COLOR_LIST = {Color.parseColor("#33B5E5"),
            Color.parseColor("#AA66CC"),
            Color.parseColor("#99CC00"),
            Color.parseColor("#FFBB33"),
            Color.parseColor("#FF4444")};
    private float mScaleFactor = 1.f;
    private int mActivePointerId;
    private float mLastTouchX;
    private float mLastTouchY;
    private float mPosX;
    private float mPosY;
    private Paint cirPaint;
    private Paint whiteLinePaint;
    private Point pieCenterPoint;
    private Paint textPaint;
    private RectF cirRect;
    private RectF cirSelectedRect;
    private RectF cirPieRect1;
    private RectF cirPieRect2;
    private RectF cirPieRect3;
    private RectF cirPieRect4;
    private int mViewWidth;
    private int mViewHeight;
    private int margin;
    private int pieRadius;
    private OnPieClickListener onPieClickListener;
    private ArrayList<PieHelper> pieHelperList;
    private int selectedIndex = NO_SELECTED_INDEX;
    private boolean showPercentLabel = true;
    private Runnable animator = new Runnable() {
        @Override
        public void run() {
            boolean needNewFrame = false;
            for (PieHelper pie : pieHelperList) {
                pie.update();
                if (!pie.isAtRest()) {
                    needNewFrame = true;
                }
            }
            if (needNewFrame) {
                postDelayed(this, 10);
            }
            invalidate();
        }
    };
    private ScaleGestureDetector mScaleDetector;

    public PieView(Context context) {
        this(context, null);
    }

    public PieView(Context context, AttributeSet attrs) {
        super(context, attrs);

        pieHelperList = new ArrayList<PieHelper>();
        cirPaint = new Paint();
        cirPaint.setAntiAlias(true);
        cirPaint.setColor(Color.GRAY);
        whiteLinePaint = new Paint(cirPaint);
        whiteLinePaint.setColor(Color.WHITE);
        whiteLinePaint.setStrokeWidth(2f);
        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(Utilities.sp2px(getContext(), 13));
        textPaint.setStrokeWidth(5);
        textPaint.setTextAlign(Paint.Align.CENTER);
        pieCenterPoint = new Point();
        cirRect = new RectF();
        cirSelectedRect = new RectF();
        cirPieRect1 = new RectF();
        cirPieRect2 = new RectF();
        cirPieRect3 = new RectF();
        cirPieRect4 = new RectF();
        cirPieRect4 = new RectF();
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
    }

    public void showPercentLabel(boolean show) {
        showPercentLabel = show;
        postInvalidate();
    }

    public void setOnPieClickListener(OnPieClickListener listener) {
        onPieClickListener = listener;
    }

    public void setDate(ArrayList<PieHelper> helperList) {
        initPies(helperList);
        pieHelperList.clear();
        removeSelectedPie();
        selectedPie(0);
        if (helperList != null && !helperList.isEmpty()) {
            for (PieHelper pieHelper : helperList) {
                pieHelperList.add(new PieHelper(pieHelper.getStartDegree(), pieHelper.getStartDegree(), pieHelper));
            }
        } else {
            pieHelperList.clear();
        }

        removeCallbacks(animator);
        post(animator);

// pieHelperList = helperList;
// postInvalidate();
    }

    /**
     * Set startDegree and endDegree for each PieHelper
     *
     * @param helperList
     */
    private void initPies(ArrayList<PieHelper> helperList) {
        float totalAngel = 340;
        for (PieHelper pie : helperList) {
            pie.setDegree(totalAngel, totalAngel + pie.getSweep());
            totalAngel += pie.getSweep();
        }
    }

    public void selectedPie(int index) {
        selectedIndex = index;
        if (onPieClickListener != null) onPieClickListener.onPieClick(index);
        postInvalidate();
    }

    public void removeSelectedPie() {
        selectedIndex = NO_SELECTED_INDEX;
        if (onPieClickListener != null) onPieClickListener.onPieClick(NO_SELECTED_INDEX);
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (pieHelperList.isEmpty()) {
            return;
        }
        canvas.save();
        canvas.scale(mScaleFactor, mScaleFactor);
        int index = 0;
        for (PieHelper pieHelper : pieHelperList) {
            boolean selected = (selectedIndex == index);
            //Log.d("selected", String.valueOf(selected));
            if (pieHelper.isColorSetted()) {
                cirPaint.setColor(pieHelper.getColor());
            } else {
                cirPaint.setColor(DEFAULT_COLOR_LIST[index % 5]);
            }
            switch (index) {
                case 0:
                    if (pieHelper.getSweep() > 0)
                        canvas.drawArc(cirPieRect1, pieHelper.getStartDegree(), pieHelper.getSweep(), true, cirPaint);
                    break;
                case 1:
                    if (pieHelper.getSweep() > 0)
                        canvas.drawArc(cirPieRect2, pieHelper.getStartDegree(), pieHelper.getSweep(), true, cirPaint);
                    break;
                case 2:
                    if (pieHelper.getSweep() > 0)
                        canvas.drawArc(cirPieRect3, pieHelper.getStartDegree(), pieHelper.getSweep(), true, cirPaint);
                    break;
                case 3:
                    if (pieHelper.getSweep() > 0)
                        canvas.drawArc(cirPieRect4, pieHelper.getStartDegree(), pieHelper.getSweep(), true, cirPaint);
                    break;
            }
            //drawText(canvas, pieHelper);

           /* cirSelectedRect.set(2, //minor margin for bigger circle
                    2,
                    mViewWidth-2,
                    mViewHeight-2);*/

            // drawLineBesideCir(canvas, pieHelper.getStartDegree(), selected);
            // drawLineBesideCir(canvas, pieHelper.getEndDegree(), selected);
            index++;
        }
        canvas.restore();

    }

    private void drawLineBesideCir(Canvas canvas, float angel, boolean selectedCir) {
        int sth2 = selectedCir ? mViewHeight / 2 : pieRadius; // Sorry I'm really don't know how to name the variable..
        int sth = 1; // And it's
        if (angel % 360 > 180 && angel % 360 < 360) {
            sth = -1;
        }
        float lineToX = (float) (mViewHeight / 2 + Math.cos(Math.toRadians(-angel)) * sth2);
        float lineToY = (float) (mViewHeight / 2 + sth * Math.abs(Math.sin(Math.toRadians(-angel))) * sth2);
        canvas.drawLine(pieCenterPoint.x, pieCenterPoint.y, lineToX, lineToY, whiteLinePaint);
    }

    private void drawPercentText(Canvas canvas, PieHelper pieHelper) {
        if (!showPercentLabel) return;
        float angel = (pieHelper.getStartDegree() + pieHelper.getEndDegree()) / 2;
        int sth = 1;
        if (angel % 360 > 180 && angel % 360 < 360) {
            sth = -1;
        }
        float x = (float) (mViewHeight / 2 + Math.cos(Math.toRadians(-angel)) * pieRadius);
        float y = (float) (mViewHeight / 2 + sth * Math.abs(Math.sin(Math.toRadians(-angel))) * pieRadius);
        canvas.drawText(pieHelper.getPercentStr(), x, y, textPaint);
    }

    private void drawText(Canvas canvas, PieHelper pieHelper) {
        if (pieHelper.getTitle() == null) return;
        float angel = (pieHelper.getStartDegree() + pieHelper.getEndDegree()) / 2;
        int sth = 1;
        if (angel % 360 > 180 && angel % 360 < 360) {
            sth = -1;
        }
        float x = (float) (mViewHeight / 2 + Math.cos(Math.toRadians(-angel)) * pieRadius);
        float y = (float) (mViewHeight / 2 + sth * Math.abs(Math.sin(Math.toRadians(-angel))) * pieRadius);
        if (!TextUtils.isEmpty(pieHelper.getTitle()) && !TextUtils.equals(pieHelper.getTitle(), "0.0"))
            canvas.drawText(pieHelper.getTitle(), x, y, textPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
            selectedIndex = findPointAt((int) event.getX(), (int) event.getY());
            if (onPieClickListener != null) {
                onPieClickListener.onPieClick(selectedIndex);
            }
            postInvalidate();
            //  mScaleDetector.onTouchEvent(event);

        }
        return true;
    }

    /**
     * find pie index where point is
     *
     * @param x
     * @param y
     * @return
     */
    private int findPointAt(int x, int y) {
        double degree = Math.atan2(x - pieCenterPoint.x, y - pieCenterPoint.y) * 180 / Math.PI;
        degree = -(degree - 180) + 270;
        int index = 0;
        for (PieHelper pieHelper : pieHelperList) {
            if (degree >= pieHelper.getStartDegree() && degree <= pieHelper.getEndDegree()) {
                return index;
            }
            index++;
        }
        return NO_SELECTED_INDEX;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mViewWidth = measureWidth(widthMeasureSpec);
        mViewHeight = measureHeight(heightMeasureSpec);
        margin = mViewWidth / 32;
        pieRadius = (mViewWidth) / 2 - margin;
        pieCenterPoint.set(pieRadius + margin, pieRadius + margin);
        /*cirRect.set(pieCenterPoint.x-pieRadius,
                pieCenterPoint.y-pieRadius,
                pieCenterPoint.x+pieRadius,
                pieCenterPoint.y+pieRadius);*/
        cirPieRect1.set(110, //minor margin for bigger circle
                110,
                mViewWidth - 110,
                mViewHeight - 110);
        cirPieRect2.set(145, //minor margin for bigger circle
                145,
                mViewWidth - 145,
                mViewHeight - 145);
        cirPieRect3.set(180, //minor margin for bigger circle
                180,
                mViewWidth - 180,
                mViewHeight - 180);
        cirPieRect4.set(215, //minor margin for bigger circle
                215,
                mViewWidth - 215,
                mViewHeight - 215);
        setMeasuredDimension(mViewWidth, mViewHeight);
    }

    private int measureWidth(int measureSpec) {
        int preferred = 3;
        return getMeasurement(measureSpec, preferred);
    }

    private int measureHeight(int measureSpec) {
        int preferred = mViewWidth;
        return getMeasurement(measureSpec, preferred);
    }

    private int getMeasurement(int measureSpec, int preferred) {
        int specSize = View.MeasureSpec.getSize(measureSpec);
        int measurement;

        switch (View.MeasureSpec.getMode(measureSpec)) {
            case View.MeasureSpec.EXACTLY:
                measurement = specSize;
                break;
            case View.MeasureSpec.AT_MOST:
                measurement = Math.min(preferred, specSize);
                break;
            default:
                measurement = preferred;
                break;
        }
        return measurement;
    }

    public interface OnPieClickListener {
        void onPieClick(int index);
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mScaleFactor *= detector.getScaleFactor();

            // Don't let the object get too small or too large.
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 5.0f));

            invalidate();
            return true;
        }
    }
}