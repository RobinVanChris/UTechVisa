package com.majorproject.utech.utechvisa;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
//import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class WelcomeScreen extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        TextView wTextView = (TextView) findViewById(R.id.wTextView);
        TextView eTextView = (TextView) findViewById(R.id.eTextView);
        Typeface vivaldi = Typeface.createFromAsset(getAssets(), "fonts/VIVALDII/VIVALDII.TTF");
        wTextView.setTypeface(vivaldi);
        eTextView.setTypeface(vivaldi);

        ImageView UnlockKey = (ImageView) findViewById(R.id.UnlockKey);
        UnlockKey.setOnClickListener(this);

    }

    public void onClick(View v) {

        int vid = v.getId();
        switch (vid){
            case  R.id.UnlockKey:
                //Toast.makeText(getApplicationContext(), "This Is Working", Toast.LENGTH_LONG).show();
                Intent i = new Intent(this, MainActivity.class );
                startActivity(i);
                this.finish();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_welcome_screen, menu);
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
