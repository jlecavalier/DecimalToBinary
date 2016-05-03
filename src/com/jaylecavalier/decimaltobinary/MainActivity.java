package com.jaylecavalier.decimaltobinary;

// Android stuff
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

// ActionBarSherlock stuff
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;

// Java Standard Libray stuff
import java.util.Arrays;
import java.util.List;

// My stuff
import com.jaylecavalier.decimaltobinary.R;

public class MainActivity extends SherlockFragmentActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupActionBar();
		setContentView(R.layout.main);
		setupSpinners();
		setFonts();
		setButtonColors();
	}

    // Basically, the only reason we need to create this custom
    // spinner adapter class is so that we can set a custom font for the spinner items.
    // Why the developers from the android team decided to make this such a difficult thing
    // to do? I shall never know.
	private class MySpinnerAdapter extends ArrayAdapter<CharSequence> {
		// Get a handle to the font we want
	    Typeface monoFont = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/FreeMono.ttf");

	    // Constructor simply calls the real constructor.
	    private MySpinnerAdapter(Context context, int resource, List<CharSequence> items) {
	        super(context, resource, items);
	    }

	    // Affects default (closed) state of the spinner
	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	        TextView tv = (TextView) super.getView(position, convertView, parent);
	        tv.setTypeface(monoFont);
	        tv.setTextColor(getApplicationContext().getResources().getColor(R.color.homebrew_green));
	        return tv;
	    }

	    // Affects opened state of the spinner
	    @Override
	    public View getDropDownView(int position, View convertView, ViewGroup parent) {
	        TextView tv = (TextView) super.getDropDownView(position, convertView, parent);
	        tv.setTypeface(monoFont);
	        tv.setTextColor(getApplicationContext().getResources().getColor(R.color.homebrew_green));
	        return tv;
	    }
	}

	private void setupSpinners() {
		Spinner spinner = (Spinner) findViewById(R.id.number_systems_spinner_1);
		// Create an ArrayAdapter using the string array and a default spinner layout
		MySpinnerAdapter adapter = new MySpinnerAdapter(
        	getApplicationContext(),
        	R.layout.spinner_item,
        	Arrays.asList((CharSequence[]) getResources().getStringArray(R.array.number_systems_spinner))
		);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(R.layout.spinner_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
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
				tv.setTextColor(getResources().getColor(R.color.homebrew_green));
			}
		}
	}

	private void setButtonColors() {
		LinearLayout mainLayout = (LinearLayout) findViewById(R.id.main_layout);
		for (int i = 0; i < mainLayout.getChildCount(); i++) {
			if (mainLayout.getChildAt(i) instanceof Button) {
				Button b = (Button) mainLayout.getChildAt(i);
				b.setBackgroundColor(getResources().getColor(R.color.homebrew_green));
				b.setTextColor(getResources().getColor(R.color.homebrew_blue));
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