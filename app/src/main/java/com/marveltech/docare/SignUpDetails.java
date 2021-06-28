package com.marveltech.docare;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.ContentResolver;
import android.content.Intent;
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
        contact2 = findViewById(R.id.signup_contact2);
//        contact3 = findViewById(R.id.signup_contact3);
        name1 = findViewById(R.id.signup_name);
//        findViewById(R.id.signup_contact_image1).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
//                startActivityForResult(i, 1);
//            }
//        });
//        findViewById(R.id.signup_contact_image2).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
//                startActivityForResult(i, 2);
//            }
//        });
//        findViewById(R.id.signup_contact_image3).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
//                startActivityForResult(i, 3);
//            }
//        });
        findViewById(R.id.signup_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                number1 = contact1.getText().toString();
//                number2 = contact2.getText().toString();
//                number3 = contact3.getText().toString();
                name = name1.getText().toString();
                number1 = contact1.getText().toString();
                number2 = contact2.getText().toString();
                String uid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
                String phone = FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber();
                if (!TextUtils.isEmpty(name)) {
                    HelperClass helperClass = new HelperClass(name, phone, uid,number1,number2);
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
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent contactdata) {
//        super.onActivityResult(requestCode, resultCode, contactdata);
//        if (resultCode == RESULT_OK) {
//            switch (requestCode) {
//                case 1: getcontact1(contactdata);
//                break;
//                case 2: getcontact2(contactdata);
//                break;
//                case 3: getcontact3(contactdata);
//                break;
//                default:
//                    Log.d("error","Error ");
//            }
//        }
//    }
//
//    private void getcontact3(Intent contactdata) {
//        Uri uri = contactdata.getData();
//        Cursor cursor3 = getContentResolver().query(uri,null,null,null,null);
//        if (cursor3.moveToFirst())
//        {
//            String number = cursor3.getString(cursor3.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//            contact3.setText(number);
//            cursor3.close();
//            Toast.makeText(this, "Picked contcat 3", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    private void getcontact2(Intent contactdata) {
////        Uri uri = contactdata.getData();
//        ContentResolver res1 = getContentResolver();
//        Cursor cursor2 = res1.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);
//        if (cursor2.moveToFirst())
//        {
//            String number = cursor2.getString(cursor2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//            contact2.setText(number);
//            cursor2.close();
//            Toast.makeText(this, "Picked contcat 2", Toast.LENGTH_SHORT).show();
//
//        }
//    }
//
//    private void getcontact1(Intent contactdata) {
//        Uri uri = contactdata.getData();
//        Cursor cursor1 = getContentResolver().query(uri,null,null,null,null);
//            String number = cursor1.getString(cursor1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//            contact1.setText(number);
//            cursor1.close();
//            Toast.makeText(this, "Picked contcat 1", Toast.LENGTH_SHORT).show();
//
//
//    }
}
