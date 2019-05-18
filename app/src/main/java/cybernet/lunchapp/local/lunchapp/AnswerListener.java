package cybernet.lunchapp.local.lunchapp;

import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

public class AnswerListener implements View.OnClickListener {

    private String password;
    private String username;
    private Integer id;
    private int type;
    private View timeview;
    private View locationview;

    public AnswerListener(String passwordIn, String usernameIn, int typeIn, String idIn, View timeviewIn, View locationviewIn){
        try {
            id = Integer.parseInt(idIn.trim());
        }
        catch(NumberFormatException nfe){
            Log.d("parseint", nfe.getMessage());
        }
        type = typeIn;
        password = passwordIn;
        username = usernameIn;
        timeview = timeviewIn;
        locationview = locationviewIn;
    }

    @Override
    public void onClick(View view){
        try{
            JSONObject vastus = new JSONObject();
            vastus.put("id", id);
            vastus.put("username", username);
            vastus.put("password", password);
            vastus.put("type", "5");
            if(type == 1) {
                vastus.put("response", "accept");
            }
            else if(type == 2){
                vastus.put("response", "decline");
            }
            else if(type == 3){
                vastus.put("response", "invite");
                vastus.put("location", ((EditText) locationview).getText().toString());
                vastus.put("time", ((EditText) timeview).getText().toString());
            }
            SendAnswer sender = new SendAnswer();
            sender.execute(vastus);
        }
        catch(JSONException exc){

        }
    }
}
