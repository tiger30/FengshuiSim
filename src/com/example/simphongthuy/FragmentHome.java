package com.example.simphongthuy;

/*
 * Copyright (C) 2013 Surviving with Android (http://www.survivingwithandroid.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import model.ModelTimSim;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FragmentHome extends Fragment {

	EditText txt_home_number_phone;
	Button btn_trang_chu_tim_sim;
	ModelTimSim mModelTimSim;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.layout_trangchu, container, false);
		txt_home_number_phone = (EditText) v
				.findViewById(R.id.txt_home_number_phone);
		btn_trang_chu_tim_sim = (Button) v
				.findViewById(R.id.btn_trang_chu_tim_sim);
		
		loadFormAndEvent();
		
		
		return v;
	}

	public void loadFormAndEvent() {
		txt_home_number_phone
				.setOnEditorActionListener(new TextView.OnEditorActionListener() {
					@Override
					public boolean onEditorAction(TextView v, int actionId,
							KeyEvent event) {
						if (actionId == EditorInfo.IME_ACTION_SEARCH) {
							Log.i("abc", "da lang nghe su kien");
							onSearch();
							return true;
						}
						return false;
					}
				});

		btn_trang_chu_tim_sim.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onSearch();
			}
		});
	}

	public void onSearch() {
		mModelTimSim = new ModelTimSim(txt_home_number_phone.getText()
				.toString(), "", "", "", "", "", "1");

		Intent newIntentTimSim = new Intent(getActivity(), ActivityTimSim.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("MODELTIMSIM", mModelTimSim);
		newIntentTimSim.putExtra("DATA", bundle);
		startActivity(newIntentTimSim);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.right_menu, menu);
	}
}