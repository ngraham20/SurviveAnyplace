package surviveanpylace.com.surviveanyplace;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper   {

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

    public void loadSqlDatabase(Context context)
    {
        SQLiteDatabase db = getWritableDatabase();

//        String CREATE_DICTIONARY_TABLE = "CREATE TABLE dictionary (\n" +
//                "    id bigint NOT NULL,\n" +
//                "    en text,\n" +
//                "    es text,\n" +
//                "    fr text,\n" +
//                "    du text,\n" +
//                "    tier tiers NOT NULL\n" +
//                ");";
//
//        db.execSQL(CREATE_DICTIONARY_TABLE);

        InputStream is = context.getResources().openRawResource(R.raw.sqldatabase);
        InputStreamReader reader = new InputStreamReader(is);
//        SQLiteDatabase db = getReadableDatabase();
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
    }

    public ArrayList<String> getTier(int tier_num) {

        if (tier_num < 1 || tier_num > 3) {
            // TODO throw error here
            return new ArrayList<>();
        }

        else {

            SQLiteDatabase db = this.getReadableDatabase();

            ArrayList<String> data = new ArrayList<>();

            Cursor cursor = db.query("Dictionary", new String[]{"id", "en", "fr", "tier"},
                    "tier='T" + tier_num + "'", null, null, null, "id");

//        String query = "SELECT id, en, fr, tier FROM Dictionary WHERE tier='T1' ORDER BY id;";
//        Cursor cursor = db.rawQuery(query, null);

            while (cursor.moveToNext()) {

                int index = cursor.getColumnIndexOrThrow("id");
                int id = cursor.getInt(index);

                index = cursor.getColumnIndexOrThrow("en");
                String en = cursor.getString(index);

                index = cursor.getColumnIndexOrThrow("fr");
                String fr = cursor.getString(index);

                index = cursor.getColumnIndexOrThrow("tier");
                String tier = cursor.getString(index);

                String result = "ID: " + id + " EN: " + en + " FR: " + fr + " TIER: " + tier;
                data.add(result);
            }
            cursor.close();
            return data;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // TODO required override stub
    }

    public void createDatabase(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
