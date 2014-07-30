package com.example.simphongthuy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import model.ModelChamDiem;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ShareActionProvider;
import android.widget.TextView;

public class ActivityChamDiem extends Activity {

	ModelChamDiem mModelChamDiem;
	TextView lbl_show_info;
	private ShareActionProvider mShareActionProvider;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity_cham_diem);
		getActionBar().setIcon(android.R.color.transparent);
		getActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.rgb(58, 32, 14)));// [RIS] set color
		getActionBar().setDisplayHomeAsUpEnabled(true);

		lbl_show_info = (TextView) findViewById(R.id.lbl_show_info);

		Intent i = getIntent();
		Bundle b = i.getBundleExtra("DATA");
		mModelChamDiem = (ModelChamDiem) b.getSerializable("MODELCHAMDIEM");

		connectURL();
	}

	/*
	 * using Async Task
	 */
	
	/*
	 * NOTE: execute calling html \ Author: RIS \ Date: 19/07/2014
	 */
	public void connectURL() {
		new Thread(new Runnable() {
			@Override
			public void run() {

				// Your implementation goes here
				// [RIS] set URL
				String url = "http://phongthuysim.info/api2/researchFengshui";
				if (!url.endsWith("?"))
					url += "?";

				List<NameValuePair> params = new LinkedList<NameValuePair>();
				params.add(new BasicNameValuePair("sim", mModelChamDiem
						.getSim()));
				params.add(new BasicNameValuePair("gender", mModelChamDiem
						.getGender()));
				params.add(new BasicNameValuePair("day", mModelChamDiem
						.getDay()));
				params.add(new BasicNameValuePair("month", mModelChamDiem
						.getMonth()));
				params.add(new BasicNameValuePair("year", mModelChamDiem
						.getYear()));
				String paramString = URLEncodedUtils.format(params, "utf-8");
				url += paramString;
				// [RIS] set URL ends here
				// [RIS] Prepare a request object
				HttpClient httpclient = new DefaultHttpClient();
				HttpGet httpget = new HttpGet(url);
				httpget.setHeader("X-SIMHOPTUOI-USERNAME", "android");
				httpget.setHeader("X-SIMHOPTUOI-PASSWORD", "android");
				// Execute the request
				HttpResponse response;
				try {
					response = httpclient.execute(httpget);
					// Examine the response status
					Log.i("Praeda", response.getStatusLine().toString());
					// Get hold of the response entity
					HttpEntity entity = response.getEntity();
					// If the response does not enclose an entity, there is
					// no need
					// to worry about connection release
					if (entity != null) {
						// A Simple JSON Response Read
						InputStream instream = entity.getContent();
						String result = convertStreamToString(instream);
						// now you have the string representation of the
						// HTML request
						lbl_show_info.setText(Html.fromHtml(result));
						Log.i("Praeda", result);
						instream.close();
					}
				} catch (Exception e) {
					Log.i("RIS: thread error", e.toString());
				}
				// connect(url);
			}
		}).start();
	}

	private static String convertStreamToString(InputStream is) {
		/*
		 * To convert the InputStream to String we use the
		 * BufferedReader.readLine() method. We iterate until the BufferedReader
		 * return null which means there's no more data to read. Each line will
		 * appended to a StringBuilder and returned as String.
		 */
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
				Log.i("value", line);
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

	/*
	 * endNOTE: execute calling html \ Author: RIS Date: 19/07/2014
	 */

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_cham_diem, menu);
		MenuItem item = menu.findItem(R.id.menu_item_share);
		// Fetch and store ShareActionProvider
	    mShareActionProvider = (ShareActionProvider) item.getActionProvider();
	    // Create the share Intent
	    String yourShareText = lbl_show_info.getText().toString();
	    Intent shareIntent = ShareCompat.IntentBuilder.from(this)
	        .setType("text/plain").setText(yourShareText).getIntent();
	    // Set the share Intent
	    mShareActionProvider.setShareIntent(shareIntent);
		// Return true to display menu
		return true;
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

}
