package ie.headway.app.disk;

import java.io.File;

import android.os.Environment;

public enum AppDir {

	ROOT(Environment.getExternalStorageDirectory() + File.separator + "Headway");
	
	private final String mPath;
	
	private AppDir(String path) {
		mPath = path;
	}
	
	/**
	 * Returns a path with this AppDir as the parent to all the specified {@code children}, 
	 * e.g ROOT.getPath("tmp", "64") would return a path ROOT/tmp/64, where ROOT is itself a path.
	 * 
	 * @param children The child directories of the current AppDir.
	 * @return The path.
	 * */
	public String getPath(CharSequence... children) {
		final StringBuilder pathBuilder = new StringBuilder(mPath);
		for(CharSequence child: children) {
			pathBuilder.append(File.separator + child);
		}
		return pathBuilder.toString();
	}
	
	/**
	 * Returns a file with this AppDir as the parent to all the specified {@code children}, 
	 * e.g ROOT.getFile("tmp", "64") would return a file representing the path ROOT/tmp/64,
	 * where ROOT does itself represent a path.
	 * 
	 * @param children The child directories of the current AppDir.
	 * @return The file representing the new path.
	 * */
	public File getFile(CharSequence... children) {
		return new File(getPath(children));
	}
	
	/**
	 * Create any directories required by the app which do not currently exist.
	 * */
	public static void makeAppDirs() {
		for(final AppDir appDir: values()) {
			if(!appDir.getFile().exists()) {
				if(!appDir.getFile().mkdirs()) {	//mkdirs doesn't throw anything if it fails.
					throw new RuntimeException("Couldn't create " + appDir.getFile());
				}
			}
		}
	}
	
}
