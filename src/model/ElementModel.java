package model;

import java.util.ArrayList;

public class ElementModel extends BaseModel {
	ElementModel mElement;
	ArrayList<ElementModel> mArrElement;

	public ElementModel(String id, String name) {
		this.Id = id;
		this.Name = name;
	}
	
	public ArrayList<ElementModel> initData() {
		mArrElement = new ArrayList<ElementModel>();
		mElement = new ElementModel("1", "Kim");
		mArrElement.add(mElement);
		mElement = new ElementModel("2", "Mộc");
		mArrElement.add(mElement);
		mElement = new ElementModel("3", "Thủy");
		mArrElement.add(mElement);
		mElement = new ElementModel("4", "Hỏa");
		mArrElement.add(mElement);
		mElement = new ElementModel("5", "Thổ");
		mArrElement.add(mElement);
		
		return mArrElement;

	}

}
