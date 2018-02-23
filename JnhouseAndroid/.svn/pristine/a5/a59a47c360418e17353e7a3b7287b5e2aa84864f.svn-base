package jnhouse.topwellsoft.com.jnhouse_android.ui.houseloan;

import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.AdapterView.OnItemSelectedListener;

public class GongjijinSpinnerSelectedListener implements OnItemSelectedListener {
	EditText et;

	public GongjijinSpinnerSelectedListener(EditText et) {
		this.et = et;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view,
			int position, long id) {
		if (position <= 4)
			et.setText(String.valueOf(4.0));
		else
			et.setText(String.valueOf(4.5));
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
	}

}
