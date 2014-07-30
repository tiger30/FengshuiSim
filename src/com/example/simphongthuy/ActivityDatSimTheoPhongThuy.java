package com.example.simphongthuy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import model.BaseModel;
import model.MenhModelData;
import model.ModelSim;
import model.ModelTimSim;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import adapter.ParseJson;
import adapter.SimAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AbsListView.OnScrollListener;

public class ActivityDatSimTheoPhongThuy extends Activity {

	Handler handlerMain;
	AtomicBoolean atomic = null;

	ModelTimSim mModelTimSim;
	TextView lbl_tim_sim_info;
	ListView lv_show_sim;
	SimAdapter mSimAdapter;
	ArrayList<ModelSim> mArrSim = new ArrayList<ModelSim>();
	ArrayList<ModelSim> mTempArrSim;
	boolean loadingMore = true;
	int page = 1;
	ProgressDialog mDialog;
	MenhModelData mMenhModelData = new MenhModelData();
	ArrayList<BaseModel> mArrMenh = new MenhModelData().getArrMenh();
	ArrayAdapter<BaseModel> mArrAdapterMenh = null;
	BaseModel mMenhSelected = new BaseModel();
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_activity_dat_sim_theo_phong_thuy);
		getActionBar().setIcon(android.R.color.transparent);
		getActionBar().setBackgroundDrawable(new ColorDrawable(Color.rgb(58, 32, 14)));//[RIS] set color PHONG THUY
		getActionBar().setDisplayHomeAsUpEnabled(true);

		//loadDialog();

		Intent i = getIntent();
		Bundle b = i.getBundleExtra("DATA");
		
		
		mModelTimSim = (ModelTimSim) b.getSerializable("MODELTIMSIM");		
		String title = mMenhModelData.returnTenMenh(mModelTimSim.getLoaiMenh()).getName();
		getActionBar().setTitle(title.toUpperCase()); 
		
		lv_show_sim = (ListView) findViewById(R.id.lv_show_sim);
		lv_show_sim.setOnScrollListener(new OnScrollListener() {
			// useless here, skip!
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// what is the bottom iten that is visible
				int lastInScreen = firstVisibleItem + visibleItemCount;
				// is the bottom item visible & not loading more already ? Load
				// more !
				if ((lastInScreen == totalItemCount) && !(loadingMore)) {
					Log.i("afterrender totalItemCount = ",
							String.valueOf(totalItemCount));
					Log.i("afterrender loadingMore = ",
							String.valueOf(loadingMore));
					Thread thread = new Thread(null, loadMoreListItems);
					thread.start();
				}
			}
		});

		
		lv_show_sim
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int position, long arg3) {

						/*Toast.makeText(getApplicationContext(),
								"Item in position " + position + " clicked",
								Toast.LENGTH_LONG).show();*/
						
						
						Intent newIntent = new Intent(getApplicationContext(),
								ActivityDatSim.class);
						Bundle bundle = new Bundle();
						bundle.putSerializable("MODELDATSIM", mArrSim.get(position));
						newIntent.putExtra("DATA", bundle);
						startActivity(newIntent);
						
						
					}
				});

		mSimAdapter = new SimAdapter(this, R.layout.layout_item_custom, mArrSim);
		lv_show_sim.setAdapter(mSimAdapter);
	
		doStart(); // load all menh
	}

	public void loadDialog() {
		mDialog = new ProgressDialog(this);
		mDialog.setMessage("Please wait...");
		mDialog.setCancelable(false);
		mDialog.show();
	}

	private void doStart() {
		// clear and update lisview
		loadDialog();
		mArrSim.clear();
		mSimAdapter.notifyDataSetChanged();
		atomic = new AtomicBoolean(false);
		Thread thCon = new Thread(new Runnable() {
			@Override
			public void run() {
				loadingMore = true;
				String url = "http://phongthuysim.info/api2/sims";
				if (!url.endsWith("?"))
					url += "?";

				List<NameValuePair> params = new LinkedList<NameValuePair>();
				params.add(new BasicNameValuePair("page", String.valueOf(page)));
				params.add(new BasicNameValuePair("pagesize", "10"));
				params.add(new BasicNameValuePair("sim", mModelTimSim.getSim()));
				params.add(new BasicNameValuePair("price", mModelTimSim
						.getGia()));
				params.add(new BasicNameValuePair("provider", mModelTimSim
						.getLoaiMang()));
				params.add(new BasicNameValuePair("element", mModelTimSim.getLoaiMenh()));
				params.add(new BasicNameValuePair("day", mModelTimSim.getDay()));
				params.add(new BasicNameValuePair("month", mModelTimSim
						.getMonth()));
				params.add(new BasicNameValuePair("year", mModelTimSim
						.getYear()));
				String paramString = URLEncodedUtils.format(params, "utf-8");
				url += paramString;

				Log.i("url load main", url);

				HttpClient httpclient = new DefaultHttpClient();
				HttpGet httpget = new HttpGet(url);
				httpget.setHeader("X-SIMHOPTUOI-USERNAME", "android");
				httpget.setHeader("X-SIMHOPTUOI-PASSWORD", "android");

				// Execute the request
				HttpResponse response;
				try {
					response = httpclient.execute(httpget);
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						InputStream instream = entity.getContent();
						String result = convertStreamToString(instream);
						Log.i("value", result);
						if (mDialog.isShowing()) {
							mDialog.dismiss();
						}
						mTempArrSim = new ArrayList<ModelSim>();
						ParseJson mParseJson = new ParseJson();
						mTempArrSim = mParseJson.ParseJsonToArray(result);
						runOnUiThread(returnRes);
						// the list to refresh
						instream.close();
					}

				} catch (Exception e) {
					Log.i("error", e.toString());
				}
			}
		});

		atomic.set(true);
		// thực thi tiến trình
		thCon.start();
	}

	private static String convertStreamToString(InputStream is) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// app icon in action bar clicked; goto parent activity.
			this.finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	// Runnable to load the items
	private Runnable loadMoreListItems = new Runnable() {
		@Override
		public void run() {
			// Set flag so we cant load new items 2 at the same time
			loadingMore = true;
			// Reset the array that holds the new items
			mTempArrSim = new ArrayList<ModelSim>();

			String url = "http://phongthuysim.info/api2/sims";
			if (!url.endsWith("?"))
				url += "?";

			List<NameValuePair> params = new LinkedList<NameValuePair>();
			page++;
			params.add(new BasicNameValuePair("page", String.valueOf(page)));
			params.add(new BasicNameValuePair("pagesize", "10"));
			params.add(new BasicNameValuePair("sim", mModelTimSim.getSim()));
			params.add(new BasicNameValuePair("price", mModelTimSim.getGia()));
			params.add(new BasicNameValuePair("provider", mModelTimSim
					.getLoaiMang()));
			params.add(new BasicNameValuePair("element", mModelTimSim.getLoaiMenh()));
			params.add(new BasicNameValuePair("day", mModelTimSim.getDay()));
			params.add(new BasicNameValuePair("month", mModelTimSim.getMonth()));
			params.add(new BasicNameValuePair("year", mModelTimSim.getYear()));
			String paramString = URLEncodedUtils.format(params, "utf-8");
			url += paramString;

			Log.i("url load more", url);

			HttpClient httpclient = new DefaultHttpClient();
			HttpGet httpget = new HttpGet(url);
			httpget.setHeader("X-SIMHOPTUOI-USERNAME", "android");
			httpget.setHeader("X-SIMHOPTUOI-PASSWORD", "android");

			// Execute the request
			HttpResponse response;
			try {
				response = httpclient.execute(httpget);
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					InputStream instream = entity.getContent();
					String result = convertStreamToString(instream);
					Log.i("value", result);
					ParseJson mParseJson = new ParseJson();
					mTempArrSim = mParseJson.ParseJsonToArray(result);

					runOnUiThread(returnRes);
					instream.close();
				}

			} catch (Exception e) {
				Log.i("error", e.toString());
			}
		}
	};

	// Since we cant update our UI from a thread this Runnable takes care of
	// that!
	private Runnable returnRes = new Runnable() {
		@Override
		public void run() {
			// Loop thru the new items and add them to the adapter
			if (mTempArrSim != null && mTempArrSim.size() > 0) {
				for (int i = 0; i < mTempArrSim.size(); i++)
					mArrSim.add(mTempArrSim.get(i));
			}

			// the list to refresh
			mSimAdapter.notifyDataSetChanged();
			// Done loading more.
			loadingMore = false;
		}
	};
}

