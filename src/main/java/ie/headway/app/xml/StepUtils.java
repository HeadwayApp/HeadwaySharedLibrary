package ie.headway.app.xml;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

import ie.headway.app.util.AppDir;

public final class StepUtils {

  private StepUtils() {
  }

  public static PortableStep contextualiseStep(final Step step, final Task task) {
    final File imgFile = new File(step.getImagePath());
    final File newImg = new File(AppDir.ROOT.getPath(task.getName(), "imgs", task.getStepCount() + ".jpg"));

    try {
      FileUtils.moveFile(imgFile, newImg);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return new PortableStep(step.getText(), newImg.getAbsolutePath(), "");
  }

}
