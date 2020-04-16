package com.example.readcontacts;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  {

    TextView show_contacts;

    String[] mColumnProjection = new String[]{
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.Contacts.CONTACT_STATUS,
            ContactsContract.Contacts.HAS_PHONE_NUMBER
    };

    //String mSelectionClause = ContactsContract.Contacts.DISPLAY_NAME + "= 'مریم' AND "+ ContactsContract.Contacts.HAS_PHONE_NUMBER + "=1";
    String mSelectionClause = ContactsContract.Contacts.DISPLAY_NAME + " = ? ";
    String[] mSelectionArgument = new String[]{"مریم"};
    String sortOrder = ContactsContract.Contacts.DISPLAY_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        show_contacts = findViewById(R.id.show_contacts);

        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI
                , mColumnProjection
                , null
                , null
                , sortOrder);
        if (cursor != null && cursor.getCount() > 0) {
            StringBuilder result = new StringBuilder();
            while (cursor.moveToNext()) {
                result.append(cursor.getString(0) + ", " +
                              cursor.getString(1) + ", " +
                              cursor.getString(2) + "\n");
            }

            show_contacts.setText(result.toString());

            show_contacts.setMovementMethod(new ScrollingMovementMethod());

            Log.i("LOG", result.toString());
        }
    }

}
