package adapter;

import java.util.ArrayList;

import com.example.simphongthuy.R;

import model.ModelSim;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SimAdapter extends ArrayAdapter<ModelSim> {

	Activity context;
	int layoutId;
	ArrayList<ModelSim> mArrSim;

	public SimAdapter(Activity context, int textViewResourceId,
			ArrayList<ModelSim> objects) {
		super(context, textViewResourceId, objects);
		this.context = context;
		this.layoutId = textViewResourceId;
		this.mArrSim = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		convertView = context.getLayoutInflater().inflate(layoutId, null);

		TextView txtShortInfor = (TextView) convertView
				.findViewById(R.id.lbl_tim_sim_dinh_dang);
		// TextView txtDetailInfor = (TextView)
		// convertView.findViewById(R.id.lbl_tim_sim_phong_thuy);
		TextView txtMang = (TextView) convertView
				.findViewById(R.id.lbl_tim_sim_mang);
		TextView txtGia = (TextView) convertView
				.findViewById(R.id.lbl_tim_sim_gia);
		TextView txtDiem = (TextView) convertView
				.findViewById(R.id.lbl_tim_sim_diem);

		// [RIS] set image value
		ImageView imgElementIcon = (ImageView) convertView
				.findViewById(R.id.imgMenhAvatar);

		ModelSim mSim = mArrSim.get(position);

		txtShortInfor.setText(mSim.getSimCode());
		// txtDetailInfor.setText(mSim.getPhongThuy());
		txtMang.setText(mSim.getSimProvider());
		txtGia.setText(mSim.getPrice());
		txtDiem.setText(mSim.getSimPoint());
		// set image value
		// imgElementIcon.setImageResource(R.drawable.ic_launcher);
		if (mSim.p1 >= mSim.p2 && mSim.p1 >= mSim.p3 && mSim.p1 >= mSim.p4
				&& mSim.p1 >= mSim.p5) {
			imgElementIcon.setImageResource(R.drawable.ic_metal);
		} else if (mSim.p2 >= mSim.p1 && mSim.p2 >= mSim.p3
				&& mSim.p2 >= mSim.p4 && mSim.p2 >= mSim.p5) {
			imgElementIcon.setImageResource(R.drawable.ic_wood);
		} else if (mSim.p3 >= mSim.p1 && mSim.p3 >= mSim.p2
				&& mSim.p3 >= mSim.p4 && mSim.p3 >= mSim.p5) {
			imgElementIcon.setImageResource(R.drawable.ic_water);
		} else if (mSim.p4 >= mSim.p1 && mSim.p4 >= mSim.p2
				&& mSim.p4 >= mSim.p3 && mSim.p2 >= mSim.p5) {
			imgElementIcon.setImageResource(R.drawable.ic_fire);
		} else if (mSim.p5 >= mSim.p1 && mSim.p5 >= mSim.p2
				&& mSim.p5 >= mSim.p3 && mSim.p5 >= mSim.p4) {
			imgElementIcon.setImageResource(R.drawable.ic_earth);
		}
		/*
		 * if (mSim.p1 == mSim.p2 && mSim.p2 == mSim.p3 && mSim.p3 == mSim.p4 &&
		 * mSim.p4 == mSim.p5) {
		 * imgElementIcon.setImageResource(R.drawable.ic_launcher); }
		 */

		return convertView;
	}

}