package com.kupo.ElephantHead.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.kupo.ElephantHead.R;


/**
 * Created by G400 on 2019/8/4.
 * 功能：自定义底部菜单栏里面的Item(配合BaseLineInstructions使用)
 * 作者：
 */
public class CanGradualView extends View {

    private Bitmap mIconNormal;
    private Bitmap mIconSelected;
    private String mText;
    private int mTextColorNormal;
    private int mTextColorSelected;
    private int mTextSize;
    private int padding;
    private float mAlpha;
    private Paint mSelectedPaint;
    private Rect mIconAvailableRect;
    private Rect mIconDrawRect;
    private Paint mTextPaint;
    private Rect mTextBound;
    private Paint.FontMetricsInt mFmi;


    public CanGradualView(Context context) {
        this(context, (AttributeSet) null);
    }

    public CanGradualView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * 初始化样式
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public CanGradualView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mTextColorNormal = -6710887;
        this.mTextColorSelected = -12140517;
        this.mTextSize = 12;
        this.padding = 5;
        this.mSelectedPaint = new Paint();
        this.mIconAvailableRect = new Rect();
        this.mIconDrawRect = new Rect();
        this.mTextSize = (int) TypedValue.applyDimension(2, (float) this.mTextSize, this.getResources().getDisplayMetrics());
        this.padding = (int) TypedValue.applyDimension(1, (float) this.padding, this.getResources().getDisplayMetrics());
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CanGradualView);
        BitmapDrawable iconNormal = (BitmapDrawable) a.getDrawable(R.styleable.CanGradualView_av_tabIconNormal);
        if (iconNormal != null) {
            this.mIconNormal = iconNormal.getBitmap();
        }

        BitmapDrawable iconSelected = (BitmapDrawable) a.getDrawable(R.styleable.CanGradualView_av_tabIconSelected);
        if (iconSelected != null) {
            this.mIconSelected = iconSelected.getBitmap();
        }

        this.mText = a.getString(R.styleable.CanGradualView_av_tabText);
        this.mTextSize = a.getDimensionPixelSize(R.styleable.CanGradualView_av_tabTextSize, this.mTextSize);
        this.mTextColorNormal = a.getColor(R.styleable.CanGradualView_av_textColorNormal, this.mTextColorNormal);
        this.mTextColorSelected = a.getColor(R.styleable.CanGradualView_av_textColorSelected, this.mTextColorSelected);
        a.recycle();
        this.initText();
    }

    /**
     * 初始化字体
     */
    private void initText() {
        if (this.mText != null) {
            this.mTextBound = new Rect();
            this.mTextPaint = new Paint();
            this.mTextPaint.setTextSize((float) this.mTextSize);
            this.mTextPaint.setAntiAlias(true);
            this.mTextPaint.setDither(true);
            this.mTextPaint.getTextBounds(this.mText, 0, this.mText.length(), this.mTextBound);
            this.mFmi = this.mTextPaint.getFontMetricsInt();
        }
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (this.mText != null || this.mIconNormal != null && this.mIconSelected != null) {
            int paddingLeft = this.getPaddingLeft();
            int paddingTop = this.getPaddingTop();
            int paddingRight = this.getPaddingRight();
            int paddingBottom = this.getPaddingBottom();
            int measuredWidth = this.getMeasuredWidth();
            int measuredHeight = this.getMeasuredHeight();
            int availableWidth = measuredWidth - paddingLeft - paddingRight;
            int availableHeight = measuredHeight - paddingTop - paddingBottom;
            int textLeft;
            int textTop;
            if (this.mText != null && this.mIconNormal != null) {
                availableHeight -= this.mTextBound.height() + this.padding;
                this.mIconAvailableRect.set(paddingLeft, paddingTop, paddingLeft + availableWidth, paddingTop + availableHeight);
                textLeft = paddingLeft + (availableWidth - this.mTextBound.width()) / 2;
                textTop = this.mIconAvailableRect.bottom + this.padding;
                this.mTextBound.set(textLeft, textTop, textLeft + this.mTextBound.width(), textTop + this.mTextBound.height());
            } else if (this.mText == null) {
                this.mIconAvailableRect.set(paddingLeft, paddingTop, paddingLeft + availableWidth, paddingTop + availableHeight);
            } else if (this.mIconNormal == null) {
                textLeft = paddingLeft + (availableWidth - this.mTextBound.width()) / 2;
                textTop = paddingTop + (availableHeight - this.mTextBound.height()) / 2;
                this.mTextBound.set(textLeft, textTop, textLeft + this.mTextBound.width(), textTop + this.mTextBound.height());
            }

        } else {
            throw new IllegalArgumentException("必须设置 tabText 或者 tabIconSelected、tabIconNormal 两个，或者全部设置");
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int alpha = (int) Math.ceil((double) (this.mAlpha * 255.0F));
        if (this.mIconNormal != null && this.mIconSelected != null) {
            Rect drawRect = this.availableToDrawRect(this.mIconAvailableRect, this.mIconNormal);
            this.mSelectedPaint.reset();
            this.mSelectedPaint.setAntiAlias(true);
            this.mSelectedPaint.setFilterBitmap(true);
            this.mSelectedPaint.setAlpha(255 - alpha);
            canvas.drawBitmap(this.mIconNormal, (Rect) null, drawRect, this.mSelectedPaint);
            this.mSelectedPaint.reset();
            this.mSelectedPaint.setAntiAlias(true);
            this.mSelectedPaint.setFilterBitmap(true);
            this.mSelectedPaint.setAlpha(alpha);
            canvas.drawBitmap(this.mIconSelected, (Rect) null, drawRect, this.mSelectedPaint);
        }

        if (this.mText != null) {
            this.mTextPaint.setColor(this.mTextColorNormal);
            this.mTextPaint.setAlpha(255 - alpha);
            canvas.drawText(this.mText, (float) this.mTextBound.left, (float) (this.mTextBound.bottom - this.mFmi.bottom / 2), this.mTextPaint);
            this.mTextPaint.setColor(this.mTextColorSelected);
            this.mTextPaint.setAlpha(alpha);
            canvas.drawText(this.mText, (float) this.mTextBound.left, (float) (this.mTextBound.bottom - this.mFmi.bottom / 2), this.mTextPaint);
        }

    }

    private Rect availableToDrawRect(Rect availableRect, Bitmap bitmap) {
        float dx = 0.0F;
        float dy = 0.0F;
        float wRatio = (float) availableRect.width() * 1.0F / (float) bitmap.getWidth();
        float hRatio = (float) availableRect.height() * 1.0F / (float) bitmap.getHeight();
        if (wRatio > hRatio) {
            dx = ((float) availableRect.width() - hRatio * (float) bitmap.getWidth()) / 2.0F;
        } else {
            dy = ((float) availableRect.height() - wRatio * (float) bitmap.getHeight()) / 2.0F;
        }

        int left = (int) ((float) availableRect.left + dx + 0.5F);
        int top = (int) ((float) availableRect.top + dy + 0.5F);
        int right = (int) ((float) availableRect.right - dx + 0.5F);
        int bottom = (int) ((float) availableRect.bottom - dy + 0.5F);
        this.mIconDrawRect.set(left, top, right, bottom);
        return this.mIconDrawRect;
    }

    public void setIconAlpha(float alpha) {
        if (alpha >= 0.0F && alpha <= 1.0F) {
            this.mAlpha = alpha;
            this.invalidateView();
        } else {
            throw new IllegalArgumentException("透明度必须是 0.0 - 1.0");
        }
    }

    private void invalidateView() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            this.invalidate();
        } else {
            this.postInvalidate();
        }

    }
}
