package ar.com.adiego73.game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import ar.com.adiego73.game.utils.AssetsHelper;

public class SplashActivity extends Activity {

	private ImageView playButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		AssetsHelper.build(getAssets());

		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().clearFlags(
				WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

		((TextView) findViewById(R.id.txt_appName)).setTypeface(AssetsHelper
				.getDolceFontTypeFace());

		playButton = (ImageView) findViewById(R.id.iv_play_splash);
	}

	@Override
	protected void onStart() {
		super.onStart();
		Toast.makeText(getApplicationContext(), "on start", Toast.LENGTH_SHORT)
				.show();
		Animation animation = AnimationUtils.loadAnimation(getBaseContext(),
				R.anim.play_animation);
		playButton.setAnimation(animation);
		playButton.startAnimation(animation);
	}

	public void click_play(View v) {
		Intent intent = new Intent(this, GameActivity.class);
		startActivity(intent);
	}

}
