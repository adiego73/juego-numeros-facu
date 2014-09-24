package ar.com.adiego73.game.sql;

import android.provider.BaseColumns;

public final class ScoreContract {
	public ScoreContract() {
	}

	public static abstract class ScoreEntry implements BaseColumns {
		public static final String TABLE_NAME = "score";
		public static final String COLUMN_DATE = "date";
		public static final String COLUMN_SCORE = "score";
	}

	public static final String SQL_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ ScoreEntry.TABLE_NAME + " (" + ScoreEntry._ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + ScoreEntry.COLUMN_DATE
			+ " INTEGER, " + ScoreEntry.COLUMN_SCORE + " INTEGER" + ");";

	public static final String SQL_DELETE_TABLE = "DELETE TABLE IF EXISTS "+ ScoreEntry.TABLE_NAME;
}
