package ar.com.adiego73.game.sql.task;

import android.content.Context;
import android.os.AsyncTask;
import ar.com.adiego73.game.dao.ScoreDAO;
import ar.com.adiego73.game.model.Score;

public class SaveTask extends AsyncTask<Score, Void, Void> {

	private ScoreDAO scoreDao;

	public SaveTask(Context c) {
		this.scoreDao = new ScoreDAO(c);
	}

	@Override
	protected Void doInBackground(Score... params) {
		for (Score s : params) {
			this.scoreDao.saveScore(s);
		}
		return null;
	}

}
