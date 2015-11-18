package ie.headway.app.xml.task;

import org.simpleframework.xml.core.Persister;

import java.io.File;

import ie.headway.app.xml.SpecialSerializer;

import static com.google.common.base.Preconditions.checkState;

public class TaskPersister extends Persister implements SpecialSerializer<Task> {

  private final String mTaskName;

  public TaskPersister() {
    this(null);
  }

  public TaskPersister(final String taskName) {
    mTaskName = taskName;
  }

  /**
   * Reads the {@link Task} from file.
   *
   * @return The task.
   * @throws TaskNotFoundException if the task does not exist.
   * */
  @Override
  public Task read() throws TaskNotFoundException {
    checkState(mTaskName != null, "task name is null");
    final Task task = new Task(mTaskName, null);
    final File taskXmlFile = task.getTaskXmlFile();
    if(!taskXmlFile.exists()) throw new TaskNotFoundException(taskXmlFile + " does not exist");

    try {
      return read(Task.class, taskXmlFile);
    } catch (Exception e) {
      throw new RuntimeException("couldn't deserialize " + mTaskName, e);
    }
  }

  /**
   * Writes the {@link Task} to file.
   *
   * @param task The task to be serialised.
   * @throws RuntimeException thrown if serialiasation fails. No checked exception is thrown but {@link SpecialSerializer#write(Object)} declares one.
   * */
  @Override
  public void write(final Task task) throws RuntimeException {
    final File fileXmlFile = task.getTaskXmlFile();
    try {
      write(task, fileXmlFile);
    } catch (Exception e) {
      throw new RuntimeException("couldn't serialize " + task, e);
    }
  }

}
