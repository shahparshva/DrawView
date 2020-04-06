package com.example.parshva.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Size;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class MyView extends View {


    Random rnd = new Random();
    int color ;


    ArrayList<ArrayList<PointF>> singlelist;
    ArrayList<PointF> item,item1;
    ArrayList<PointF> firsrt_main,last_main;
    ArrayList<Integer> type_draw,color_draw;
    private static final int SIZE = 2;
    int flag=0;


    private SparseArray<PointF> mActivePointers;
    private Paint mPaint,mpaint1;
    private int[] colors = {Color.BLUE, Color.GREEN, Color.MAGENTA,
            Color.BLACK, Color.CYAN, Color.GRAY, Color.RED, Color.DKGRAY,
            Color.LTGRAY, Color.YELLOW};

    private Paint textPaint;


    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {

        singlelist = new ArrayList<>();
        item = new ArrayList<PointF>();
        item1 = new ArrayList<PointF>();
        firsrt_main = new ArrayList<PointF>();
        last_main = new ArrayList<PointF>();
        type_draw=new ArrayList<>();
        color_draw=new ArrayList<>();
        mActivePointers = new SparseArray<PointF>();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mpaint1= new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(10);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        mpaint1 = new Paint(Paint.HINTING_ON);
        mpaint1.setStrokeWidth(15);
        mpaint1.setColor(Color.RED);
        mpaint1.setStyle(Paint.Style.FILL_AND_STROKE);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(20);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // get pointer index from the event object
        int pointerIndex = event.getActionIndex();

        // get pointer ID
        int pointerId = event.getPointerId(pointerIndex);

        // get masked (not specific to a pointer) action
        int maskedAction = event.getActionMasked();




         Log.v("Color", String.valueOf(color));
        //Log.v("Pointer Id*****", String.valueOf(pointerId));
       // Log.v("Pointer Index------", String.valueOf(pointerIndex));
        switch (maskedAction) {

            case MotionEvent.ACTION_DOWN: {
                rnd = new Random();
                color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                item = new ArrayList<PointF>();
                PointF f = new PointF();
                f.x = event.getX(pointerIndex);
                f.y = event.getY(pointerIndex);
                mActivePointers.put(pointerId, f);
                item.add(f);
                break;
            }
            case MotionEvent.ACTION_POINTER_DOWN: {
                rnd = new Random();
                color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                // We have a new pointer. Lets add it to the list of pointers

                PointF f = new PointF();
                f.x = event.getX(pointerIndex);
                f.y = event.getY(pointerIndex);

                PointF f2 = new PointF();
                f2.x = event.getX(0);
                f2.y = event.getY(0);
                Log.v("Pointer Id*****", String.valueOf(f2.x));
                Log.v("Pointer Index------", String.valueOf(f2.y));
                firsrt_main.add(f2);

                PointF f1 = new PointF();
                f1.x = event.getX(1);
                f1.y = event.getY(1);
                Log.v("Pointer Id*****", String.valueOf(f1.x));
                Log.v("Pointer Index------", String.valueOf(f1.y));
                last_main.add(f1);

                type_draw.add(1);

                mActivePointers.put(pointerId, f);
                break;
            }
            case MotionEvent.ACTION_MOVE: { // a pointer was moved
                //for (int size = event.getPointerCount(), i = 0; i < size; i++) {
                if (event.getPointerCount() == 1) {
                    PointF point = new PointF();

                    point.x = event.getX();
                    point.y = event.getY();
                    if(flag!=1)
                    item.add(point);

                }
                else if(event.getPointerCount()>=2)
                {
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

                }
                // }
                break;
            }
            case MotionEvent.ACTION_UP:
            {
                mActivePointers.remove(pointerId);
                singlelist.add(item);
                type_draw.add(0);
                item = new ArrayList<PointF>();
                Log.v("Sizeeeee", String.valueOf(item.size()));
                flag=0;
                break;
            }
            case MotionEvent.ACTION_POINTER_UP: {
                mActivePointers.remove(pointerId);
                flag=1;
            }
            case MotionEvent.ACTION_CANCEL: {
                mActivePointers.remove(pointerId);
                singlelist.add(item);
                type_draw.add(0);
                item = new ArrayList<PointF>();
                item = new ArrayList<>();
                break;
            }
        }
        invalidate();

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        DrawLine(canvas);
        super.onDraw(canvas);

        //mpaint1.setColor(color);


        Log.v("Size_Of_Type", String.valueOf(type_draw.size()));
        Log.v("Size_of_multi", String.valueOf(firsrt_main.size()));
        Log.v("Size_Of_single", String.valueOf(singlelist.size()));
        int x=firsrt_main.size();
        if(x!=0)
        {
            for(int p=0;p<firsrt_main.size();p++)
            {
                PointF fp=firsrt_main.get(p);
                PointF lp=last_main.get(p);
                canvas.drawLine(fp.x, fp.y, lp.x, lp.y, mpaint1);
            }
        }

    }

    void DrawLine(Canvas canvas)
    {
        PointF point, point2;
        // draw all pointers

        Log.e("Sizeeeee", String.valueOf(item.size()));
        for (int j = 0; j < item.size(); j++) {
            point = item.get(j);
            mpaint1.setStrokeWidth(10);
            canvas.drawCircle(point.x, point.y, SIZE, mpaint1);
            mpaint1.setStrokeWidth(15);

            if(j!=item.size()-1 ) {
                point2 = item.get(j + 1);
                canvas.drawLine(point.x, point.y, point2.x, point2.y, mpaint1);
            }
        }


        for (int i = 0; i < singlelist.size(); i++) {
            item1 = singlelist.get(i);
            for (int j = 0; j < item1.size(); j++) {
                point = item1.get(j);
                mpaint1.setStrokeWidth(10);
                canvas.drawCircle(point.x, point.y, SIZE, mpaint1);
                mpaint1.setStrokeWidth(15);
                if(j!=item1.size()-1 ) {
                    point2 = item1.get(j + 1);
                    canvas.drawLine(point.x, point.y, point2.x, point2.y, mpaint1);
                }
            }

         /*   if (point != null)
                mPaint.setColor(colors[i % 9]);
            canvas.drawCircle(point.x, point.y, SIZE, mPaint);*/
        }


        /*for (int size = mActivePointers.size(), i = 0; i < size; i++) {
            PointF point = mActivePointers.valueAt(i);
            if (point != null)
                mPaint.setColor(colors[i % 9]);
            canvas.drawCircle(point.x, point.y, SIZE, mPaint);
        }*/
        canvas.drawText("Total pointers: " + mActivePointers.size(), 10, 40, textPaint);
    }

    void clear()
    {
        firsrt_main.clear();
        last_main.clear();
        singlelist.clear();
        item1.clear();
        item.clear();
        type_draw.clear();
        invalidate();
    }
}