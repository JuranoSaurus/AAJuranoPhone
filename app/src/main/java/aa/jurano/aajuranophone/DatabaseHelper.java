package aa.jurano.aajuranophone;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jool on 15. 10. 9..
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    /*private static SQLiteDatabase db;*/
    private Context context;

    public DatabaseHelper(Context context){
        this(context, "address", null, 1);
        this.context = context;
    }

    private DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //초기화
        createTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //기존 데이터 삭제
    }

    /*public DatabaseHelper(Context context){
        this.context = context;
        if(DatabaseHelper.db == null){
            this.db = context.openOrCreateDatabase("address",context.MODE_PRIVATE, null);
            this.createTable();
        }
        this.createTable();
    }
    public void chaneContext(Context context){
        this.context = context;
    }
*/
    private void createTable(SQLiteDatabase db){

        if(!isExistsTable(db)){
            String query = " CREATE TABLE ADDRESS ( ";
            query += " ID INTEGER PRIMARY KEY AUTOINCREMENT, ";
            query += " NAME VARCHAR, ";
            query += " AGE INTEGER, ";
            query += " PHONE VARCHAR, ";
            query += " JOB TEXT  ); ";

            db.execSQL(query);
        }

    }
    //테이블이 존재하는지 안하는지
    private boolean isExistsTable(SQLiteDatabase db){

        String query = " SELECT COUNT(NAME) " +
                " FROM sqlite_master  " +
                " WHERE type='table' " +
                " AND name='ADDRESS'; ";

        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToNext()){
            return cursor.getInt(0) > 0;
        }
        return false;
    }

    public void inserAddressData(AddressInfo addressInfo){

        SQLiteDatabase db = getWritableDatabase();
//        String query = "";
        String query = context.getString(R.string.INSERT_QUERY);
        query = String.format(query, "('" + addressInfo.getName() + "',", addressInfo.getAge(), ",'" + addressInfo.getPhone() + "','" + addressInfo.getJob() + "')");
        Log.d("QUERY", query);
        db.execSQL(query);
    }

    public List<AddressInfo> getAllAddress(){

        SQLiteDatabase db = getReadableDatabase();

//        String query = "";
        String query = context.getString(R.string.SELECT_QUERY);

        Cursor cursor = db.rawQuery(query, null);
        List<AddressInfo> addressList = new ArrayList<AddressInfo>();
        AddressInfo addressInfo = null;

        while (cursor.moveToNext()){
            addressInfo = new AddressInfo();
            addressInfo.setName(cursor.getString(0));
            addressInfo.setAge(cursor.getInt(1));
            addressInfo.setPhone(cursor.getString(2));
            addressInfo.setJob(cursor.getString(3));
            addressList.add(addressInfo);
        }
        return addressList;
    }

}
