package com.finance365.bird.fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.finance365.bird.R;
import com.finance365.bird.entity.ProvinceBean;
import com.finance365.bird.ui.view.PopupDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by admin on 2016/6/6.
 */
public class Fragment4 extends BaseFragment {
    private ArrayList<ProvinceBean> options1Items = new ArrayList<ProvinceBean>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<ArrayList<String>>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<ArrayList<ArrayList<String>>>();
    private TextView tvTime, tvOptions;
    TimePickerView pvTime;
    OptionsPickerView pvOptions;
    View vMasker;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setContainerView(R.layout.fragment_4);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        vMasker = view.findViewById(R.id.vMasker);
        tvTime = (TextView) view.findViewById(R.id.tv_time);
        tvOptions = (TextView) view.findViewById(R.id.tv_options);
        pvTime = new TimePickerView(mActivity, TimePickerView.Type.YEAR_MONTH_DAY);
        //时间回调
        pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                tvTime.setText(getTime(date));
            }
        });
        tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvTime.show();
            }
        });
        pvTime.setCyclic(false);

        pvOptions = new OptionsPickerView(mActivity);
        //选项1
        options1Items.add(new ProvinceBean(0, "广东", "广东省，以岭南东道、广南东路得名", "其他数据"));
        options1Items.add(new ProvinceBean(1, "湖南", "湖南省地处中国中部、长江中游，因大部分区域处于洞庭湖以南而得名湖南", "芒果TV"));
        options1Items.add(new ProvinceBean(3, "广西", "嗯～～", ""));

        //选项2
        ArrayList<String> options2Items_01 = new ArrayList<String>();
        options2Items_01.add("广州");
        options2Items_01.add("佛山");
        options2Items_01.add("东莞");
        options2Items_01.add("阳江");
        options2Items_01.add("珠海");
        ArrayList<String> options2Items_02 = new ArrayList<String>();
        options2Items_02.add("长沙");
        options2Items_02.add("岳阳");
        ArrayList<String> options2Items_03 = new ArrayList<String>();
        options2Items_03.add("桂林");
        options2Items.add(options2Items_01);
        options2Items.add(options2Items_02);
        options2Items.add(options2Items_03);

        //选项3
        ArrayList<ArrayList<String>> options3Items_01 = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> options3Items_02 = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> options3Items_03 = new ArrayList<ArrayList<String>>();
        ArrayList<String> options3Items_01_01 = new ArrayList<String>();
        options3Items_01_01.add("白云");
        options3Items_01_01.add("天河");
        options3Items_01_01.add("海珠");
        options3Items_01_01.add("越秀");
        options3Items_01.add(options3Items_01_01);
        ArrayList<String> options3Items_01_02 = new ArrayList<String>();
        options3Items_01_02.add("南海");
        options3Items_01_02.add("高明");
        options3Items_01_02.add("顺德");
        options3Items_01_02.add("禅城");
        options3Items_01.add(options3Items_01_02);
        ArrayList<String> options3Items_01_03 = new ArrayList<String>();
        options3Items_01_03.add("其他");
        options3Items_01_03.add("常平");
        options3Items_01_03.add("虎门");
        options3Items_01.add(options3Items_01_03);
        ArrayList<String> options3Items_01_04 = new ArrayList<String>();
        options3Items_01_04.add("其他1");
        options3Items_01_04.add("其他2");
        options3Items_01_04.add("其他3");
        options3Items_01.add(options3Items_01_04);
        ArrayList<String> options3Items_01_05 = new ArrayList<String>();
        options3Items_01_05.add("其他1");
        options3Items_01_05.add("其他2");
        options3Items_01_05.add("其他3");
        options3Items_01.add(options3Items_01_05);

        ArrayList<String> options3Items_02_01 = new ArrayList<String>();
        options3Items_02_01.add("长沙长沙长沙长沙长沙长沙长沙长沙长沙1111111111");
        options3Items_02_01.add("长沙2");
        options3Items_02_01.add("长沙3");
        options3Items_02_01.add("长沙4");
        options3Items_02_01.add("长沙5");
        options3Items_02_01.add("长沙6");
        options3Items_02_01.add("长沙7");
        options3Items_02_01.add("长沙8");
        options3Items_02.add(options3Items_02_01);
        ArrayList<String> options3Items_02_02 = new ArrayList<String>();
        options3Items_02_02.add("岳1");
        options3Items_02_02.add("岳2");
        options3Items_02_02.add("岳3");
        options3Items_02_02.add("岳4");
        options3Items_02_02.add("岳5");
        options3Items_02_02.add("岳6");
        options3Items_02_02.add("岳7");
        options3Items_02_02.add("岳8");
        options3Items_02_02.add("岳9");
        options3Items_02.add(options3Items_02_02);
        ArrayList<String> options3Items_03_01 = new ArrayList<String>();
        options3Items_03_01.add("好山水");
        options3Items_03.add(options3Items_03_01);

        options3Items.add(options3Items_01);
        options3Items.add(options3Items_02);
        options3Items.add(options3Items_03);
        //三级联动效果
        pvOptions.setPicker(options1Items, options2Items, options3Items, true);
        //设置选择的三级单位
//        pwOptions.setLabels("省", "市", "区");
        pvOptions.setTitle("选择城市");
        pvOptions.setCyclic(false, false, true);
        //设置默认选中的三级项目
        //监听确定选择按钮
        pvOptions.setSelectOptions(1, 1, 1);
        pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText()
                        + options2Items.get(options1).get(option2)
                        + options3Items.get(options1).get(option2).get(options3);
                tvOptions.setText(tx);
                vMasker.setVisibility(View.GONE);
            }
        });
        //点击弹出选项选择器
        tvOptions.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pvOptions.show();
            }
        });
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.mine_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mine) {

            PopupDialog.createMuilt(mActivity, "提示信息", "提示内容", new PopupDialog.PopupClickListener() {
                @Override
                public void onClick(int button) {
                    if (button == PopupDialog.OK) {
                        mActivity.lenghtShort("点击了ok");
                    } else {
                        mActivity.lenghtShort("点击了cancel");
                    }
                }
            }).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
}