/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model.core.external;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 *
 * @author Wouter
 */
public interface Saveable {
  public abstract Node toXML(Document document);
}
