package aa.jurano.aajuranophone;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

    @ViewById
    EditText etName;

    @ViewById
    EditText etAge;

    @ViewById
    EditText etNumber;

    @ViewById
    EditText etJob;

    private DatabaseHelper databaseHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseHelper = new DatabaseHelper(this);

    }

    @Click
    void btnInsertData(){
        String name = etName.getText().toString();
        String age = etAge.getText().toString();
        String phone = etNumber.getText().toString();
        String job = etJob.getText().toString();
        if(name.length() == 0){
            showDilag("이름을 입력하세요");
        }
        else if(age.length() == 0){
            showDilag("나이를 입력하세요");
        }
        else if(phone.length() == 0){
            showDilag("번호를 입력하세요");
        }
        else if(job.length() == 0){
            showDilag("직업을 입력하세요");
        }
        else{

            AddressInfo addressInfo = new AddressInfo();
            addressInfo.setName(name);
            addressInfo.setAge(Integer.parseInt(age));
            addressInfo.setPhone(phone);
            addressInfo.setJob(job);

            databaseHelper.inserAddressData(addressInfo);
        }
    }


    @Click
    void btnShowAllData(){
        Intent intent = new Intent(this, AddressListActivity_.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void showDilag(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("알림!");
        builder.setMessage(message);
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

}
