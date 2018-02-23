package jnhouse.topwellsoft.com.jnhouse_android.ui.houseloan;

import java.math.BigDecimal;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;



import jnhouse.topwellsoft.com.jnhouse_android.R;

@SuppressLint("ValidFragment")
public class ZuHeFragment extends Fragment {
	private static final String[] nx = { "1", "2", "3", "4", "5", "10", "15",
			"20", "25", "30" };
	private static final String[] lv = { "最新基准利率7折", "最新基准利率7.5折", "最新基准利率8折",
			"最新基准利率8.5折", "最新基准利率9折", "最新基准利率9.5折", "最新基准利率", "最新基准利率1.1倍",
			"最新基准利率1.2倍", "最新基准利率1.3倍" };
	private EditText gongjijinlilv, shangyelilv;
	private Spinner nxvj1, lvv1;
	private Button subj1;
	private TextView am10;
	private TextView am11;
	private TextView am12;
	private TextView am13;
	private TextView am14;
	private EditText zevx1, zevx2;
	private double lilv = 4.9;
	private LinearLayout zuhe_layout;
	private RelativeLayout zonge_layout;
	private int fangshi = 0;
	private RadioGroup radioGroup;
	private PieChart mChart;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.gongjijin, container, false);
		initMyView(view);
		return view;
	}

	private void initMyView(View view) {
		init(view);
		clear();
		zuhe_layout = (LinearLayout) view.findViewById(R.id.zuhe_layout);
		zonge_layout = (RelativeLayout) view.findViewById(R.id.zonge_layout);
		zuhe_layout.setVisibility(View.VISIBLE);
		zonge_layout.setVisibility(View.GONE);
		nxvj1 = (Spinner) view.findViewById(R.id.nxv1);
		lvv1 = (Spinner) view.findViewById(R.id.lvv1);
		gongjijinlilv = (EditText) view.findViewById(R.id.gongjijinlilv);
		shangyelilv = (EditText) view.findViewById(R.id.shangyelilv);
		initPieChart(view);
		radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
		radioGroup
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						if (checkedId == R.id.radioBenjin) {
							fangshi = 1;
						} else {
							fangshi = 0;
						}
					}
				});
		zevx1 = (EditText) view.findViewById(R.id.zevx1);
		zevx2 = (EditText) view.findViewById(R.id.zevx2);
		shangyelilv.setText(String.valueOf(4.9));
		gongjijinlilv.setText(String.valueOf(3.25));
		subj1 = (Button) view.findViewById(R.id.sub1);
		// 将可选内容与ArrayAdapter连接起来
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				this.getActivity(), android.R.layout.simple_spinner_item, nx);
		// 设置下拉列表的风格
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// 将adapter 添加到spinner中
		nxvj1.setAdapter(adapter);
		nxvj1.setSelection(7, true);
		// 设置默认值
		nxvj1.setVisibility(View.VISIBLE);
		// 添加事件Spinner事件监听
		nxvj1.setOnItemSelectedListener(new GongjijinSpinnerSelectedListener(
				gongjijinlilv));
		// 将可选内容与ArrayAdapter连接起来
		ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
				this.getActivity(), android.R.layout.simple_spinner_item, lv);
		// 设置下拉列表的风格
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// 将adapter 添加到spinner中
		lvv1.setAdapter(adapter1);
		lvv1.setSelection(6, true);
		// 设置默认值
		lvv1.setVisibility(View.VISIBLE);
		// 添加事件Spinner事件监听
		lvv1.setOnItemSelectedListener(new ShangYeSpinnerSelectedListener(
				shangyelilv, lilv));
		this.subj1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				JiSuan();
			}
		});
		JiSuan();
	}

	private void initPieChart(View view) {
		mChart = (PieChart) view.findViewById(R.id.chart1);
		mChart.setDescription("");
		//mChart.seterDrawYValues(true);
		mChart.setDrawHoleEnabled(false);
		mChart.setRotationAngle(0);
		//mChart.setDrawXValues(true);
		mChart.setRotationEnabled(false);
		mChart.setUsePercentValues(true);
		setData(1, 100, 0, 0, 0);
		mChart.animateXY(1500, 1500);
	}

	private void JiSuan() {
		Editable value2 = zevx1.getText();
		if (value2.toString() == null || value2.toString().length() <= 0) {
			Toast.makeText(getActivity(), "公积金贷款总额不能为空", Toast.LENGTH_SHORT)
					.show();
			return;
		}
		Editable value3 = zevx2.getText();
		if (value3.toString() == null || value3.toString().length() <= 0) {
			Toast.makeText(getActivity(), "商业贷款总额不能为空", Toast.LENGTH_SHORT)
					.show();
			return;
		}
		Editable value = gongjijinlilv.getText();
		if (value.toString() == null || value.toString().length() <= 0) {
			Toast.makeText(getActivity(), "公积金利率不能为空", Toast.LENGTH_SHORT)
					.show();
			return;
		}
		Editable value1 = shangyelilv.getText();
		if (value1.toString() == null || value1.toString().length() <= 0) {
			Toast.makeText(getActivity(), "商业利率不能为空", Toast.LENGTH_SHORT)
					.show();
			return;
		}
		double ze = Double.parseDouble(zevx1.getText().toString()) * 10000;
		double nx = Double.parseDouble(nxvj1.getSelectedItem().toString()) * 12;
		double rate = Double.parseDouble(gongjijinlilv.getText().toString()) / 100;
		double ze1 = Double.parseDouble(zevx2.getText().toString()) * 10000;
		double rate1 = Double.parseDouble(shangyelilv.getText().toString()) / 100;
		cal(ze, nx, rate, ze1, rate1);
		setData(1, 100, ze, nx, rate);
	}

	public void cal(double ze, double nx, double rate, double ze1, double rate1) {
		double zem = (ze * rate / 12 * Math.pow((1 + rate / 12), nx))
				/ (Math.pow((1 + rate / 12), nx) - 1);
		double amount = zem * nx;
		double rateAmount = amount - ze;

		double zem1 = (ze1 * rate1 / 12 * Math.pow((1 + rate1 / 12), nx))
				/ (Math.pow((1 + rate1 / 12), nx) - 1);
		double amount1 = zem1 * nx;
		double rateAmount1 = amount1 - ze1;

		BigDecimal zemvalue = new BigDecimal(zem + zem1);
		double zemval = zemvalue.setScale(2, BigDecimal.ROUND_HALF_UP)
				.doubleValue();

		BigDecimal amountvalue = new BigDecimal(amount + amount1);
		double amountval = amountvalue.setScale(2, BigDecimal.ROUND_HALF_UP)
				.doubleValue();

		BigDecimal rateAmountvalue = new BigDecimal(rateAmount + rateAmount1);
		double rateAmountval = rateAmountvalue.setScale(2,
				BigDecimal.ROUND_HALF_UP).doubleValue();

		double benjinm = ze / nx;
		double lixim = ze * (rate / 12);
		double diff = benjinm * (rate / 12);
		double huankuanm = benjinm + lixim;
		double zuihoukuan = diff + benjinm;
		double av = (huankuanm + zuihoukuan) / 2;
		double zong = av * nx;
		double zongli = zong - ze;

		double benjinm1 = ze1 / nx;
		double lixim1 = ze1 * (rate1 / 12);
		double diff1 = benjinm1 * (rate1 / 12);
		double huankuanm1 = benjinm1 + lixim1;
		double zuihoukuan1 = diff1 + benjinm1;
		double av1 = (huankuanm1 + zuihoukuan1) / 2;
		double zong1 = av1 * nx;
		double zongli1 = zong1 - ze1;

		BigDecimal huankuanmvalue = new BigDecimal(huankuanm + huankuanm1);
		double huankuanmval = huankuanmvalue.setScale(2,
				BigDecimal.ROUND_HALF_UP).doubleValue();

		BigDecimal diffvalue = new BigDecimal(diff + diff1);
		double diffmval = diffvalue.setScale(2, BigDecimal.ROUND_HALF_UP)
				.doubleValue();

		BigDecimal zongvalue = new BigDecimal(zong + zong1);
		double zongval = zongvalue.setScale(2, BigDecimal.ROUND_HALF_UP)
				.doubleValue();

		BigDecimal zonglivalue = new BigDecimal(zongli + zongli1);
		double zonglival = zonglivalue.setScale(2, BigDecimal.ROUND_HALF_UP)
				.doubleValue();
		if (fangshi == 0) {
			am10.setText((new BigDecimal((ze + ze1) / 10000)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue()
					+ "万元");
			am11.setText(nx + "月");
			am12.setText(zemval + "元");
			am13.setText((new BigDecimal(rateAmountval / 10000)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue()
					+ "万元");
			am14.setText((new BigDecimal(amountval / 10000)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue()
					+ "万元");
		} else {
			am10.setText((new BigDecimal((ze + ze1) / 10000)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue()
					+ "万元");
			am11.setText(nx + "月");
			am12.setText("首月" + huankuanmval + ",月减" + diffmval);
			am13.setText((new BigDecimal(zonglival / 10000)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue()
					+ "万元");
			am14.setText((new BigDecimal(zongval / 10000)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue()
					+ "万元");
		}
	}

	private void setData(int count, float range, double ze, double nx,
			double rate) {
		double zem = (ze * rate / 12 * Math.pow((1 + rate / 12), nx))
				/ (Math.pow((1 + rate / 12), nx) - 1);
		double amount = zem * nx;
		double rateAmount = amount - ze;
		ArrayList<Entry> yVals1 = new ArrayList<Entry>();
		yVals1.add(new Entry((float) (ze / amount), 0));
		yVals1.add(new Entry((float) (rateAmount / amount), 1));
		ArrayList<String> xVals = new ArrayList<String>();
		xVals.add("贷款本金");
		xVals.add("贷款利息");
		PieDataSet set1 = new PieDataSet(yVals1, "");
		set1.setSliceSpace(3f);
		ArrayList<Integer> colors = new ArrayList<Integer>();
		colors.add(ColorTemplate.getHoloBlue());
		colors.add(Color.rgb(255,85,85));
		set1.setColors(colors);
		PieData data = new PieData(xVals, set1);
		mChart.setData(data);
		mChart.highlightValues(null);
		mChart.animateXY(1500, 1500);
		mChart.invalidate();
	}

	public void init(View view) {
		am10 = (TextView) view.findViewById(R.id.am10);
		am11 = (TextView) view.findViewById(R.id.am11);
		am12 = (TextView) view.findViewById(R.id.am12);
		am13 = (TextView) view.findViewById(R.id.am13);
		am14 = (TextView) view.findViewById(R.id.am14);
	}

	public void clear() {
		am10.setText(0 + "万元");
		am11.setText(0 + "月");
		am12.setText(0 + "元");
		am13.setText(0 + "万元");
		am14.setText(0 + "万元");
	}
}
