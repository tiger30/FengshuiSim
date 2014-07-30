package adapter;

import java.util.ArrayList;

import model.ProviderModelData;
import model.ModelSim;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ParseJson {
	String OutputData = "";
	JSONObject jsonResponse;
	ArrayList<ModelSim> mArrSim = new ArrayList<ModelSim>();
	ProviderModelData mProviderModelData = new ProviderModelData();

	public ArrayList<ModelSim> ParseJsonToArray(String strJson) {
		try {

			jsonResponse = new JSONObject(strJson);
			JSONArray jsonMainNode = jsonResponse.optJSONArray("sims");
			int lengthJsonArr = jsonMainNode.length();
			for (int i = 0; i < lengthJsonArr; i++) {
				JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
				int id = Integer.parseInt(jsonChildNode.optString("id")
						.toString());
				String sim = jsonChildNode.optString("sim").toString();
				String code = jsonChildNode.optString("code").toString();
				String price = jsonChildNode.optString("price").toString();
				String provider = jsonChildNode.optString("provider")
						.toString();
				String mProvider = mProviderModelData.returnProviderName(provider)
						.getName();
				//Date: 12/07/14
				// RIS
				String point = jsonChildNode.optString("point").toString();
				// get element point
				int p1 = Integer.parseInt(jsonChildNode.optString("p1")
						.toString());
				int p2 = Integer.parseInt(jsonChildNode.optString("p2")
						.toString());
				int p3 = Integer.parseInt(jsonChildNode.optString("p3")
						.toString());
				int p4 = Integer.parseInt(jsonChildNode.optString("p4")
						.toString());
				int p5 = Integer.parseInt(jsonChildNode.optString("p5")
						.toString());
				
				//Date 15/07/2014
				mArrSim.add(new ModelSim(id, code, price, mProvider, point, p1,
						p2, p3, p4, p5));
				//RIS end here				
				 /*mArrSim.add(new ModelSim(id, code, mProvider, "Update late",
				 price));*/
				 
			}

		} catch (JSONException e) {

			e.printStackTrace();
		}
		return mArrSim;
	}
	// RIS: provider is not the value of elements.
	//
	/*
	 * public String returnMenhSim(int provider) { String mSimMenh =
	 * "Sim mệnh ";
	 * 
	 * switch(provider) { case 1: mSimMenh+="kim"; break; case 2:
	 * mSimMenh+="mộc"; break; case 3: mSimMenh+="thủy"; break; case 4:
	 * mSimMenh+="hỏa"; break; case 5: mSimMenh+="thổ"; break; };
	 * 
	 * return mSimMenh; }
	 */
}
