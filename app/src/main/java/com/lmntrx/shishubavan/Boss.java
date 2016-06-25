package com.lmntrx.shishubavan;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.telephony.gsm.SmsManager;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;

/***
 * Created by livin on 28-Apr-16.
 */
public class Boss {

    static String LogTag = "Janaseva->" + Boss.class.getSimpleName();


    public final static String TYPE_POLICE = "TYPE_POLICE";
    public final static String TYPE_FIRETRUCK = "TYPE_FIRETRUCK";
    public final static String TYPE_AMBULANCE = "TYPE_AMBULANCE";
    public final static String TYPE_CHILD_ABUSE = "TYPE_CHILD_ABUSE";
    public final static String TYPE_STRAY_DOGS = "TYPE_STRAY_DOGS";
    public final static String TYPE_SEXUAL_ASSAULT = "TYPE_SEXUAL_ASSAULT";
    public static final String TYPE_SHISHUBAVAN = "TYPE_SHISHUBAVAN";
    public final static String TYPE_EXCISE = "TYPE_EXCISE";
    public static final String TYPE_CUSTOMS = "TYPE_CUSTOMS";
    public static final String TYPE_BLOOD_BANKS = "TYPE_BLOOD_BANKS";
    public static final int PERMISSIONS_REQUEST_CALL_PHONE = 51;
    public static final int PERMISSIONS_REQUEST_LOCATION_ACCESS = 52;
    public static final int PERMISSIONS_REQUEST_SEND_SMS = 53;

    private static String chosenCustomNumber;


