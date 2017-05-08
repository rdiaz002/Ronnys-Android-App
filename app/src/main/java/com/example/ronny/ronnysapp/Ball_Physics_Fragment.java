package com.example.ronny.ronnysapp;


import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GestureDetectorCompat;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

public class Ball_Physics_Fragment extends Fragment implements GestureDetector.OnGestureListener {
    private static final double GRAVITY = 9.81;
    private static float x_init = 0;
    private static float y_init = 0;
    private static int Width = 0;
    private static int Height = 0;
    ImageView b1;
    private GestureDetectorCompat mDetector;

    public Ball_Physics_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = (View) inflater.inflate(R.layout.fragment_ball_physics, container, false);

        mDetector = new GestureDetectorCompat(getActivity(), this);
        root.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case 2:
                        y_init = motionEvent.getRawY() - b1.getHeight();
                        x_init = motionEvent.getRawX() - (b1.getWidth() / 2);
                        b1.setTranslationX(x_init);
                        b1.setTranslationY(y_init);
                        break;
                    default:

                }
                return mDetector.onTouchEvent(motionEvent);
            }
        });
        return root;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        b1 = (ImageView) getView().findViewById(R.id.ball);

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        y_init = motionEvent1.getRawY() - b1.getHeight();
        x_init = motionEvent1.getRawX() - (b1.getWidth() / 2);
        b1.setTranslationX(x_init);
        b1.setTranslationY(y_init);
        onStartAnimation(v, v1);
        return true;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        y_init = motionEvent.getRawY() - b1.getHeight();
        x_init = motionEvent.getRawX() - (b1.getWidth() / 2);
        b1.setTranslationX(x_init);
        b1.setTranslationY(y_init);
        return true;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {

        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    public void onStartAnimation(float vx, float vy) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Width = displayMetrics.widthPixels;
        Height = displayMetrics.heightPixels;
        vx /= 12;
        vy /= 12;
        float Vy_init = vy;
        float Vx_init = vx;
        boolean end_state = true;
        Path track = new Path();
        track.moveTo(x_init, y_init);


        for (double t = 1; end_state; t += 0.01) {

            y_init += (vy * t) + (0.5) * (GRAVITY) * Math.pow(t, 2);

            if (y_init > Height - b1.getHeight()) {
                y_init = Height - b1.getHeight();
                vy = (float) 0.8 * Vy_init;
                Vy_init = vy;
            } else if (y_init < 0) {
                y_init = 0;
                vy = -vy;
            }

            vy += (GRAVITY) * t;
            x_init += (vx * t);

            if (x_init > Width - b1.getWidth() / 2) {
                vx = (float) -0.5 * Vx_init;
                Vx_init = vx;
                x_init = Width - b1.getWidth() / 2;
            } else if (x_init < -b1.getWidth() / 2) {
                vx = (float) -0.5 * Vx_init;
                Vx_init = vx;
                x_init = -b1.getWidth() / 2;
            }
            track.lineTo(x_init, y_init);

            if ((int) Vy_init == 0) {
                end_state = false;
            }
        }
        ObjectAnimator ball = ObjectAnimator.ofFloat(b1, "X", "Y", track);
        ball.setDuration(2000);
        ball.setInterpolator(new DecelerateInterpolator());
        ball.start();


    }

    public float getX() {
        return x_init;
    }

    public float getY() {
        return y_init;
    }


}
