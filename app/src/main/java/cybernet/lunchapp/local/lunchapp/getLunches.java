package cybernet.lunchapp.local.lunchapp;

import android.app.Activity;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import org.json.JSONArray;

public class getLunches extends updateUI {

    public getLunches(Activity activity){super(activity);}

    protected void onPostExecute(JSONArray result){
        Activity activity = mWeakActivity.get();
        ScrollView scrollview = activity.findViewById(R.id.mainView);
        scrollview.removeAllViews();
        LinearLayout lunchesList = new LinearLayout(activity);

    }
}
