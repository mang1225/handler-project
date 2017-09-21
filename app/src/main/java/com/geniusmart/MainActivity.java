package com.geniusmart;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.geniusmart.future.FutureActivity;
import com.geniusmart.one.HandlerActivity;
import com.geniusmart.three.AsyncTaskActivity;
import com.geniusmart.two.HandlerThreadActivity;
import com.geniusmart.two.HandlerThreadActivity2;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void startHandler(View view) {
    startActivity(new Intent(this, HandlerActivity.class));
  }

  public void startHandlerThread(View view) {
    startActivity(new Intent(this, HandlerThreadActivity.class));
  }

  public void startThreadCommunication(View view) {
    startActivity(new Intent(this, HandlerThreadActivity2.class));
  }

  public void startAsyncTask(View view) {
    startActivity(new Intent(this, AsyncTaskActivity.class));
  }

  public void startFuture(View view) {
    startActivity(new Intent(this, FutureActivity.class));
  }
}
