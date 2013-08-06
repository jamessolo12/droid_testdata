package com.example.testdata;

import java.util.List;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.app.ListFragment;

public class ShowFragment extends ListFragment {

	private SQLiteDatabase database;
	private MySqlLiteHelper dbHelper;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		dbHelper = new MySqlLiteHelper(getActivity());
		database = dbHelper.getWritableDatabase();
		
		showAll();
	}
	
	public void showAll(){
		List<Muppet> values				= dbHelper.getAllUsers(database);
		final ArrayAdapter<Muppet> adapter = new ArrayAdapter<Muppet>(getActivity(), android.R.layout.simple_list_item_1, values);
		
		setListAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// Do something with the data
	}
}