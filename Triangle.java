/**
 * Triangle.java -- Implements a simple triangle with hard-coded shape.


 *                  Supports color, location, and size specification, 
 *
 * @author Nithin Sivakumar
 * 09/06/2015 - derived loosely from earlier JOGL demos for OpenGL 2.
 * 12/27/16 Nithin Modified to use LWJGL MemoryUtil tool rather than BufferUtils
 */
import org.lwjgl.opengl.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import org.lwjgl.system.MemoryUtil;

import java.nio.*;
import java.io.*;
/*
 * Triangle class extends Shape2D Class which is responsible for the setter and getter methods.
 * Variables inherited from the Shape
 * */
class Triangle extends Shape2D
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
     *
     */
    public Triangle()
    {
        /*********************************************************************
         * Triangle coordinates for the basic triangle.
         * setLocation by default is zero when no values are provide which can 
         * be provided from the makeScene method of the SceneMaker Class.
         * setColor default value is Yellow which is (1,1,0).
         * *******************************************************************/
        shaderPgm = Shape2D.shaderProgram;    // copy current shaderProgram id
        
        float dx[] = { -0.25f, 0.25f, 0.0f };
        float dy[] = {  0.0f,  0.0f,  0.5f };

        setLocation( 0, 0 );
        setColor( 1, 1, 0 );   // Yellow is default color

        nVerts = 3;			// Triangle takes 3 Vertices
        coords = new float[ nVerts * 2 ];
        
        int c = 0; 
        for ( int i = 0; i < nVerts; i++ )
        {
            coords[ c++ ] = dx[ i ];
            coords[ c++ ] = dy[ i ];
        }
        makeBuffers(coords);	// Call to makeBuffers method in Shape2D
    
        if ( unif_vColor == -1 )
        {
            unif_vColor = glGetUniformLocation( shaderPgm, "vColor" );
            unif_model  = glGetUniformLocation( shaderPgm, "model" );
        }
    }
    
}
