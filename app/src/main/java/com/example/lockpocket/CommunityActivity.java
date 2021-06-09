package com.example.lockpocket;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.lockpocket.account.LoginActivity;
import com.example.lockpocket.account.PreferenceManager;
import com.example.lockpocket.utils.LockScreen;


public class CommunityActivity extends AppCompatActivity {


    private DrawerLayout drawerLayout;
    private View drawerView;
    private Context mcontext;
    public TextView DrawerName, DrawerId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mcontext = this;
        setContentView(R.layout.activity_community);
        Button LoginBtn = (Button) findViewById(R.id.logoutBtn);
        Button QuestionBtn = (Button) findViewById(R.id.QuestionBtn);
        Button HelpBtn = (Button) findViewById(R.id.HelpBtn);
        DrawerName = findViewById(R.id.DrawerName);
        DrawerId = findViewById(R.id.DrawerId);
        DrawerName.setText(PreferenceManager.getString(mcontext, "userName"));
        DrawerId.setText(PreferenceManager.getString(mcontext, "Id"));
        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.logoutBtn:
                        PreferenceManager.clear(mcontext);
                        Intent ToLogin = new Intent(getApplicationContext(), LoginActivity.class);
                        ToLogin.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        LockScreen.getInstance().deactivate();
                        startActivity(ToLogin);
                        break ;
                    case R.id.HelpBtn:
                        Intent DrawerintentHelp = new Intent(getApplicationContext(), HelpActivity.class);
                        startActivity(DrawerintentHelp);
                        break ;
                    case R.id.QuestionBtn:
                        Intent DrawerintentQuestion = new Intent(getApplicationContext(), QuestionActivity.class);
                        startActivity(DrawerintentQuestion);
                        break ;
                }
            }
        };
        LoginBtn.setOnClickListener(onClickListener);
        QuestionBtn.setOnClickListener(onClickListener);
        HelpBtn.setOnClickListener(onClickListener);
        ImageButton.OnClickListener onClickListener2 = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.ToCommunitybtn:
                        Intent CommunityintentCommunity = new Intent(getApplicationContext(), CommunityActivity.class);
                        startActivity(CommunityintentCommunity);
                        break ;
                    case R.id.ToHomebtn:
                        Intent CommunityintentToEdit = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(CommunityintentToEdit);
                        break ;
                    case R.id.ToEditbtn:
                        Intent CommunityintentHelp = new Intent(getApplicationContext(), EditActivity.class);
                        startActivity(CommunityintentHelp);
                        break ;
                    case R.id.MainToMenubtn:
                        drawerLayout.openDrawer(drawerView);
                        break ;
                }
            }
        };
        ImageButton CommunityToMenuButton = (ImageButton) findViewById(R.id.MainToMenubtn);
        ImageButton CommunityToCommunityButton = (ImageButton) findViewById(R.id.ToCommunitybtn);
        ImageButton CommunityToEditButton = (ImageButton) findViewById(R.id.ToHomebtn);
        ImageButton CommunityToHelpButton = (ImageButton) findViewById(R.id.ToEditbtn);
        CommunityToCommunityButton.setOnClickListener(onClickListener2);
        CommunityToEditButton.setOnClickListener(onClickListener2);
        CommunityToHelpButton.setOnClickListener(onClickListener2);
        CommunityToMenuButton.setOnClickListener(onClickListener2);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_Commu);
        drawerView = (View) findViewById(R.id.drawerView);
    }
}