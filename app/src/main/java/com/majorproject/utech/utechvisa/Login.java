package com.majorproject.utech.utechvisa;

//import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

public class Login extends Activity implements View.OnClickListener{

    private EditText userName, Password;
    private Button SignIn;

    private ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();

    private static final String LOGIN_URL = "http://10.0.2.2:80/UTechVisa/login.php";

    //JSON element ids from repsonse of php script:
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = (EditText)findViewById(R.id.UserName);
        Password = (EditText)findViewById(R.id.Password);

        TextView eTextView = (TextView) findViewById(R.id.eTextView);
        Typeface vivaldi = Typeface.createFromAsset(getAssets(), "fonts/VIVALDII/VIVALDII.TTF");
        eTextView.setTypeface(vivaldi);

        userName = (EditText) findViewById(R.id.UserName);
        Typeface myriad = Typeface.createFromAsset(getAssets(), "fonts/MyriadPro_Regular/MyriadPro_Regular.otf");
        userName.setTypeface(myriad);

        SignIn = (Button) findViewById(R.id.SignIn);
        SignIn.setOnClickListener(this);

    }

    public void onClick(View v) {

        int vid = v.getId();
        switch (vid){
            case  R.id.SignIn:
                //Toast.makeText(getApplicationContext(), "This Is Working", Toast.LENGTH_LONG).show();
                new AttemptLogin().execute();
                /*Intent i = new Intent(this, WelcomeScreen.class );
                startActivity(i);
                this.finish();*/
        }

    }
    class AttemptLogin extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        boolean failure = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Login.this);
            pDialog.setMessage("Attempting login...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
            // TODO Auto-generated method stub
            // Check for success tag
            int success;
            String username = userName.getText().toString();
            String password = Password.getText().toString();
            try {
                // Building Parameters
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("ID", username));
                     params.add(new BasicNameValuePair("password", password));

                    Log.d("request!", "starting");
                    // getting product details by making HTTP request
                    JSONObject json = jsonParser.makeHttpRequest(LOGIN_URL, "POST", params);

                    // check your log for json response
                    Log.d("Login attempt", json.toString());

                // json success tag
                    success = json.getInt(TAG_SUCCESS);
                    if (success == 1) {
                        Log.d("Login Successful!", json.toString());
                        Intent i = new Intent(Login.this, WelcomeScreen.class);
                        finish();
                        startActivity(i);

                        return json.getString(TAG_MESSAGE);
                    }
                else
                {
                    Log.d("Login Failure!", json.getString(TAG_MESSAGE));
                    return json.getString(TAG_MESSAGE);

                }
            }

            catch (JSONException e)
            {
                e.printStackTrace();
            }

            return null;

        }
        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted
            pDialog.dismiss();
            if (file_url != null){
                Toast.makeText(Login.this, file_url, Toast.LENGTH_LONG).show();
            }

        }


    }

}
