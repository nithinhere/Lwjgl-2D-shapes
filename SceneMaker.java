/**
 * SceneMaker.java - demonstrates some of the most fundamental concepts

 *                   of programming in LWJGL3 within the context of an
 *                   object oriented data management methodology.
 *       This simple demo is not considered with efficient use of GPU 
 *       computational power, nor in minimizing data transfer between the
 *       CPU and GPU. That will come later.
 *
 * @author rdb
 * 09/06/15 version 1.0
 *          This version is not a great example of good style or organization 
 *          or cleanliness! But it works. 
 * 09/08/15 version 1.2
 *          Improved testing for OS to determine what OpenGL version can be
 *             used and to edit GLSL version specification when running on
 *             Linux machines
 *          version 1.2b 
 *             added warning message if input shaders are not version 330
 * 09/09/15 version 1.2c
 *             moved keyboard handler setup to its own method
 * 09/13/15 version 2.0
 *             moved and refactored openWindow, makeShaderProgram to 
 *             UtilsLWJGL (derived from DemoUtils).
 * 11/27/16 version 2.1
 *              Modified for new LWJGL 3.1 conventions
 *             
 * This program makes use of code from demos found at lwjgl.org accessed as
 * lwjgl3-demo-master and downloaded in late August 2015. It also uses a
 * slightly modified complete class from that package, UtilsLWJGL.
 */
import org.lwjgl.glfw.*;

import org.lwjgl.opengl.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import org.lwjgl.system.MemoryUtil;

import javafx.geometry.Point2D;

import java.nio.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Vector;

import org.joml.Vector2f;

public class SceneMaker extends Scene
{
    //---------------------- instance variables ----------------------
    // window size parameters
    int windowW = 600;
    int windowH = 640;
    
    // shader program id.
    int shaderPgm; 

    // storage for shapes created
    ArrayList<Shape2D> shapes = new ArrayList<Shape2D>();
    
    // We need to strongly reference callback instances.
    private GLFWErrorCallback errorCallback;
    private GLFWKeyCallback   keyCallback;
    
    // The window handle
    protected long window;
    
    //--------------- Constructor  ----------------------------------------
    public SceneMaker()
    {
        // Setup error callback to print to System.err.
        errorCallback = GLFWErrorCallback.createPrint( System.err );
        errorCallback.set(); // make it active

        window = UtilsLWJGL.openWindow( "Scene", windowW, windowH );

        // The next line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the ContextCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities(); 
        
        try 
        {
            shaderPgm = UtilsLWJGL.makeShaderProgram( "basicLWJGL.vsh", 
                                                      "basicLWJGL.fsh" );
            glUseProgram( shaderPgm ); 
            Shape2D.shaderProgram = shaderPgm;  // tell the Shape class about it
        } 
        catch ( IOException iox )  
        {
            System.err.println( "Shader construction failed." );
            System.exit( -1 );
        }
        setupView();
        makeScene();
        
        setupKeyHandler();

        renderLoop();
            
        // Clean up glfw stuff
		glfwFreeCallbacks( window );
		glfwDestroyWindow( window );
		glfwSetErrorCallback( null ).free();
		glfwTerminate();
		Scene s=new Scene();
		s.redraw();
		
    }
        
    //--------------------- setupKeyHandler ----------------------
    /**
     * void setupKeyHandler
     */
    private void setupKeyHandler()
    {
        // Setup a key callback. It is called every time a key is pressed, 
        //      repeated or released.
        glfwSetKeyCallback( window, 
            keyCallback = new GLFWKeyCallback()
            {
               @Override
                public void invoke( long keyWindow, int key, 
                                    int scancode, int action, int mods )
                {
                   // escape key means terminate; set flag; test in render loop
                   if (  key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE 
                         || key == GLFW_KEY_Q && action == GLFW_RELEASE )
                       glfwSetWindowShouldClose( window, true );
                   if( key == GLFW_KEY_P && action == GLFW_RELEASE)
                   	glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
            }
        });
    }
    
    //-------------------------- loop ----------------------------
    /**
     * Loop until user closes the window or kills the program.
     */
    private void renderLoop() 
    {
        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while ( ! glfwWindowShouldClose( window ) )
        {
            // redraw the frame
            redraw();

            glfwSwapBuffers( window ); // swap the color buffers

            // Wait for window events. The key callback above will only be
            // invoked during this call.
            // lwjgl demos use glfwPollEvents(), which uses nearly 2X
            // the cpu time for simple demos as glfwWaitEvents.
            glfwWaitEvents();
        }
    }
    
