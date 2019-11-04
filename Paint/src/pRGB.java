import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;

class pRGB implements Serializable		//将PC中的二维数组打包成pRGB类	
 	{
 		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private int r,g,b;
 		private int x,y;
 		public pRGB(Point po,Color color)
 		{
 			this.setX(po.x);
 			this.setY(po.y);
 			this.setR(color.getRed());
 			this.setG(color.getGreen());
 			this.setB(color.getBlue());
 		}
 		public pRGB(Object object)
 		{
 			pRGB p=(pRGB)object;
	 			this.setR(p.r);
	 			this.setG(p.g);
	 			this.setB(p.b);
	 			this.setX(p.x);
	 			this.setY(p.y);
 		}
 		public pRGB(int x,int y,Color color)
 		{
 			this.setX(x);
 			this.setY(y);
 			this.setR(color.getRed());
 			this.setG(color.getGreen());
 			this.setB(color.getBlue());
 		}
		public int getR() {
			return r;
		}
		public void setR(int r) {
			this.r = r;
		}
		public int getG() {
			return g;
		}
		public void setG(int g) {
			this.g = g;
		}
		public int getB() {
			return b;
		}
		public void setB(int b) {
			this.b = b;
		}
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;

		}
		public void String()
		{
			System.out.println("!X:"+this.x+",Y:"+this.y+",r:"+this.r+",g:"+this.g+",b:"+this.b);
		}
 	}