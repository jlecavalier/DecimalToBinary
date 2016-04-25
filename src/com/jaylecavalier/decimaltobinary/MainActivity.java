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
			int input = Integer.parseInt(input_string);
			String output_string = toBinary(input);
			displayResult(output_string);
		}
	}

	private boolean isEmpty(EditText etext) {
		return etext.getText().toString().trim().length() == 0;
	}

	private String toBinary(int decimal) {
		return "0101";
	}

	private void displayResult(String binary) {
		TextView output_number = (TextView) findViewById(R.id.output_number);
		output_number.setText(binary);
		output_number.setVisibility(View.VISIBLE);
	}
}