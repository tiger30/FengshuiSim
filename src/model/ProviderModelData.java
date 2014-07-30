package model;

import java.util.ArrayList;

public class ProviderModelData {
	ArrayList<ProviderModel> mArrMang =  new ArrayList<ProviderModel>();
	public ProviderModelData() {
		initDataMang();
	}
	public ArrayList<ProviderModel> initDataMang() {

		mArrMang.add(new ProviderModel("1", "Viettel"));
		mArrMang.add(new ProviderModel("2", "Mobifone"));
		mArrMang.add(new ProviderModel("3", "Vinaphone"));
		mArrMang.add(new ProviderModel("4", "Vietnamobile"));
		mArrMang.add(new ProviderModel("5", "GMobile"));
		mArrMang.add(new ProviderModel("7", "SFone"));
		return mArrMang;
	}

	public ArrayList<ProviderModel> getArrMang() {
		return mArrMang;
	}
	public ProviderModel returnProviderName(String id) {
		for (ProviderModel mMang : mArrMang) {
			if (mMang.getId().equals(id)) {
				return mMang; // gotcha!
			}
		}
		return null;
	}
}
