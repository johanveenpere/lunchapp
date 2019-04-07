package cybernet.lunchapp.local.lunchapp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONObject;

public class LogIn extends updateUI{

    public LogIn(Activity activity){
        super(activity);
    }

     protected void onPostExecute(JSONArray result){
        Activity activity = mWeakActivity.get();
        if(result.length() > 0){
            //
            Intent startMainActivity = new Intent(activity, MainActivity.class);
            startMainActivity.putExtra("username" , username);
            startMainActivity.putExtra("password", password);
            activity.startActivity(startMainActivity);
        }
     }
}
