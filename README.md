# Paint
A small project to complete a Java graphical user interface job with the goal of implementing a small land drawing program in Java

#Purpose and requirements
1 Toolbar increases the selection of brush color and fill color, selects the shape of the graphic (straight line, ellipse, rectangle, rounded rectangle or random line dragged by the mouse, etc.), selects the filling method (real, dashed, diagonal dotted line, etc.), adds rubber and Color picking and other functions.
2 Design typical polygonal components, such as regular pentagons, pentagrams, etc., draw polygon graphics, calculate area, fill polygons with multiple fill patterns; drag the border to change size.
3 Save the drawing shape and its properties to a file, then read the redraw.

#Design and analysis
1. Storage: Considering the use of the Canvas class in JAVA and the convenience of file storage, the canvas is divided into height×weith pixels and stored in a two-dimensional array of Color class. When the file storage operation of the canvas is performed When you package each point and Color class into a custom pRGB (a total of 5 integers, three values ​​of RGB in Point.x, Point.y and Color), and use the object byte stream To store.
2. Tools:
Pencil: Add a mouse event listener, get the point of the current mouse on the canvas when the mouse clicks, and call
DrawOval() to draw the image;
Fill: Use recursive algorithm to find and fill points in multiple directions that are not the current color
Brush: Add a mouse event listener to get the current mouse point on the canvas when the mouse clicks, and draw a 10×10 rectangle on the canvas.
Eraser: Add a mouse event listener to get the current mouse point on the canvas when the mouse clicks, and set the surrounding 5×5 rectangle as the background color.
Color: Get the color of the current p[x][y] and set it as the background color.
Shape: call the methods that come with Graphics to display, and display the area of ​​the currently drawn graphics
