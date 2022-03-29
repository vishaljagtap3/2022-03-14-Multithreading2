package com.aavidsoft.multithreading2;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;

public class DownloadThread extends AsyncTask<String, Integer, ArrayList<String>> {

    private ProgressDialog progressDialog;
    private Context context;
    private Handler handler;

    public DownloadThread(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Fetching friends info..");
        progressDialog.show();
    }

    @Override
    protected ArrayList<String> doInBackground(String... username) {

        ArrayList<String> friendNames = new ArrayList<>();

        for(int i = 0; i <= 10; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            friendNames.add("XYZ " + i);
            publishProgress(i * 10);
        }
        return friendNames;
    }

    @Override
    protected void onProgressUpdate(Integer... value) {
        super.onProgressUpdate(value);

        Message message = new Message();
        message.arg1 = 1;
        message.obj = value;

        handler.sendMessage(message);
        //handler.handleMessage(message);
    }

    @Override
    protected void onPostExecute(ArrayList<String> friendNames) {
        super.onPostExecute(friendNames);
        progressDialog.dismiss();

        Message message = new Message();
        message.arg1 = 2;
        message.obj = friendNames;

        handler.sendMessage(message);
        //handler.handleMessage(message);
    }
}
