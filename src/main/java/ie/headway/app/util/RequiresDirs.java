package ie.headway.app.util;

import ie.headway.app.xml.task.Task;

/**
 * Implemented by any class who's objects require a
 * specific directory structure in order to work,
 * <p/>
 * e.g. a {@link Task} object has an associated Task and Task/img directory associated with it.
 */
public interface RequiresDirs {

  void makeRequiredDirs();

}
