package com.kolibree.android.codingtest;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    private int counter;

    Handler handler;

    TextView counterTextView, timerTextView;
    ScrollView scrollView;

    final Counter counter1 = new Counter();
    private boolean isFinished = false;
    ArrayList<CounterThread> threads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterTextView = findViewById(R.id.counter);
        timerTextView = findViewById(R.id.timer);
        scrollView = findViewById(R.id.scrollView);

        findViewById(R.id.reset_button).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                counter = 0;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        handler = new Handler();

        threads = new ArrayList<>();
        for (int i = 0; i < ThreadLocalRandom.current().nextInt(10, 25); i++) {
            threads.add(new CounterThread());
        }

        for (CounterThread thread : threads) {
            thread.start();
        }

        new CountDownTimer(10000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(millisUntilFinished / 1000 + " seconds left");
            }

            @Override
            public void onFinish() {
                isFinished = true;

                for (CounterThread thread : threads) {
                    thread.interrupt();
                }
            }
        }
                .start();

    }

    private class CounterThread extends Thread {

        @Override
        public void run() {

            synchronized (counter1) {
                if (!isFinished) {
                    for (int i = 0; i < 10; i++) {
                        try {
                            Thread.sleep(ThreadLocalRandom.current().nextInt(50, 2000));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        counter = counter1.increment();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                counterTextView.append("Counter is " + counter + "\n");
                                scrollView.fullScroll(View.FOCUS_DOWN);
                            }
                        });
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            counterTextView.append(
                                    "\nTimer completed. Created " + threads.size() + " threads and counter is " + counter
                                            + "\n");
                            scrollView.fullScroll(View.FOCUS_DOWN);
                        }
                    });
                }
            }
        }
    }

    public static class Counter {
        private AtomicInteger value = new AtomicInteger();

        public int getValue() {
            return value.get();
        }

        public int increment() {
            return value.incrementAndGet();
        }
    }
}
