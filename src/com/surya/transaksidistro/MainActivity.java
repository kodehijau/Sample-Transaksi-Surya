package com.surya.transaksidistro;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button btnTrx = (Button) findViewById(R.id.btnTransaksi);
		btnTrx.setOnClickListener(this);

		findViewById(R.id.btnTransaksi).setOnClickListener(this);
		findViewById(R.id.btnBantuan).setOnClickListener(this);
		findViewById(R.id.btnTentang).setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = null;
		switch (v.getId()) {
		case R.id.btnTransaksi:
			intent = new Intent(MainActivity.this, PelangganActivity.class);
			startActivity(intent);
			
			break;
		case R.id.btnBantuan:
			intent = new Intent(MainActivity.this, Bantuan.class);
			startActivity(intent);

			break;
		case R.id.btnTentang:
			intent = new Intent(MainActivity.this, Tentang.class);
			startActivity(intent);

			break;
		default:
			break;
		}

	}

}
