# Paint
A small project to complete a Java graphical user interface job with the goal of implementing a small land drawing program in Java

一个用于完成java图形界面的小项目，目的是编写一个简单的画图程序

#Purpose and requirements
1 Toolbar increases the selection of brush color and fill color, selects the shape of the graphic (straight line, ellipse, rectangle, rounded rectangle or random line dragged by the mouse, etc.), selects the filling method (real, dashed, diagonal dotted line, etc.), adds rubber and Color picking and other functions.
2 Design typical polygonal components, such as regular pentagons, pentagrams, etc., draw polygon graphics, calculate area, fill polygons with multiple fill patterns; drag the border to change size.
3 Save the drawing shape and its properties to a file, then read the redraw.

目的和要求
1.工具栏增加选择画笔颜色和填充色、选择图形形状（直线、椭圆、矩形、圆角矩形或鼠标拖动的随意线等）、选择填充方式（实、虚线、斜虚线等），增加橡皮和取色等功能。
2. 设计典型多边形组件，如正五边形、五角星等，绘制多边形图形，计算面积，以多种填充模式填充多边形；拖动边框以改变大小。
3.将所绘图形及其属性保存到文件，再读取重画。




#Design and analysis
1. Storage: Considering the use of the Canvas class in JAVA and the convenience of file storage, the canvas is divided into height×weith pixels and stored in a two-dimensional array of Color class. When the file storage operation of the canvas is performed When you package each point and Color class into a custom pRGB (a total of 5 integers, three values ​​of RGB in Point.x, Point.y and Color), and use the object byte stream To store.
2. Tools:
Pencil: Add a mouse event listener, get the point of the current mouse on the canvas when the mouse clicks, and call
DrawOval() to draw the image;
Fill: Use recursive algorithm to find and fill points in multiple directions that are not the current color
Brush: Add a mouse event listener to get the current mouse point on the canvas when the mouse clicks, and draw a 10×10 rectangle on the canvas.
Eraser: Add a mouse event listener to get the current mouse point on the canvas when the mouse clicks, and set the surrounding 5×5 rectangle as the background color.
Color: Get the color of the current p[x][y] and set it as the background color.
Shape: call the methods that come with Graphics to display, and display the area of ​​the currently drawn graphics。

#设计与分析
1.存储：考虑到要使用JAVA中的Canvas类，并方便文件的存储，所以将画布分成height×weith个像素点，并使用一个Color类的二维数组来储存，当进行画布的   文件存储操作时，将每一个点和Color类打包成一个自定义的pRGB（其中一共有5个整形，分别为Point.x,Point.y和Color中的RGB三个值）类,并使用对象字   节流来存储。
2.工具：
  铅笔：添加鼠标事件监听器，，在鼠标点击时获得当前鼠标在画布上的点，并调用
  DrawOval（）来画出图像；
	填充：采用递归算法，向多个方向寻找不为当前颜色的点并填充
	刷子：添加鼠标事件监听器，，在鼠标点击时获得当前鼠标在画布上的点，并在画布上画出10×10的矩形。
	橡皮：添加鼠标事件监听器，，在鼠标点击时获得当前鼠标在画布上的点，并周围5×5的矩形设置为背景色。
	取色：获得当前p【x】【y】的颜色并设置为背景色。

