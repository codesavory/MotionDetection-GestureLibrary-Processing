package motiondetection.gesture;


import processing.core.*;
import processing.video.*;

/**
 * This is a template class and can be used to start a new processing Library.
 * Make sure you rename this class as well as the name of the example package 'template' 
 * to your own Library naming convention.
 * 
 * (the tag example followed by the name of an example included in folder 'examples' will
 * automatically include the example in the javadoc.)
 *
 * @example Hello 
 */

public class BkgdSubtraction {
	
	// myParent is a reference to the parent sketch
	PApplet myParent;

	int myVariable = 0;
	
	//my variables
	public float pgestX = 0.0f, pgestY=0.0f, gestX=50.0f, gestY=50.0f;
	float threshold = 25;
	float motionX = 0;
	float motionY = 0;
	PImage prev;
	//String[] cameras = Capture.list();
	
	public final static String VERSION = "##library.prettyVersion##";
	

	/**
	 * a Constructor, usually called in the setup() method in your sketch to
	 * initialize and start the Library.
	 * 
	 * @example Hello
	 * @param theParent the parent PApplet
	 */
	public BkgdSubtraction(PApplet theParent) {
		myParent = theParent;
		prev = myParent.createImage(myParent.width, myParent.height, 1); //	createImage(w, h, RGB)
		welcome();
	}
	
	float distSq(float x1, float y1, float z1, float x2, float y2, float z2) 
	{
		  float d = (x2-x1)*(x2-x1) + (y2-y1)*(y2-y1) +(z2-z1)*(z2-z1);
		  return d;
	}
	
	public void getValues(Capture video)
	{
		  video.loadPixels();
		  prev.loadPixels();
		
		  //threshold = map(mouseX, 0, width, 0, 100);
		  threshold = 50;
		  int count = 0;
		  float avgX = 0;
		  float avgY = 0;
		
		  //loadPixels();
		  // Begin loop to walk through every pixel
		  for (int x = 0; x < video.width; x++ ) 
		  {
		    for (int y = 0; y < video.height; y++ ) 
		    {
		    	
		    	int loc = x + y*video.width;            // Step 1, what is the 1D pixel location
		        int current = video.pixels[loc];      	// Step 2, what is the current color
		        int previous = prev.pixels[loc]; 		// Step 3, what is the previous color
	
		        // Step 4, compare colors (previous vs. current)
		        float b1 = current%256;
		        float g1 = current/256%256;
		        float r1 = current/256/256%256;
		        
		        float b2 = previous%256;
		        float g2 = previous/256%256;
		        float r2 = previous/256/256%256;
			
		        float d = distSq(r1, g1, b1, r2, g2, b2); 
		
				if (d > threshold*threshold) 
				{
					//stroke(255);
					//strokeWeight(1);
					//point(x, y);
					avgX += x;
					avgY += y;
					count++;
					//pixels[loc] = color(255);
			    } 
				else 
				{
					//pixels[loc] = color(0);
			    }
		    }
		  }
		  //updatePixels();
		
		  // We only consider the color found if its color distance is less than 10. 
		  // This threshold of 10 is arbitrary and you can adjust this number depending on how accurate you require the tracking to be.
		  if (count > 200) { 
		    motionX = avgX / count;
		    motionY = avgY / count;
		     
		  }
		  else {
		  }
		  
		  this.pgestX = gestX;
		  this.pgestY = gestY;
		  
		  this.gestX = myParent.lerp(gestX, motionX, 0.1f);
		  this.gestY = myParent.lerp(gestY, motionY, 0.1f);
		  
		  //put this frame to prev
		  prev.copy(video, 0, 0, video.width, video.height, 0, 0, prev.width, prev.height);
	}
	
	private void welcome() {
		System.out.println("##library.name## ##library.prettyVersion## by ##author##");
	}
	
	
	public String sayHello() {
		return "hello library.";
	}
	/**
	 * return the version of the Library.
	 * 
	 * @return String
	 */
	public static String version() {
		return VERSION;
	}

	/**
	 * 
	 * @param theA the width of test
	 * @param theB the height of test
	 */
	public void setVariable(int theA, int theB) {
		myVariable = theA + theB;
	}

	/**
	 * 
	 * @return int
	 */
	public int getVariable() {
		return myVariable;
	}
}

