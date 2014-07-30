package model;

import java.util.ArrayList;

public class PriceModel extends BaseModel {
	PriceModel mPrice;
	ArrayList<PriceModel> mArrPrice;

	public PriceModel(String id, String name) {
		this.Id = id;
		this.Name = name;
	}
	
	public PriceModel() {
	}
}
