package com.aavidsoft.multithreading2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btnFetchFriends;
    private TextView txtFriendNames;

    private ArrayList<String> friendNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFetchFriends = findViewById(R.id.btnFetchFriends);
        txtFriendNames = findViewById(R.id.txtFriendNames);

        btnFetchFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DownloadThread(
                        MainActivity.this,
                        new MyFBHandler()
                )
                        .execute("vishal");
            }
        });
    }

    public class MyFBHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            //if(msg.obj instanceof ArrayList) {
            if(msg.arg1 == 2) {
                friendNames = (ArrayList<String>) msg.obj;
                for (String name : friendNames) {
                    txtFriendNames.append(
                            name + " --  "
                    );
                }
            }

            //if (msg.obj instanceof Integer[]) {
            if(msg.arg1 == 1) {
                btnFetchFriends.setText( ((Integer[])msg.obj)[0] + " % ");
            }
        }
    }
}