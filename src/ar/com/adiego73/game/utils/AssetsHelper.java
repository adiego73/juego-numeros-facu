package ar.com.adiego73.game.utils;

import android.content.res.AssetManager;
import android.graphics.Typeface;

public class AssetsHelper {
	private static final String FONT_PATH_DOLCE = "fonts/dolce.ttf";
	private static final String FONT_PATH_CODE = "fonts/code.otf";
	private static AssetsHelper instance = null;
	private AssetManager manager = null;
	
	public static void build(AssetManager am){
			if(instance == null){
				instance = new AssetsHelper();
				instance.manager = am;
			}
	}
	
	public static Typeface getDolceFontTypeFace(){
		Typeface typeFace = Typeface.createFromAsset(instance.manager, FONT_PATH_DOLCE);
		return typeFace;
	}
	
	public static Typeface getCodeFontTypeFace(){
		Typeface typeFace = Typeface.createFromAsset(instance.manager, FONT_PATH_CODE);
		return typeFace;
	}
	
}
