package com.geniusmart.three;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.geniusmart.R;

public class AsyncTaskActivity extends Activity implements View.OnClickListener {

    public static final String TAG = "WWW";
    private TextView showTextView;
    MyAsyncTask myAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        findViewById(R.id.btn_cancle2).setOnClickListener(this);
        findViewById(R.id.btn_cancle1).setOnClickListener(this);
        findViewById(R.id.btn_execute).setOnClickListener(this);
        showTextView = (TextView) findViewById(R.id.tv_show);
//        myAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_execute:
                new Thread() {
                    public void run() {
                        myAsyncTask = new MyAsyncTask();
                        myAsyncTask.execute();
                    }
                }.start();

                break;
            case R.id.btn_cancle1:
                //参数为ture时，允许任务执行一半终止
                myAsyncTask.cancel(true);
                break;
            case R.id.btn_cancle2:
                //参数为false时，不允许任务执行一半终止
                myAsyncTask.cancel(false);
                break;
        }
    }

    class MyAsyncTask extends AsyncTask<String, Integer, String> {

        @Override
        protected void onCancelled() {//4个on开头，和1个do开头
            Log.e(TAG, "onCancelled");
            showTextView.setText("onCancelled");
        }

        @Override
        protected void onPostExecute(String s) {
            Log.e(TAG, "onPostExecute");
            showTextView.setText("onPostExecute");
        }

        @Override
        protected void onPreExecute() {
            Log.e(TAG, "onPreExecute");
            //showTextView.setText("onPreExecute");
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.e(TAG, "onProgressUpdate" + values[0]);
            showTextView.setText("onProgressUpdate" + values[0]);
        }

        @Override
        protected String doInBackground(String... params) {
            Log.e(TAG, "doInBackground--start");
            for (int i = 1; i <= 100000; i++) {
                if (i % 1000 == 0) {
                    publishProgress(i / 1000);
                }
            }
            Log.e(TAG, "doInBackground--end");
            return null;
        }
    }

}
