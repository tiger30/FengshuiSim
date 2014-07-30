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

import model.ModelChamDiem;
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

public class FragmentChamDiem extends Fragment {

	Button btn_cham_sim;
	EditText txt_cham_diem_sim, txt_cham_diem_ngay, txt_cham_diem_thang,
			txt_cham_diem_nam;

	Spinner cb_cham_diem_gioi_tinh;
	ModelChamDiem mModelChamDiem;
	String arrGender[] = { "Nam", "Nữ" };
	String mGenderSelected;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.layout_cham_diem_sim, container,
				false);

		cb_cham_diem_gioi_tinh = (Spinner) v
				.findViewById(R.id.cb_cham_diem_gioi_tinh);
		btn_cham_sim = (Button) v.findViewById(R.id.btn_cham_diem_sim);
		btn_cham_sim.setOnClickListener(new myEvent());

		txt_cham_diem_sim = (EditText) v.findViewById(R.id.txt_cham_diem_sim);

		txt_cham_diem_ngay = (EditText) v.findViewById(R.id.txt_cham_diem_ngay);
		txt_cham_diem_thang = (EditText) v
				.findViewById(R.id.txt_cham_diem_thang);
		txt_cham_diem_nam = (EditText) v.findViewById(R.id.txt_cham_diem_nam);

		// Gán Data source (arr) vào Adapter
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, arrGender);
		// phải gọi lệnh này để hiển thị danh sách cho Spinner
		adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		// Thiết lập adapter cho Spinner
		cb_cham_diem_gioi_tinh.setAdapter(adapter);
		// thiết lập sự kiện chọn phần tử cho Spinner
		cb_cham_diem_gioi_tinh.setOnItemSelectedListener(new MyProcessEvent());

		return v;
	}

	public class myEvent implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (v.getId() == R.id.btn_cham_diem_sim) {
				mModelChamDiem = new ModelChamDiem(txt_cham_diem_sim.getText()
						.toString(), mGenderSelected
						.toString(), txt_cham_diem_ngay.getText().toString(),
						txt_cham_diem_thang.getText().toString(),
						txt_cham_diem_nam.getText().toString());

				Intent newIntentChamDiem = new Intent(getActivity(),
						ActivityChamDiem.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("MODELCHAMDIEM", mModelChamDiem);
				newIntentChamDiem.putExtra("DATA", bundle);
				startActivity(newIntentChamDiem);
			}
		}

	}

	// Class tạo sự kiện
	private class MyProcessEvent implements OnItemSelectedListener {
		// Khi có chọn lựa thì vào hàm này
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			mGenderSelected = arrGender[arg2];
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
	}
}