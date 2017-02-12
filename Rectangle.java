/**
 * Rectangle.java -- Implements a simple rectangle with hard-coded shape.

 *                  Supports color, location, and size specification, 
 *
 * @author Nithin Sivakumar
 * 09/06/2015 - derived loosely from earlier JOGL demos for OpenGL 2.
 * 02/06/16 Nithin modified to use LWJGL MemoryUtil tool rather than BufferUtils
 */

import static org.lwjgl.opengl.GL20.*;



class Rectangle extends Shape2D
{
    //------------------- instance variables ------------------------
    protected int     nVerts;
    protected float[] coords; 
  
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
     * Location, height, width are passed as parameter to the rectangle 
     */
    public Rectangle(float loc_a, float loc_b, float width, float height) 
    {
    	/*********************************************************************
        * Triangle coordinates for the basic triangle.
        * setLocation by default is zero when no values are provide which can 
        * be provided from the makeScene method of the SceneMaker Class.
        * Rectangle is generated from the triangle coordinates
        * setColor default value is Yellow which is (1,1,0).
        **********************************************************************/
    	
    	shaderPgm = Shape2D.shaderProgram;    // copy current shaderProgram id
    	
    	float dx[] = { 0.2f, 0.2f, 0.5f, 0.2f, 0.5f, 0.5f};  
        float dy[] = { 0.3f, 0.1f, 0.1f, 0.3f, 0.1f, 0.3f};
        
        setLocation( loc_a, loc_b ); // setLocation with the argument passed
        setColor( 0, 1, 0 );   // Yellow is default color
        setSize(width, height); // Rectangle takes the width and height as the size

        nVerts = 6;
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
    
    
    public Rectangle() 
    {
    	/*********************************************************************
        * Triangle coordinates for the basic triangle.
        * setLocation by default is zero when no values are provide which can 
        * be provided from the makeScene method of the SceneMaker Class.
        * Rectangle is generated from the triangle coordinates
        * setColor default value is Yellow which is (1,1,0).
        **********************************************************************/
    	
    	shaderPgm = Shape2D.shaderProgram;    // copy current shaderProgram id
    	
    	float dx[] = { 0.2f, 0.2f, 0.5f, 0.2f, 0.5f, 0.5f};  
        float dy[] = { 0.3f, 0.1f, 0.1f, 0.3f, 0.1f, 0.3f};
        
        setLocation( 0, 0 ); // setLocation with the argument passed
        setColor( 0, 1, 0 );   // Yellow is default color
        setSize(0, 0); // Rectangle takes the width and height as the size

        nVerts = 6;
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
    
}
