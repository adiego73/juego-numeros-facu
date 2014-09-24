package ar.com.adiego73.game;

import java.util.List;
import java.util.concurrent.ExecutionException;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import ar.com.adiego73.game.model.Score;
import ar.com.adiego73.game.sql.task.GetTask;

public class ScoresActivity extends ActionBarActivity {

	private ListView listScores;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scores);
		listScores = (ListView) findViewById(R.id.listScores);
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
			List<Score> scores = task.execute(new Void[1]).get();
			ArrayAdapter<Score> adapter = new ArrayAdapter<Score>(
					getBaseContext(), android.R.layout.simple_list_item_1,
					scores);
			listScores.setAdapter(adapter);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}
