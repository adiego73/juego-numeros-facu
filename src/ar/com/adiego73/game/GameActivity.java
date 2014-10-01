package ar.com.adiego73.game;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import ar.com.adiego73.game.attempt.adapter.AttemptAdapter;
import ar.com.adiego73.game.model.Attempt;
import ar.com.adiego73.game.model.Game;
import ar.com.adiego73.game.model.Score;
import ar.com.adiego73.game.sql.dao.ScoreDAO;
import ar.com.adiego73.game.sql.task.SaveTask;
import ar.com.adiego73.game.utils.EventFactory;

public class GameActivity extends ActionBarActivity {

	private List<EditText> numbers;
	private ListView listAttempts;
	private AttemptAdapter attemptsAdapter;
	private List<Attempt> attempts;
	private Game game = new Game();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);

		this.attempts = new ArrayList<Attempt>();
		this.numbers = new ArrayList<EditText>();
		this.numbers.add((EditText) findViewById(R.id.primerNumero));
		this.numbers.add((EditText) findViewById(R.id.segundoNumero));
		this.numbers.add((EditText) findViewById(R.id.tercerNumero));
		this.numbers.add((EditText) findViewById(R.id.cuartoNumero));
		this.listAttempts = (ListView) findViewById(R.id.listAttempts);

		this.attemptsAdapter = new AttemptAdapter(getBaseContext(), attempts);
		listAttempts.setAdapter(attemptsAdapter);
	}

	@Override
	protected void onStart() {
		super.onStart();
		Iterator<EditText> it = numbers.iterator();
		Integer num = 1;
		while (it.hasNext()) {
			EditText et = it.next();
			EditText next = it.hasNext() ? numbers.get(num) : null;
			et.addTextChangedListener(EventFactory
					.createTextWatcher(
							et,
							next,
							(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)));
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

		switch (id) {
		case R.id.action_scores:
			goToScoresActivity();
			return true;
		case R.id.action_howto:
			goToHowtoActivity();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ScoreDAO.destroy();
	}

	public void click_probarSuerte(View v) {
		ArrayList<Integer> numeros = getNumbersFromTextEdit(numbers);

		if (numeros.isEmpty()) {
			this.showMessage("Error", "Complete todos los numeros");
			numbers.get(0).requestFocus();
			return;
		}

		Integer resultNum[] = game.probarNumeros(numeros);
		String bien = resultNum[0] > 0 ? resultNum[0] + "B" : "";
		String regular = resultNum[1] > 0 ? resultNum[1] + "R" : "";
		String result = bien + " " + regular;

		Attempt attempt = new Attempt();
		attempt.setHelp(result);
		attempt.setId(game.getIntentos());
		attempt.setNumber(getStringFromNumbers(numeros));

		addToListView(attempt);

		this.resetNumbers();

		if (this.game.getGano()) {
			this.showMessage("Ganaste!",
					"Felicitaciones! Ganaste en " + game.getIntentos()
							+ " intentos.");
			this.saveScore(game.getIntentos());
			game.build();
			emptyListView();
		}
		numbers.get(0).requestFocus();
	}

	public void click_reiniciar(View v) {
		this.showMessage(
				"Perdiste",
				"En " + game.getIntentos()
						+ " intentos no adivinaste el numero: "
						+ game.getNumeroAdivinar());
		game.build();
		this.resetNumbers();
		numbers.get(0).requestFocus();
		emptyListView();
	}

	private void saveScore(Integer intentos) {
		Score s = new Score();
		s.setDate(new Date(System.currentTimeMillis()));
		s.setScore(intentos);

		SaveTask task = new SaveTask(getBaseContext());
		task.execute(s);
	}

	private void goToScoresActivity() {
		Intent intent = new Intent(this, ScoresActivity.class);
		startActivity(intent);
	}

	private void goToHowtoActivity() {
		Intent intent = new Intent(this, HowToActivity.class);
		startActivity(intent);
	}

	private void showMessage(String title, String message) {
		new AlertDialog.Builder(GameActivity.this).setTitle(title)
				.setMessage(message).setCancelable(true)
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

	private void addToListView(Attempt attempt) {
		game.addAttempt(attempt);
		attempts.add(attempt);
		attemptsAdapter.notifyDataSetChanged();
	}

	private void emptyListView() {
		attempts = new ArrayList<Attempt>();
		attemptsAdapter = new AttemptAdapter(getBaseContext(), attempts);
		listAttempts.setAdapter(attemptsAdapter);
	}

	private String getStringFromNumbers(List<Integer> numbers) {
		String result = "";
		for (Integer g : numbers) {
			result += g.toString();
		}
		return result;
	}

	private void resetNumbers() {
		for (EditText et : numbers) {
			et.setText("");
		}
	}
}
