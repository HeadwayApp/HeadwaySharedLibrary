package ie.headway.app;

import static ie.headway.app.disk.AppDir.makeAppDirs;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public abstract class HeadwaySplashScreenActivity extends Activity {

	public static final String TAG = "headway.jar";
	
	@Override
	public void onCreate(final Bundle savedInstanceBundle) {
		super.onCreate(savedInstanceBundle);
//		Log.d(TAG, "onCreate splash screen."); 
//		setContentView(R.layout.activity_splash_screen);
//		makeAppDirs();
//		exitSplashScreen(5000);
	}
	
	/**
	 * TODO Inspect this method to see if it can be improved,
	 * or if a more efficient approarch can be taken. This code
	 * was copy-pasta from stackOverflow and I don't know how it works.
	 * 
	 * Override this method to decide what will happen after the SplashScreen executes.
	 * 
	 * TODO ^^^Improve this javadoc, give better explanation.^^^
	 * */
	protected abstract void exitSplashScreen(final long delay);

}
