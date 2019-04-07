package cybernet.lunchapp.local.lunchapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

public class updateUI extends AsyncTask<JSONObject, Void, JSONArray>{
        WeakReference<Activity> mWeakActivity;
        Context vContext;
        String username;
        String password;

        public updateUI(Activity activity){
            mWeakActivity = new WeakReference<Activity>(activity);
            vContext = activity;
        }

        @Override
        protected JSONArray doInBackground(JSONObject... userInfo) {
            try {
                username = userInfo[0].getString("username");
                password = userInfo[0].getString("password");
            }
            catch(JSONException exc){
                Log.e("JSON error", exc.getMessage());
            }
            ServerConnection login = new ServerConnection();
            return login.sendRequest(userInfo[0]);
        }
}
