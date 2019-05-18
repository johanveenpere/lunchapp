package cybernet.lunchapp.local.lunchapp;

import android.os.AsyncTask;

import org.json.JSONObject;

public class SendAnswer extends AsyncTask<JSONObject, Void, String> {
    @Override
    protected String doInBackground(JSONObject... jsonObjects) {
        JSONObject answer = jsonObjects[0];
        ServerConnection conn = new ServerConnection();
        conn.sendRequest(answer);
        return "lollus";
    }
}
