package ar.com.adiego73.game;

import java.util.List;
import java.util.concurrent.ExecutionException;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;
import ar.com.adiego73.game.attempt.adapter.ScoreAdapter;
import ar.com.adiego73.game.model.Score;
import ar.com.adiego73.game.sql.task.GetTask;

public class ScoresActivity extends ActionBarActivity {

	private ListView listScores;
	private ScoreAdapter scoreAdapter;
	private List<Score> scores;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scores);
		listScores = (ListView) findViewById(R.id.listScores);
		this.scoreAdapter = new ScoreAdapter(getBaseContext(), scores);
		listScores.setAdapter(scoreAdapter);
	}

	@Override
	protected void onStart() {
		super.onStart();

		this.fillListView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.scores, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_back) {
			onBackPressed();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void fillListView() {
		GetTask task = new GetTask(getBaseContext());
		try {
			scores = task.execute(new Void[1]).get();
			this.scoreAdapter.notifyDataSetChanged();
		} catch (InterruptedException e) {
			Toast.makeText(getApplicationContext(), e.getMessage(),
					Toast.LENGTH_LONG).show();
		} catch (ExecutionException e) {
			Toast.makeText(getApplicationContext(), e.getMessage(),
					Toast.LENGTH_LONG).show();
		}
	}
}