    public static void call_phone(String phoneNo[], Context ctx, Activity activity) {
        if (UserPreferences.readUserChoice(ctx) == R.id.callAndSmsRB || UserPreferences.readUserChoice(ctx) == R.id.callOnlyRB) {
            Log.i("Status", "call made");
            Uri number = Uri.parse("tel:" + phoneNo[0]);
            Intent callIntent = new Intent(Intent.ACTION_CALL, number);
            if (ActivityCompat.checkSelfPermission(ctx, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                activity.startActivity(callIntent);
            } else {
                Log.e(LogTag, "No Permission");
                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.CALL_PHONE}, PERMISSIONS_REQUEST_CALL_PHONE);
            }
        }

    }

    public static void call(final int id, final Activity activity, final Location location) {

        if (UserPreferences.isWarningOn(activity))
            warnUser(id, activity, location);
        else {
            switch (id) {

                case R.id.card_ambulance:
                    call_phone(MotherOfDatabases.getEnabledNumbers(Boss.TYPE_AMBULANCE), Application.getContext(), activity);
                    sendTextMessageIfPossible(MotherOfDatabases.getEnabledNumbers(Boss.TYPE_AMBULANCE), location, Boss.TYPE_AMBULANCE, activity);
                    break;
                case R.id.card_animalAbuse:
                    call_phone(MotherOfDatabases.getEnabledNumbers(Boss.TYPE_STRAY_DOGS), Application.getContext(), activity);
                    sendTextMessageIfPossible(MotherOfDatabases.getEnabledNumbers(Boss.TYPE_STRAY_DOGS), location, Boss.TYPE_STRAY_DOGS, activity);
                    break;
                case R.id.card_childAbuse:
                    call_phone(MotherOfDatabases.getEnabledNumbers(Boss.TYPE_CHILD_ABUSE), Application.getContext(), activity);
                    sendTextMessageIfPossible(MotherOfDatabases.getEnabledNumbers(Boss.TYPE_CHILD_ABUSE), location, Boss.TYPE_CHILD_ABUSE, activity);
                    break;
                case R.id.card_police:
                    call_phone(MotherOfDatabases.getEnabledNumbers(Boss.TYPE_POLICE), Application.getContext(), activity);
                    sendTextMessageIfPossible(MotherOfDatabases.getEnabledNumbers(Boss.TYPE_POLICE), location, Boss.TYPE_POLICE, activity);
                    break;
                case R.id.card_firetruck:
                    call_phone(MotherOfDatabases.getEnabledNumbers(Boss.TYPE_FIRETRUCK), Application.getContext(), activity);
                    sendTextMessageIfPossible(MotherOfDatabases.getEnabledNumbers(Boss.TYPE_FIRETRUCK), location, Boss.TYPE_FIRETRUCK, activity);
                    break;
                case R.id.card_sexualAbuse:
                    call_phone(MotherOfDatabases.getEnabledNumbers(Boss.TYPE_SEXUAL_ASSAULT), Application.getContext(), activity);
                    sendTextMessageIfPossible(MotherOfDatabases.getEnabledNumbers(Boss.TYPE_SEXUAL_ASSAULT), location, Boss.TYPE_SEXUAL_ASSAULT, activity);
                    break;
                case R.id.card_shishubavan:
                    call_phone(MotherOfDatabases.getEnabledNumbers(Boss.TYPE_SHISHUBAVAN), Application.getContext(), activity);
                    sendTextMessageIfPossible(MotherOfDatabases.getEnabledNumbers(Boss.TYPE_SHISHUBAVAN), location, Boss.TYPE_SHISHUBAVAN, activity);
                    break;
                case R.id.card_customs:
                    call_phone(MotherOfDatabases.getEnabledNumbers(Boss.TYPE_CUSTOMS), Application.getContext(), activity);
                    sendTextMessageIfPossible(MotherOfDatabases.getEnabledNumbers(Boss.TYPE_CUSTOMS), location, Boss.TYPE_CUSTOMS, activity);
                    break;
                case R.id.card_drugs:
                    call_phone(MotherOfDatabases.getEnabledNumbers(Boss.TYPE_EXCISE), Application.getContext(), activity);
                    sendTextMessageIfPossible(MotherOfDatabases.getEnabledNumbers(Boss.TYPE_EXCISE), location, Boss.TYPE_EXCISE, activity);
                    break;
                case R.id.card_blood_bank:
                    call_phone(MotherOfDatabases.getEnabledNumbers(Boss.TYPE_BLOOD_BANKS), Application.getContext(), activity);
                    sendTextMessageIfPossible(MotherOfDatabases.getEnabledNumbers(Boss.TYPE_BLOOD_BANKS), location, Boss.TYPE_BLOOD_BANKS, activity);
                    break;
            }
        }

    }

    public static void warnUser(final int id, final Activity activity, final Location location) {
        new AlertDialog.Builder(activity)
                .setTitle("Warning")
                .setMessage("Your action sends your number and location to authorities. Prank calls are punishable!\nDo you still want to make an alert?\nNB: This warning can be turned off in settings")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        switch (id) {

                            case R.id.card_ambulance:
                                call_phone(MotherOfDatabases.getPhoneNumbersOf(Boss.TYPE_AMBULANCE, Application.getContext()), Application.getContext(), activity);
                                sendTextMessageIfPossible(MotherOfDatabases.getPhoneNumbersOf(Boss.TYPE_AMBULANCE, Application.getContext()), location, Boss.TYPE_AMBULANCE, activity);
                                break;
                            case R.id.card_animalAbuse:
                                call_phone(MotherOfDatabases.getPhoneNumbersOf(Boss.TYPE_STRAY_DOGS, Application.getContext()), Application.getContext(), activity);
                                sendTextMessageIfPossible(MotherOfDatabases.getPhoneNumbersOf(Boss.TYPE_STRAY_DOGS, Application.getContext()), location, Boss.TYPE_STRAY_DOGS, activity);
                                break;
                            case R.id.card_childAbuse:
                                call_phone(MotherOfDatabases.getPhoneNumbersOf(Boss.TYPE_CHILD_ABUSE, Application.getContext()), Application.getContext(), activity);
                                sendTextMessageIfPossible(MotherOfDatabases.getPhoneNumbersOf(Boss.TYPE_CHILD_ABUSE, Application.getContext()), location, Boss.TYPE_CHILD_ABUSE, activity);
                                break;
                            case R.id.card_police:
                                call_phone(MotherOfDatabases.getPhoneNumbersOf(Boss.TYPE_POLICE, Application.getContext()), Application.getContext(), activity);
                                sendTextMessageIfPossible(MotherOfDatabases.getPhoneNumbersOf(Boss.TYPE_POLICE, Application.getContext()), location, Boss.TYPE_POLICE, activity);
                                break;
                            case R.id.card_firetruck:
                                call_phone(MotherOfDatabases.getPhoneNumbersOf(Boss.TYPE_FIRETRUCK, Application.getContext()), Application.getContext(), activity);
                                sendTextMessageIfPossible(MotherOfDatabases.getPhoneNumbersOf(Boss.TYPE_FIRETRUCK, Application.getContext()), location, Boss.TYPE_FIRETRUCK, activity);
                                break;
                            case R.id.card_sexualAbuse:
                                call_phone(MotherOfDatabases.getPhoneNumbersOf(Boss.TYPE_SEXUAL_ASSAULT, Application.getContext()), Application.getContext(), activity);
                                sendTextMessageIfPossible(MotherOfDatabases.getPhoneNumbersOf(Boss.TYPE_SEXUAL_ASSAULT, Application.getContext()), location, Boss.TYPE_SEXUAL_ASSAULT, activity);
                                break;
                            case R.id.card_shishubavan:
                                call_phone(MotherOfDatabases.getPhoneNumbersOf(Boss.TYPE_SHISHUBAVAN, Application.getContext()), Application.getContext(), activity);
                                sendTextMessageIfPossible(MotherOfDatabases.getPhoneNumbersOf(Boss.TYPE_SHISHUBAVAN, Application.getContext()), location, Boss.TYPE_SHISHUBAVAN, activity);
                                break;
                            case R.id.card_drugs:
                                call_phone(MotherOfDatabases.getPhoneNumbersOf(Boss.TYPE_EXCISE, Application.getContext()), Application.getContext(), activity);
                                sendTextMessageIfPossible(MotherOfDatabases.getPhoneNumbersOf(Boss.TYPE_EXCISE, Application.getContext()), location, Boss.TYPE_EXCISE, activity);
                                break;
                            case R.id.card_customs:
                                call_phone(MotherOfDatabases.getPhoneNumbersOf(Boss.TYPE_CUSTOMS, Application.getContext()), Application.getContext(), activity);
                                sendTextMessageIfPossible(MotherOfDatabases.getPhoneNumbersOf(Boss.TYPE_CUSTOMS, Application.getContext()), location, Boss.TYPE_CUSTOMS, activity);
                                break;
                            case R.id.card_blood_bank:
                                call_phone(MotherOfDatabases.getPhoneNumbersOf(Boss.TYPE_BLOOD_BANKS, Application.getContext()), Application.getContext(), activity);
                                sendTextMessageIfPossible(MotherOfDatabases.getPhoneNumbersOf(Boss.TYPE_BLOOD_BANKS, Application.getContext()), location, Boss.TYPE_BLOOD_BANKS, activity);
                                break;
                        }
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public static void sendTextMessageIfPossible(String[] telNumbers, Location location, String msgType, Activity activity) {

        if (location != null) {

            if (!(UserPreferences.readUserChoice(Application.getContext()) == R.id.smsOnlyRB)) {
                String messageBody = "";
                SmsManager smsMgr = SmsManager.getDefault();

                for (String telNumber : telNumbers) {
                    if (telNumber != null) {
                        switch (msgType) {
                            case Boss.TYPE_AMBULANCE:
                                messageBody = "Urgent Ambulance requirement\nLocation:http://maps.google.com/?q=" + location.getLatitude() + "," + location.getLongitude();
                                break;
                            case Boss.TYPE_CHILD_ABUSE:
                                messageBody = "Urgent!! \nChild Abuse reported\nLocation:http://maps.google.com/?q=" + location.getLatitude() + "," + location.getLongitude();
                                break;
                            case Boss.TYPE_FIRETRUCK:
                                messageBody = "Urgent FireForce requirement\nLocation:http://maps.google.com/?q=" + location.getLatitude() + "," + location.getLongitude();
                                break;
                            case Boss.TYPE_POLICE:
                                messageBody = "Urgent Police requirement\nLocation:http://maps.google.com/?q=" + location.getLatitude() + "," + location.getLongitude();
                                break;
                            case Boss.TYPE_SEXUAL_ASSAULT:
                                messageBody = "Urgent!! \nSexual Assault reported\nLocation:http://maps.google.com/?q=" + location.getLatitude() + "," + location.getLongitude();
                                break;
                            case Boss.TYPE_STRAY_DOGS:
                                messageBody = "Urgent!! \nStray Dogs Attack reported\nLocation:http://maps.google.com/?q=" + location.getLatitude() + "," + location.getLongitude();
                                break;
                            case Boss.TYPE_SHISHUBAVAN:
                                messageBody = "Urgent!! \nHelp Me call\nLocation:http://maps.google.com/?q=" + location.getLatitude() + "," + location.getLongitude();
                                break;
                            case Boss.TYPE_EXCISE:
                                messageBody = "Urgent!! \nIllegal Drugs or Alcohol usage reported\nLocation:http://maps.google.com/?q=" + location.getLatitude() + "," + location.getLongitude();
                                break;
                            case Boss.TYPE_CUSTOMS:
                                messageBody = "Urgent!! \nSmuggling or illegal activity reported\nLocation:http://maps.google.com/?q=" + location.getLatitude() + "," + location.getLongitude();
                                break;
                            case Boss.TYPE_BLOOD_BANKS:
                                messageBody = "Urgent!! \nBlood Urgency\nLocation:http://maps.google.com/?q=" + location.getLatitude() + "," + location.getLongitude();
                                break;
                        }
                        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                            try {
                                if (!telNumber.startsWith("0") && telNumber.length() >= 10){
                                    smsMgr.sendTextMessage(telNumber, null, messageBody, null, null);
                                    Log.i("Janaseva->Boss->sendSMS", "SMS sent to "+telNumber);
                                }
                                else Log.i("Janaseva->Boss->sendSMS", "Not a mobile number");
                            } catch (NullPointerException e) {
                                Log.e("Janaseva->Boss->sendSMS", e.getLocalizedMessage() + "");
                            }
                        } else {
                            Log.e(LogTag, "No Permission");
                            ActivityCompat.requestPermissions(activity,
                                    new String[]{Manifest.permission.SEND_SMS}, PERMISSIONS_REQUEST_SEND_SMS);
                        }
                    }
                }
            }

        } else {


            if (!(UserPreferences.readUserChoice(Application.getContext()) == R.id.callOnlyRB)) {
                String messageBody = "";
                SmsManager smsMgr = SmsManager.getDefault();

                for (String telNumber : telNumbers) {
                    if (telNumber != null) {
                        switch (msgType) {
                            case Boss.TYPE_AMBULANCE:
                                messageBody = "Urgent Ambulance requirement";
                                break;
                            case Boss.TYPE_CHILD_ABUSE:
                                messageBody = "Urgent!! \nChild Abuse reported";
                                break;
                            case Boss.TYPE_FIRETRUCK:
                                messageBody = "Urgent FireForce requirement";
                                break;
                            case Boss.TYPE_POLICE:
                                messageBody = "Urgent Police requirement";
                                break;
                            case Boss.TYPE_SEXUAL_ASSAULT:
                                messageBody = "Urgent!! \nSexual Assault reported";
                                break;
                            case Boss.TYPE_STRAY_DOGS:
                                messageBody = "Urgent!! \nStray Dogs Attack reported";
                                break;
                            case Boss.TYPE_SHISHUBAVAN:
                                messageBody = "Urgent!! \nHelp Me Call";
                                break;
                            case Boss.TYPE_EXCISE:
                                messageBody = "Urgent!! \nIllegal Drugs or Alcohol usage reported";
                                break;
                            case Boss.TYPE_CUSTOMS:
                                messageBody = "Urgent!! \nSmuggling or illegal activity reported";
                                break;
                            case Boss.TYPE_BLOOD_BANKS:
                                messageBody = "Urgent!! \nBlood Urgency";
                                break;
                        }
                        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                            try {
                                if (!telNumber.startsWith("0") && telNumber.length() >= 10){
                                    smsMgr.sendTextMessage(telNumber, null, messageBody, null, null);
                                    Log.i("Janaseva->Boss->sendSMS", "SMS sent to "+telNumber);
                                }
                            } catch (NullPointerException e) {
                                Log.e("JanaSeva->Boss", e.getLocalizedMessage());
                            }
                        } else {
                            Log.e(LogTag, "No Permission");
                            ActivityCompat.requestPermissions(activity,
                                    new String[]{Manifest.permission.SEND_SMS}, PERMISSIONS_REQUEST_SEND_SMS);
                            return;
                        }
                    }
                }
            }
        }
    }

    public static void customCall(final Context context, Activity activity, Location location) {
        final String number = UserPreferences.getCustomNumber(context);
        String messageBody;
        SmsManager smsMgr = SmsManager.getDefault();

        if (!number.equals("0")){
            if (UserPreferences.readUserChoice(context) == R.id.callAndSmsRB) {
                //Calling
                Log.i("Status", "call made");
                Uri phNumber = Uri.parse("tel:" + number);
                Intent callIntent = new Intent(Intent.ACTION_CALL, phNumber);
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    activity.startActivity(callIntent);
                } else {
                    Log.e(LogTag, "No Permission");
                    ActivityCompat.requestPermissions(activity,
                            new String[]{Manifest.permission.CALL_PHONE}, PERMISSIONS_REQUEST_CALL_PHONE);
                }

                //Sending SMS
                if (location!=null)
                    messageBody = "Urgent!! \nHelp Me Please. I am in danger.\nLocation:http://maps.google.com/?q=" + location.getLatitude() + "," + location.getLongitude();
                else
                    messageBody = "Urgent!! \nHelp Me Please. I am in danger.";

                smsMgr.sendTextMessage(number, null, messageBody, null, null);
                Log.i("Janaseva->Boss->sendSMS", "SMS sent to "+number);
            }else if (UserPreferences.readUserChoice(context) == R.id.callOnlyRB){
                Log.i("Status", "call made");
                Uri phNumber = Uri.parse("tel:" + number);
                Intent callIntent = new Intent(Intent.ACTION_CALL, phNumber);
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    activity.startActivity(callIntent);
                } else {
                    Log.e(LogTag, "No Permission");
                    ActivityCompat.requestPermissions(activity,
                            new String[]{Manifest.permission.CALL_PHONE}, PERMISSIONS_REQUEST_CALL_PHONE);
                }
            }else if (UserPreferences.readUserChoice(context) == R.id.smsOnlyRB){
                if (location!=null)
                    messageBody = "Urgent!! \nHelp Me Please. I am in danger.\nLocation:http://maps.google.com/?q=" + location.getLatitude() + "," + location.getLongitude();
                else
                    messageBody = "Urgent!! \nHelp Me Please. I am in danger.";

                smsMgr.sendTextMessage(number, null, messageBody, null, null);
                Log.i("Janaseva->Boss->sendSMS", "SMS sent to "+number);
            }
        }else{
            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle);
            builder.setTitle("Enter a number");
            final EditText input = new EditText(context);
            input.setInputType(InputType.TYPE_CLASS_NUMBER);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                input.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            }
            input.setMaxLines(1);
            input.setHint("Phone Number");
            builder.setView(input);
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    chosenCustomNumber = input.getText().toString();
                    if (!chosenCustomNumber.isEmpty() && !chosenCustomNumber.equals("") && chosenCustomNumber.length()>=10){
                        UserPreferences.saveCustomNumber(context,chosenCustomNumber);
                        MainActivity.customCallTXT.setText(String.format("Call: %s\nPress and hold to edit number.", chosenCustomNumber));
                    }else {
                        Toast.makeText(context,"Please enter a valid number",Toast.LENGTH_SHORT).show();
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
            builder.show();

        }

    }
}
