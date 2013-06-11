package com.surya.transaksidistro;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class PesananActivity extends Activity implements OnClickListener {

	TextView nama, alamat, nohp, txtNamaBarang, txtHargaBarang, txtJumlah,
			txtTotal;
	String get_nama, get_alamat, get_nohp;
	Integer jmlBarisSpiner = 0;
	Button btnSimpan;
	// Global 0(@@)0
	Global gSpiner = new Global(PesananActivity.this);
	String[][] collection = null;
	String currentVn = "";
	String kodeBarang = "";
	String namaBarang = "";
	String harga = "";
	Spinner spKodeBarang;
	Json jsonParser = new Json();
	int selectCurrent;
	// Progress Dialog
	public ProgressDialog pDialog;
	// url to create new product
	private static String url_create_product = "http://10.0.2.2/api-barang/insert-barang.php";

	// JSON Node names
	private static final String TAG_SUCCESS = "success";

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pesanan);

		btnSimpan = (Button) findViewById(R.id.btnSimpan);
		btnSimpan.setOnClickListener(this);
		// Inisialisasi 0(@@)0
		spKodeBarang = (Spinner) findViewById(R.id.spKodeBarang);
		selectCurrent = spKodeBarang.getSelectedItemPosition();
		nama = (TextView) findViewById(R.id.nama_textView);
		alamat = (TextView) findViewById(R.id.alamat_textView);
		nohp = (TextView) findViewById(R.id.nohp_textView);
		txtNamaBarang = (TextView) findViewById(R.id.txtNamaBarang);
		txtHargaBarang = (TextView) findViewById(R.id.txtHarga);
		txtJumlah = (TextView) findViewById(R.id.txtJumlah);
		txtTotal = (TextView) findViewById(R.id.txtTotal);

		Bundle b = getIntent().getExtras();
		get_nama = b.getString("parse_nama");
		get_alamat = b.getString("parse_alamat");
		get_nohp = b.getString("parse_nohp");

		nama.setText(get_nama);
		alamat.setText(get_alamat);
		nohp.setText(get_nohp);

		try {
			currentVn = "kode_barang";
			collection = gSpiner.getCollection("index.php");
			jmlBarisSpiner = gSpiner.getJumlahBaris();
			String[] barang = gSpiner.getAllRowsAtSpecifiedVn(collection,
					currentVn, jmlBarisSpiner);
			ArrayAdapter<String> adapter_barang = new ArrayAdapter<String>(
					this, android.R.layout.simple_spinner_item, barang);
			adapter_barang
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spKodeBarang.setAdapter(adapter_barang);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}

		// Klik Spinner 0(@@)0
		spKodeBarang.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parentView,
					View selectedItemView, int position, long id) {

				namaBarang = gSpiner.getOneCellByPairVnValue(collection,
						currentVn, spKodeBarang.getSelectedItem().toString(),
						"nama_barang", jmlBarisSpiner);
				harga = gSpiner.getOneCellByPairVnValue(collection, currentVn,
						spKodeBarang.getSelectedItem().toString(), "harga",
						jmlBarisSpiner);

				txtNamaBarang.setText(namaBarang);
				txtHargaBarang.setText(harga);

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		new CreateNewProduct().execute();
	}

	/*
	 * *
	 * Background asyntask simpan barang
	 */
	class CreateNewProduct extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(PesananActivity.this);
			pDialog.setMessage("Creating Product..");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		/**
		 * Creating product
		 * */
		protected String doInBackground(String... args) {
			String anama = nama.getText().toString();
			String aalamat = alamat.getText().toString();
			String ahp = nohp.getText().toString();
			String akode = spKodeBarang.getSelectedItem().toString();
			String aharga = txtHargaBarang.getText().toString();
			String ajml = txtJumlah.getText().toString();
			String atotal = txtTotal.getText().toString();

			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("nama", anama));
			params.add(new BasicNameValuePair("alamat", aalamat));
			params.add(new BasicNameValuePair("hp", ahp));
			params.add(new BasicNameValuePair("kode_barang", String
					.valueOf(akode)));
			params.add(new BasicNameValuePair("harga_barang", aharga));
			params.add(new BasicNameValuePair("jml_barang", ajml));
			params.add(new BasicNameValuePair("total", atotal));

			// getting JSON Object
			// Note that create product url accepts POST method
			JSONObject json = jsonParser.makeHttpRequest(url_create_product,
					"POST", params);

			// check log cat fro response
			Log.d("Create Response", json.toString());

			// check for success tag
			try {
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					// Toast.makeText(PesananActivity.this, "Berhasil order",
					// Toast.LENGTH_SHORT).show();
					// successfully created product
					Intent i = new Intent(PesananActivity.this,
							PelangganActivity.class);
					startActivity(i);

					// closing this screen
					finish();
				} else {
					// failed to create product
					Toast.makeText(getApplicationContext(), "FUCK",
							Toast.LENGTH_LONG).show();
				}
			} catch (JSONException e) {
				Log.d("gagal men :", e.getMessage());
			}

			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once done
			pDialog.dismiss();
		}

	}
}
