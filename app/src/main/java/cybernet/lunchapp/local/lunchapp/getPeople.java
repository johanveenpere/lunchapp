package cybernet.lunchapp.local.lunchapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class getPeople extends updateUI  {

    public getPeople(Activity activity){
        super(activity);
    }

    @Override
    protected void onPostExecute(JSONArray result) {
        Activity activity = mWeakActivity.get();
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ScrollView scrollview = activity.findViewById(R.id.mainView);
        LinearLayout userList = new LinearLayout(activity);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        userList.setOrientation(LinearLayout.VERTICAL);
        for(Integer i = 0; i < result.length(); i++) {
            View user = inflater.inflate(R.layout.person, null);
            try {
                Log.d("interesting person", i.toString());
                JSONObject interestingPerson = result.getJSONObject(i);
                TextView name = user.findViewById(R.id.name);
                name.setText(interestingPerson.getString("name"));
                TextView profession = user.findViewById(R.id.profession);
                profession.setText(interestingPerson.getString("job"));
                user.setTag(interestingPerson.getString("id"));
                userList.addView(user);
            }
            catch(JSONException exc){
                Log.e("JSON error", exc.getMessage());
            }

        }
        scrollview.addView(userList);
    }
}
