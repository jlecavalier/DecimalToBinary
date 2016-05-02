package com.jaylecavalier.decimaltobinary;

// Android stuff
import android.app.ActivityManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

// ActionBarSherlock stuff
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;

// My stuff
import com.jaylecavalier.decimaltobinary.R;

public class MainActivity extends SherlockFragmentActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupActionBar();
		setContentView(R.layout.main);
		setFonts();
	}

	public void convert(View view) {
		EditText input_etext = (EditText) findViewById(R.id.input_number);
		if (!isEmpty(input_etext)) {
			String input_string = input_etext.getText().toString();
			try {
				long input = Long.parseLong(input_string);
				String output_string = toBinary(input);
				displayResult(output_string);
			}
			catch (NumberFormatException e) {
				displayResult("Cannot convert input :(");
			}
		}
	}

	private void setFonts() {
		// Get a handle for the mono font
		Typeface monoFont = Typeface.createFromAsset(getAssets(), "fonts/FreeMono.ttf");

		LinearLayout mainLayout = (LinearLayout) findViewById(R.id.main_layout);
		for (int i = 0; i < mainLayout.getChildCount(); i++) {
			if (mainLayout.getChildAt(i) instanceof TextView) {
				TextView tv = (TextView) mainLayout.getChildAt(i);
				tv.setTypeface(monoFont);
			}
		}
	}

	private void setupActionBar() {
		ActionBar ab = getSupportActionBar();
		int c = Color.parseColor("#0A782F");
		ColorDrawable cd = new ColorDrawable(c);
		ab.setBackgroundDrawable(cd);
	}

	private boolean isEmpty(EditText etext) {
		return etext.getText().toString().trim().length() == 0;
	}

	private String toBinary(long decimal) {
	    if (decimal < 0 || decimal >= Long.MAX_VALUE) {
	    	return "Cannot convert input :(";
	    }
		if (decimal == 0) {
			return "0";
		}
		String binary = "";
		long tmp = decimal;
		while (tmp != 0) {
			long rem = tmp % 2;
			binary = rem + binary;
			tmp = (long) Math.floor(tmp / 2);
		}
		return binary;
	}

	private void displayResult(String binary) {
		TextView output_number = (TextView) findViewById(R.id.output_number);
		output_number.setText(binary);
		output_number.setVisibility(View.VISIBLE);
	}
}