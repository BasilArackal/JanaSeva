package com.lmntrx.shishubavan;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CustomNumbersSettings extends AppCompatActivity {

    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 2;
    private static final int PICK_CALL_CONTACT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_numbers_settings);
        TextView chosenNumberTextView = (TextView) findViewById(R.id.chosenCallNumberDisplay);
        assert chosenNumberTextView != null;
        String chosenNumber = UserPreferences.getCustomNumber(this);
        String displayName = UserPreferences.getCustomNumberName(this);
        if (chosenNumber.equals("0")){
                chosenNumberTextView.setText(R.string.no_number_chosen);
        }else
            if (displayName.equals("0"))
                chosenNumberTextView.setText(chosenNumber);
            else
                chosenNumberTextView.setText(String.format("%s\n%s", displayName, chosenNumber));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PICK_CALL_CONTACT:
                Cursor cursor = null;
                final TextView textView = (TextView)findViewById(R.id.chosenCallNumberDisplay);
                assert textView != null;
                String phoneNumber = "",displayName = "";
                List<String> allNumbers = new ArrayList<>();
                int phoneIdx,nameIdx;
                try {
                    Uri result = data.getData();
                    String id = result.getLastPathSegment();
                    cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?", new String[] { id }, null);
                    assert cursor != null;
                    phoneIdx = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DATA);
                    nameIdx = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                    if (cursor.moveToFirst()) {
                        displayName = cursor.getString(nameIdx);
                        while (!cursor.isAfterLast()) {
                            phoneNumber = cursor.getString(phoneIdx);
                            allNumbers.add(phoneNumber);
                            cursor.moveToNext();
                        }
                    } else {
                        Log.d(CustomNumbersSettings.class.getSimpleName(),"No numbers were chosen");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (cursor != null) {
                        cursor.close();
                    }

                    final CharSequence[] items = allNumbers.toArray(new String[allNumbers.size()]);
                    AlertDialog.Builder builder = new AlertDialog.Builder(CustomNumbersSettings.this);
                    builder.setTitle("Choose a number");
                    final String finalDisplayName = displayName;
                    builder.setItems(items, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            String selectedNumber = items[item].toString();
                            selectedNumber = selectedNumber.replace("-", "");
                            textView.setText(String.format("%s\n%s", finalDisplayName, selectedNumber));
                            UserPreferences.saveCustomNumber(CustomNumbersSettings.this,selectedNumber);
                            UserPreferences.saveCustomNumberName(CustomNumbersSettings.this, finalDisplayName);
                            MainActivity.customCallTXT.setText(String.format("Call: %s\nPress & hold to edit number.", selectedNumber));
                        }
                    });
                    AlertDialog alert = builder.create();
                    if(allNumbers.size() > 1) {
                        alert.show();
                    } else {
                        String selectedNumber = phoneNumber;
                        selectedNumber = selectedNumber.replace("-", "");
                        textView.setText(String.format("%s\n%s", displayName, selectedNumber));
                        UserPreferences.saveCustomNumber(CustomNumbersSettings.this,selectedNumber);
                        UserPreferences.saveCustomNumberName(CustomNumbersSettings.this,displayName);
                        MainActivity.customCallTXT.setText(String.format("Call: %s\nPress & hold to edit number.", selectedNumber));
                    }

                    if (phoneNumber.length() == 0) {
                        Log.d(CustomNumbersSettings.class.getSimpleName(),"No numbers were chosen");
                    }
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSIONS_REQUEST_READ_CONTACTS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                    startActivityForResult(intent, PICK_CALL_CONTACT);
                }else {
                    Toast.makeText(CustomNumbersSettings.this, "Please grant permission to read your contacts!", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    public void chooseCallContact(View view) {

        if (ActivityCompat.checkSelfPermission(CustomNumbersSettings.this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
            startActivityForResult(intent, PICK_CALL_CONTACT);
        } else {
            Log.e(CustomNumbersSettings.class.getSimpleName(), "No Permission to read contacts");
            ActivityCompat.requestPermissions(CustomNumbersSettings.this,
                    new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
        }

    }
}
