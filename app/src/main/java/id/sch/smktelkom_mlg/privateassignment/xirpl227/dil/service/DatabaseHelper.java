package id.sch.smktelkom_mlg.privateassignment.xirpl227.dil.service;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static int DATABASE_VERSION = 1;
    public static String DATABASE_NAME = "db_moview";

    public static String TABLE_NAME = "tb_saved";
    public static String KEY1 = "id_saved";
    public static String KEY2 = "title";
    public static String KEY3 = "desc";
    public static String KEY4 = "banner";

    private String TAG = "~DatabaseHelper";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Creating table " + TABLE_NAME);
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
                + "(" + KEY1 + " INTEGER PRIMARY KEY,"
                + KEY2 + " VARCHAR(70),"
                + KEY3 + " VARCHAR(200),"
                + KEY4 + " TEXT" + ")";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // create new tables
        onCreate(db);
    }

    public boolean saveMovie(String title, String desc, String banner) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY2, title);
        contentValues.put(KEY3, desc);
        contentValues.put(KEY4, banner);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            Log.d(TAG, "Insert failed");
            return false;
        } else {
            Log.d(TAG, "Insert successfull");
            return true;
        }
    }
}