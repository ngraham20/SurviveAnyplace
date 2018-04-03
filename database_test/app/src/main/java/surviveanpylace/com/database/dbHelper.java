package surviveanpylace.com.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;

/**
 * Created by loder on 2/23/2018.
 */

public class dbHelper {

    //private static String DB_PATH = "/data/data/surviveanyplace.com.sqldatabase/databases/";

    private static String DB_NAME = "lang.db";

    private SQLiteDatabase myDataBase;

    private final Context myContext;

    private String path = Environment.getExternalStorageDirectory() + "/" + "myDatabase/";

    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     * @param context
     */
    public dbHelper(Context context) {
        this.myContext = context;

        // Create the parent path
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String fullName = path + "mylog";
        File file = new File (fullName);

        SQLiteDatabase.openOrCreateDatabase("sqldatabase.db", null);
    }
}
