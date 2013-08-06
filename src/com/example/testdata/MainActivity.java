package com.example.testdata;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;

public class MainActivity extends Activity {

	// Database fields
	private SQLiteDatabase database;
	private MySqlLiteHelper dbHelper;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button button;
		
		//Kermit button
        button = (Button) findViewById(R.id.btnKermit);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	addKermit();
            }
        });

		//Fuzzy button
        button = (Button) findViewById(R.id.btnFuzzy);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	addFuzzy();
            }
        });

        //show all button
        button = (Button) findViewById(R.id.btnRefresh);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	refresh();
            }
        });
	}
	
	
	@Override
	protected void onResume() {
		open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		close();
		super.onPause();
	}

	
	public void open(){
		dbHelper = new MySqlLiteHelper(this);
		database = dbHelper.getWritableDatabase();
	}
	

	public void close() {
		dbHelper.close();
	}
		
		
	public void addKermit(){
		//add user called kermit
		dbHelper.addNewUserProperly(database, "Kermit");
	}

		
	public void addFuzzy(){
		//add user called fuzzy
		popup();
	}

		
	public void refresh(){
		//get fragment to refresh
		ShowFragment viewer = (ShowFragment) getFragmentManager().findFragmentById(R.id.fragment1);
	    viewer.showAll();
	}

	
	public void popup(){
		AlertDialog.Builder alert = new AlertDialog.Builder(this);

		alert.setTitle("Title");
		alert.setMessage("Message");

		// Set an EditText view to get user input 
		final EditText input = new EditText(this);
		alert.setView(input);

		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				String value = input.getText().toString();
				dbHelper.addNewUserProperly(database,value);
			}
		});

		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				// Canceled.
			}
		});

		alert.show();
	}
	
	
}
