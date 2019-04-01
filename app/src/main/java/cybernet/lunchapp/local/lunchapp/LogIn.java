package cybernet.lunchapp.local.lunchapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONObject;

public class LogIn extends updateUI{

     protected void onPostExecute(JSONArray result){
        if(result.length() > 0){
            //
            activityView.getContext().startActivity(new Intent(activityView.getContext(), MainActivity.class));
        }
     }
}
