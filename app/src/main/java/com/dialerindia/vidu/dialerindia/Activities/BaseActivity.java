package com.dialerindia.vidu.dialerindia.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dialerindia.vidu.dialerindia.Constants.Constants;
import com.dialerindia.vidu.dialerindia.R;

public class BaseActivity extends AppCompatActivity {

    View alertLayout;
    ProgressDialog progress;
    Constants constants = new Constants();

    public void Toast(String Message) {
        Toast.makeText(getApplicationContext(), Message, Toast.LENGTH_LONG).show();
    }

    public void setBackground(Context context) {
        ImageView img = findViewById(R.id.background);
        Glide.with(context).load(R.drawable.back2).into(img);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void showLoadingDialog(Context context, String Message) {
        progress = new ProgressDialog(this, ProgressDialog.THEME_DEVICE_DEFAULT_LIGHT);
        progress.setMessage("Please Wait....");
        progress.setTitle(Message);
        progress.setCancelable(false);
        progress.show();

    }

    public void showLoadingDialogOnUIThread(final Context context, final String Message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showLoadingDialog(context, Message);
            }
        });

    }

    public void hideLoadingDialog() {
        if (progress != null) {
            progress.dismiss();
            progress = null;
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
//        if (progress != null) {
//            hideLoadingDialog();
//        }
    }

}
