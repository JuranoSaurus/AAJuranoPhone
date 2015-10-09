package aa.jurano.aajuranophone;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import org.androidannotations.annotations.EActivity;

import java.util.List;

@EActivity(R.layout.activity_address_list)
public class AddressListActivity extends Activity {
    private ListView addressList;

    private  DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_list);

        databaseHelper = new DatabaseHelper(this);

        setTitle("주소록");
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        addressList = (ListView) findViewById(R.id.addressList);

        List<AddressInfo> addressInfoList = databaseHelper.getAllAddress();
        addressList.setAdapter(new AddressAdapter(addressInfoList, this));
    }


}
