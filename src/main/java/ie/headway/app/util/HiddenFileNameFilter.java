package ie.headway.app.util;


import java.io.File;
import java.io.FilenameFilter;

/**
 * FileNameFilter which does not list "hidden" files, i.e. files which are prepended with a '.'
 * */
public class HiddenFileNameFilter implements FilenameFilter {

  @Override
  public boolean accept(File dir, String filename) {
    if(!dir.getName().isEmpty() && dir.getName().charAt(0) == '.') {
      return false;
    }else if(!filename.isEmpty() && filename.charAt(0) == '.') {
      return false;
    }else {
      return true;
    }
  }

}
