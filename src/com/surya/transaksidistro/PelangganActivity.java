package com.surya.transaksidistro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PelangganActivity extends Activity {

	String var_nama, var_alamat, var_nohp;
	EditText nama, alamat, nohp;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pelanggan);

		nama = (EditText) findViewById(R.id.nama_editText);
		alamat = (EditText) findViewById(R.id.alamat_editText);
		nohp = (EditText) findViewById(R.id.tgllahir_editText);

		Button reset = (Button) findViewById(R.id.hapus_btn);
		reset.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "Transaksi Batal",
						Toast.LENGTH_SHORT).show();
				finish();
			}
		});

		Button submit = (Button) findViewById(R.id.kirim_btn);
		submit.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				var_nama = nama.getText().toString();
				var_alamat = alamat.getText().toString();
				var_nohp = nohp.getText().toString();
				Intent i = null;
				i = new Intent(PelangganActivity.this, PesananActivity.class);
				Bundle b = new Bundle();
				b.putString("parse_nama", var_nama);
				b.putString("parse_alamat", var_alamat);
				b.putString("parse_nohp", var_nohp);
				i.putExtras(b);
				startActivity(i);
				finish();
			}
		});
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent I = new Intent(getApplicationContext(), MainActivity.class);
		I.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(I);
		finish();
	}
}