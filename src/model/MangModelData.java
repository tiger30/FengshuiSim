package model;

import java.util.ArrayList;

public class MangModelData {
	ArrayList<MangModel> mArrMang =  new ArrayList<MangModel>();
	public MangModelData() {
		initDataMang();
	}
	public ArrayList<MangModel> initDataMang() {

		mArrMang.add(new MangModel("1", "Viettel"));
		mArrMang.add(new MangModel("2", "Mobifone"));
		mArrMang.add(new MangModel("3", "Vinaphone"));
		mArrMang.add(new MangModel("4", "Vietnamobile"));
		mArrMang.add(new MangModel("5", "GMobile"));
		mArrMang.add(new MangModel("7", "SFone"));
		return mArrMang;
	}

	public ArrayList<MangModel> getArrMang() {
		return mArrMang;
	}
	public MangModel returnTenMang(String id) {
		for (MangModel mMang : mArrMang) {
			if (mMang.getId().equals(id)) {
				return mMang; // gotcha!
			}
		}
		return null;
	}
}
