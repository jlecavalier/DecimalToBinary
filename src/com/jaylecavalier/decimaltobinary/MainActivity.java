package com.jaylecavalier.decimaltobinary;

// Android stuff
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

// ActionBarSherlock stuff
import com.actionbarsherlock.app.SherlockFragmentActivity;

// My stuff
import com.jaylecavalier.decimaltobinary.R;

public class MainActivity extends SherlockFragmentActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
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