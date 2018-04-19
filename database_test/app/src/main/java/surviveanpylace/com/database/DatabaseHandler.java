package surviveanpylace.com.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.SimpleCursorAdapter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Created by loder on 2/23/2018.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "survive_anyplace";

    // Contacts table name
    private static final String TABLE_DICTIONARY = "Dictionary";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";

    private String DATABASE_CONTENT = null;

    DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public String readDatabase(Context context)
    {
        InputStream is = context.getResources().openRawResource(R.raw.sqldatabase);
        InputStreamReader reader = new InputStreamReader(is);
        SQLiteDatabase db = getReadableDatabase();
       try {
           BufferedReader buff = new BufferedReader(reader);
           String line = null;

           while ((line = buff.readLine()) != null) {
               StringBuilder sb = new StringBuilder();
               sb.append(line);
               String query = sb.toString();
               db.execSQL(query);
           }
       } catch (IOException ioe)
       {
           System.out.println(ioe);
       }
       // db.execSQL(DATABASE_CONTENT);
       return DATABASE_CONTENT;
    }
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_DICTIONARY_TABLE = "CREATE TABLE dictionary (\n" +
                "    id bigint NOT NULL,\n" +
                "    en text,\n" +
                "    es text,\n" +
                "    fr text,\n" +
                "    du text,\n" +
                "    tier tiers NOT NULL\n" +
                ");";

        db.execSQL(CREATE_DICTIONARY_TABLE);
    }

    // Upgrading sqldatabase
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DICTIONARY);

        // Create tables again
        onCreate(db);
    }

    // Adding new contact
    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName()); // Contact Name
        values.put(KEY_PH_NO, contact.getPhoneNumber()); // Contact Phone Number

        // Inserting Row
        db.insert(TABLE_DICTIONARY, null, values);
        db.close(); // Closing sqldatabase connection
    }

    // Getting single contact
    public Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_DICTIONARY, new String[] { KEY_ID,
                        KEY_NAME, KEY_PH_NO }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return contact
        return contact;
    }

    // Getting All Contacts
    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_DICTIONARY;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_DICTIONARY;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
    // Updating single contact
    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PH_NO, contact.getPhoneNumber());

        // updating row
        return db.update(TABLE_DICTIONARY, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
    }

    // Deleting single contact
    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DICTIONARY, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
        db.close();
    }

    public ArrayList<String> getTierOne() {

        SQLiteDatabase db = this.getReadableDatabase();

//        Hashtable<String, String> data = new Hashtable<>();

        ArrayList<String> data = new ArrayList<>();

        //String columns[] = new String["id", "en", "fr"];
        Cursor cursor = db.query("Dictionary", new String[]{"id", "en", "fr", "tier"},
                "tier='T1'", null, null, null, null);

        while(cursor.moveToNext())
        {

            int index = cursor.getColumnIndexOrThrow("id");
            int id = cursor.getInt(index);

            index = cursor.getColumnIndexOrThrow("en");
            String en = cursor.getString(index);

            index = cursor.getColumnIndexOrThrow("fr");
            String fr = cursor.getString(index);

            index = cursor.getColumnIndexOrThrow("tier");
            String tier = cursor.getString(index);

//            Hashtable<String, String> hsh = new Hashtable<>();
//
//            hsh.put("id", String.valueOf(id));
//            hsh.put("en", en);
//            hsh.put("fr", fr);
//            hsh.put("tier", tier);

//            data.add(hsh);
            String result = "ID: " + id + " EN: " + en + " FR: " + fr + " TIER: " + tier;
            data.add(result);
        }
        cursor.close();
        return data;
    }

//    public ArrayList<String> getTierOne() {
//
//        SQLiteDatabase db = this.getReadableDatabase();
//
////        Hashtable<String, String> data = new Hashtable<>();
//
//        List<Hashtable<String, String>> data = new ArrayList<>();
//
//        //String columns[] = new String["id", "en", "fr"];
//        Cursor cursor = db.query("Dictionary", new String[]{"id", "en", "fr", "tier"},
//                "tier='T1'", null, null, null, null);

//        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
//                R.layout.activity_main,
//                cursor,
//                new String[]{"id", "en", "fr"},
//                null
//                )
//    }

    public Hashtable<String, String> getTierTwo() {

        SQLiteDatabase db = this.getReadableDatabase();

        Hashtable<String, String> data = new Hashtable<>();

        Cursor cursor = db.query("Dictionary", new String[]{"id", "en", "fr"},
                "tier= 'T2'", null, null, null, "id");
        while (cursor.moveToNext())
        {
            int index = cursor.getColumnIndexOrThrow("id");
            int id = cursor.getInt(index);

            index = cursor.getColumnIndexOrThrow("en");
            String en = cursor.getString(index);

            index = cursor.getColumnIndexOrThrow("fr");
            String fr = cursor.getString(index);

            data.put ("id", String. valueOf(id));
            data.put("en", en);
            data.put("fr", fr);

        }
        cursor.close();
        return data;

    }

    public Hashtable<String, String> getTierThree() {

        SQLiteDatabase db = this.getReadableDatabase();

        Hashtable<String, String> data = new Hashtable<>();

        Cursor cursor = db.query("Dictionary", new String[]{"id", "en", "fr"},
                "tier='T3'", null, null, null, "id");
        {
            int index = cursor.getColumnIndexOrThrow("id");
            int id = cursor.getInt(index);

            index = cursor.getColumnIndexOrThrow("en");
            String en = cursor.getString(index);

            index = cursor.getColumnIndexOrThrow("fr");
            String fr = cursor.getString(index);

            data.put ("id", String. valueOf(id));
            data.put ("en", en);
            data.put ("fr", fr);
         }
        cursor.close();
        return data;

        }

    }

