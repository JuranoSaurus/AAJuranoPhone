package aa.jurano.aajuranophone;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EActivity(R.layout.activity_address_list)
public class AddressListActivity extends Activity {
    @ViewById
    ListView addressList;

    private  DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseHelper = new DatabaseHelper(this);

        setTitle("주소록");
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        List<AddressInfo> addressInfoList = databaseHelper.getAllAddress();
        Log.d("AddressListActivity", addressInfoList.toString());
        addressList.setAdapter(new AddressAdapter(addressInfoList, this));
    }
}
