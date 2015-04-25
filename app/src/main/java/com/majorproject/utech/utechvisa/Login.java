package com.majorproject.utech.utechvisa;

//import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Login extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView eTextView = (TextView) findViewById(R.id.eTextView);
        Typeface vivaldi = Typeface.createFromAsset(getAssets(), "fonts/VIVALDII/VIVALDII.TTF");
        eTextView.setTypeface(vivaldi);

        EditText userName = (EditText) findViewById(R.id.UserName);
        Typeface myriad = Typeface.createFromAsset(getAssets(), "fonts/MyriadPro_Regular/MyriadPro_Regular.otf");
        userName.setTypeface(myriad);

        Button signIn = (Button) findViewById(R.id.SignIn);
        signIn.setOnClickListener(this);

    }

    public void onClick(View v) {

        int vid = v.getId();
        switch (vid){
            case  R.id.SignIn:
                //Toast.makeText(getApplicationContext(), "This Is Working", Toast.LENGTH_LONG).show();
                Intent i = new Intent(this, WelcomeScreen.class );
                startActivity(i);
                this.finish();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
