package com.ekreative.geeklabs;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private final int ZERO = 1024;
    private static final String TAG = MainActivity.class.getSimpleName();
    private CClient mClient;
    public int leftValue = 0, rightValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mClient = new CClient();
        Thread myThready = new Thread(mClient);
        myThready.start();
        setUpProgressBars();
        sendData();
    }

    private void sendData() {

        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Log.i(TAG, "Sending : "
                        + String.valueOf(leftValue) + "  "
                        + String.valueOf(rightValue));

                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        mClient.Send("abcd:" + String.valueOf(leftValue)
                                + ":" + String.valueOf(rightValue) + ":5:");
                        return null;
                    }
                }.execute();
            }
        }, 5000, 50);
    }

    private void setUpProgressBars() {
        final VerticalSeekBar left = (VerticalSeekBar) findViewById(R.id.left);
        left.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean isFromUser) {
                //Log.i(TAG, "Left : " + i);
                leftValue = i - ZERO;
                //sendData();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Log.i(TAG, "onStopTrackingTouch LEFT");
                left.setProgress(ZERO);
                left.updateThumb();
                leftValue = 0;
                //sendData();
            }
        });

        final VerticalSeekBar right = (VerticalSeekBar) findViewById(R.id.right);
        right.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean isFromUser) {
                //Log.i(TAG, "Right : " + i);
                rightValue = i - ZERO;
                //sendData();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Log.i(TAG, "onStopTrackingTouch RIGHT");
                right.setProgress(ZERO);
                right.updateThumb();
                rightValue = 0;
                //sendData();
            }
        });
    }
}