package model;

import java.util.ArrayList;

public class ProviderModel extends BaseModel {
	ProviderModel mMang;
	ArrayList<ProviderModel> mArrMang;

	public ProviderModel(String id, String name) {
		this.Id = id;
		this.Name = name;
	}
	public ProviderModel() {
		
	}
}
