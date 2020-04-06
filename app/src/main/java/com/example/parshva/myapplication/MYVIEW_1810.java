package com.example.parshva.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;



import com.github.lzyzsd.randomcolor.RandomColor;


public class MYVIEW_1810 extends View {


    Random rnd = new Random();
    int color, count_single = 0, count_multi = 0, value, color_1, color_2, double_true = 0;
    PointF point, point2;

    ArrayList<ArrayList<PointF>> singlelist, singlelist_1;
    ArrayList<PointF> item, item1;
    ArrayList<PointF> firsrt_main, last_main;
    ArrayList<PointF> firsrt_main_1, last_main_1;
    ArrayList<Integer> type_draw, color_draw;
    ArrayList<Integer> type_draw_1, color_draw_1;
    private static final int SIZE = 2;
    RandomColor randomColor;
    int baseRed;
    int baseGreen;
    int baseBlue;
    int flag = 0;


    private SparseArray<PointF> mActivePointers;
    private Paint mPaint, mpaint1;
    private int[] colors = {Color.BLUE, Color.GREEN, Color.MAGENTA,
            Color.BLACK, Color.CYAN, Color.GRAY, Color.RED, Color.DKGRAY,
            Color.LTGRAY, Color.YELLOW};

    private Paint textPaint;


