package model;

import java.util.ArrayList;

public class MangModel extends BaseModel {
	MangModel mMang;
	ArrayList<MangModel> mArrMang;

	public MangModel(String id, String name) {
		this.Id = id;
		this.Name = name;
	}
	public MangModel() {
		
	}
}
