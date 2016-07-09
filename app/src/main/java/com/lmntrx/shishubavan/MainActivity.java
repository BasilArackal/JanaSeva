package com.lmntrx.shishubavan;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    Location location;
    LocationManager locationManager;

    View v = null;

    public static Boolean pinVerified = false;

    View cardJanaseva,
            cardStrayDogs,
            cardPolice,
            cardAmbulance,
            cardFireForce,
            cardSexualAssault,
            cardChildAbuse,
            cardCustomNumber,
            cardVigilance,
            cardExcise,
            cardBloodBanks,
            cardHighwayPolice,
            cardRailwayPolice;

    public static TextView customCallTXT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (UserPreferences.isThisFirstOpenAfterUpdate_2_4(this)){
            UserPreferences.clearAllPrefs(this);
            MotherOfDatabases.deleteOldDB();
            MotherOfDatabases.populateDB(this);
            UserPreferences.updateDBVersion(this,getResources().getInteger(R.integer.DATABASE_VERSION));
            UserPreferences.writeUserChoice(R.id.callOnlyRB,this);
        }

        if (UserPreferences.isThisFirstOpen(MainActivity.this)){
            UserPreferences.writeUserChoice(R.id.callOnlyRB,this);
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
        cardChildAbuse = findViewById(R.id.card_childAbuse);
        cardFireForce = findViewById(R.id.card_firetruck);
        cardJanaseva = findViewById(R.id.card_shishubavan);
        cardPolice = findViewById(R.id.card_police);
        cardSexualAssault = findViewById(R.id.card_sexualAbuse);
        cardStrayDogs = findViewById(R.id.card_animalAbuse);
        cardCustomNumber = findViewById(R.id.card_custom);
        cardVigilance = findViewById(R.id.card_vigilance);
        cardExcise = findViewById(R.id.card_excise);
        cardBloodBanks = findViewById(R.id.card_blood_bank);
        cardHighwayPolice = findViewById(R.id.card_highwaypolice);
        cardRailwayPolice = findViewById(R.id.card_railwaypolice);
        customCallTXT = (TextView)findViewById(R.id.custom_card_subtext);

        if (!UserPreferences.getCustomNumber(this).equals("0"))
            customCallTXT.setText(String.format("Call: %s\nPress & hold to edit number.", UserPreferences.getCustomNumber(this)));

        //Assigning onLongPress event listeners to each of the cards
        cardStrayDogs.setOnLongClickListener(new onLongPress());
        cardSexualAssault.setOnLongClickListener(new onLongPress());
        cardPolice.setOnLongClickListener(new onLongPress());
        cardAmbulance.setOnLongClickListener(new onLongPress());
        cardFireForce.setOnLongClickListener(new onLongPress());
        cardChildAbuse.setOnLongClickListener(new onLongPress());
        cardJanaseva.setOnLongClickListener(new onLongPress());
        cardBloodBanks.setOnLongClickListener(new onLongPress());
        cardVigilance.setOnLongClickListener(new onLongPress());
        cardExcise.setOnLongClickListener(new onLongPress());
        cardHighwayPolice.setOnLongClickListener(new onLongPress());
        cardRailwayPolice.setOnLongClickListener(new onLongPress());
        cardCustomNumber.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                /*android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(MainActivity.this, R.style.AppCompatAlertDialogStyle);
                builder.setTitle("Enter a number");
                final EditText input = new EditText(MainActivity.this);
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    input.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                }
                input.setMaxLines(1);
                input.setHint("Phone Number");
                if (!UserPreferences.getCustomNumber(MainActivity.this).equals("0"))
                    input.setText(UserPreferences.getCustomNumber(MainActivity.this));
                builder.setView(input);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        chosenCustomNumber = input.getText().toString();
                        if (!chosenCustomNumber.isEmpty() && !chosenCustomNumber.equals("") && chosenCustomNumber.length()>=10){
                            UserPreferences.saveCustomNumber(MainActivity.this,chosenCustomNumber);
                            customCallTXT.setText(String.format("Call: %s\nPress and hold to edit number.", chosenCustomNumber));
                        }else {
                            Toast.makeText(MainActivity.this,"Please enter a valid number",Toast.LENGTH_SHORT).show();
                            input.setText("");
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();*/
                startActivity(new Intent(MainActivity.this,CustomNumbersSettings.class));
                return false;
            }
        });



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
    public void onBackPressed() {
        super.onBackPressed();
        pinVerified = false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
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
            }
        }
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
        if (UserPreferences.isWarningOn(this)){
            new android.app.AlertDialog.Builder(MainActivity.this)
                    .setTitle("Warning")
                    .setMessage("Your action sends your number and location to authorities. Prank calls are punishable!\nDo you still want to make an alert?\nNB: This warning can be turned off in settings")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Boss.customCall(MainActivity.this,MainActivity.this,location);
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }else
            Boss.customCall(this,this,location);
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
