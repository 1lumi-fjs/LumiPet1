package com.lumi.pet;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;

public class PetView extends View {

    private final Paint paint =
            new Paint(Paint.ANTI_ALIAS_FLAG);

    private int mood = 0;

    private final long startTime =
            System.currentTimeMillis();

    public PetView(Context context) {
        super(context);
    }

    public void nextMood() {
        mood = (mood + 1) % 3;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        float w = getWidth();
        float h = getHeight();

        float time =
                (System.currentTimeMillis()
                        - startTime) / 1000f;

        float floating =
                (float)Math.sin(time * 2.0f) * 4f;

        canvas.save();
        canvas.translate(0, floating);

        // 阴影
        paint.setColor(0x22000000);

        canvas.drawOval(
                new RectF(
                        w * .15f,
                        h * .82f,
                        w * .85f,
                        h * .92f
                ),
                paint
        );

        // 尾巴
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setColor(0xFF8B76B5);

        Path tail = new Path();

        tail.moveTo(
                w * .70f,
                h * .62f
        );

        tail.cubicTo(
                w * .98f,
                h * .56f,
                w * .98f,
                h * .80f,
                w * .76f,
                h * .78f
        );

        canvas.drawPath(tail, paint);

        paint.setStyle(Paint.Style.FILL);

        // 衣服
        paint.setColor(0xFFEDE8F7);

        Path dress = new Path();

        dress.moveTo(
                w * .31f,
                h * .51f
        );

        dress.lineTo(
                w * .69f,
                h * .51f
        );

        dress.lineTo(
                w * .88f,
                h * .86f
        );

        dress.lineTo(
                w * .12f,
                h * .86f
        );

        dress.close();

        canvas.drawPath(dress, paint);

        // 银色装饰
        paint.setColor(0xFFC9C1D9);

        canvas.drawRect(
                w * .47f,
                h * .57f,
                w * .53f,
                h * .75f,
                paint
        );

        // 白色头发
        paint.setColor(Color.WHITE);

        canvas.drawRoundRect(
                new RectF(
                        w * .19f,
                        h * .17f,
                        w * .81f,
                        h * .67f
                ),
                60,
                60,
                paint
        );

        // 左尖耳
        paint.setColor(0xFFEDE8F7);

        Path ear = new Path();

        ear.moveTo(
                w * .23f,
                h * .29f
        );

        ear.lineTo(
                w * .06f,
                h * .10f
        );

        ear.lineTo(
                w * .20f,
                h * .43f
        );

        ear.close();

        canvas.drawPath(ear, paint);

        // 右尖耳
        ear = new Path();

        ear.moveTo(
                w * .77f,
                h * .29f
        );

        ear.lineTo(
                w * .94f,
                h * .10f
        );

        ear.lineTo(
                w * .80f,
                h * .43f
        );

        ear.close();

        canvas.drawPath(ear, paint);

        // 双角
        paint.setColor(0xFFB9A9D6);

        Path horn = new Path();

        horn.moveTo(
                w * .34f,
                h * .18f
        );

        horn.lineTo(
                w * .40f,
                h * .02f
        );

        horn.lineTo(
                w * .46f,
                h * .18f
        );

        horn.close();

        canvas.drawPath(horn, paint);

        horn = new Path();

        horn.moveTo(
                w * .54f,
                h * .18f
        );

        horn.lineTo(
                w * .60f,
                h * .02f
        );

        horn.lineTo(
                w * .66f,
                h * .18f
        );

        horn.close();

        canvas.drawPath(horn, paint);

        // 眼睛
        paint.setColor(0xFF8B76B5);

        if (mood == 2) {

            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(5);

            canvas.drawArc(
                    new RectF(
                            w * .31f,
                            h * .37f,
                            w * .43f,
                            h * .46f
                    ),
                    10,
                    160,
                    false,
                    paint
            );

            canvas.drawArc(
                    new RectF(
                            w * .57f,
                            h * .37f,
                            w * .69f,
                            h * .46f
                    ),
                    10,
                    160,
                    false,
                    paint
            );

            paint.setStyle(Paint.Style.FILL);

        } else {

            canvas.drawOval(
                    new RectF(
                            w * .31f,
                            h * .37f,
                            w * .43f,
                            h * .48f
                    ),
                    paint
            );

            canvas.drawOval(
                    new RectF(
                            w * .57f,
                            h * .37f,
                            w * .69f,
                            h * .48f
                    ),
                    paint
            );
        }

        // 嘴巴
        paint.setColor(0xFF8B76B5);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4);

        canvas.drawArc(
                new RectF(
                        w * .43f,
                        h * .47f,
                        w * .57f,
                        h * .56f
                ),
                0,
                180,
                false,
                paint
        );

        paint.setStyle(Paint.Style.FILL);

        // 对话框
        paint.setColor(0xF7FFFFFF);

        canvas.drawRoundRect(
                new RectF(
                        w * .08f,
                        h * .90f,
                        w * .92f,
                        h * .99f
                ),
                18,
                18,
                paint
        );

        paint.setColor(0xFF705C93);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(21);

        String message;

        if (mood == 0) {
            message = "你好呀～";
        } else if (mood == 1) {
            message = "今天也要加油！";
        } else {
            message = "眨眼～";
        }

        canvas.drawText(
                message,
                w / 2,
                h * .965f,
                paint
        );

        canvas.restore();

        postInvalidateDelayed(40);
    }
}
