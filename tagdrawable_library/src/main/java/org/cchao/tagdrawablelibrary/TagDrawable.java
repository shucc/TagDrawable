package org.cchao.tagdrawablelibrary;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

import androidx.annotation.ColorInt;
import androidx.annotation.IntRange;

/**
 * Created by shucc on 17/7/7.
 * cc@cchao.org
 */
public class TagDrawable extends Drawable {

    public static final int ARROW_UP = 0;
    public static final int ARROW_DOWN = 1;
    public static final int ARROW_LEFT = 2;
    public static final int ARROW_RIGHT = 3;

    private int arrowDirection;

    private float arrowWidth;

    private float arrowHeight;

    private float radiusSize;

    //距离左侧，上方距离
    private float margin;

    private int backgroundColor;

    private int strokeColor;

    private float strokeWidth;

    private Paint paint;

    private Paint strokePaint;

    private RectF strokeRectF;

    private RectF rectF;

    private TagDrawable() {
    }

    /**
     * @param arrowDirection  箭头方向
     * @param arrowWidth      箭头宽度
     * @param arrowHeight     箭头高度
     * @param radiusSize      边缘圆弧半径
     * @param margin          距离上方或左侧距离
     * @param backgroundColor 背景底色
     * @param strokeColor     边线颜色
     * @param strokeWidth     边线宽度
     */
    private TagDrawable(@IntRange(from = 0, to = 3) int arrowDirection, float arrowWidth, float arrowHeight, float radiusSize
            , float margin, @ColorInt int backgroundColor, @ColorInt int strokeColor, float strokeWidth) {
        this.arrowDirection = arrowDirection;
        this.arrowWidth = arrowWidth;
        this.arrowHeight = arrowHeight;
        this.radiusSize = radiusSize;
        this.margin = margin;
        this.backgroundColor = backgroundColor;
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;

        paint = new Paint();
        paint.setColor(backgroundColor);
        paint.setAntiAlias(true);

        if (-1 != strokeColor) {
            strokePaint = new Paint();
        }
    }


    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);
        if (ARROW_UP == arrowDirection) {
            strokeRectF = new RectF(left, top + arrowHeight, right, bottom);
        } else if (ARROW_DOWN == arrowDirection) {
            strokeRectF = new RectF(left, top, right, bottom - arrowHeight);
        } else if (ARROW_LEFT == arrowDirection) {
            strokeRectF = new RectF(left + arrowWidth, top, right, bottom);
        } else if (ARROW_RIGHT == arrowDirection) {
            strokeRectF = new RectF(left, top, right - arrowWidth, bottom);
        } else {
            throw new NullPointerException("Arrow direction param error!");
        }
        rectF = new RectF(strokeRectF.left + strokeWidth, strokeRectF.top + strokeWidth, strokeRectF.right - strokeWidth, strokeRectF.bottom - strokeWidth);
    }

    @Override
    public void draw(Canvas canvas) {
        if (strokePaint != null) {
            strokePaint.setColor(strokeColor);
            strokePaint.setStyle(Paint.Style.FILL);
            strokePaint.setStrokeWidth(strokeWidth);
            strokePaint.setAntiAlias(true);
            canvas.drawRoundRect(strokeRectF, radiusSize, radiusSize, strokePaint);
        }
        canvas.drawRoundRect(rectF, radiusSize, radiusSize, paint);

        Path path = new Path();
        Path strokePath = new Path();
        if (ARROW_UP == arrowDirection) {
            float width = strokeRectF.width();
            if (margin > 0) {
                width = margin * 2 + arrowWidth;
            }
            path.moveTo(width / 2, strokeWidth * 2);
            path.lineTo((width + arrowWidth) / 2 - strokeWidth, arrowHeight + strokeWidth);
            path.lineTo((width - arrowWidth) / 2 + strokeWidth, arrowHeight + strokeWidth);

            if (strokePaint != null) {
                strokePath.moveTo(width / 2, 0);
                strokePath.lineTo((width + arrowWidth) / 2, arrowHeight);
                strokePath.lineTo((width - arrowWidth) / 2, arrowHeight);
            }
        } else if (ARROW_DOWN == arrowDirection) {
            float width = strokeRectF.width();
            if (margin > 0) {
                width = margin * 2 + arrowWidth;
            }
            float height = strokeRectF.height() + arrowHeight;
            path.moveTo(width / 2, height - strokeWidth * 2);
            if (strokePaint != null) {
                path.lineTo((width + arrowWidth) / 2, height - arrowWidth);
                path.lineTo((width - arrowWidth) / 2, height - arrowWidth);
                strokePath.moveTo(width / 2, height);
                strokePath.lineTo((width + arrowWidth) / 2, height - arrowHeight);
                strokePath.lineTo((width - arrowWidth) / 2, height - arrowHeight);
            } else {
                path.lineTo((width + arrowWidth) / 2, height - arrowHeight);
                path.lineTo((width - arrowWidth) / 2, height - arrowHeight);
            }
        } else if (ARROW_LEFT == arrowDirection) {
            float height = strokeRectF.height();
            if (margin > 0) {
                height = margin * 2 + arrowHeight;
            }
            path.moveTo(strokeWidth * 2, height / 2);
            path.lineTo(arrowWidth + strokeWidth, (height + arrowHeight) / 2 - strokeWidth);
            path.lineTo(arrowWidth + strokeWidth, (height - arrowHeight) / 2 + strokeWidth);

            if (strokePaint != null) {
                strokePath.moveTo(0, height / 2);
                strokePath.lineTo(arrowWidth, (height + arrowHeight) / 2);
                strokePath.lineTo(arrowWidth, (height - arrowHeight) / 2);
            }
        } else if (ARROW_RIGHT == arrowDirection) {
            float height = strokeRectF.height();
            float width = strokeRectF.width() + arrowWidth;
            if (margin > 0) {
                height = margin * 2 + arrowHeight;
            }
            path.moveTo(width - strokeWidth * 2, height / 2);
            path.lineTo(width - arrowWidth - strokeWidth, (height + arrowHeight) / 2 - strokeWidth);
            path.lineTo(width - arrowWidth - strokeWidth, (height - arrowHeight) / 2 + strokeWidth);

            if (strokePaint != null) {
                strokePath.moveTo(width, height / 2);
                strokePath.lineTo(width - arrowWidth, (height + arrowHeight) / 2);
                strokePath.lineTo(width - arrowWidth, (height - arrowHeight) / 2);
            }
        }
        path.close();
        if (strokePaint != null) {
            strokePath.close();
            canvas.drawPath(strokePath, strokePaint);
        }
        canvas.drawPath(path, paint);
    }

    @Override
    public int getIntrinsicHeight() {
        return super.getIntrinsicHeight();
    }

    @Override
    public int getIntrinsicWidth() {
        return super.getIntrinsicWidth();
    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }

    public static class Builder {

        private int arrowDirection = TagDrawable.ARROW_LEFT;

        private float arrowWidth;

        private float arrowHeight;

        private float radiusSize;

        //距离左侧，上方距离
        private float margin = 0;

        private int backgroundColor;

        private int strokeColor = -1;

        private float strokeWidth = 0;

        /**
         * 设置箭头方向
         * @param arrowDirection
         * @return
         */
        public Builder setArrowDirection(@IntRange(from = 0, to = 3) int arrowDirection) {
            this.arrowDirection = arrowDirection;
            return this;
        }

        /**
         * 箭头宽度
         * @param arrowWidth
         * @return
         */
        public Builder setArrowWidth(float arrowWidth) {
            this.arrowWidth = arrowWidth;
            return this;
        }

        /**
         * 箭头高度
         * @param arrowHeight
         * @return
         */
        public Builder setArrowHeight(float arrowHeight) {
            this.arrowHeight = arrowHeight;
            return this;
        }

        /**
         * 边缘圆弧半径
         * @param radiusSize
         * @return
         */
        public Builder setRadiusSize(float radiusSize) {
            this.radiusSize = radiusSize;
            return this;
        }

        /**
         * 箭头向左向右为上边距，箭头向上向下则为左边距
         * @param margin
         * @return
         */
        public Builder setMargin(float margin) {
            this.margin = margin;
            return this;
        }

        /**
         * 背景色
         * @param backgroundColor
         * @return
         */
        public Builder setBackgroundColor(@ColorInt int backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        /**
         * 边线颜色
         * @param strokeColor
         * @return
         */
        public Builder setStrokeColor(@ColorInt int strokeColor) {
            this.strokeColor = strokeColor;
            return this;
        }

        /**
         * 边线宽度
         * @param strokeWidth
         * @return
         */
        public Builder setStrokeWidth(float strokeWidth) {
            this.strokeWidth = strokeWidth;
            return this;
        }

        public TagDrawable build() {
            return new TagDrawable(arrowDirection, arrowWidth, arrowHeight, radiusSize, margin, backgroundColor, strokeColor, strokeWidth);
        }
    }
}
