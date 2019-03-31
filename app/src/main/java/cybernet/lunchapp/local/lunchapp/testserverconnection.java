package cybernet.lunchapp.local.lunchapp;

import android.os.AsyncTask;
import android.util.Log;

import java.io.PrintStream;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.net.Socket;
import org.json.JSONArray;
import org.json.JSONObject;

import android.view.View;
import android.widget.ScrollView;


public class testserverconnection extends AsyncTask<String, Void, String> {

    private JSONObject query;

    protected String doInBackground(String... args) {

        return null;
    }

    protected void onPostExcecute(String result){


    }

    public void logIn(String username, String password){
        query = new JSONObject();
        Log.d("logIn", username + ";" + password);
        try {
            query.put("username", username);
            query.put("password", password);
            query.put("type", "2");
        }
        catch(org.json.JSONException exc) {

        }
    }



}
