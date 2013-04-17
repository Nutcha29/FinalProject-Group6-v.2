/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model.core.external;

import org.w3c.dom.Node;

/**
 *
 * @author Wouter
 */
public interface Loadable {
  /**
   * Returns an instance of this object, given the XML node.
   * @param thisClassInXML The node containing the XML of the class data.
   * @return The Object containing a new instance of this class, with the data loaded!
   */
  public abstract Object onLoad(Node thisClassInXML);
}
