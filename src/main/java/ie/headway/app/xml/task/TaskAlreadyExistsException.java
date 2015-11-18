package ie.headway.app.xml.task;

public class TaskAlreadyExistsException extends TaskException {

  public TaskAlreadyExistsException() {

  }

  public TaskAlreadyExistsException(final String detailMessage) {
    super(detailMessage);
  }

  public TaskAlreadyExistsException(final String detailMessage, final Throwable throwable) {
    super(detailMessage, throwable);
  }

  public TaskAlreadyExistsException(final Throwable throwable) {
    super(throwable);
  }

}
