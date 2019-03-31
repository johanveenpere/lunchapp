package cybernet.lunchapp.local.lunchapp;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

public class LogIn extends AsyncTask<JSONObject, Void, JSONArray> {

    @Override
    protected JSONArray doInBackground(JSONObject... userInfo) {
        ServerConnection login = new ServerConnection();
        return login.sendRequest(userInfo[0]);
    }

     protected void onPostExecute(JSONArray result){

     }
}
