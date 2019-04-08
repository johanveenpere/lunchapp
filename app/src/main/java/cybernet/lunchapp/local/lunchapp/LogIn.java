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
        if(result != null) {
            Activity activity = mWeakActivity.get();
                //
                Intent startMainActivity = new Intent(activity, MainActivity.class);
                startMainActivity.putExtra("username", username);
                startMainActivity.putExtra("password", password);
                activity.startActivity(startMainActivity);
        }
     }
}
