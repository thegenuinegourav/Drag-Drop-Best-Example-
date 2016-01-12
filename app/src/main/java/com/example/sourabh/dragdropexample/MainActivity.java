package com.example.sourabh.dragdropexample;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener,View.OnDragListener {

    private TextView text1,text2,text3,text4,text5,text6;

    //When touched text gets dropped into either text4 or text5 or text6 then this method will be called
    @Override
    public boolean onDrag(View v, DragEvent event) {
        if (event.getAction()==DragEvent.ACTION_DROP)
        {
            //handle the dragged view being dropped over a target view
            TextView dropped = (TextView)event.getLocalState();
            TextView dropTarget = (TextView) v;
            //stop displaying the view where it was before it was dragged
            dropped.setVisibility(View.INVISIBLE);

            //if an item has already been dropped here, there will be different string
            String text=dropTarget.getText().toString();
            //if there is already an item here, set it back visible in its original place
            if(text.equals(text1.getText().toString())) text1.setVisibility(View.VISIBLE);
            else if(text.equals(text2.getText().toString())) text2.setVisibility(View.VISIBLE);
            else if(text.equals(text3.getText().toString())) text3.setVisibility(View.VISIBLE);

            //update the text and color in the target view to reflect the data being dropped
            dropTarget.setText(dropped.getText());
            dropTarget.setBackgroundColor(Color.BLUE);
        }
        return true;
    }

    //When text1 or text2 or text3 gets clicked or touched then this method will be called
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction()==MotionEvent.ACTION_DOWN)
        {
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(null, shadowBuilder, v, 0);
            return true;
        }
        else return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = (TextView)findViewById(R.id.text1);
        text2 = (TextView)findViewById(R.id.text2);
        text3 = (TextView)findViewById(R.id.text3);
        text4 = (TextView)findViewById(R.id.text4);
        text5 = (TextView)findViewById(R.id.text5);
        text6 = (TextView)findViewById(R.id.text6);

        //Setting touch and drag listeners
        text1.setOnTouchListener(this);
        text2.setOnTouchListener(this);
        text3.setOnTouchListener(this);
        text4.setOnDragListener(this);
        text5.setOnDragListener(this);
        text6.setOnDragListener(this);
    }
}
