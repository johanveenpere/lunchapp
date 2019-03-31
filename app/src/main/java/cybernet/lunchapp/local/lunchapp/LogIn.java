package cybernet.lunchapp.local.lunchapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONObject;

public class LogIn extends AsyncTask<JSONObject, Void, JSONArray> {
    View activityView;
    @Override
    protected JSONArray doInBackground(JSONObject... userInfo) {
        ServerConnection login = new ServerConnection();
        return login.sendRequest(userInfo[0]);
    }

     protected void onPostExecute(JSONArray result){
        if(result.length() > 0){
            //
            activityView.getContext().startActivity(new Intent(activityView.getContext(), MainActivity.class));
        }
     }

     public void setActivityView(View view){
        activityView = view;
     }
}
