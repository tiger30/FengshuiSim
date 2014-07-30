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

import java.util.ArrayList;

import model.ProviderModel;
import model.ProviderModelData;
import model.ModelTimSim;
import model.PriceModel;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class FragmentTimSimPhongThuy extends Fragment {

	Spinner cb_tim_sim_mang, cb_tim_sim_gia;
	Button btn_tim_sim_search;
	// cặp đối tượng dùng cho Spinner
	
	
	ProviderModelData mMangModelData = new ProviderModelData();
	ArrayList<ProviderModel> mArrMang = mMangModelData.getArrMang();
	
	
	ArrayAdapter<ProviderModel> mAdapterMang = null;

	// cặp đối tượng dùng cho Spinner
	ArrayList<PriceModel> mArrPrice = new ArrayList<PriceModel>();
	ArrayAdapter<PriceModel> mAdapterPrice = null;

	ProviderModel mMangModelSelected;
	PriceModel mPriceModelSelected;
	ModelTimSim mModelTimSim;

	EditText txt_tim_sim_sim, txt_tim_sim_ngay, txt_tim_sim_thang,
			txt_tim_sim_nam;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.layout_tim_sim, container, false);
		cb_tim_sim_mang = (Spinner) v.findViewById(R.id.cb_tim_sim_mang);
		cb_tim_sim_gia = (Spinner) v.findViewById(R.id.cb_tim_sim_gia);
		btn_tim_sim_search = (Button) v.findViewById(R.id.btn_tim_sim_search);

		txt_tim_sim_sim = (EditText) v.findViewById(R.id.txt_tim_sim_sim);
		txt_tim_sim_ngay = (EditText) v.findViewById(R.id.txt_tim_sim_ngay);
		txt_tim_sim_thang = (EditText) v.findViewById(R.id.txt_tim_sim_thang);
		txt_tim_sim_nam = (EditText) v.findViewById(R.id.txt_tim_sim_nam);

		loadForm();
		return v;
	}

	private void loadForm() {
		initDataPrice();		
		mMangModelSelected = mArrMang.get(0);
		mPriceModelSelected = mArrPrice.get(0);
		

		// TODO Auto-generated method stub
		// Cấu hình cho Spinner
		mAdapterMang = new ArrayAdapter<ProviderModel>(getActivity(),
				android.R.layout.simple_spinner_item, mArrMang);

		mAdapterMang
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cb_tim_sim_mang.setAdapter(mAdapterMang);
		cb_tim_sim_gia.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				mMangModelSelected = mArrMang.get(arg2);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

		mAdapterPrice = new ArrayAdapter<PriceModel>(getActivity(),
				android.R.layout.simple_spinner_item, mArrPrice);

		mAdapterPrice
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cb_tim_sim_gia.setAdapter(mAdapterPrice);
		
		cb_tim_sim_gia.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				mPriceModelSelected = mArrPrice.get(arg2);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

		btn_tim_sim_search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stubt
				mModelTimSim = new ModelTimSim(txt_tim_sim_sim.getText()
						.toString(), mPriceModelSelected.getId(),
						mMangModelSelected.getId(), txt_tim_sim_ngay.getText()
								.toString(), txt_tim_sim_thang.getText()
								.toString(), txt_tim_sim_nam.getText()
								.toString(), "");
				
				
				Intent newIntentTimSim = new Intent(getActivity(),
						ActivityTimSim.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("MODELTIMSIM", mModelTimSim);
				newIntentTimSim.putExtra("DATA", bundle);
				startActivity(newIntentTimSim);
				
			}
		});
	}
	public void initDataPrice() {
		PriceModel mPrice;
		mPrice = new PriceModel("1", "Dưới 200.000");
		mArrPrice.add(mPrice);
		mPrice = new PriceModel("2", "2 trăm - 5 trăm");
		mArrPrice.add(mPrice);
		mPrice = new PriceModel("3", "5 trăm - 1 triệu");
		mArrPrice.add(mPrice);
		mPrice = new PriceModel("4", "1 triệu - 1.5 triệu");
		mArrPrice.add(mPrice);
		mPrice = new PriceModel("5", "1.5 triệu - 2 triệu");
		mArrPrice.add(mPrice);
		mPrice = new PriceModel("6", "2 triệu - 3 triệu");

		mPrice = new PriceModel("7", "3 triệu - 4 triệu");
		mArrPrice.add(mPrice);
		mPrice = new PriceModel("8", "4 triệu - 6 triệu");
		mArrPrice.add(mPrice);
		mPrice = new PriceModel("9", "6 triệu - 8 triệu");
		mArrPrice.add(mPrice);
		mPrice = new PriceModel("10", "8 triệu - 10 triệu");
		mArrPrice.add(mPrice);
		mPrice = new PriceModel("11", "10 triệu - 12 triệu");
		mArrPrice.add(mPrice);
		mPrice = new PriceModel("12", "12 triệu - 20 triệu");
		mArrPrice.add(mPrice);
		mPrice = new PriceModel("13", "20 triệu - 60 triệu");
		mArrPrice.add(mPrice);
		mPrice = new PriceModel("14", "60 triệu - 100 triệu");
		mArrPrice.add(mPrice);
		mPrice = new PriceModel("15", "Trên 100 triệu");
		mArrPrice.add(mPrice);
	}
}