    //------------------ makeScene --------------------------
    /**
     * Create the objects that make up the scene.
     */
    private void makeScene()
    {
        GL11.glClearColor( 0.0f, 0.0f, 0.0f, 0.0f );
        
        /**************************************************************
         * Shape Triangle- Which draws head of the doll
         * Call to the default constructor
         **************************************************************/
        Triangle tri = new Triangle();
        tri.setLocation( 1.15f, 0.9f );
        tri.setColor( 1, 0, 1 );   // cyan
        shapes.add( tri );
        
        tri = new Triangle();  // yellow is default
        tri.setLocation( 0.1f, -0.2f );
        tri.setSize( 2, 0.7f );
        shapes.add( tri ); // Add to the shape array List
       
        
        /**************************************************************
         * Shape Rectangle:
         * Call to the default constructor
         **************************************************************/
        Rectangle rec1=new Rectangle();
        rec1.setLocation(0.3f,1.0f );
        rec1.setColor(0.5f, 0.2f, 0.5f);
        rec1.setSize(1, 3);
        shapes.add(rec1);
        
        /**************************************************************
         * Shape Rectangle:
         * Call to the constructor which takes location - x and y, width
         * and height of the rectangle.
         **************************************************************/
        Rectangle rect = new Rectangle(-0.25f, 0.25f, 0.25f, 0.25f);
        rect.setLocation(0.1f, 0);
        rect.setColor(0, 0.5f, 1);
        rect.setSize( 3,3);
        shapes.add(rect);

        rect.setColor(0, 0, 1);
        shapes.add(rect);
        
        rect = new Rectangle(-0.7f, 0.3f, 2.5f, 3f);
        rect.setColor(1, 0.5f, 0);
        shapes.add(rect);
        
        rect= new Rectangle(0.3f, -2.2f, 2, 5);
        rect.setColor(1, 1f, 0.5f);
        shapes.add(rect); 
        
         /**************************************************************
         * Shape Polygon:
         * Call to the default constructor
         **************************************************************/
        Polygon default_check=new Polygon();
        default_check.setLocation(-1.5f, 1.0f);
        default_check.setColor(0, 1, 0.5f);
        default_check.setSize( 1, 3);
        shapes.add(default_check);
        
        /**************************************************************
         * Shape Polygon:
         * Call to the default constructor
         **************************************************************/
        Polygon pol = new Polygon(x1,y1);
        pol.setLocation(0.5f, -0.61f);
        pol.setColor(1, 0, 0);
        pol.setSize( 1, 3);
        shapes.add(pol);
        
        /**************************************************************
         * Shape Polygon:
         * Call to the constructor which takes x and y coordinates as 
         * array of floats.
         **************************************************************/
        float[] a1={ 0.5f, 0.7f, 0.7f, 0.5f, 0.7f, 0.5f, 0.5f, 0.2f, 0.5f}; // Totall 9 vertices
        float[] b1={ 0.1f, 0.3f, 0.1f, 0.1f, 0.3f, 0.3f, 0.1f, 0.3f, 0.3f};
        
        Polygon polygon1 = new Polygon(a1,b1); 
        polygon1.setLocation(0.9f, -0.61f);
        polygon1.setColor(1, 0, 0);
        polygon1.setSize( 1, 3);
        shapes.add(polygon1);
        
        /**************************************************************
         * Shape Triangle: from Polygon Class
         * Call to the constructor which takes x and y coordinates as 
         * array of floats.
         * No of vertices computed as nVerts
         * Pass three values to draw triangle from polygon class
         **************************************************************/
        float[] t1={0.9f, 0.9f, 0.4f};
        float[] t2={0.6f, 0.8f, 0.8f};
       	Polygon p12=new Polygon(t1, t2);
        p12.setLocation(-2.3f, -4.0f);
        p12.setSize( 2f, 5f);
        p12.setColor(0.9f, 0.5f, 0.5f);
        shapes.add(p12);
        
        /**************************************************************
         * Shape Polygon:
         * Call to the constructor which encapsulates x and y coordinates as 
         * one object
         * use - Vector2f JOML 
         **************************************************************/
        Vector<Vector2f> vf= new Vector<Vector2f>();  // Vectorlist of vectors
        
        Vector2f pt1=new Vector2f();	// Initialize Vector2f points
        pt1.add(0.9f, 0.6f); // add coordinates to pt
        vf.add(pt1); // add points to the vector 2f
        
        Vector2f pt2=new Vector2f();
        pt2.add(0.9f,0.8f);
        vf.add(pt2);
        
        Vector2f pt3=new Vector2f();
        pt3.add(0.4f,0.8f);
        vf.add(pt3);
        
        Polygon p2=new Polygon(vf);
        p2.setLocation(-1.9f, -3.0f);
        p2.setSize(1, 3);
        p2.setColor(1, 0, 1);
        shapes.add(p2);
        
        /**************************************************************
         * Shape Polygon:
         * Call to the constructor which encapsulates x and y coordinates as 
         * one object
         * use - Vector2f JOML 
         **************************************************************/
        Vector<Vector2f> vf1= new Vector<Vector2f>();
        
        Vector2f p01=new Vector2f();
        p01.add(0.9f, 0.6f);
        vf.add(p01);
        
        Vector2f p02=new Vector2f();
        p02.add(0.9f,0.8f);
        vf.add(p02);
        
        Vector2f p03=new Vector2f();
        p03.add(0.4f,0.8f);
        vf.add(p03);
        
        Polygon p3=new Polygon(vf);
        p3.setLocation(-2.2f, -2.6f);
        p3.setSize(1, 2);
        p3.setColor(0, 0, 1);
        shapes.add(p3);
        
         /**************************************************************
         * Shape Polygon: from Polygon Class
         * Call to the constructor which takes x and y coordinates as 
         * array of floats.
         * No of vertices computed as nVerts
         * Input coordinates obtained from Scene class
         * Pass three values to draw triangle from polygon class
         **************************************************************/
        Polygon polygon = new Polygon(a,b);
        polygon.setLocation(-0.7f, -2.2f);
        polygon.setColor(1, 0.5f, 0.5f);
        polygon.setSize( 2, 5);
        shapes.add(polygon);
        
        Polygon p1=new Polygon(r1, r2);
    	p1.setLocation(-2.5f, -0.5f);
    	p1.setColor(0.5f, 0.5f, 0.5f);
    	p1.setSize( 2.5f, 1.3f);
    	shapes.add(p1);
    	
    	p1=new Polygon(r1, r2);
    	p1.setLocation(-2.32f, -0.176f);
    	p1.setColor(1, 0.5f, 1);
    	p1.setSize( 2.2f, 1.2f);
    	shapes.add(p1);
    	
    	p1=new Polygon(r1, r2);
    	p1.setLocation(-2.11f, 0.128f);
    	p1.setColor(0.5f, 0.5f, 1);
    	p1.setSize( 1.9f, 1.1f);
    	shapes.add(p1);
    	
    	p1=new Polygon(r1, r2);
    	p1.setLocation(-1.9f, 0.41f);
    	p1.setColor(0.5f, 1, 1);
    	p1.setSize( 1.6f, 1.0f);
    	shapes.add(p1);
    	
    	/**************************************************************
         * Shape Pentagon: from Polygon Class
         * Call to the constructor which takes x and y coordinates as 
         * array of floats.
         * Five side polygon
         * No of vertices computed as nVerts
         * Input coordinates for the Scene Polygon which draws polygon shape
         **************************************************************/
    	Polygon py=new Polygon(pentagon_x,pentagon_y);
    	py.setLocation(-0.9f, 1.1f);
    	py.setColor(0.5f, 0, 1);
    	py.setSize(1, 2);
    	shapes.add(py);
    	

       }
    