    public MYVIEW_1810(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {

        singlelist = new ArrayList<>();
        singlelist_1 = new ArrayList<>();
        item = new ArrayList<PointF>();
        item1 = new ArrayList<PointF>();
        firsrt_main = new ArrayList<PointF>();
        firsrt_main_1 = new ArrayList<PointF>();
        last_main = new ArrayList<PointF>();
        last_main_1 = new ArrayList<PointF>();
        type_draw = new ArrayList<>();
        color_draw = new ArrayList<>();
        type_draw_1 = new ArrayList<>();
        color_draw_1 = new ArrayList<>();
        mActivePointers = new SparseArray<PointF>();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mpaint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(12);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        mpaint1 = new Paint(Paint.HINTING_ON);
        mpaint1.setStrokeWidth(18);
        mpaint1.setColor(Color.RED);
        mpaint1.setStyle(Paint.Style.FILL_AND_STROKE);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(20);

        final int baseColor = Color.WHITE;

        baseRed = Color.red(baseColor);
        baseGreen = Color.green(baseColor);
        baseBlue = Color.blue(baseColor);
        randomColor = new RandomColor();

        randomColor.randomColor(0, RandomColor.SaturationType.MONOCHROME, RandomColor.Luminosity.BRIGHT);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        clearOnlyRedo();
        // get pointer index from the event object
        int pointerIndex = event.getActionIndex();

        // get pointer ID
        int pointerId = event.getPointerId(pointerIndex);

        // get masked (not specific to a pointer) action
        int maskedAction = event.getActionMasked();

        rnd = new Random();


        Log.v("Color", String.valueOf(color));
        //Log.v("Pointer Id*****", String.valueOf(pointerId));
        // Log.v("Pointer Index------", String.valueOf(pointerIndex));
        switch (maskedAction) {

            case MotionEvent.ACTION_DOWN: {
                color = randomColor.randomColor();
                //color = Color.argb(255, baseRed + rnd.nextInt(256) / 2, baseGreen + rnd.nextInt(256) / 2, baseBlue + rnd.nextInt(256) / 2);
                color_2 = color;
                item = new ArrayList<PointF>();
                PointF f = new PointF();
                f.x = event.getX(pointerIndex);
                f.y = event.getY(pointerIndex);
                mActivePointers.put(pointerId, f);
                item.add(f);

                break;
            }
            case MotionEvent.ACTION_POINTER_DOWN: {

                double_true = 1;
                // We have a new pointer. Lets add it to the list of pointers
                color = randomColor.randomColor();
                //color = Color.argb(255, baseRed + rnd.nextInt(256) / 2, baseGreen + rnd.nextInt(256) / 2, baseBlue + rnd.nextInt(256) / 2);
                //color = Color.argb(255, rnd.nextInt(150), rnd.nextInt(150), rnd.nextInt(150));
                //color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                PointF f = new PointF();
                f.x = event.getX(pointerIndex);
                f.y = event.getY(pointerIndex);

                PointF f2 = new PointF();
                f2.x = event.getX(0);
                f2.y = event.getY(0);
                Log.v("Pointer Id*****", String.valueOf(f2.x));
                Log.v("Pointer Index------", String.valueOf(f2.y));
                firsrt_main.add(f2);


                item.add(f2);
                singlelist.add(item);
                type_draw.add(0);
                color_draw.add(color_2);
                item = new ArrayList<PointF>();

                PointF f1 = new PointF();
                f1.x = event.getX(1);
                f1.y = event.getY(1);
                Log.v("Pointer Id*****", String.valueOf(f1.x));
                Log.v("Pointer Index------", String.valueOf(f1.y));
                last_main.add(f1);

                type_draw.add(1);
                color_draw.add(color);

                mActivePointers.put(pointerId, f);
                break;
            }
            case MotionEvent.ACTION_MOVE: { // a pointer was moved
                //for (int size = event.getPointerCount(), i = 0; i < size; i++) {
                if (event.getPointerCount() == 1) {
                    PointF point = new PointF();

                    point.x = event.getX();
                    point.y = event.getY();
                    if (flag != 1)
                        item.add(point);

                } else if (event.getPointerCount() >= 2) {
                    color = randomColor.randomColor();
                    //color = Color.argb(255, rnd.nextInt(150) + 100,  rnd.nextInt(150) + 100,  + rnd.nextInt(150) + 100);
                    // color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                    PointF f = new PointF();
                    f.x = event.getX(0);
                    f.y = event.getY(0);

                    Log.v("Pointer Id*****", String.valueOf(f.x));
                    Log.v("Pointer Index------", String.valueOf(f.y));
                    firsrt_main.add(f);

                    PointF f1 = new PointF();
                    f1.x = event.getX(1);
                    f1.y = event.getY(1);
                    Log.v("Pointer Id*****", String.valueOf(f1.x));
                    Log.v("Pointer Index------", String.valueOf(f1.y));
                    last_main.add(f1);
                    type_draw.add(1);
                    color_draw.add(color);

                }
                // }
                break;
            }
            case MotionEvent.ACTION_UP: {

                if (double_true != 1) {
                    mActivePointers.remove(pointerId);
                    singlelist.add(item);
                    type_draw.add(0);
                    color_draw.add(color_2);
                    item = new ArrayList<PointF>();
                    Log.v("Sizeeeee", String.valueOf(item.size()));
                    flag = 0;
                }
                flag = 0;

                double_true = 0;
                break;
            }
            case MotionEvent.ACTION_POINTER_UP: {
                mActivePointers.remove(pointerId);
               flag = 1;
                item = new ArrayList<PointF>();
            }
            case MotionEvent.ACTION_CANCEL: {
                mActivePointers.remove(pointerId);
                //flag = 1;
               /* singlelist.add(item);
                type_draw.add(0);
                color_draw.add(color_2);
                item = new ArrayList<PointF>();
                item = new ArrayList<>();*/
                break;
            }
        }
        invalidate();

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {


        super.onDraw(canvas);


        count_multi = 0;
        count_single = 0;
        for (int i = 0; i < type_draw.size(); i++) {
            value = type_draw.get(i);
            color_1 = color_draw.get(i);
            mpaint1.setColor(color_1);
            if (value == 0) {
                item1 = singlelist.get(count_single);
                Draw_Single_Line(canvas);
                count_single++;
            } else {
                Draw_for_Multi(canvas, count_multi);
                count_multi++;
            }


        }


        mpaint1.setColor(color_2);


        Log.v("Size_Of_Type", String.valueOf(type_draw.size()));
        Log.v("Size_of_multi", String.valueOf(firsrt_main.size()));
        Log.v("Size_Of_single", String.valueOf(singlelist.size()));
        Log.v("Size_Of_Color", String.valueOf(color_draw.size()));
        item1 = item;
        Draw_Single_Line(canvas);

        /*DrawLine1(canvas);
        int x=firsrt_main.size();
        if(x!=0)
        {
            for(int p=0;p<firsrt_main.size();p++)
            {
                *//*PointF fp=firsrt_main.get(p);
                PointF lp=last_main.get(p);
                canvas.drawLine(fp.x, fp.y, lp.x, lp.y, mpaint1);*//*

                Draw_for_Multi(canvas,p);
            }
        }*/

    }

    void Draw_for_Multi(Canvas canvas, int p) {
        point = firsrt_main.get(p);
        point2 = last_main.get(p);
        mpaint1.setStrokeWidth(12);
        canvas.drawCircle(point.x, point.y, SIZE, mpaint1);
        canvas.drawCircle(point2.x, point2.y, SIZE, mpaint1);
        mpaint1.setStrokeWidth(18);
        canvas.drawLine(point.x, point.y, point2.x, point2.y, mpaint1);
    }

    void Draw_Single_Line(Canvas canvas) {
        for (int j = 0; j < item1.size(); j++) {
            point = item1.get(j);
            mpaint1.setStrokeWidth(12);
            canvas.drawCircle(point.x, point.y, SIZE, mpaint1);
            mpaint1.setStrokeWidth(18);
            if (j != item1.size() - 1) {
                point2 = item1.get(j + 1);
                canvas.drawLine(point.x, point.y, point2.x, point2.y, mpaint1);
            }
        }
    }


    void clearOnlyRedo() {
        //firsrt_main.clear();
        firsrt_main_1.clear();
        //last_main.clear();
        last_main_1.clear();
        //singlelist.clear();
        singlelist_1.clear();
        //item1.clear();
        // item.clear();
        // type_draw.clear();
        type_draw_1.clear();
        // color_draw.clear();
        color_draw_1.clear();
        invalidate();
    }

    void clear() {
        firsrt_main.clear();
        firsrt_main_1.clear();
        last_main.clear();
        last_main_1.clear();
        singlelist.clear();
        singlelist_1.clear();
        item1.clear();
        item.clear();
        type_draw.clear();
        type_draw_1.clear();
        color_draw.clear();
        color_draw_1.clear();
        invalidate();
    }

    void undo() {
        int i;


        if (type_draw.size() != 0) {
            int value = type_draw.get(type_draw.size() - 1);
            if (value == 0) {
                i = singlelist.size();
                singlelist_1.add(singlelist.get(i - 1));
                singlelist.remove(i - 1);
            } else {
                i = firsrt_main.size();
                firsrt_main_1.add(firsrt_main.get(i - 1));
                firsrt_main.remove(i - 1);

                i = last_main.size();
                last_main_1.add(last_main.get(i - 1));
                last_main.remove(i - 1);
            }


            i = type_draw.size();
            type_draw_1.add(type_draw.get(i - 1));
            type_draw.remove(i - 1);

            i = color_draw.size();
            color_draw_1.add(color_draw.get(i - 1));
            color_draw.remove(i - 1);

        }


        invalidate();

    }

    void Redo() {
        int i;


        if (type_draw_1.size() != 0) {
            int value = type_draw_1.get(type_draw_1.size() - 1);
            if (value == 0) {
                i = singlelist_1.size();
                singlelist.add(singlelist_1.get(i - 1));
                singlelist_1.remove(i - 1);
            } else {
                i = firsrt_main_1.size();
                firsrt_main.add(firsrt_main_1.get(i - 1));
                firsrt_main_1.remove(i - 1);

                i = last_main_1.size();
                last_main.add(last_main_1.get(i - 1));
                last_main_1.remove(i - 1);
            }


            i = type_draw_1.size();
            type_draw.add(type_draw_1.get(i - 1));
            type_draw_1.remove(i - 1);

            i = color_draw_1.size();
            color_draw.add(color_draw_1.get(i - 1));
            color_draw_1.remove(i - 1);

        }


        invalidate();

    }
}