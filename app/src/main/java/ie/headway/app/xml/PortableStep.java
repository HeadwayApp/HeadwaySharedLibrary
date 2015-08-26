package ie.headway.app.xml;

import android.os.Environment;

public class PortableStep extends Step {

	/**
	 * This constant represents the system dependant path element which PortableStep will resolve.
	 * */
	public static final String PATH_ARTIFACT = "EXTERNAL_STORAGE_DIRECTORY";
	
	@Override
	public String getImagePath() {
		return super.getImagePath().replace(
				PATH_ARTIFACT, Environment.getExternalStorageDirectory().getPath());
	}
	
}
