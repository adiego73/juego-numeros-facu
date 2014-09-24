package ar.com.adiego73.game.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ScoreDBHelper extends SQLiteOpenHelper {

	public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "adivinaElNumero.db";
    
    public ScoreDBHelper(Context c) {
    	super(c,DATABASE_NAME, null, DATABASE_VERSION);
	}
    
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(ScoreContract.SQL_CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
