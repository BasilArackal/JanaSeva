package com.lmntrx.shishubavan;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.lmntrx.shishubavan.DatabaseModels.PoliceTable;

public class MainActivity extends Activity {

    Location location;
    LocationManager locationManager;

    View v = null;

    public static Boolean pinVerified = false;

    View cardJanaseva, cardStrayDogs, cardPolice, cardAmbulance, cardFireForce, cardSexualAssault, cardCildAbuse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (UserPreferences.isThisFirstOpenAfterUpdate1_5_3(this))
            UserPreferences.clearAllPrefs(this);

        if (UserPreferences.isThisFirstOpen(MainActivity.this)){
            UserPreferences.writeUserChoice(R.id.callOnlyRB,this);
        }

        if (UserPreferences.getCurrentDBVersion(this)!=this.getResources().getInteger(R.integer.DATABASE_VERSION)){
            MotherOfDatabases.deleteOldDB();
            MotherOfDatabases.populateDB(this);
            UserPreferences.updateDBVersion(this,getResources().getInteger(R.integer.DATABASE_VERSION));
        }

        //Getting User Location
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location == null) {
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }
            if (location == null) {
                location = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
            }
        } else {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, Boss.PERMISSIONS_REQUEST_LOCATION_ACCESS);
        }

        //Initializing card variables
        cardAmbulance = findViewById(R.id.card_ambulance);
        cardCildAbuse = findViewById(R.id.card_childAbuse);
        cardFireForce = findViewById(R.id.card_firetruck);
        cardJanaseva = findViewById(R.id.card_shishubavan);
        cardPolice = findViewById(R.id.card_police);
        cardSexualAssault = findViewById(R.id.card_sexualAbuse);
        cardStrayDogs = findViewById(R.id.card_animalAbuse);

        //Assigning onLongPress event listeners to each of the cards
        cardStrayDogs.setOnLongClickListener(new onLongPress());
        cardSexualAssault.setOnLongClickListener(new onLongPress());
        cardPolice.setOnLongClickListener(new onLongPress());
        cardAmbulance.setOnLongClickListener(new onLongPress());
        cardFireForce.setOnLongClickListener(new onLongPress());
        cardCildAbuse.setOnLongClickListener(new onLongPress());
        cardJanaseva.setOnLongClickListener(new onLongPress());
    }

    @Override
    protected void onResume() {
        if (UserPreferences.isChildLockEnabled(MainActivity.this) && !pinVerified){
            startActivity(new Intent(MainActivity.this,Pin.class));
            pinVerified = true;
            MainActivity.this.finish();
        }

        super.onResume();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case Boss.PERMISSIONS_REQUEST_CALL_PHONE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    makeCall(v);
                } else {
                    Toast.makeText(MainActivity.this, "Permission to make a phone call was denied", Toast.LENGTH_LONG).show();
                }
                return;
            }
            case Boss.PERMISSIONS_REQUEST_LOCATION_ACCESS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if (location == null) {
                            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        }
                        if (location == null) {
                            location = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
                        }

                    }

                } else {

                    Toast.makeText(MainActivity.this, "Permission to locate you was denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
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
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public void makeCall(View view) {
        v=view;
        Boss.call(view.getId(),MainActivity.this,location);
    }

    public void launchSettings(View view)
    {
        Intent i=new Intent(getApplicationContext(),Settings.class);
        startActivity(i);
    }

    public void launchAbout(View view)
    {
        Intent i=new Intent(getApplicationContext(),About.class);
        startActivity(i);
    }

    public void makeCustomCall(View view) {
        Boss.customCall(this);
    }

    private class onLongPress implements View.OnLongClickListener {

        @Override
        public boolean onLongClick(View v) {
            Intent intent = new Intent(MainActivity.this,SelectNumbersActivity.class);
            intent.putExtra("LONG_PRESSED_VIEW_ID",v.getId());
            MainActivity.this.startActivity(intent);
            return false;
        }
    }

}
