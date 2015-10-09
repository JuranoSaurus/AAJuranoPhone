package aa.jurano.aajuranophone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jool on 15. 10. 9..
 */
public class AddressAdapter extends BaseAdapter{
    private List<AddressInfo> addressInfoList;
    private Context context;

    public AddressAdapter(List<AddressInfo> addressInfoList, Context context) {
        this.addressInfoList = addressInfoList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return addressInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return addressInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        AddressHolder holder = null;
        if(convertView == null){
            holder = new AddressHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.addressitem, parent, false);

            holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            holder.tvAge = (TextView) convertView.findViewById(R.id.tvAge);
            holder.tvPhone = (TextView) convertView.findViewById(R.id.tvPhone);
            holder.tvJob = (TextView) convertView.findViewById(R.id.tvJob);

        }
        else{
            holder = (AddressHolder) convertView.getTag();
        }

        AddressInfo addAdressInfo = (AddressInfo) getItem(position);

        holder.tvName.setText(addAdressInfo.getName());
        holder.tvAge.setText(addAdressInfo.getAge() + "");
        holder.tvPhone.setText(addAdressInfo.getPhone());
        holder.tvJob.setText(addAdressInfo.getJob());

        convertView.setTag(holder);
        return convertView;
    }
}
