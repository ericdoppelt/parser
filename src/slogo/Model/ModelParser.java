package slogo.Model;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ModelParser {

  /**
   * Simple parser based on regular expressions that matches input strings to kinds of program elements.
   * Based off of ProgramParser by Robert Duvall
   * 2/20/2020
   * @author Frank Tang
   */

  private String commandFromController;
//  private static final String RESOURCES_PACKAGE;
//  private static final String PROPERTIES;
  private List<Entry<String, Pattern>> mySymbols;


  public ModelParser(){
    mySymbols = new ArrayList<>();
//    commandFromController = inputString;
  }


  /**
   * Adds the given resource file to this language's recognized types
   */
  public void addPatterns (String syntax) {
    ResourceBundle resources = ResourceBundle.getBundle(syntax);
    for (String key : Collections.list(resources.getKeys())) {
      String regex = resources.getString(key);
      mySymbols.add(new SimpleEntry<>(key,
          // THIS IS THE IMPORTANT LINE
          Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
    }
  }

  /**
   * Returns language's type associated with the given text if one exists
   */
  public String getSymbol (String text) {
    final String ERROR = "NO MATCH";
    for (Entry<String, Pattern> e : mySymbols) {
      if (match(text, e.getValue())) {
        return e.getKey();
      }
    }
    // FIXME: perhaps throw an exception instead
    return ERROR;
  }

  // Returns true if the given text matches the given regular expression pattern
  private boolean match (String text, Pattern regex) {
    // THIS IS THE IMPORTANT LINE
    return regex.matcher(text).matches();
  }
}
