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
	private SQLiteDatabase db;
	
	public ScoreDAO(Context c) {
		dbHelper = new ScoreDBHelper(c);
		db = dbHelper.getWritableDatabase();
	}
	
	public Long saveScore(Score score){
		ContentValues values = new ContentValues();
		
		values.put(ScoreContract.ScoreEntry.COLUMN_DATE, System.currentTimeMillis());
		values.put(ScoreContract.ScoreEntry.COLUMN_SCORE, score.getScore());
		
		return db.insert(ScoreContract.ScoreEntry.TABLE_NAME, null, values);
	}
	
	public List<Score> getScores(){
		List<Score> scores = new ArrayList<Score>();
		
		String[] columns = {ScoreContract.ScoreEntry.COLUMN_DATE, ScoreContract.ScoreEntry.COLUMN_SCORE};
		String orderBy = ScoreContract.ScoreEntry._ID + " ASC"; 
		
		Cursor cursor = db.query(ScoreContract.ScoreEntry.TABLE_NAME, columns, null, null, null, null, orderBy);

		cursor.moveToFirst();
		while(cursor.moveToNext()){
			Score s = new Score();
			s.setDate(new Date(cursor.getLong(cursor.getColumnIndexOrThrow(ScoreContract.ScoreEntry.COLUMN_DATE))));
			s.setScore(cursor.getInt(cursor.getColumnIndexOrThrow(ScoreContract.ScoreEntry.COLUMN_SCORE)));
			scores.add(s);
		}
		return scores;
	}
}
