/* 
 * Scene Class
 * Authors:  Nithin Sivakumar
 */
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import org.lwjgl.opengl.GL11;
/*
 * Scene Class - Encapsulates the collection of Shape objects 
 * in the Scene.
 * 
 */
public class Scene extends Shape2D {
	
    /**************************************************************
     * Shape Polygon: from Polygon Class
     * Call to the constructor which takes x and y coordinates as 
     * array of floats.
     * No of vertices computed as nVerts
     * Input coordinates for the Scene Polygon which draws polygon shape
     **************************************************************/
	float[] x={ 0.2f, 0.2f, 0.5f, 0.2f, 0.5f, 0.5f, 0.5f, 0.7f, 0.5f}; // X- Coordinates
    float[] y={ 0.3f, 0.1f, 0.1f, 0.3f, 0.1f, 0.3f, 0.3f, 0.3f, 0.1f}; // Y- Coordinates
	
    float[] a={ 0.5f, 0.7f, 0.7f, 0.5f, 0.7f, 0.5f, 0.5f, 0.2f, 0.5f}; // X- Coordinates
    float[] b={ 0.1f, 0.3f, 0.1f, 0.1f, 0.3f, 0.3f, 0.1f, 0.3f, 0.3f}; // Y- Coordinates
    
    float[] x1={ 0.2f, 0.2f, 0.5f, 0.2f, 0.5f, 0.5f, 0.5f, 0.7f, 0.5f }; // X- Coordinates
    float[] y1={ 0.3f, 0.1f, 0.1f, 0.3f, 0.1f, 0.3f, 0.3f, 0.3f, 0.1f }; // Y- Coordinates
    
    /**************************************************************
     * Shape Rectangle: from Polygon Class
     * Call to the constructor which takes x and y coordinates as 
     * array of floats.
     * No of vertices computed as nVerts
     * Input coordinates for the Scene Polygon which draws polygon shape
     **************************************************************/
    float[] r1={0.9f, 0.9f, 0.4f, 0.9f,  0.4f, 0.4f}; // X- Coordinates
    float[] r2={0.6f, 0.8f, 0.8f, 0.6f,  0.6f, 0.8f}; // Y- Coordinates
    
    /**************************************************************
     * Shape Pentagon: from Polygon Class
     * Call to the constructor which takes x and y coordinates as 
     * array of floats.
     * No of vertices computed as nVerts
     * Input coordinates for the Scene Polygon which draws polygon shape
     **************************************************************/
    float[] pentagon_x={ 0.2f, 0.2f, 0.5f, 0.2f, 0.5f, 0.5f, 0.5f, 0.7f, 0.5f, 0.9f, 0.7f, 0.5f  };  // X- Coordinates
    float[] pentagon_y={ 0.3f, 0.1f, 0.1f, 0.3f, 0.1f, 0.3f, 0.3f, 0.3f, 0.1f, 0.4f, 0.3f, 0.1f  }; // Y- Coordinates
    
    
    
    
    
    
    //--- ------------------------redraw-------------------------------
    @Override
    public void redraw() {
    	// TODO Auto-generated method stub
    	super.redraw();
    }
    
}
