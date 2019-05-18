package cybernet.lunchapp.local.lunchapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import static android.gesture.GestureOverlayView.ORIENTATION_VERTICAL;

public class getLunches extends updateUI {

    public getLunches(Activity activity){super(activity);}

    protected void onPostExecute(JSONArray result){
        final Activity activity = mWeakActivity.get();
        ScrollView scrollview = activity.findViewById(R.id.mainView);
        scrollview.removeAllViews();
        LinearLayout lunchesList = new LinearLayout(activity);
        lunchesList.setOrientation(LinearLayout.VERTICAL);
        LinearLayout acceptedLunches = new LinearLayout(activity);
        acceptedLunches.setOrientation(LinearLayout.VERTICAL);
        LinearLayout otherWaitingForUser = new LinearLayout(activity);
        otherWaitingForUser.setOrientation(LinearLayout.VERTICAL);
        LinearLayout userWaitingForOther = new LinearLayout(activity);
        userWaitingForOther.setOrientation(LinearLayout.VERTICAL);

        TextView sectionHeader1 = new TextView(activity);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        Resources r = activity.getResources();
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,10,r.getDisplayMetrics());
        params.setMargins(px,0,0,0);
        sectionHeader1.setLayoutParams(params);
        sectionHeader1.setPadding(0,20,0,20);
        sectionHeader1.setTextSize(20.f);
        Boolean list1Contains = false;
        Boolean list2Contains = false;
        Boolean list3Contains = false;

        TextView sectionHeader2 = new TextView(activity);
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params2.setMargins(px,0,0,0);
        sectionHeader2.setLayoutParams(params2);
        sectionHeader2.setPadding(0,20,0,20);
        sectionHeader2.setTextSize(20.f);

        TextView sectionHeader3 = new TextView(activity);
        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params3.setMargins(px,0,0,0);
        sectionHeader3.setLayoutParams(params3);
        sectionHeader3.setPadding(0,20,0,20);
        sectionHeader3.setTextSize(20.f);

        sectionHeader1.setText("kokkulepitud");
        acceptedLunches.addView(sectionHeader1);

        sectionHeader2.setText("ootavad vastust");
        otherWaitingForUser.addView(sectionHeader2);

        sectionHeader3.setText("teise ootel");
        userWaitingForOther.addView(sectionHeader3);

        final LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for(int i = 0; i < result.length(); i++) {
            final View lunch = inflater.inflate(R.layout.lunch, null);
            TextView activityHeader = activity.findViewById(R.id.header);
            activityHeader.setText("Kokkulepped");
            try {
                final JSONObject lunchData = result.getJSONObject(i);
                String status = lunchData.getString("status");
                String role = lunchData.getString("role");
                RelativeLayout background = lunch.findViewById(R.id.lunchBackground);
                TextView secPartyName = lunch.findViewById(R.id.lunch_name);
                secPartyName.setText(lunchData.getString("name"));
                TextView location = lunch.findViewById(R.id.location);
                location.setText(lunchData.getString("location"));
                TextView datetime = lunch.findViewById(R.id.datetime);
                datetime.setText(lunchData.getString("time"));
                lunch.setOnClickListener(
                        new View.OnClickListener(){
                            public void onClick(View view){
                                //ava popup
                                try{
                                    Log.d("inimese ID", lunchData.get("id").toString());
                                    Intent dialogIntent = new Intent(activity, DialogActivity.class);
                                    lunchData.put("username", username);
                                    lunchData.put("password", password);
                                    dialogIntent.putExtra("jsonobject", lunchData.toString());
                                    activity.startActivity(dialogIntent);
                                    /*
                                    View popup = inflater.inflate(R.layout.popup, (ViewGroup) activity.findViewById(R.id.popupparent));
                                    EditText aeg = popup.findViewById(R.id.popuptime);
                                    aeg.setText(lunchData.getString("time"));
                                    EditText koht = popup.findViewById(R.id.popuplocation);
                                    koht.setText(lunchData.getString("location"));
                                    final PopupWindow popupwindow = new PopupWindow(popup, 50, 50, true);
                                    popupwindow.showAtLocation(activity.findViewById(R.id.popupparent), Gravity.CENTER,0,0);
                                    popupwindow.update();
                                    */
                                }
                                catch(JSONException exc){
                                    Log.d("json klikk info", exc.getMessage());
                                }
                            }
                        }
                );
                if(status.equals("bothaccepted") && role.equals("invitee")){
                    acceptedLunches.addView(lunch);
                    list1Contains = true;
                }
                else if(status.equals("waitingforother") && role.equals("inviter")){
                    otherWaitingForUser.addView(lunch);
                    list2Contains = true;
                }
                else if(status.equals("waitingforother") && role.equals("invitee")){
                    userWaitingForOther.addView(lunch);
                    list3Contains = true;
                }
                //lunchesList.addView(lunch);
            }
            catch(JSONException exc){

            }
        }
        if(list1Contains) lunchesList.addView(acceptedLunches);
        if(list2Contains) lunchesList.addView(otherWaitingForUser);
        if(list3Contains) lunchesList.addView(userWaitingForOther);
        scrollview.addView(lunchesList);

    }
}
