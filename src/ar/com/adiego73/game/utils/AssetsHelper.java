package ar.com.adiego73.game.utils;

import android.content.res.AssetManager;
import android.graphics.Typeface;

public class AssetsHelper {
	private static final String FONT_PATH = "fonts/wolf.otf";
	
	public static Typeface getTypeFace(AssetManager manager){
		Typeface typeFace = Typeface.createFromAsset(manager, FONT_PATH);
		return typeFace;
	}
	
}
