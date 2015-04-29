package com.majorproject.utech.utechvisa;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

/**
 * Created by Christophe on 4/28/2015.
 */
public class ReturnBit extends Activity {

    ImageView profile_pic;
    RoundImage roundedImage;

    public Bitmap round()
    {
        profile_pic = (ImageView) findViewById(R.id.profile_pic);
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.batman_arkham);
        roundedImage = new RoundImage(bm);
        profile_pic.setImageDrawable(roundedImage);

        return bm;

    }

}