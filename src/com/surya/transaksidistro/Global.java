/* Nama file  : Global.java
 * Website    : www.aplysit.com
 * Deskripsi  : - Menangani koneksi data dengan web server.   
 *              - Menangani pemrosesan data yang diterima dari server
 *              - Maksimal atribut yang dapat diproses adalah sebanyak 5
 * Author     : Ivan Michael Siregar and team
 **/

package com.surya.transaksidistro;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

public class Global {
	// Gunakan ini jika port http yang Anda gunakan adalah defaul 80
	// private static String url = "http://10.0.2.2/project/autofilljson/";

	// Gunakan ini jika port http yang Anda gunakan adalah 8080
	private static String url = "http://10.0.2.2/api-barang/";

	private static String session = null;
	private static Integer jumlahbaris = 0;

	Context ctx;

	public Global(Context ctx) {
		this.ctx = ctx;
	}

	public String getUrl() {
		return this.url;
	}

	public String getSession() {
		return this.session;
	}

	public void setSession(String param) {
		this.session = param;
	}

	public Integer getJumlahBaris() {
		return this.jumlahbaris;
	}

	public void setJumlahBaris(Integer param) {
		this.jumlahbaris = param;
	}

	public String[][] getCollection(String url_param) throws JSONException {
		Json jParser = new Json();
		JSONObject json = jParser.getJSONFromUrl(this.getUrl() + url_param);
		JSONArray jarray = json.getJSONArray("barang");

		// Buka bagian ini untuk melihat berapa jumlah baris yang diterima
		// Toast.makeText(ctx, "Jumlah baris = "+jarray.length() ,10).show();

		String[][] anArray;
		anArray = new String[jarray.length()][5];
		setJumlahBaris(jarray.length());

		for (Integer i = 0; i < jarray.length(); i++) {
			anArray[i][0] = jarray.getJSONObject(i).getString("kode_barang");
			anArray[i][1] = jarray.getJSONObject(i).getString("nama_barang");
			anArray[i][2] = jarray.getJSONObject(i).getString("harga");
			// Buka bagian ini untuk melihat secara detil data yang diterima
			// Toast.makeText(ctx, i +" "+ anArray[i][1], 10).show();
		}

		return anArray;
	}

	public String[] getAllRowsAtSpecifiedVn(String[][] sourceArray,
			String param, Integer panjang) {

		String[] anArray = new String[this.getJumlahBaris()];

		for (Integer i = 0; i < panjang; i++) {
			if (param.equalsIgnoreCase("kode_barang")) {
				anArray[i] = sourceArray[i][0];
			} else if (param.equalsIgnoreCase("nama_barang")) {
				anArray[i] = sourceArray[i][1];
			} else if (param.equalsIgnoreCase("harga")) {
				anArray[i] = sourceArray[i][2];
			}
		}

		// anArray[0] = "a"; anArray[1] = "b"; anArray[2] = "c"; anArray[3] =
		// "d";

		return anArray;
	}

	// Bagian ini untukmendapatkan nilai pada sebuah sel
	// Parameter yang harus didefinisikan adalah nama field dan nilai pada field
	// tersebut
	// maka secara otomatis akan mengembalikan nilai field yang dicari.
	// Contoh:
	// Didefinisikan : v2 dan Semester 1
	// Dicari : v3
	// Maka akan dihasillan nilai pada v3 yang pada v2 nya bernilai Semeseter 1
	public String getOneCellByPairVnValue(String[][] sourceArray,
			String originalvn, String originalvnvalue, String targetvn,
			Integer panjang) {

		Integer i;
		String hasil = "";

		for (i = 0; i < panjang; i++) {
			if (originalvn.equalsIgnoreCase("kode_barang")) {
				if (sourceArray[i][0].equals(originalvnvalue)) {
					if (targetvn.equalsIgnoreCase("kode_barang")) {
						hasil = sourceArray[i][0];
					} else if (targetvn.equalsIgnoreCase("nama_barang")) {
						hasil = sourceArray[i][1];
					} else if (targetvn.equalsIgnoreCase("harga")) {
						hasil = sourceArray[i][2];
					}
				}
			} else if (originalvn.equalsIgnoreCase("nama_barang")) {
				if (sourceArray[i][1].equals(originalvnvalue)) {
					// hasil = "fuh";
					if (targetvn.equalsIgnoreCase("kode_barang")) {
						hasil = sourceArray[i][0];
					} else if (targetvn.equalsIgnoreCase("nama_barang")) {
						hasil = sourceArray[i][1];
					} else if (targetvn.equalsIgnoreCase("harga")) {
						hasil = sourceArray[i][2];
					}
				}
			} else if (originalvn.equalsIgnoreCase("harga")) {
				if (sourceArray[i][0].equals(originalvnvalue)) {
					if (targetvn.equalsIgnoreCase("kode_barang")) {
						hasil = sourceArray[i][0];
					} else if (targetvn.equalsIgnoreCase("nama_barang")) {
						hasil = sourceArray[i][1];
					} else if (targetvn.equalsIgnoreCase("harga")) {
						hasil = sourceArray[i][2];
					}
				}
			}
		}

		return hasil;
	}

}
