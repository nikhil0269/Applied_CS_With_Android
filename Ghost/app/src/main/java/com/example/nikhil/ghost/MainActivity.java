package com.example.nikhil.ghost;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

import static android.view.KeyEvent.KEYCODE_A;
import static android.view.KeyEvent.KEYCODE_B;
import static android.view.KeyEvent.KEYCODE_C;
import static android.view.KeyEvent.KEYCODE_D;
import static android.view.KeyEvent.KEYCODE_E;
import static android.view.KeyEvent.KEYCODE_F;
import static android.view.KeyEvent.KEYCODE_G;
import static android.view.KeyEvent.KEYCODE_H;
import static android.view.KeyEvent.KEYCODE_I;
import static android.view.KeyEvent.KEYCODE_J;
import static android.view.KeyEvent.KEYCODE_K;
import static android.view.KeyEvent.KEYCODE_L;
import static android.view.KeyEvent.KEYCODE_M;
import static android.view.KeyEvent.KEYCODE_N;
import static android.view.KeyEvent.KEYCODE_O;
import static android.view.KeyEvent.KEYCODE_P;
import static android.view.KeyEvent.KEYCODE_Q;
import static android.view.KeyEvent.KEYCODE_R;
import static android.view.KeyEvent.KEYCODE_S;
import static android.view.KeyEvent.KEYCODE_T;
import static android.view.KeyEvent.KEYCODE_U;
import static android.view.KeyEvent.KEYCODE_V;
import static android.view.KeyEvent.KEYCODE_W;
import static android.view.KeyEvent.KEYCODE_X;
import static android.view.KeyEvent.KEYCODE_Y;
import static android.view.KeyEvent.KEYCODE_Z;

public class MainActivity extends AppCompatActivity {
    private static final String COMPUTER_TURN = "Computer's turn";
    private static final String USER_TURN = "Your turn";
    private boolean userTurn = false;
    private Random random = new Random();


    Button btn1,btn2;
    TextView tv1,tv2;
    SimpleDictionary dictionary;
    String fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1=(TextView)findViewById(R.id.tv1);
        tv2=(TextView)findViewById(R.id.tv2);
        btn1=(Button)findViewById(R.id.btn1);
        btn2=(Button)findViewById(R.id.btn2);

        AssetManager assetManager=getAssets();
        try {
            InputStream inputStream=assetManager.open("words.txt");
            dictionary=new SimpleDictionary(new InputStreamReader(inputStream));
        } catch (IOException e) {
            e.printStackTrace();
        }

        onStart(null);
    }

    void challenge(View v)
    {
        btn1.setEnabled(false);
        String temp=dictionary.getAnyWordStartingWith(fragment);
        if((temp==null)||(dictionary.isWord(fragment)))
        {
            tv2.setText("You Win!");

        }
        else {
            tv2.setText("Computer Win!");

        }
        btn1.setEnabled(true);

    }

    void restart(View v)
    {
        fragment=null;

        onStart(null);
    }



    public boolean onStart(View view) {
        userTurn = random.nextBoolean();
        //TextView text = (TextView) findViewById(R.id.tv1);
        tv1.setText("");
        //TextView label = (TextView) findViewById(R.id.tv2);
        if (userTurn) {
            tv2.setText(USER_TURN);

        } else {
            tv2.setText(COMPUTER_TURN);
            computerTurn();
        }
        return true;
    }

    private void computerTurn() {
        //TextView label = (TextView) findViewById(R.id.tv2);
        // Do computer turn stuff then make it the user's turn again
        btn1.setEnabled(false);
        if(fragment!=null) {
            if (dictionary.isWord(fragment)) {
                tv2.setText("Computer Wins!");
                return;
            }
        }
        //This method gives any possible word starting with fragment string
        //To get longest possible word add some more logic
        String temp=dictionary.getAnyWordStartingWith(fragment);
        if(temp==null)
        {
            tv2.setText("Computer Wins!  no Word will form");

            return;
        }
        else {
            if(fragment==null) fragment = "";
            fragment = temp.substring(0, (fragment.length()) + 1);
            tv1.setText(fragment);
            userTurn = true;
            tv2.setText(USER_TURN);
            btn1.setEnabled(true);
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode)
        {
            case KEYCODE_A:
            case KEYCODE_B:
            case KEYCODE_C:
            case KEYCODE_D:
            case KEYCODE_E:
            case KEYCODE_F:
            case KEYCODE_G:
            case KEYCODE_H:
            case KEYCODE_I:
            case KEYCODE_J:
            case KEYCODE_K:
            case KEYCODE_L:
            case KEYCODE_M:
            case KEYCODE_N:
            case KEYCODE_O:
            case KEYCODE_P:
            case KEYCODE_Q:
            case KEYCODE_R:
            case KEYCODE_S:
            case KEYCODE_T:
            case KEYCODE_U:
            case KEYCODE_V:
            case KEYCODE_W:
            case KEYCODE_X:
            case KEYCODE_Y:
            case KEYCODE_Z:
                char ch=(char)event.getUnicodeChar();
                Character character=new Character(ch);
                String sub=character.toString();
                if(fragment==null)
                {
                    fragment="";
                }
                fragment=fragment+sub;
                tv1.setText(fragment);
                //calling to computer turn
                computerTurn();
                return true;
        }
        return super.onKeyUp(keyCode, event);
    }
}
