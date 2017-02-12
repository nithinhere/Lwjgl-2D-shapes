/**
 * P0.java - demonstrates some of the most fundamental concepts

 *                   of programming in LWJGL3 within the context of an
 *                   object oriented data management methodology.
 *       This simple demo is not considered with efficient use of GPU 
 *       computational power, nor in minimizing data transfer between the
 *       CPU and GPU. That will come later.
 *
 * @author Nithin Sivakumar
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

import java.nio.*;
import java.io.*;
import java.util.ArrayList;
/*
 * Main method creates an instance to the SceneMaker class.
 * SceneMaker Class gets instantiated from the main method.
 * SceneMaker Class is responsible for defining the objects to draw the shapes.
 * */
public class P0  
{
    public static void main( String args[] )
    {
       SceneMaker sm=new SceneMaker(); // Invoking SceneMaker Class
    }
}
