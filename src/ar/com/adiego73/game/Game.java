package ar.com.adiego73.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.TextView;
import ar.com.adiego73.game.model.AdivinarNumeroGame;
import ar.com.adiego73.game.utils.NumbersUtils;

// TODO: revisar esto: http://developer.android.com/training/basics/data-storage/databases.html

public class Game extends ActionBarActivity {

	private List<EditText> numbers;
	private TextView plantillaResultados;
	private AdivinarNumeroGame game = new AdivinarNumeroGame();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		this.plantillaResultados = (TextView) findViewById(R.id.plantillaResultados);
	}

	@Override
	protected void onStart() {
		super.onStart();

		numbers = new ArrayList<EditText>();
		numbers.add((EditText) findViewById(R.id.primerNumero));
		numbers.add((EditText) findViewById(R.id.segundoNumero));
		numbers.add((EditText) findViewById(R.id.tercerNumero));
		numbers.add((EditText) findViewById(R.id.cuartoNumero));

		Iterator<EditText> it = numbers.iterator();
		Integer num = 1;
		while (it.hasNext()) {
			EditText et = it.next();
			EditText next = it.hasNext() ? numbers.get(num) : null;
			et.setOnKeyListener(this.createKeyListener(et, next));
			num++;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void click_probarSuerte(View v) {
		ArrayList<Integer> numeros = getNumbersFromTextEdit(numbers);

		if (numeros.isEmpty()) {
			this.showMessage("Error", "Complete todos los numeros");
			return;
		}

		String result = game.probarNumeros(numeros);
		plantillaResultados.append(result + " intentos: "
				+ game.getIntentos() + "\n");
		this.resetNumbers();
		if (this.game.getGano()) {
			this.showMessage("Ganaste!",
					"Felicitaciones! Ganaste en " + game.getIntentos()
							+ " intentos.");
			game.build();
		}
	}

	public void click_reiniciar(View v) {
		game.build();
		plantillaResultados.setText("");
	}

	private OnKeyListener createKeyListener(final EditText editText,
			final EditText editTextNext) {
		OnKeyListener listener = new OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (isNumberKey(keyCode) && editText.getText().length() == 1) {
					if (editTextNext != null) {
						editTextNext.requestFocus();
					}
				}
				return false;
			}

			private boolean isNumberKey(int keyCode) {
				switch (keyCode) {
				case KeyEvent.KEYCODE_0:
				case KeyEvent.KEYCODE_1:
				case KeyEvent.KEYCODE_2:
				case KeyEvent.KEYCODE_3:
				case KeyEvent.KEYCODE_4:
				case KeyEvent.KEYCODE_5:
				case KeyEvent.KEYCODE_6:
				case KeyEvent.KEYCODE_7:
				case KeyEvent.KEYCODE_8:
				case KeyEvent.KEYCODE_9:
					return true;
				default:
					return false;
				}
			}
		};
		return listener;
	}

	private void showMessage(String title, String message) {
		new AlertDialog.Builder(Game.this).setTitle(title).setMessage(message)
				.setCancelable(true)
				.setPositiveButton("Ok", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				}).create().show();
	}

	private ArrayList<Integer> getNumbersFromTextEdit(List<EditText> numbers) {
		ArrayList<Integer> result = new ArrayList<Integer>();

		for (EditText et : numbers) {
			if (et.getText().length() == 0) {
				return new ArrayList<Integer>();
			}

			result.add(Integer.valueOf(et.getText().toString()));
		}

		return result;
	}

	private void resetNumbers() {
		for (EditText et : numbers) {
			et.setText("");
		}
	}
}
