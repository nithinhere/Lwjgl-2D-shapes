/**
 * Polygon.java -- Implements a polygon with input coordinates.


 *                  Supports color, location, and size specification, 
 *
 * @author Nithin Sivakumar
 * 09/06/2015 - derived loosely from earlier JOGL demos for OpenGL 2.
 * 02/12/17 Nithin Sivakumar Modified to use LWJGL MemoryUtil tool rather than BufferUtils
 */
import static org.lwjgl.opengl.GL20.*;

import java.util.Vector;

import org.joml.Vector2f;

class Polygon extends Shape2D
{
    //------------------- instance variables ------------------------
    protected int     nVerts;
    protected float[] coords; 

    // dx, dy coordinates to pass to the coords----------------------
	private float[] dx;
	private float[] dy;

	// Vector2f x,y coordinates -------------------------------------
	public Vector2f x;
	public Vector2f y;




    //---------- Triangle related instance variables
    // Inherited from shape:
    // xLoc, yLoc, xSize, ySize
    
    //-------------------- constructor -----------------------------
    /**
     * The instance needs to know the shaderProgram to be used, in order
     *    to implement color, location and size changes.
     * It might have been better to have the shaderProgram be provided
     *    by a class variable in a GLSL support class.
     *
     * @param shaderProgram the shader program to be used.
     *
     */
	
	public Polygon()
    {
    	
	    float[] dx={ 0.5f, 0.7f, 0.7f, 0.5f, 0.7f, 0.5f, 0.5f, 0.2f, 0.5f};
	    float[] dy={ 0.1f, 0.3f, 0.1f, 0.1f, 0.3f, 0.3f, 0.1f, 0.3f, 0.3f};
    	
    	shaderPgm = Shape2D.shaderProgram;    // copy current shaderProgram id
    	/*********************************************************************
         * coordinates for the Polygon are obtained from the SceneMaker Class.
         * setLocation by default is zero when no values are provide which can 
         * be provided from the makeScene method of the SceneMaker Class.
         * Rectangle is generated from the triangle coordinates
         * setColor default value is Yellow which is (1,1,0).
         * Constructor takes arguments as array of floats which inturn maps to 
         * the dx, dy to redraw method inherited from shape2D.
         **********************************************************************/
        setLocation( 0, 0 );
        setColor( 1, 1, 0 );   // Yellow is default color
        
        // No of vertices takes array length----------------------------------- 
        nVerts = 9;
        coords = new float[ nVerts * 2 ];
        
        int c = 0;
        for ( int i = 0; i < nVerts; i++ )
        {
            coords[ c++ ] = dx[ i ];
            coords[ c++ ] = dy[ i ];
        }
        makeBuffers(coords); // Call to makeBuffers(coords) method in Shape2D
    
        if ( unif_vColor == -1 )
        {
            unif_vColor = glGetUniformLocation( shaderPgm, "vColor" );
            unif_model  = glGetUniformLocation( shaderPgm, "model" );
        }
    }
	
	
    public Polygon(float[] a, float[] b)
    {
    	// Set a, b (x, y) coordinates to dx, dy
    	this.dx=a;
    	this.dy=b;
    	
    	shaderPgm = Shape2D.shaderProgram;    // copy current shaderProgram id
    	/*********************************************************************
         * coordinates for the Polygon are obtained from the SceneMaker Class.
         * setLocation by default is zero when no values are provide which can 
         * be provided from the makeScene method of the SceneMaker Class.
         * Rectangle is generated from the triangle coordinates
         * setColor default value is Yellow which is (1,1,0).
         * Constructor takes arguments as array of floats which inturn maps to 
         * the dx, dy to redraw method inherited from shape2D.
         **********************************************************************/
        setLocation( 0, 0 );
        setColor( 1, 1, 0 );   // Yellow is default color
        
        // No of vertices takes array length----------------------------------- 
        nVerts = a.length;
        coords = new float[ nVerts * 2 ];
        
        int c = 0;
        for ( int i = 0; i < nVerts; i++ )
        {
            coords[ c++ ] = dx[ i ];
            coords[ c++ ] = dy[ i ];
        }
        makeBuffers(coords); // Call to makeBuffers(coords) method in Shape2D
    
        if ( unif_vColor == -1 )
        {
            unif_vColor = glGetUniformLocation( shaderPgm, "vColor" );
            unif_model  = glGetUniformLocation( shaderPgm, "model" );
        }
    }
    
    /*********************************************************************
     * Constructor takes Object with x and y coordinates
     * coordinates for the Polygon are obtained from the SceneMaker Class.
     * setLocation by default is zero when no values are provide which can 
     * be provided from the makeScene method of the SceneMaker Class.
     * Rectangle is generated from the triangle coordinates
     * setColor default value is Yellow which is (1,1,0).
     * Constructor takes arguments as array of floats which inturn maps to 
     * the dx, dy to redraw method inherited from shape2D.
     **********************************************************************/
    public Polygon(Vector<Vector2f> vf){
    	
    	
    	shaderPgm = Shape2D.shaderProgram;    // copy current shaderProgram id

        setLocation( 0, 0 );
        setColor( 1, 1, 0 );   // Yellow is default color

        nVerts = vf.size();
        coords = new float[ nVerts * 2 ];
        
        int c = 0;
        for (Vector2f v : vf )
        {
        	//System.out.println(v.x);
            coords[ c++ ] = v.x;
            coords[ c++ ] = v.y;
        }
        makeBuffers(coords);
    
        if ( unif_vColor == -1 )
        {
            unif_vColor = glGetUniformLocation( shaderPgm, "vColor" );
            unif_model  = glGetUniformLocation( shaderPgm, "model" );
        }
    	
    }
 
}
