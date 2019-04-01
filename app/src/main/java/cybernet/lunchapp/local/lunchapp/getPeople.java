package cybernet.lunchapp.local.lunchapp;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONObject;

public class getPeople extends updateUI  {
    @Override
    protected void onPostExecute(JSONArray result) {
        LayoutInflater inflater = (LayoutInflater) activityView.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View user = inflater.inflate(R.layout.person,null);
        for(int i = 0; i < result.length(); i++){
            JSONObject interestingPerson = result.getJSONObject(i);
            user.findViewById(R.id.textView)
        }
        user.findViewById
        LinearLayout userList;
        userList.addView(user);

    }
}
