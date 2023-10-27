package com.onetechsol.ipayment.widgets;

/*
public class CustumTextView extends AppCompatTextView {

   // private static  String MY_TEXT = "";
    private Path mArc;

    private Paint textPaint,piePaint,shadowPaint;
    private StaticLayout layout;
    public CustumTextView(Context context) {
        super(context);
        //MY_TEXT=text;
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(context.getResources().getColor(R.color.red));
            textPaint.setTextSize(70);


        piePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        piePaint.setStyle(Paint.Style.FILL);
        piePaint.setTextSize(70);

        shadowPaint = new Paint(0);
        shadowPaint.setColor(0xff101010);
        shadowPaint.setMaskFilter(new BlurMaskFilter(8, BlurMaskFilter.Blur.NORMAL));
        }

    public CustumTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    public CustumTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Try for a width based on our minimum
        int minw = getPaddingLeft() + getPaddingRight() + getSuggestedMinimumWidth();
        int w = resolveSizeAndState(minw, widthMeasureSpec, 1);

        // Whatever the width ends up being, ask for a height that would let the pie
        // get as big as it can
        int minh = MeasureSpec.getSize(w) - (int)100 + getPaddingBottom() + getPaddingTop();
        int h = resolveSizeAndState(MeasureSpec.getSize(w) - (int)100, heightMeasureSpec, 0);

        setMeasuredDimension(w, h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);



        // Draw the label text
      //  canvas.drawText(data.get(currentItem).mLabel, textX, textY, textPaint);

        // Draw the pie slices
     */
/*   for (int i = 0; i < data.size(); ++i) {
            ClipData.Item it = data.get(i);
            piePaint.setShader(it.shader);
            canvas.drawArc(bounds,
                    360 - it.endAngle,
                    it.endAngle - it.startAngle,
                    true, piePaint);
        }*//*


    }
}
*/
