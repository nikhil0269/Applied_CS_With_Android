package com.example.nikhil.scarnesdice;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {
    TextView tv1;
    ImageView iv1;
    Button btn1,btn2,btn3;
    int mtotalScore=0,mturnScore=0,ctotalScore=0,cturnScore=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1=(TextView)findViewById(R.id.tv1);
        iv1=(ImageView)findViewById(R.id.iv1);
        btn1=(Button)findViewById(R.id.btn1);
        btn2=(Button)findViewById(R.id.btn2);
        btn3=(Button)findViewById(R.id.btn3);
        btn2.setEnabled(false);

    }

    void roll (View v) throws InterruptedException {
        final Animation animRotate= AnimationUtils.loadAnimation(this,R.anim.anim1);

        btn2.setEnabled(true);
        Random rand=new Random();
        int n=rand.nextInt(6)+1;
        switch (n)
        {
            case 1:
                iv1.setImageResource(R.drawable.dice1);
                iv1.startAnimation(animRotate
                );
                mturnScore=0;
                //n=0;
                hold(btn2);
                //btn1.setEnabled(false);
                break;
            case 2:
                iv1.setImageResource(R.drawable.dice2);
                break;
            case 3:
                iv1.setImageResource(R.drawable.dice3);
                break;
            case 4:
                iv1.setImageResource(R.drawable.dice4);
                break;
            case 5:
                iv1.setImageResource(R.drawable.dice5);
                break;
            case 6:
                iv1.setImageResource(R.drawable.dice6);
                break;

        }
        if(n!=1) {
            iv1.startAnimation(animRotate);
            mturnScore = mturnScore + n;
            if(mtotalScore+mturnScore>=100)
            {
                btn1.setEnabled(false);
                btn2.setEnabled(false);
                mtotalScore=mtotalScore+mturnScore;
                tv1.setText("You Win! Score: "+mtotalScore+" Computer Score: "+ctotalScore);
            }
            else {
                tv1.setText("Your turn score: " + mturnScore);
            }
        }


    }

    void hold(View v) throws InterruptedException {
        btn2.setEnabled(false);
        int n=0;
        mtotalScore=mtotalScore+mturnScore;
        mturnScore=0;
        tv1.setText("Your score: "+mtotalScore+" Computer score: "+ctotalScore);
        btn1.setEnabled(false);


        while (true)
        {
            //call delay
            Random rand=new Random();
            //TimeUnit.SECONDS.sleep(1);

            SystemClock.sleep(800);
            //Thread.sleep(800);
            //for(int j=0;j<200000000;j++);
            n=rand.nextInt(6)+1;
            switch (n)
            {
                case 1:
                    iv1.setImageResource(R.drawable.dice1);
                    cturnScore=0;
                    n=0;

                    //hold(btn2);

                    break;
                case 2:
                    iv1.setImageResource(R.drawable.dice2);
                    break;
                case 3:
                    iv1.setImageResource(R.drawable.dice3);
                    break;
                case 4:
                    iv1.setImageResource(R.drawable.dice4);
                    break;
                case 5:
                    iv1.setImageResource(R.drawable.dice5);
                    break;
                case 6:
                    iv1.setImageResource(R.drawable.dice6);
                    break;

            }

            cturnScore=cturnScore+n;
            if(ctotalScore+cturnScore>=100)
            {
                btn1.setEnabled(false);
                btn2.setEnabled(false);
                ctotalScore=ctotalScore+cturnScore;
                tv1.setText("Computer Wins! score: "+ctotalScore+" You Score: "+mtotalScore);
                break;
            }
            tv1.setText("Computer Score: "+cturnScore);
            if(cturnScore>10) break;
            if(n==0) break;
        }
        if(ctotalScore+cturnScore<100){
        ctotalScore=ctotalScore+cturnScore;
        cturnScore=0;
            SystemClock.sleep(800);
        //TimeUnit.SECONDS.sleep(1);
        //Nikhil
            // Thread.sleep(800);
            //for(int j=0;j<300000000;j++);
        tv1.setText("Your Score: "+mtotalScore+" Computer Score: "+ctotalScore);
        btn1.setEnabled(true);}

    }

    void reset(View v)
    {

        mtotalScore=0;
        mturnScore=0;
        ctotalScore=0;
        cturnScore=0;
        tv1.setText("Your Score: "+mtotalScore+" Computer Score: "+ctotalScore);
        btn1.setEnabled(true);
    }
}
