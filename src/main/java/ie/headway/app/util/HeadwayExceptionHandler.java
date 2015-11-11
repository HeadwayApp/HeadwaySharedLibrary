package ie.headway.app.util;

import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * An implementation of {@link java.lang.Thread.UncaughtExceptionHandler} which is registered for all
 * Headway apps in {@link HeadwaySplashScreenActivity#onCreate(Bundle)}.
 *
 * When an uncaught exception occurs the exception's stack trace is written to a timestamped log file
 * in the {@link AppDir#LOGS} directory.
 * */
public class HeadwayExceptionHandler implements Thread.UncaughtExceptionHandler {

  private static final String TAG = "HeadwayExceptionHandler";

  @Override
  public void uncaughtException(final Thread thread, final Throwable ex) {
    logExceptionToFile(ex);
  }

  /**
   * Writes the exception to a new timestamped log file in the {@link AppDir#LOGS} directory.
   *
   * @param ex The exception to be logged to file.
   * */
  private void logExceptionToFile(final Throwable ex) {
    final File logFile = AppDir.LOGS.getFile("headway_" + System.currentTimeMillis() + ".log");
    PrintStream ps = null;
    try {
      ps = new PrintStream(logFile);
      ex.printStackTrace(ps);
      ps.flush();
    } catch (FileNotFoundException e) {
      Log.e(TAG, "couldn't write exception to file:  " + ex.toString());
    } finally {
      if(ps != null) ps.close();
    }
  }

}
