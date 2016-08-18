package com.luomo.addressS;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends Activity {
    private Context mContext;
    private List<AddressDomain> mAddresses;
    private ListView mLvAddress;
    private AddressAdapter mAddressAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mLvAddress = (ListView) findViewById(R.id.lv_address);
        initData();
    }

    private void initData() {
        //生成数据
        GeneratedData();
        mLvAddress.setAdapter(mAddressAdapter = new AddressAdapter(mContext, mAddresses));
    }

    private void GeneratedData() {
        mAddresses = new ArrayList<AddressDomain>();
        mAddresses.add(new AddressDomain("1", "曹阿瞒", "18710990897", "0"));
        mAddresses.add(new AddressDomain("2", "关羽", "18710990896", "0"));
        mAddresses.add(new AddressDomain("3", "刘备", "18710990895", "0"));
        mAddresses.add(new AddressDomain("4", "司马懿", "18710990894", "1"));
        mAddresses.add(new AddressDomain("5", "张飞", "18710990893", "0"));
        mAddresses.add(new AddressDomain("6", "诸葛亮", "18710990892", "0"));
    }

    class AddressAdapter extends BaseAdapter {
        private Context mContext;
        private List<AddressDomain> addresses;

        public AddressAdapter(Context mContext, List<AddressDomain> mAddresses) {
            this.mContext = mContext;
            this.addresses = mAddresses;
            sortData();
        }

        /**
         * 排序
         */
        private void sortData() {
            //对list进行排序，优先级 是否是默认助理、id
            Collections.sort(addresses, new Comparator<AddressDomain>() {

                @Override
                public int compare(AddressDomain lhs, AddressDomain rhs) {
                    if(lhs.getDefaultFlag().compareToIgnoreCase(rhs.getDefaultFlag())<0){
                        return 1;
                    }else if(lhs.getDefaultFlag().compareToIgnoreCase(rhs.getDefaultFlag())==0){
                        return lhs.getId().compareToIgnoreCase(rhs.getId());
                    }else{
                        return -1;
                    }
                }
            });
        }

        @Override
        public int getCount() {
            return addresses.size();
        }

        @Override
        public Object getItem(int position) {
            return addresses.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        /**
         * @param position
         * @param convertView
         * @param parent
         * @return
         */
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder viewHolder;
            if (convertView == null) {
                //--
                convertView = View.inflate(mContext, R.layout.layout_address_item, null);
                viewHolder = new ViewHolder();
                viewHolder.tvId = (TextView) convertView.findViewById(R.id.tv_id);
                viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
                viewHolder.tvPhone = (TextView) convertView.findViewById(R.id.tv_phone);
                viewHolder.cbSelected = (CheckBox) convertView.findViewById(R.id.cb_selected);
                viewHolder.llSplit = (LinearLayout) convertView.findViewById(R.id.ll_split);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.tvId.setText(addresses.get(position).getId());
            viewHolder.tvName.setText(addresses.get(position).getName());
            viewHolder.tvPhone.setText(addresses.get(position).getMobilePhone());
            viewHolder.cbSelected.setChecked(addresses.get(position).getDefaultFlag() == "1");
            //最后一个则隐藏掉分割线
            if (addresses.size() == addresses.indexOf(addresses.get(position)) + 1) {
                viewHolder.llSplit.setVisibility(View.GONE);
            } else {
                viewHolder.llSplit.setVisibility(View.VISIBLE);
            }
            //设置为默认助理
            viewHolder.cbSelected.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (viewHolder.cbSelected.isChecked()) {
                        if(addresses.get(0).getDefaultFlag()=="1"){//之前第一个助理是默认助理
                            addresses.get(0).setDefaultFlag("0");
                        }
                        addresses.get(position).setDefaultFlag("1");
                    } else {
                        addresses.get(position).setDefaultFlag("0");
                    }
                    AddressAdapter.this.notifyDataSetChanged();
                }
            });
            return convertView;
        }

        @Override
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
            sortData();
        }
    }

    class ViewHolder {
        private TextView tvId;
        private TextView tvName;
        private TextView tvPhone;
        private CheckBox cbSelected;
        private LinearLayout llSplit;
    }
}
