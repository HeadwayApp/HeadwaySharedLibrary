package ie.headway.app.util;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import ie.headway.app.R;

import static ie.headway.app.util.AppDir.makeAppDirs;

public abstract class HeadwaySplashScreenActivity extends Activity {

  private static long SPLASH_SCREEN_TIMEOUT = 5000L;

	@Override
	public void onCreate(final Bundle savedInstanceBundle) {
		super.onCreate(savedInstanceBundle);
		Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
		fixBackStack();
		setContentView(R.layout.activity_splash_screen);
		makeAppDirs();
		exitSplashScreen(SPLASH_SCREEN_TIMEOUT);
	}

	/**
	 * This method is used to circumvent a bug which has existed in Android since API 1 whereby the
   * app appears to restart when it should resume,
   * see: http://stackoverflow.com/questions/18449541/android-application-restarts-when-opened-by-clicking-the-application-icon
   * for more information.
	 * */
	private void fixBackStack() {
		if (!isTaskRoot()) {
			finish();
		}
	}

  /**
   * Run the {@code runnable} on the main thread after {@code delay} milliseconds.
   *
   * @param runnable The runnable to run.
   * @param delay The amount of time in milliseconds which should elapse before running the runnable.
   * */
  protected void runAfterDelay(final Runnable runnable, final long delay) {
    final Handler handler = new Handler();
    handler.postDelayed(runnable, delay);
  }
	
	/**
	 * This method is called at the end of HeadwaySplashScreenActivity's onCreate(), subclasses should
   * override this method in order to decide what to do after the splash screen has displayed.
   *
   * @param delay The amount of time in milliseconds which should elapse before exiting the splash screen.
	 * */
	protected abstract void exitSplashScreen(final long delay);

}
