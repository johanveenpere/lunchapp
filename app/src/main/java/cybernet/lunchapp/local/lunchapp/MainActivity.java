package cybernet.lunchapp.local.lunchapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.app.ActionBar;
import android.graphics.drawable.ColorDrawable;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.Button;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.Socket;
import java.io.PrintStream;


public class MainActivity extends Activity {
    String username;
    String password;
    final Activity context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainlayout);
        username = getIntent().getExtras().getString("username");
        password = getIntent().getExtras().getString("password");
        getPeople getPeopleInitQuery = new getPeople(this);
        JSONObject initGetPeopleRequest = new JSONObject();
        try {
            initGetPeopleRequest.put("username", username);
            initGetPeopleRequest.put("password", password);
            initGetPeopleRequest.put("type", "2");
            getPeopleInitQuery.execute(initGetPeopleRequest);
        }
        catch(JSONException exc){
            Log.d("JSON error", exc.getMessage());
        }

        ImageButton showPersonList = findViewById(R.id.button4);
        showPersonList.setOnClickListener(
               new View.OnClickListener(){
                   public void onClick(View view){
                       getPeople getPeopleQuery = new getPeople(context);
                       JSONObject GetPeopleRequest = new JSONObject();
                       try {
                           GetPeopleRequest.put("username", username);
                           GetPeopleRequest.put("password", password);
                           GetPeopleRequest.put("type", "2");
                           getPeopleQuery.execute(GetPeopleRequest);
                       }
                       catch(JSONException exc){
                           Log.d("JSON error", exc.getMessage());
                       }
                   }
               }
        );

        ImageButton showLunches = findViewById(R.id.button5);
        showLunches.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view){
                        getLunches getLunchesQuery = new getLunches(context);
                        JSONObject ProfileRequest = new JSONObject();
                        try {
                            ProfileRequest.put("username", username);
                            ProfileRequest.put("password", password);
                            ProfileRequest.put("type", "3");
                            getLunchesQuery.execute(ProfileRequest);
                        }
                        catch(JSONException exc){
                            Log.d("JSON error", exc.getMessage());
                        }
                    }
                }
        );

        ImageButton showProfile = findViewById(R.id.button6);
        //ScrollView lounad = new ScrollView(this);
        //LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //params.setMargins(0,20,0,20);
        //Create a layout---------------
        //LinearLayout linearLayout = new LinearLayout(this);
        //linearLayout.setOrientation(LinearLayout.VERTICAL);
        //lounad.addView(linearLayout);

        //----Create a TextView------
        /*
        for (int i = 0; i < 10 ; i++) {
            LinearLayout.LayoutParams subparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            LinearLayout lunchOption = new LinearLayout(this);
            lunchOption.setLayoutParams(params);
            lunchOption.setOrientation(LinearLayout.VERTICAL);

            ColorDrawable lunchBackground = new ColorDrawable();
            lunchBackground.setColorFilter(0xFFfceccc,PorterDuff.Mode.SRC_OVER);
            lunchOption.setBackground(lunchBackground);

            TextView lunchLocation = new TextView(this);
            lunchLocation.setText("louna asukoht");
            lunchLocation.setLayoutParams(subparams);
            lunchOption.addView(lunchLocation);

            TextView lunchTime = new TextView(this);
            lunchTime.setText("louna aeg");
            lunchTime.setLayoutParams(subparams);
            lunchOption.addView(lunchTime);

            TextView lunchParticipants = new TextView(this);
            lunchParticipants.setText("liitunud isikud");
            lunchParticipants.setLayoutParams(subparams);
            lunchOption.addView(lunchParticipants);

            Button acceptButton = new Button(this);
            acceptButton.setText("noustu");
            acceptButton.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    new testserverconnection().execute();
                }
            });
            lunchOption.addView(acceptButton);

            linearLayout.addView(lunchOption);
        }
        */
        //LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ActionBar.LayoutParams.FILL_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        //this.setContentView(lounad);


    }

    @Override
    protected void onResume() {
        super.onResume();
        //getPeopleInitQuery = new getPeople(this);
    }

}
