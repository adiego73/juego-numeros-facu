package ar.com.adiego73.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import ar.com.adiego73.game.model.Game;
import ar.com.adiego73.game.utils.EventFactory;

// TODO: revisar esto: http://developer.android.com/training/basics/data-storage/databases.html

public class GameActivity extends ActionBarActivity {

	private List<EditText> numbers;
	private TextView plantillaResultados;
	private Game game = new Game();

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
			et.addTextChangedListener(EventFactory.createTextWatcher(et, next));
			num++;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
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
			numbers.get(0).requestFocus();
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
			plantillaResultados.setText("");
		}
		numbers.get(0).requestFocus();
	}

	public void click_reiniciar(View v) {
		this.showMessage("Perdiste", "En "+game.getIntentos()+" intentos no adivinaste el numero: "+game.getNumeroAdivinar());
		game.build();
		this.resetNumbers();
		numbers.get(0).requestFocus();
		plantillaResultados.setText("");
	}

	private void showMessage(String title, String message) {
		new AlertDialog.Builder(GameActivity.this).setTitle(title).setMessage(message)
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
