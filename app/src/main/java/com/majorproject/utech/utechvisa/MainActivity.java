package com.majorproject.utech.utechvisa;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;



public class MainActivity extends ActionBarActivity implements ActionBar.TabListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            //Genius
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.getFragment(position);
            //return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();

            switch (position) {
                case 0:
                    return "Profile".toUpperCase(l);
                    //return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return "Challenges".toUpperCase(l);
                    //return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return "Notices".toUpperCase(l);
                case 3:
                    return "LeaderBoard".toUpperCase(l);

            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */

        //This is The Key
      public static Fragment getFragment(int i){

            switch (i) {
                case 0:
                    // The first section of the app is the most interesting -- it offers
                    // a launchpad into the other demonstrations in this example application.
                    return new Profile();
                case 1:
                    return new Challenges();
                case 2:
                    return new Notices();
                case 3:
                    return new Leaderboard();
                default:
                    // The other sections of the app are dummy placeholders.
                    PlaceholderFragment fragment = new PlaceholderFragment();
                    Bundle args = new Bundle();
                    args.putInt("Not Working", i);
                    fragment.setArguments(args);
                    return fragment;
            }

        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }


    public static class Challenges extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_challenges, container, false);

            return rootView;

        }

    }

    public static class Notices extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_notices, container, false);


            String result = null;
            InputStream is = null;

            try{
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://10.0.2.2:80/UTechVisa/login.php");


                HttpResponse response = httpClient.execute(httppost);
                HttpEntity entity = response.getEntity();
                is = entity.getContent();

                //Log.e("log_tag", "Connection Success" + e.toString());
            }
            catch (Exception e){
                Log.e("log_tage,", "Error in http connection" + e.toString());
                // Figure out how to reslove this
                //Toast.makeText(getActivity().getApplicationContext(),"Connection fail",Toast.LENGTH_SHORT).show();
            }

            try {

                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso - 8859 - 1"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine())!= null)
                {
                    sb.append(line + "\n");
                }

                is.close();

                result = sb.toString();
            }catch (Exception e){
                //check if this causes crashing
                //Toast.makeText(getActivity().getApplicationContext(), "Input reading fail", Toast.LENGTH_SHORT).show();
            }

            try{
                JSONArray jArray = new JSONArray(result);
                TextView message = (TextView) rootView.findViewById(R.id.messages);

                for ( int i = -1; i < jArray.length() - 1; i++)
                {
                    JSONObject json_data = jArray.getJSONObject(i);

                    message.setText(" "+ String.valueOf(json_data.getInt("ID"))+ " " + json_data.getString("message"+ "\n"));

                }


            }

            catch (JSONException e) {
                Log.e("log_tag", "Error parsing data" + e.toString());
                e.printStackTrace();
                Toast.makeText(getActivity().getApplicationContext(), "JsonArray fail", Toast.LENGTH_SHORT).show();
            }
            catch (NullPointerException e)
            {
                e.printStackTrace();
                Toast.makeText(getActivity().getApplicationContext(), "Did not Recieve Messages, NullPointer", Toast.LENGTH_SHORT).show();

            }

            return rootView;

        }

    }

    public static class Leaderboard extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_leader_board, container, false);

            return rootView;

        }

    }

    public static class Profile extends Fragment {

        ImageView profile_pic;
        RoundImage roundedImage;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

            profile_pic = (ImageView) rootView.findViewById(R.id.profile_pic);
            Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.profile);
            roundedImage = new RoundImage(bm);
            profile_pic.setImageDrawable(roundedImage);

            return rootView;

        }

    }

}
