# Lwjgl-2D-shapes
1. Preliminaries
1. Download either BasicLWLJG.jar or basicGLSL.tar (or basicGLSL.tar.gz)
2. Rename BasicLWJGL.java to P0.java or basicGLSL.cpp to p0.cpp. This is important since our test
  framework tries to run either the class P0 or the executable p0. Be sure to edit the Makefile to set the
  MAIN or PROG variable to P0 or p0.
  
2. Refactor Triangle/Shape2D code
  Both the Java and C++ demos do all the rendering work in the Triangle class. Now that we are adding other
  children to the Shape2D hierarchy, this code should be shared as much as possible. It would be “safest”, to
  refactor the existing code first before trying to generalize it to support the additional classes. In particular, the
  makeBuffers and redraw methods need to become the responsibility of Shape2D.
3. Rectangle
  Add a Rectangle class that inherits from Shape. A Rectangle object should be specified by location and
  width, height parameters to the constructor. It should inherit the set/get methods from Shape2D. In addition, you
  should refactor the existing Shape2D/Triangle code so that the makeBuffers and redraw methods are moved to
  Shape2D and generalized (just a bit of generalization is needed)
4. Polygon
  Add a Polygon class that extends the Shape2D class. A Polygon object constructor can take information
  defining any number of points. You should define at least 2 constructors for Polygon: one that takes an array of
  x and an array of y coordinates and one that uses a class that encapsulates x and y into one object.
6. Interaction
  There will be only one simple interaction requirement for this assignment. If the user presses the “p” key,
  you should toggle your display from drawing “filled” triangles to drawing only the edges of the triangles. This
  is done with a single OpenGL call:
  glPolygonMode( GL_FRONT_AND_BACK, GL_LINE ); // Default 2nd parm: GL_FILL
  
  Run the p0.java which has main function on it.
