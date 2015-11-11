package ie.headway.app.xml;

import org.simpleframework.xml.core.Persister;

import java.io.File;

import ie.headway.app.util.AppDir;

public class TaskPersister extends Persister {

  public Task read(final String taskName) throws TaskNotFoundException {
    final File file = new File(AppDir.ROOT.getPath(taskName, "task.xml"));

    if(!file.exists()) throw new TaskNotFoundException(file.getAbsolutePath() + "does not exist");

    try {
      return read(Task.class, file);
    } catch (Exception e) {
      throw new RuntimeException("couldn't deserialize " + taskName);
    }
  }

  public void write(final Task task) {
    final File file = new File(AppDir.ROOT.getPath(task.getName(), "task.xml"));
    try {
      write(task, file);
    } catch (Exception e) {
      throw new RuntimeException("couldn't serialize " + task.toString());
    }
  }

}
