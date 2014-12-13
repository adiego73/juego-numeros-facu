package ar.com.adiego73.game;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;
import ar.com.adiego73.game.utils.AssetsHelper;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		AssetsHelper.build(getAssets());
		
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().clearFlags(
				WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
		
		((TextView) findViewById(R.id.txt_appName)).setTypeface(AssetsHelper.getDolceFontTypeFace());
	}
}
