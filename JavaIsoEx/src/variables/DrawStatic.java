/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package variables;

/**
 *
 * @author Wouter
 */
public class DrawStatic {

  /*private static Color getColor(Image image, int x, int y, int w, int h) {
  int[] pixels = new int[w * h];
  PixelGrabber pg = new PixelGrabber(image, 0, 0, w, h, pixels, 0, w);
  try {
  pg.grabPixels();
  } catch (InterruptedException e) {
  }
  //color = (int[]) px.getPixels();
  int c = pixels[x + (w * y)];  // or  pixels[x * width + y]
  // and the Java Color is ...
  Color color = new Color(c, true);
  System.out.println("Found color (" + color.getRed() + ", " + color.getGreen()
  + ", " + color.getBlue() + ", " + color.getAlpha() + ")");

  return color;
  }*/
  /*
  public static Image floodFill(Graphics g, BufferedImage image, int x, int y, Color replaceBy) {
    int w = image.getWidth(null), h = image.getHeight(null);
    Color originalColor = new Color(image.getRGB(x, y));
    System.out.println(image.getRGB(x, y));
    Stack<Point> stack = new Stack<Point>();

    g.setColor(replaceBy);
    System.out.println("Attempting to replace " + originalColor + " by " + replaceBy);
    stack.add(new Point(x, y));
    while (!stack.isEmpty()) {
      Point p = stack.pop();
      Color grabbedColor = new Color(image.getRGB(p.x, p.y));

      if (grabbedColor.equals(originalColor)) {
        g.drawLine(p.x, p.y, p.x, p.y);
      } else continue;

      // Add left
      if (p.x - 1 >= 0) {
        stack.push(new Point(p.x - 1, p.y));
      }
      // Add top
      if (p.y - 1 >= 0) {
        stack.push(new Point(p.x, p.y - 1));
      }
      // Add right
      if (p.x + 1 < w) {
        stack.push(new Point(p.x + 1, p.y));
      }
      // Add bottom
      if (p.y + 1 < h) {
        stack.push(new Point(p.x, p.y + 1));
      }
    }
    return image;
  }*/
}
