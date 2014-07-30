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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentTimSimTheoMenh extends Fragment {
	Button btn1, btn2, btn3, btn4, btn5;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.layout_sim_hop_menh, container,
				false);
		btn1 = (Button) v.findViewById(R.id.btn_sim_menh_kim);
		btn2 = (Button) v.findViewById(R.id.btn_sim_menh_moc);
		btn3 = (Button) v.findViewById(R.id.btn_sim_menh_thuy);
		btn4 = (Button) v.findViewById(R.id.btn_sim_menh_hoa);
		btn5 = (Button) v.findViewById(R.id.btn_sim_menh_tho);

		btn1.setOnClickListener(new onMyClick());
		btn2.setOnClickListener(new onMyClick());
		btn3.setOnClickListener(new onMyClick());
		btn4.setOnClickListener(new onMyClick());
		btn5.setOnClickListener(new onMyClick());

		return v;
	}

	public class onMyClick implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btn_sim_menh_kim:
				onSearch("1");
				break;

			case R.id.btn_sim_menh_moc:
				onSearch("2");
				break;

			case R.id.btn_sim_menh_thuy:
				onSearch("3");
				break;

			case R.id.btn_sim_menh_hoa:
				onSearch("4");
				break;

			case R.id.btn_sim_menh_tho:
				onSearch("5");
				break;

			default:
				break;
			}
		}

	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.right_menu, menu);
	}

	public void onSearch(String mLoaiMenh) {
		ModelTimSim mModelTimSim = new ModelTimSim("", "", "", "", "", "",
				mLoaiMenh);

		Intent newIntentTimSim = new Intent(getActivity(),
				ActivityTimSimTheoMenh.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("MODELTIMSIM", mModelTimSim);
		newIntentTimSim.putExtra("DATA", bundle);
		startActivity(newIntentTimSim);
	}

}