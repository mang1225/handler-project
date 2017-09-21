package com.geniusmart.other;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.geniusmart.R;

/**
 * test handler
 * 09-06
 */
public class TestHandlerActivity extends Activity implements OnClickListener {

  public Button handlerThreadBTN;
  MyHandlerThread handlerThread;
  Handler handler;

  /**
   * Called when the activity is first created.
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_handler);
    //打印UI线程的名称
    Log.d("WWW", "onCreate  CurrentThread -->" + Thread.currentThread().getName());

    handlerThreadBTN = (Button) findViewById(R.id.handlerThreadBtn);
    handlerThreadBTN.setOnClickListener(this);

    handlerThread = new MyHandlerThread("myHanler");
    handlerThread.start();
    //注意： 这里必须用到handler的这个构造器，因为需要把callback传进去，从而使自己的HandlerThread的handlerMessage来替换掉Handler原生的handlerThread
    handler = new Handler(handlerThread.getLooper(), handlerThread);
  }

  @Override
  public void onClick(View v) {
    //点击按钮后来开启线程
    handler.sendEmptyMessage(1);
  }

  private class MyHandlerThread extends HandlerThread implements Callback {

    public MyHandlerThread(String name) {
      super(name);
    }

    @Override
    public boolean handleMessage(Message msg) {
      Log.d("WWW", "msg.what-->" + msg.what);
      //打印线程的名称
      Log.d("WWW", "CurrentThread -->" + Thread.currentThread().getName());
      return true;
    }
  }
}