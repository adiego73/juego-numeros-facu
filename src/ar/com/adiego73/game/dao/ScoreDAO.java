package ar.com.adiego73.game.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import ar.com.adiego73.game.model.Score;
import ar.com.adiego73.game.sql.ScoreContract;
import ar.com.adiego73.game.sql.ScoreDBHelper;

public final class ScoreDAO {

	private ScoreDBHelper dbHelper;
	private static SQLiteDatabase db;

	public ScoreDAO(Context c) {
		dbHelper = new ScoreDBHelper(c);
	}

	public Long saveScore(Score score) {
		db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put(ScoreContract.ScoreEntry.COLUMN_DATE,
				System.currentTimeMillis());
		values.put(ScoreContract.ScoreEntry.COLUMN_SCORE, score.getScore());

		Long id = db.insert(ScoreContract.ScoreEntry.TABLE_NAME, null, values);
		return id;
	}

	public List<Score> getScores() {
		List<Score> scores = new ArrayList<Score>();

		db = dbHelper.getReadableDatabase();
		String[] columns = { ScoreContract.ScoreEntry._ID,
				ScoreContract.ScoreEntry.COLUMN_DATE,
				ScoreContract.ScoreEntry.COLUMN_SCORE };
		String orderBy = ScoreContract.ScoreEntry._ID + " ASC";

		Cursor cursor = db.query(ScoreContract.ScoreEntry.TABLE_NAME, columns,
				null, null, null, null, orderBy);

		cursor.moveToFirst();
		while (cursor.isAfterLast() == false) {
			Score s = new Score();
			s.setId(cursor.getInt(cursor
					.getColumnIndexOrThrow(ScoreContract.ScoreEntry._ID)));
			s.setDate(new Date(
					cursor.getLong(cursor
							.getColumnIndexOrThrow(ScoreContract.ScoreEntry.COLUMN_DATE))));
			s.setScore(cursor.getInt(cursor
					.getColumnIndexOrThrow(ScoreContract.ScoreEntry.COLUMN_SCORE)));
			scores.add(s);
			cursor.moveToNext();
		}
		cursor.close();
		return scores;
	}

	public static void destroy() {
		if (db.isOpen()) {
			db.close();
		}
	}
}
