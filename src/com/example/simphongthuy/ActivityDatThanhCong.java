package com.example.simphongthuy;

import model.ModelSim;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ActivityDatThanhCong extends Activity {
	ModelSim mSim;
	TextView txt_value_sim_thanh_cong;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity_dat_thanh_cong);
		getActionBar().setIcon(android.R.color.transparent);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setBackgroundDrawable(new ColorDrawable(Color.rgb(58, 32, 14)));//Set PHONG THUY COLOR
		
		Intent i = getIntent();
		Bundle b = i.getBundleExtra("DATA");
		mSim = (ModelSim) b.getSerializable("MODELDATSIM");
		txt_value_sim_thanh_cong = (TextView)findViewById(R.id.txt_value_sim_thanh_cong);
		txt_value_sim_thanh_cong.setText(mSim.getSimCode());
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_dat_thanh_cong, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// app icon in action bar clicked; goto parent activity.
			this.finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
