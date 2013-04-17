package model.core;
//not part of engine. i created this class for testing purpose
import java.awt.Graphics;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class mySquare extends Square{


	public mySquare(int x, int y){

		super.setGridX(x);
		super.setGridY(y);
	}
	@Override
	public Node toXML(Document document) {

		return null;
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
	}

}
