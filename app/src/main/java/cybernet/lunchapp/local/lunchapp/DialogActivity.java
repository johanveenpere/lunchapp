package cybernet.lunchapp.local.lunchapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class DialogActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            JSONObject intentData = new JSONObject(getIntent().getExtras().getString("jsonobject"));
            String status = intentData.getString("status");
            String secPartyName = intentData.getString("name");
            String time = intentData.getString("time");
            String location = intentData.getString("location");
            String password = intentData.getString("password");
            String username = intentData.getString("username");
            String secPartyRole = intentData.getString("role");
            setContentView(R.layout.popup);

            LinearLayout background = findViewById(R.id.background);
            RelativeLayout titlebar = findViewById(R.id.titlebar);

            TextView secPartyNameText = findViewById(R.id.secPartyName);
            EditText locationText = findViewById(R.id.popuplocation);
            EditText timeText = findViewById(R.id.popuptime);

            secPartyNameText.setText(secPartyName);
            locationText.setText(location);
            timeText.setText(time);
            Button nupp1 = findViewById(R.id.popupbutton);

            if(secPartyRole.equals("invitee") && (status.equals("waitingforother") || status.equals("bothaccepted"))){
                //kasutaja saadetud kutse
                //saab ainult keelduda
                TextView pealkiri = findViewById(R.id.pealkiri);
                pealkiri.setText("tühista kutse");
                nupp1.setText("tühista");
                nupp1.setBackgroundResource(R.color.colorCancel);
                titlebar.setBackgroundResource(R.color.colorLunchInviteFromUser);
                background.setBackgroundResource(R.color.colorLunchInviteFromUserLight);
                disableEditText(locationText);
                disableEditText(timeText);
                nupp1.setOnClickListener(new AnswerListener(password, username, 2, intentData.getString("id"), null, null ));
            }
            else if(secPartyRole.equals("inviter") && status.equals("waitingforother")){
                //kasutajale saadetud kutse
                //saab vastu võtta ja keelduda
                TextView pealkiri = findViewById(R.id.pealkiri);
                pealkiri.setText("sind kutsub");
                disableEditText(locationText);
                disableEditText(timeText);
                nupp1.setText("nõustu");
                nupp1.setBackgroundResource(R.color.colorAccept);
                LinearLayout nupud = findViewById(R.id.nupud);
                Button keeldunupp = new Button(this);
                keeldunupp.setText("keeldu");
                keeldunupp.setBackgroundResource(R.color.colorCancel);
                nupud.addView(keeldunupp);
                titlebar.setBackgroundResource(R.color.colorLunchInviteToUser);
                background.setBackgroundResource(R.color.colorLunchInviteToUserLight);
                nupp1.setOnClickListener(new AnswerListener(password, username, 1, intentData.getString("id"), null, null ));
                keeldunupp.setOnClickListener(new AnswerListener(password, username, 2, intentData.getString("id"), null, null ));
            }
            else if(secPartyRole.equals("inviter") && status.equals("bothaccepted")){
                //kokkuleppitud lõuna
                //saab ainult tühistada
                TextView pealkiri = findViewById(R.id.pealkiri);
                pealkiri.setText("lõuna");
                nupp1.setText("tühista");
                nupp1.setBackgroundResource(R.color.colorCancel);
                disableEditText( (EditText) findViewById(R.id.popuplocation));
                disableEditText( (EditText) findViewById(R.id.popuptime));
                titlebar.setBackgroundResource(R.color.colorLunchArranged);
                background.setBackgroundResource(R.color.colorLunchArrangedLight);
                nupp1.setOnClickListener(new AnswerListener(password, username, 2, intentData.getString("id"), null, null ));
            }
            else if(status.equals("ignorestatus")){
                nupp1.setOnClickListener(new AnswerListener(password, username, 3, intentData.getString("id"), timeText, locationText));
            }
        }
        catch(JSONException exc){
            Log.d("json", exc.getMessage());
        }

    }


    private void disableEditText(EditText editText) {
        editText.setFocusable(false);
        editText.setEnabled(false);
        editText.setCursorVisible(false);
        editText.setKeyListener(null);
        editText.setBackgroundColor(Color.TRANSPARENT);
    }
}
