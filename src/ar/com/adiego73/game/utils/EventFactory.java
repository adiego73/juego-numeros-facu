package ar.com.adiego73.game.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class EventFactory {

	public static TextWatcher createTextWatcher(final EditText editText,
			final EditText editTextNext) {
		
		TextWatcher watcher = new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if (editText.getText().length() == 1) {
					if (editTextNext != null) {
						editTextNext.requestFocus();
					}
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			
			@Override
			public void afterTextChanged(Editable s) {
			}
		};
		
		return watcher;
	}

}
