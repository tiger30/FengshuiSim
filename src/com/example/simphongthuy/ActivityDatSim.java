package com.example.simphongthuy;

import model.ModelSim;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityDatSim extends Activity {

	ModelSim mSim;
	TextView txt_value_sim_ordered;
	TextView txt_value_sim_price_ordered;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity_dat_sim);
		getActionBar().setIcon(android.R.color.transparent);
		getActionBar().setBackgroundDrawable(new ColorDrawable(Color.rgb(58, 32, 14)));//[RIS] set color
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		Intent i = getIntent();
		Bundle b = i.getBundleExtra("DATA");
		mSim = (ModelSim) b.getSerializable("MODELDATSIM");
		
		txt_value_sim_ordered = (TextView)findViewById(R.id.txt_sim_dat_sim);
		txt_value_sim_ordered.setText(mSim.getSimCode());
		
		txt_value_sim_price_ordered = (TextView)findViewById(R.id.txt_sim_price);
		txt_value_sim_price_ordered.setText(mSim.getPrice());	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_dat_sim, menu);
		return true;
	}
	
	
	public void onClickDatSim(View view) {
		Log.i("a", "onclick list view item");
		Intent newIntentTimSim = new Intent(this,
				ActivityDatThanhCong.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("MODELDATSIM", mSim);
		newIntentTimSim.putExtra("DATA", bundle);
		startActivity(newIntentTimSim);
		
	}
 	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		View view = getCurrentFocus();
		boolean ret = super.dispatchTouchEvent(event);

		if (view instanceof EditText) {
			View w = getCurrentFocus();
			int scrcoords[] = new int[2];
			w.getLocationOnScreen(scrcoords);
			float x = event.getRawX() + w.getLeft() - scrcoords[0];
			float y = event.getRawY() + w.getTop() - scrcoords[1];

			if (event.getAction() == MotionEvent.ACTION_UP
					&& (x < w.getLeft() || x >= w.getRight() || y < w.getTop() || y > w
							.getBottom())) {
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(getWindow().getCurrentFocus()
						.getWindowToken(), 0);
			}
		}
		return ret;
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
