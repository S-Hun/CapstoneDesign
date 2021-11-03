package com.example.lockpocket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.lockpocket.lockmethod.UnlockBar;
import com.example.lockpocket.utils.BitmapConverter;
import com.example.lockpocket.utils.GridLock46;
import com.example.lockpocket.utils.LockTable;
import com.example.lockpocket.utils.PreferenceManager;
import com.example.lockpocket.utils.TableFloater;

import java.util.Date;

public class LockScreenActivity extends AppCompatActivity {
    private static View[] pos;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    LinearLayout layout;
    ImageView IV_background;
    String UI;

    LockTable lockTableObject;
    ViewGroup lockTableLayout;

    private int currentApiVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lockscreen);
        ScreenSetup();

        layout = findViewById(R.id.activity_lockscreen);
        UnlockBar unlock = (UnlockBar)findViewById(R.id.unlock);
        UI = PreferenceManager.getString(getApplicationContext(), "edit_lockscreen");

        CreateScreen();

        // Attach listener
        unlock.setOnUnlockListenerRight(new UnlockBar.OnUnlockListener() {
            @Override
            public void onUnlock()
            {
                finish();
            }
        });


        unlock.setOnUnlockListenerLeft(new UnlockBar.OnUnlockListener() {
            @Override
            public void onUnlock()
            {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
    }

    void CreateScreen() {
        RelativeLayout vg = new RelativeLayout(getApplicationContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        params.weight = 6;
        params.setMargins(0, 100, 10, 0);
        vg.setLayoutParams(params);
        layout.addView(vg, 0);
        lockTableLayout = vg;

        String backgroundBitmapString = PreferenceManager.getString(getApplicationContext(), "edit_background");
        if(!backgroundBitmapString.equals("")) {
            Bitmap bitmap = BitmapConverter.StringToBitmap(backgroundBitmapString);
            layout.setBackground(new BitmapDrawable(getResources(), bitmap));
        }
        lockTableLayout.post(new Runnable() {
            @Override
            public void run() {
                lockTableSetup();
            }
        });
    }

    void lockTableSetup() {
        String template = PreferenceManager.getString(getApplicationContext(), "edit_lockscreen");
        String[] li = template.split("/");
        if(!template.equals("")) {
            int height = lockTableLayout.getHeight();
            if(li[0].equals("grid46")){
                lockTableObject = new GridLock46(getApplicationContext(), lockTableLayout);
                Point p = new Point(height/6, height/6);
                lockTableObject.stringToTable(template, new Size(p.x, p.y));
            }
        }
    }

    void ScreenSetup() {
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }
}
