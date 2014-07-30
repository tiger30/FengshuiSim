package model;

import java.util.ArrayList;

public class MenhModelData {
	ArrayList<BaseModel> mArrMenh = new ArrayList<BaseModel>();

	public MenhModelData() {
		initDataMang();
	}

	public ArrayList<BaseModel> initDataMang() {

		mArrMenh.add(new ProviderModel("1", "Sim Mệnh Kim"));
		mArrMenh.add(new ProviderModel("2", "Sim Mệnh Mộc"));
		mArrMenh.add(new ProviderModel("3", "Sim Mệnh Thủy"));
		mArrMenh.add(new ProviderModel("4", "Sim Mệnh Hỏa"));
		mArrMenh.add(new ProviderModel("5", "Sim Mệnh  Thổ"));
		return mArrMenh;
	}

	public ArrayList<BaseModel> getArrMenh() {
		return mArrMenh;
	}

	public BaseModel returnTenMenh(String id) {
		for (BaseModel mMenh : mArrMenh) {
			if (mMenh.getId().equals(id)) {
				return mMenh; // gotcha!
			}
		}
		return null;
	}
}
