package ar.com.adiego73.game.sql.task;

import java.util.List;

import android.content.Context;
import android.os.AsyncTask;
import ar.com.adiego73.game.model.Score;
import ar.com.adiego73.game.sql.dao.ScoreDAO;

public class GetTask extends AsyncTask<Void, Void, List<Score>> {

	private ScoreDAO scoreDao;

	public GetTask(Context c) {
		this.scoreDao = new ScoreDAO(c);
	}

	@Override
	protected List<Score> doInBackground(Void... params) {
		return this.scoreDao.getScores();
	}

}
