package com.example.ronny.ronnysapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Calculator_Fragment extends Fragment {
    private static double buffer = 0,buffer2=0;
    TextView numView,buf1,buf2;
    private enum operations {
        add, sub, mult, div
    }
    private operations state;

    public Calculator_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_calculator, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        numView = (TextView)getView().findViewById(R.id.Number_View);
        buf1 = (TextView)getView().findViewById(R.id.buf1);
        buf2 = (TextView)getView().findViewById(R.id.buf2);
        getView().findViewById(R.id.one).setOnClickListener(click);
        getView().findViewById(R.id.two).setOnClickListener(click);
        getView().findViewById(R.id.three).setOnClickListener(click);
        getView().findViewById(R.id.four).setOnClickListener(click);
        getView().findViewById(R.id.five).setOnClickListener(click);
        getView().findViewById(R.id.six).setOnClickListener(click);
        getView().findViewById(R.id.seven).setOnClickListener(click);
        getView().findViewById(R.id.eight).setOnClickListener(click);
        getView().findViewById(R.id.nine).setOnClickListener(click);
        getView().findViewById(R.id.Addition).setOnClickListener(click);
        getView().findViewById(R.id.clear).setOnClickListener(click);
        getView().findViewById(R.id.Divison).setOnClickListener(click);
        getView().findViewById(R.id.ENTER).setOnClickListener(click);
        getView().findViewById(R.id.Multiplication).setOnClickListener(click);
        getView().findViewById(R.id.Subtraction).setOnClickListener(click);
        getView().findViewById(R.id.zero).setOnClickListener(click);
    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case (R.id.Addition):
                    state= operations.add;
                    buffer2=buffer;
                    buffer=0;
                    break;
                case (R.id.Subtraction):
                    state= operations.sub;
                    buffer2=buffer;
                    buffer=0;
                    break;
                case (R.id.Multiplication):
                    state= operations.mult;
                    buffer2=buffer;
                    buffer=0;
                    break;
                case (R.id.Divison):
                    state= operations.div;
                    buffer2=buffer;
                    buffer=0;
                    break;
                case (R.id.ENTER):
                    switch (state){
                        case add:
                            buffer+=buffer2;
                            buffer2=buffer;
                            break;
                        case sub:
                            buffer-=buffer2;
                            buffer2=buffer;
                            break;
                        case mult:
                            buffer*=buffer2;
                            buffer2=buffer;
                            break;
                        case div:
                            buffer/=buffer2;
                            buffer2=buffer;
                            break;
                        default:
                            break;
                    }
                    break;
                case (R.id.clear):
                    buffer=0;
                    break;
                default:
                    compute(view);
                    break;


            }
            numView.setText(String.valueOf(buffer));
            buf1.setText(String.valueOf(buffer));
            buf2.setText(String.valueOf(buffer2));

        }
    };

    public void compute(View v) {
            if (buffer!=0){
                buffer*=10;
                buffer+=Integer.parseInt(((Button)v).getText().toString());
            }else{
                buffer+=Integer.parseInt(((Button)v).getText().toString());
            }


    }
}
