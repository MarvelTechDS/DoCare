package com.marveltech.docare;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.nfc.tech.NfcB;
import android.os.Bundle;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Objects;

public class SignUpDetails extends AppCompatActivity {
    EditText contact1, contact2, contact3, name1;
    String number1, number2, number3, name;
    public static final int PICK_CONTACT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_details);
        contact1 = findViewById(R.id.signup_contact1);
//        contact2 = findViewById(R.id.signup_contact2);
//        contact3 = findViewById(R.id.signup_contact3);
        name1 = findViewById(R.id.signup_name);
        findViewById(R.id.signup_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = name1.getText().toString();
//                number1 = contact1.getText().toString();
//                number2 = contact2.getText().toString();
                String uid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
                String phone = FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber();
                if (!TextUtils.isEmpty(name)) {
                    HelperClass helperClass = new HelperClass(name, phone, uid);
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference reference = database.getReference("Users");
                    reference.child(uid).setValue(helperClass);
                    startActivity(new Intent(SignUpDetails.this, HomeActivity2.class));
                } else {
                    Toast.makeText(SignUpDetails.this, "Enter Correct Details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//    public void openContacts(View view) {
//        // Intent intent = null;
//        int choose_button = 0;
//        if (view.getId() == R.id.signup_contact1)
//            choose_button = 1;
//        else if (view.getId() == R.id.signup_contact2)
//            choose_button = 2;
//        else if (view.getId() == R.id.signup_contact3)
//            choose_button = 3;
//
//        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
//        startActivityForResult(intent, choose_button);
//    }
//
//    public void onActivityResult(int reqCode, int resultCode, Intent data) {
//        super.onActivityResult(reqCode, resultCode, data);
//        if (resultCode == 0)
//            return;
//        String cNumber = null;
//        String name = null;
//
//        if (resultCode == Activity.RESULT_OK) {
//
//            Uri contactData = data.getData();
//            Cursor c = managedQuery(contactData, null, null, null, null);
//            if (c.moveToFirst()) {
//
//
//                String id = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
//
//                String hasPhone = c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
//
//                if (hasPhone.equalsIgnoreCase("1")) {
//                    Cursor phones = getContentResolver().query(
//                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
//                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id,
//                            null, null);
//                    phones.moveToFirst();
//                    cNumber = phones.getString(phones.getColumnIndex("data1"));
//                    System.out.println("number is:" + cNumber);
//                }
//                name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
//
//
//            }
//        }
//        showSelectedNumber(cNumber);
//        if (reqCode == 1)
//            contact1.setText(cNumber);
//        else if (reqCode == 2)
//            contact2.setText(cNumber);
//        else if (reqCode == 3)
//            contact3.setText(cNumber);
//        else
//            Log.d("Hi","hi");
//    }
//
//    public void showSelectedNumber(String number) {
//        Toast.makeText(this, "Selected contact Number: " + number, Toast.LENGTH_LONG).show();
//
//    }
}