    //------------------ setupView --------------------------
    /**
     * We have a constant viewing and projection specification.
     * Can define it once and send the spec to the shader.
     */
    void setupView()
    {
        // equivalent to glOrtho2D( -2, 2, -2, 2 )
        float[] pXv={ 0.5f, 0,   0, 0,  
                        0, 0.5f, 0, 0,  
                        0,  0,   1, 0,  
                        0,  0,   0, 1 };

        // Best LWJGL practice is to use C-like memory allocation outside
        //    Java's storage management; done with MemoryUtil functions.
        FloatBuffer pXvBuf = MemoryUtil.memAllocFloat( pXv.length );

        pXvBuf.put( pXv ).flip();

        /****************************
        // default: equivalent to glOrtho2D( -1, 1, -1, 1 )
        float pXv[]={ 1,  0, 0, 0,  
                           0,  1, 0, 0,  
                           0,  0, 1, 0,  
                           0,  0, 0, 1 };
        /****************************/
        
        //--- now push the composite into a uniform var in vertex shader
        //  this id does not need to be global since we never change 
        //  projection or viewing specs in this program.
        int unif_pXv = glGetUniformLocation( shaderPgm, "projXview" );

        // Transfer the matrix to the GPU
        glUniformMatrix4fv( unif_pXv, false, pXvBuf );

        // Free the non-Java memory used
        MemoryUtil.memFree( pXvBuf );  
    }
    //------------------------ redraw() ----------------------------
    public void redraw()
    {
        // clear the framebuffer
    	glClear( GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT ); 

        for (Shape2D s: shapes)
            s.redraw();
            
        GL11.glFlush();
    }
    
    
}
