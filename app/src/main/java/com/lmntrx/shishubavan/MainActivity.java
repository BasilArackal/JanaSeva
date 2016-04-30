package com.lmntrx.shishubavan;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }else location = null;

        if (location == null){
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
        if (location == null){
            location = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
        }
        /*DB_HANDLER = new MotherOfDatabases(this);
        if (UserPreferences.isThisFirstOpen(this)){
            DB_HANDLER.createTable(MotherOfDatabases.NAME_POLICE_TABLE);
            DB_HANDLER.createTable(MotherOfDatabases.NAME_AMBULANCE_TABLE);
            DB_HANDLER.createTable(MotherOfDatabases.NAME_FIRE_FORCE_TABLE);
            DB_HANDLER.addRowsTo(MotherOfDatabases.NAME_FIRE_FORCE_TABLE);
            DB_HANDLER.addRowsTo(MotherOfDatabases.NAME_POLICE_TABLE);
            DB_HANDLER.addRowsTo(MotherOfDatabases.NAME_AMBULANCE_TABLE);
        }*/
    }




    public void addButton(final View view) {



        /*  // Grid View Items
        //params.setMargins(left, top, right, bottom);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        CardView newCard = new CardView(this);
        ImageView dangerButton = new ImageView(this);
        dangerButton.setImageResource(R.drawable.danger);
        dangerButton.setLayoutParams(new ActionBar.LayoutParams(200, 200));
        dangerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCall(v);
            }
        });

        newCard.addView(dangerButton);

        gridLayout.addView(newCard);

        */

        // Declarations
        LinearLayout linearLayout=(LinearLayout) findViewById(R.id.LinearLayoutForAllCards);
        CardView newCard= new CardView(this);
        RelativeLayout cardDetails= new RelativeLayout(this);
        ImageView cardImage = new ImageView(this);
        TextView cardText = new TextView(this);
        TextView cardSubText = new TextView(this);

        // Convert DP to Pixels. LayoutParams Accept Pixels
        int dp100 = dpToPx(100,view);
        int dp16 = dpToPx(16,view);
        int dp10 = dpToPx(10,view);
        int dp7 = dpToPx(7,view);

        // Parameters for Card image
        RelativeLayout.LayoutParams imageParams = new RelativeLayout.LayoutParams(dp100,dp100);

        // Parameters for Main Text
        RelativeLayout.LayoutParams textParams1 = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        // Parameters for Sub Text
        RelativeLayout.LayoutParams textParams2 = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        // Parameters for Entire Card
        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        // Properties for Card Image
        imageParams.setMargins(0,0,dp16,0);
        cardImage.setLayoutParams(imageParams);
        cardImage.setImageResource(R.drawable.danger);
        cardImage.setId(R.id.id1);

        // Properties for Main Text
        textParams1.addRule(RelativeLayout.RIGHT_OF,R.id.id1);
        cardText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);      // Convert Pixels To SP
        cardText.setLayoutParams(textParams1);
        cardText.setText("New Card");
        cardText.setId(R.id.id2);

        // Properties for Sub Text
        textParams2.addRule(RelativeLayout.RIGHT_OF,R.id.id1);
        textParams2.addRule(RelativeLayout.BELOW,R.id.id2);
        cardSubText.setLayoutParams(textParams2);
        cardSubText.setText("Update Details");
        cardSubText.setId(R.id.id3);

        // Properties For Inner Relative Layout. Add Stuffs to Relative Layout here
        cardDetails.setPadding(dp16,dp16,dp16,dp16);
        cardDetails.addView(cardImage);
        cardDetails.addView(cardText);
        cardDetails.addView(cardSubText);

        // Properties For Card Layout.
        cardParams.setMargins(dp16,dp16,dp16,0);
        newCard.setLayoutParams(cardParams);
        newCard.setCardElevation(dp10);
        newCard.setRadius(dp7);
        newCard.setPadding(dp16,dp16,0,0);
        newCard.addView(cardDetails);

        // Properties For Outer Linear Layout. Add the created card here.
        linearLayout.addView(newCard);

        // onClick on new cards not implemented. Use Global ID arrays and assign new ids to each cardView . Limit number of
        // new cards created by x=10 (say). Initially create 10 new ids on menu/ids.xml. Pass that id to makeCall();
    }

    public int dpToPx(int dp,View view) {
        DisplayMetrics displayMetrics = view.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    public void makeCall(View view) {
        Boss.call(view.getId(),MainActivity.this,location);
    }
}
