
import java.awt.event.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;


class paintJFrame  extends JFrame  
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;  
	PaintCanvas PC=new PaintCanvas(); 		//画布类
	PersonJPanel JP=new PersonJPanel();		//工具栏类
	areaJPanel AJP=new areaJPanel();		//位于画布下方用于显示x和y以及面积的Panel
	public paintJFrame()
	{
		super("绘画");
		this.setBackground(Color.DARK_GRAY);	
		Dimension dim=getToolkit().getScreenSize();			//设置Frame出现在屏幕中央
		this.setBounds(dim.width/4,dim.height/4 , dim.width/2, dim.height/2);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.getContentPane().add(PC);
		this.getContentPane().add(JP,"North");
		this.getContentPane().add(AJP,"South");
		this.setVisible(true);
		this.addWindowListener(new WindowPaint());  //添加自定义的窗口事件监听器	
		this.setResizable(false);
		this.setBackground(Color.DARK_GRAY);
	}	
	class PaintCanvas extends Canvas implements MouseListener,MouseMotionListener
	{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Color[][] p; 
//		private int Thickness=5;
		private Color NowColor=Color.black;
		private Point Now,lastNow; 				//用于画随意线和铅笔的点类
		private Point start,end,lastend;		//用于绘画直线和
		private	Point fp;    //用于实现填充
		
		public PaintCanvas()
		{
			NowColor=Color.BLACK;
			start=end=lastend=null;
			Now=lastNow=null;
			this.setSize(1000, 400);
			p=new Color[1000][400];
			for(int i=0;i<p.length;i++) 
				for(int j=0;j<p[0].length;j++)
					p[i][j]=Color.white;
			this.setBackground(Color.white);
			this.addMouseMotionListener(this);
			this.addMouseListener(this);
		}
		
		//鼠标事件监听器
		public void mouseMoved(MouseEvent e) 
		{
			
		 AJP.location.setText(e.getX()+","+e.getY()+"像素"); //鼠标移动时显示x和y
		}
		public void mouseClicked(MouseEvent e) {
			
		}
		public void mouseEntered(MouseEvent e) {
	
			
		}	
		public void mouseExited(MouseEvent e) {
		
			
		}
		public void mousePressed(MouseEvent e) 
		{
			lastNow=null;
			Now=new Point(e.getX(),e.getY());
			lastend=new Point();
			paint(this.getGraphics());
			start=new Point(e.getX(),e.getY());
		}		
		public void mouseDragged(MouseEvent e) 
		{
			try {
				if(JP.tool>6)	
					AJP.size.setText(Math.abs(start.x-end.x)+"×"+Math.abs(start.y-end.y));
				lastNow=Now;
				Now=new Point(e.getX(),e.getY());
				
				lastend=end;
				end=new Point(e.getX(),e.getY());
				
				paint(this.getGraphics());
				
				AJP.location.setText(e.getX()+","+e.getY()+"像素");		//显示鼠标位置的X和Y的大小
			}
			catch(Exception ex)
			{
				
			}
		}
		public void mouseReleased(MouseEvent e) 
		{
			end=new Point(e.getX(),e.getY());
			
//			this.SetPColor(new Point(e.getX(),e.getY()));
			
			fp=new Point(e.getX(),e.getY());
			
			paint(this.getGraphics());
			
			if(JP.tool>6)	
			{
				AJP.size.setText(Math.abs(start.x-end.x)+"×"+Math.abs(start.y-end.y));
				switch(JP.tool)
				{
					case 7:{AJP.area.setText((int)Math.abs(start.x-end.x)/2   //计算面积
							+"×"+(int)Math.abs(start.y-end.y)/2); }
							break;
				
					case 8:{AJP.area.setText((int)Math.abs(start.x-end.x)/2   //计算面积
							+"×"+(int)Math.abs(start.y-end.y)/2+"π"); }break;
					case 9:{AJP.area.setText((int)Math.abs(start.x-end.x)/2   //计算面积
							+"×"+(int)Math.abs(start.y-end.y)/2); }break;
					case 10:break;
					case 11:break;
					default:break;
				}
			}
			
			lastNow=Now=null;				//把绘画所用的点重新设为空
			start=end=lastend=null;
			fp=null;
		}			

		//获得和设置当前颜色
		public void setNowColor(Color NowColor) 
		{
			this.NowColor=NowColor;
		}
		public Color getNowColor()
		{
			return NowColor;
		}
		
		
		
		public void paint(Graphics g)
		{	
//			System.out.println(JP.tool);
			try
			{	
				switch(JP.tool)
				{ 
					case 0:PenPaint(g); break;
					case 1:fillPaint(); break; 
					case 2:rushPaint();break; 
					case 3:rubPaint();break; 
					case 4:gainColor(); break;
					case 5:break;
					case 6:LinePaint(g); break;
					case 7:RectPaint();break;
					case 8:OvalPaint();break;
					case 9:roundRectPaint();break;
					case 10:break;
					case 11:break;
					case 12:{			
								Graphics Gra=this.getGraphics();     //重画整个画布
								for(int i=0;i<p.length;i++)
								{
									for(int j=0;j<p[i].length;j++)
									{			
										if(p[i][j]!=Color.WHITE)	
										{	
											NowColor=p[i][j];
											Gra.setColor(NowColor);
											Gra.drawOval(i,j, 1, 1);	
										}
									}
								}
							}break;	
					case 13:{												//清空整个
								g.setColor(Color.white);
								g.fillRect(0, 0, this.getWidth(), this.getHeight());
								for(int i=0;i<PC.p.length;i++)
									for(int j=0;j<PC.p[i].length;j++)
									{
										PC.p[i][j]=Color.white;
									}
							}break;
					default:break; 
				}
			}
			catch(Exception ex)
			{
				
			}
		}	
		public void SetPColor(Point now)   //储存每个点上的颜色作为记录
		{
			if(now!=null)
				p[now.x][now.y]=NowColor;
		} 
		public void SetPColor(Point a,Point b) 	//将两个点构成的直线中所有的点储存到二维数组p中
		{
			if(a!=null&&b!=null)
			{  if (a.x==b.x) 		//考虑如果a和b的x相等
			   {
			       int from = Math.min(a.y,b.y);
			       int to = Math.max(a.y, b.y);
			       for (int y = from; y <= to; y++) 
			       {
			    	 this.SetPColor(new Point(a.x,y));
			       }
			   } else 				//a和b的x不相等
			    {
			       int slope =(int)( ( (a.y-b.y))/((a.x -b.x ))); //计算直线的斜率
					for(int x=Math.min(start.x,end.x),y=Math.min(start.x,end.x);
						x!=Math.max(start.x,end.x)&&y!=Math.max(start.y,end.y);y++,x++)
						{	
							System.out.print(x+"    "+y);
							try {
								if(this.p[x][y]==Color.white&&(y-a.y)/(x-a.x)==Math.random()*(slope-0.5)+0.5)	
								{
									System.out.println("?\n");
									this.SetPColor(new Point(x,y));
								}
							}
							catch(Exception e)
							{
								
							}
						}
			    }
			}
		}
		public void update(Graphics g)
		{
			JP.tool=12;
			paint(g);
		}
		private void PenPaint(Graphics g)	// tool=0时铅笔绘画： 实现跟随鼠标轨迹画线
		{
			if(Now!=null)
			{	
 				g.setColor(PC.NowColor);			
				g.fillOval(Now.x,Now.y,1,1);
				this.SetPColor(new Point(Now.x,Now.y));
			}
			if(lastNow!=null)
			{	
				g.drawLine(Now.x, Now.y, lastNow.x, lastNow.y);
			}
		}
		private void fillPaint()				//tool=1 时实现颜色填充
		{
			if(fp!=null)
			{		
				fillPaint(this.fp,NowColor,p[fp.x][fp.y]);
				System.out.println(NowColor);
			}
		}
		private void fillPaint(Point po,Color newOne,Color oldOne)
		{
			Graphics Gra=this.getGraphics();
			int left=po.y,right=po.y,up=po.x,down=po.x;
			while((--left>=0) && p[po.x][left]==oldOne);			
			left++;
			while((++right<p[po.x].length) && p[po.x][right]==oldOne);
			right--;
			while((--up>=0) && p[up][po.y]==oldOne);
			up++;
			while((++down<p.length) && p[down][po.y]==oldOne);
			down--;
			for(int i=up;i<=down;i++)
				for(int j=left;j<=right;j++)
					{
						Gra.setColor(newOne);
						Gra.drawOval(i, j, 1, 1);
						p[i][j]=newOne;
					}		
			
			if(up-1>=0 && p[up-1][left]==oldOne)
				fillPaint(new Point(up-1,left),newOne,oldOne);
			if(left-1>=0 && p[up][left-1]==oldOne)
				fillPaint(new Point(up,left-1),newOne,oldOne);
			if(up-1>=0 && p[up-1][right]==oldOne)
				fillPaint(new Point(up-1,right),newOne,oldOne);
			if(right+1<p[po.x].length && p[up][right+1]==oldOne)
				fillPaint(new Point(up,right+1),newOne,oldOne);
			
			
			
			if(down+1<p.length && p[down+1][right]==oldOne)
				fillPaint(new Point(down+1,right),newOne,oldOne);
			if(right+1<p[po.x].length && p[down][right+1]==oldOne)
				fillPaint(new Point(down,right+1),newOne,oldOne);
			if(down+1<p.length && p[down+1][left]==oldOne)
				fillPaint(new Point(up+1,left),newOne,oldOne);
			if(left-1>=0 && p[down][left-1]==oldOne)
				fillPaint(new Point(down,left-1),newOne,oldOne);	
		}
		private void rushPaint() //tool=2时实现刷子
		{
			Graphics Gra=this.getGraphics();
			Gra.setColor(NowColor);
			if(Now!=null)
			{	
				Gra.fillRect(Now.x, Now.y, 10, 10);
				for(int i=0;i<5;i++)				
					for(int j=0;j<5;j++)
						{	
//							if(p[Now.x+i][Now.y-j]==Color.white)
							{	
								this.SetPColor(new Point(Now.x+i,Now.y-j));
							}
						}
			}
		}
		private void rubPaint()   //tool=3时实现橡皮
		{
			Graphics Gra=this.getGraphics();
			Gra.setColor(getBackground());
			if(Now!=null)
			{	
				System.out.println(this.NowColor);
				Gra.fillRect(Now.x, Now.y, 10, 10);
				for(int i=0;i<5;i++)				//以Now为左上角的10×10矩形内部所有的点设置为背景色
					for(int j=0;j<5;j++)
						{	
							if(p[Now.x+i][Now.y-j]!=Color.white)
							{	
								NowColor=Color.WHITE;
								this.SetPColor(new Point(Now.x+i,Now.y-j));
							}
						}
			}
		}
		//未实现	
		private void gainColor()		// tool=4时实现获取鼠标位置的颜色并将它设置为当前颜色    
		{
			if(end!=null&&p[end.x][end.y]!=Color.white)
			{
				NowColor=p[end.x][end.y];
				PC.NowColor=p[end.x][end.y];
				JP.nowcolor1.setBackground(NowColor);
			}
		}
		private void LinePaint(Graphics g)			//tool=6时实现画直线
		{
			if(start!=null&&lastend!=null)
			{
				g.setColor(Color.WHITE);
				g.drawLine(start.x, start.y, lastend.x, lastend.y);
				g.setColor(NowColor);
				g.drawLine(start.x, start.y, end.x, end.y);
			}
		}
		private void RectPaint()			//tool=7时实现画矩形
		{
			Graphics Gra=this.getGraphics();
			if(start!=null&&end!=null)
			{
				Gra.setColor(NowColor);
				Gra.drawRect(Math.min(start.x,end.x), Math.min(start.y,end.y),
						Math.max(start.x,end.x)-Math.min(start.x,end.x),
						Math.max(start.y,end.y)-Math.min(start.y,end.y));
				for(int x=Math.min(start.x,end.x);x!= Math.max(start.x,end.x) ;x++)
				{	
					p[x][start.y]=this.NowColor;
					p[x][end.y]=this.NowColor;
				}
					
				for(int y=Math.min(start.y,end.y);y!=Math.max(start.y,end.y);y++)
				{	
					p[start.x][y]=this.NowColor;
					p[end.x][y]=this.NowColor;			
				}
			}	
		}


		private void OvalPaint()			//tool=8时实现画圆
		{
			Graphics Gra=this.getGraphics();
			if(start!=null&&end!=null)
			{
				double a=Math.abs(start.x-end.x)/2;
				double b=Math.abs(start.y-end.y)/2;
				Gra.setColor(NowColor);
				
				Gra.drawOval(Math.min(start.x, end.x), Math.min(start.y, end.y),(int)a*2,(int)b*2);

				Point O=new Point((start.x+end.x)/2,(start.y+end.y)/2);
				for(double x=Math.min(start.x,end.x);x!= Math.max(start.x,end.x) ;x++)	
					for(double y=Math.min(start.y,end.y);y!=Math.max(start.y,end.y);y++)
					{
						if((x-O.x)*(x-O.x)/(a*a)+((y-O.y)*(y-O.y))/(b*b)==1)
							{
								System.out.println(x+" "+y);
								this.SetPColor(new Point((int)x,(int)y));
							}
					}
			}		
		}
		private void roundRectPaint()		//tool=9时实现圆角矩形
		{
			Graphics Gra=this.getGraphics();
			if(start!=null&&end!=null)
			{
//				System.out.println("!");
				Gra.setColor(this.getBackground());
				Gra.drawRoundRect(start.x, start.y, Math.abs(lastend.x-start.x), Math.abs(lastend.y-start.y),10,10);
				Gra.setColor(NowColor);
				Gra.drawRoundRect(start.x, start.y, Math.abs(end.x-start.x), Math.abs(end.y-start.y),10,10);
				for(int x=Math.min(start.x,end.x);x!= Math.max(start.x,end.x) ;x++)
				{	
					p[x][start.y]=this.NowColor;
					p[x][end.y]=this.NowColor;
				}
					
				for(int y=Math.min(start.y,end.y);y!=Math.max(start.y,end.y);y++)
				{	
					p[start.x][y]=this.NowColor;
					p[end.x][y]=this.NowColor;			
				}
			}	
		}
	}
 	class PersonJPanel extends JPanel  implements ActionListener//上方工具栏类
	{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			JButton[] fileButton=new JButton[3];
			String[] str2= {"打开","保存","新建"};
			
			private int tool=0;      //tool用于返回给画布类来实现当前选择的功能
			JButton[] toolGroup;   	//工具栏按钮和名称数组                         
			String[] str= {"铅笔","填充","刷子","橡皮","取色","  "};

			String[] str1={"直线","矩形","椭圆","圆角矩形","  ","   "};
			JButton[] shapeGroup=new JButton[str1.length];
			Button nowcolor1;      //按钮用于显示当前颜色
			private Color[] colors= {Color.black,Color.WHITE,Color.RED,Color.ORANGE,Color.YELLOW,       //颜色数组
					 				Color.GREEN,Color.CYAN,Color.BLUE,Color.PINK,Color.GRAY,
					 				Color.LIGHT_GRAY,Color.CYAN,Color.MAGENTA};
			private Button[] button=new Button[colors.length];                      //颜色按钮数组
			Button choose;         //用于弹出颜色编辑窗口
			public PersonJPanel()
			{
		
				this.setLayout(new GridLayout(1,4,2,2));
				this.setBorder(BorderFactory.createEtchedBorder());
				this.setVisible(true);	
				
				JPanel file=new JPanel();				//文件面板
				file.setLayout(new BorderLayout());
				file.setBorder(BorderFactory.createEtchedBorder());
				//粘贴,打开,保存板块
				JPanel cut=new JPanel();
				cut.setLayout(new GridLayout());
				for(int i=0;i<fileButton.length;i++)
				{	
					fileButton[i]=new JButton(str2[i]);	
					fileButton[i].addActionListener(this);			
					cut.add(fileButton[i],"Center");
				}
				file.add(cut,"Center");
				file.add(new Label("文件",1),"South");
				this.add(file);
				
				
				JPanel tool=new JPanel();				//工具栏
				tool.setLayout(new BorderLayout(10,10));
				tool.setBorder(BorderFactory.createEtchedBorder());
				
				JPanel tool1=new JPanel();
				tool1.setLayout(new GridLayout(3,3,1,1));
				
				toolGroup=new JButton[str.length];
				for(int i=0;i<str.length;i++)
				{
					toolGroup[i]=new JButton(str[i]);
					toolGroup[i].addActionListener(this);
					tool1.add(toolGroup[i]);
				}
				tool.add(tool1,"Center");		
				tool.add(new Label("工具",1),"South");
				this.add(tool);		
				
				JPanel shape=new JPanel();                 //功能栏
				shape.setBorder(BorderFactory.createEtchedBorder());
				shape.setLayout(new BorderLayout());
				JPanel shapelist=new JPanel();
				shapelist.setLayout(new GridLayout(3,3,1,1));
				for(int i=0;i<str1.length;i++)
				{
					shapeGroup[i]=new JButton(str1[i]);
					shapelist.add(shapeGroup[i]);
					shapeGroup[i].addActionListener(this);
				}
				shape.add(shapelist,"Center");
				shape.add(new Label("形状",1),"South");
				this.add(shape);
				
				JPanel	colorPanel=new JPanel();              //颜色面板
				colorPanel.setBorder(BorderFactory.createEtchedBorder());
				colorPanel.setLayout(new BorderLayout(10,10));
				colorPanel.add(new Label("颜色",1),"South");	
				
				//创建一个面板显示当前颜色
				JPanel  Color1=new JPanel();				
				Color1.setLayout(new BorderLayout());
				
				nowcolor1= new Button();                 //初始化显示颜色的按钮
				nowcolor1.setBackground(PC.NowColor);
				nowcolor1.addActionListener(this);				
				Color1.add(nowcolor1,"Center");
				Color1.add(new JLabel("当前颜色"),"South");
				colorPanel.add(Color1,"West");
				
				JPanel colorChoose=new JPanel();			//3*5颜色选择框
				colorChoose.setLayout(new GridLayout(3,5,1,1));
				for(int i=0;i<colors.length;i++)	
				{
					button[i]=new Button("\n  \n  ");  //设置按钮大小为正方形
					button[i].setBackground(colors[i]);
					button[i].addActionListener(this);
					colorChoose.add(button[i]);
				}
				colorPanel.add(colorChoose,"Center");
		
				choose=new Button("编辑颜色");			//设置此按钮用于弹出颜色选择框
				choose.addActionListener(this);
				colorPanel.add(choose,"East");
				
				//添加到	PersonPanel	
				this.add(colorPanel);
			}	
			public void actionPerformed(ActionEvent me) 
			{	
				File file;

				JFileChooser fchooser=new  JFileChooser(".") ;		
				fchooser.setFileFilter(new ExtensionFileFilter("图像文件(*.pic)",".pic"));
				if(me.getSource()==fileButton[0])		//"打开","保存","新建"
				{	
					try 
					{
						FileInputStream fin;
						fchooser.showOpenDialog(this);
						file=fchooser.getSelectedFile();
					
						fin = new FileInputStream(fchooser.getSelectedFile());
						ObjectInputStream obj=new ObjectInputStream(fin);
						pRGB po= (pRGB)obj.readObject();
						while(po!=null)
						{	
							
//							System.out.println(po.getG());
							PC.p[po.getX()][po.getY()]=new Color(po.getR(),po.getG(),po.getB());	
							po= (pRGB)obj.readObject();		
						}		
							
						obj.close();
						fin.close();
					} 
					catch (Exception e)
					{
						
					}
					
					PC.repaint();      
				}
				if(me.getSource()==fileButton[1])
				{

						try {					
							FileOutputStream fout = null;
							fchooser.showSaveDialog(this);
							file=fchooser.getSelectedFile();
							if(!file.getName().endsWith(".pic"))
								file=new File(file.getAbsolutePath()+".pic");
								fout = new FileOutputStream(fchooser.getSelectedFile());
								ObjectOutputStream obj=new ObjectOutputStream(fout);
								for(int x=0;x<PC.p.length;x++)
								for(int y=0;y<PC.p[x].length;y++)
								{
									if(PC.p[x][y]!=Color.WHITE)
									{
										System.out.println("!!!");
										obj.writeObject(new pRGB(x,y,PC.p[x][y]));
										
										System.out.println("!");
									}
								}
								obj.writeObject(null);
							fout.close();
							obj.close();
							
						} catch (Exception e) 
						{
							
						}
//						catch(Exception e)
				}
				if(me.getSource()==fileButton[2])		
				{
					file=new File("");
					tool=13;
					PC.paint(PC.getGraphics());    //清空整个画布
				}
				for(int i=0;i<str.length+str1.length;i++)
				{
					if(i<str.length&&me.getSource()==toolGroup[i])
						tool=i;	
				}
				for(int i=0;i<str1.length;i++)
				{	
					if(me.getSource()==shapeGroup[i])
						tool=str1.length+i;
				}
				if(me.getSource()==toolGroup[3])
				{	
					PC.NowColor=PC.getBackground();
					JP.nowcolor1.setBackground(PC.NowColor);
				}
				for(int i=0;i<colors.length;i++)
				{	
					if(me.getSource()==button[i])
						PC.NowColor=colors[i];
					
					nowcolor1.setBackground(PC.NowColor); 			//显示当前选择的颜色
					PC.setNowColor(PC.NowColor);
				}	
				if(me.getSource()==choose)
				{	
					PC.NowColor= JColorChooser.showDialog(null, "选择颜色",Color.gray);
					nowcolor1.setBackground(PC.NowColor);
					choose.setBackground(PC.NowColor);
				}
			}
	}	
 	class WindowPaint implements WindowListener    //窗口事件监听器类
 	{
 		
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
			
			int i=JOptionPane.showConfirmDialog(null, "是否保存当前文件","确认",JOptionPane.YES_NO_CANCEL_OPTION);
			if(i==0)
			{
				
			}
		}

		@Override
		public void windowDeactivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void windowDeiconified(WindowEvent arg0) 
		{	
			int t=JP.tool;
//			System.out.println(t);
			PC.repaint();
			JP.tool=t;
			
		}

		@Override
		public void windowIconified(WindowEvent arg0) 
		{
			windowDeiconified(arg0);
		}

		@Override
		public void windowOpened(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}
 	} 	
 	class areaJPanel extends JPanel			//画布下方显示鼠标位置，大小，和面积的JPanel
 	{
 		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private TextField location=new TextField(10),size=new TextField(10),area=new TextField(10);
 		public areaJPanel()
 		{
 			this.setLayout(new GridLayout(1,10));
 			this.add(new Label("+",2));
 			this.add(location);
 			location.setEnabled(false);
 			
 			this.add(new Label("大小",2));
 			this.add(size);
 			size.setEnabled(false);
 			
 			this.add(new Label("面积",2));
 			this.add(area);
 			area.setEnabled(false);
 		}
 	}
 	public class ExtensionFileFilter extends FileFilter //文件过滤器
 	{
 		private String description,extension;
 		public ExtensionFileFilter(String description,String extension )
 		{
 			this.extension=extension;
 			this.description=description;
 		}
 		public boolean accept(File arg0) {
 			
 			return arg0.getName().toLowerCase().endsWith(this.extension);
 		}
 		public String getDescription() {
 			
 			return this.description;
 		}

 	}
}
