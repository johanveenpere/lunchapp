package cybernet.lunchapp.local.lunchapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.widget.Button;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.support.design.widget.TextInputEditText;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final Activity context = this;

        Button loginbutton = findViewById(R.id.email_sign_in_button);
        loginbutton.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view){
                        TextInputEditText username = findViewById(R.id.username);
                        TextInputEditText password = findViewById(R.id.password);
                        JSONObject loginData = new JSONObject();
                        try {
                            loginData.put("username", username.getText().toString());
                            loginData.put("password", password.getText().toString());
                            loginData.put("type", "2");
                        }
                        catch(org.json.JSONException exc) {
                            Log.e("JSON error", exc.getMessage());
                        }
                        //ServerConnection test = new ServerConnection();
                        //test.sendRequest(loginData);
                        LogIn loginquery = new LogIn(context);
                        loginquery.execute(loginData);

                    }
                }

        );

    }
}

