package ie.headway.app.xml;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

import ie.headway.app.util.AppDir;

public final class StepUtils {

  private StepUtils() {
  }

  /**
   * TODO: Quick impl, needs refactoring.
   * */
  public static PortableStep contextualiseStep(final Step step, final Task task) {
    final File imgFile = new File(step.getImagePath());
    final File newImg = AppDir.ROOT.getFile(task.getName(), "imgs", task.getStepCount() + ".jpg");

    final String audPath = step.getAudioPath();
    final File newAud = !audPath.isEmpty() ? AppDir.ROOT.getFile(task.getName(), "auds", task.getStepCount() + ".3gp") : null;

    try {
      FileUtils.moveFile(imgFile, newImg);
      if(newAud != null) {
        FileUtils.moveFile(new File(audPath), newAud);
        return new PortableStep(step.getText(), newImg.getAbsolutePath(), newAud.getAbsolutePath());
      }
    } catch (IOException ioe) {
      throw new RuntimeException(ioe);
    }

    return new PortableStep(step.getText(), newImg.getAbsolutePath(), "");
  }

}
