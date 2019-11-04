import java.awt.event.*;
import java.awt.*;
import javax.swing.*;



public class PaintCanvasJFrame extends JFrame implements WindowListener,ActionListener  //测试用途
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color NowColor=Color.BLUE;
	public PaintCanvas PA=new PaintCanvas();
	JButton JB;
	PenCanvas PC=new PenCanvas();
	public PaintCanvasJFrame()
	{
		this.setVisible(true);
		this.setBounds(400,300,400,300);
		this.add(PA);
		this.addWindowListener(this);
		
		JPanel JP= new JPanel();
		JB= new JButton("直线");
		JB.addActionListener(this);
		JP.add(JB);
		this.add(JP,"North");
		
	}
	class PaintCanvas extends Canvas implements MouseListener,MouseMotionListener
	{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private PenCanvas PC;
		private Point now;
		private Color[][] p; 
		public PaintCanvas()
		{
			now=null;
			this.setSize(600, 600);
			p=new Color[this.getWidth()][this.getHeight()];
			this.setBackground(Color.white);
			this.addMouseMotionListener(this);
			this.addMouseListener(this);
		}
		public void mouseMoved(MouseEvent e) {
		
			
		}
		public void mouseClicked(MouseEvent e) {
			
		}
		public void mouseEntered(MouseEvent e) {
	
			
		}	
		public void mouseExited(MouseEvent e) {
		
			
		}
		public void mousePressed(MouseEvent e) 
		{
			now=new Point(e.getX(),e.getY());
			PA.SetPColor();
			repaint();
			
		}		
		public void mouseDragged(MouseEvent e) 
		{
			now=new Point(e.getX(),e.getY());
			PA.SetPColor();
			repaint();
			
		}
		public void mouseReleased(MouseEvent e) 
		{
			now=new Point(e.getX(),e.getY());
			PA.SetPColor();
			repaint();
		}	
		public void paint(Graphics g)
		{
//			if(now!=null)
//			{	
// 				g.setColor(NowColor);			
//				g.fillOval(now.x,now.y,1,1);
//			}
//			if(p[count]!=null&&p[count-1]!=null)
//				g.drawLine(p[count].x, p[count].y, p[count-1].x, p[count-1].y);
		}
		public void update(Graphics g)
		{
			paint(g);
		}
		public void SetPColor()
		{
			if(now!=null)
				p[now.x][now.y]=this.getGraphics().getColor();
		}
		public void repaint()
		{
			Graphics Gra=this.getGraphics();
			for(int i=0;i<p.length;i++)
			{
				for(int j=0;j<p[i].length;j++)
				{	
					if(p[i][j]!=null)
					{	NowColor=p[i][j];
						Gra.setColor(NowColor);
						now=new Point(i,j);
						Gra.drawOval(now.x, now.y, 1, 1);
					}	
				}
			}
		}
	}
 		//窗口事件监听器
		@Override
		public void windowActivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosed(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeactivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void windowDeiconified(WindowEvent arg0) 
		{	
			PA.repaint();
		}

		@Override
		public void windowIconified(WindowEvent arg0) 
		{
			PA.repaint();
			
		}

		@Override
		public void windowOpened(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}
 		
	class PenCanvas extends Canvas implements MouseListener,MouseMotionListener
	{

		/**
		 * 
		 */
		Point start,end,lastend;
		public PenCanvas()
		{
			start=end=lastend=null;
		}
		private static final long serialVersionUID = 1L;

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			lastend=end;
			end=new Point(e.getX(),e.getY());
			Paint();
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			start=new Point(arg0.getX(),arg0.getY());
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			end=new Point(arg0.getX(),arg0.getY());
			Paint();
		}
		private void Paint()
		{
			Graphics Gra=this.getGraphics();
			if(start!=null&&end!=null)
			{
				Gra.setColor(Color.BLUE);
				Gra.drawLine(start.x, start.y, lastend.x, lastend.y);
				Gra.setColor(NowColor);
				Gra.drawLine(start.x, start.y, end.x, end.y);
			}
				
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==JB)
		{	
			this.add(PC,"Center");
		}	
		
	}
}