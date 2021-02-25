// Template for using the motion detection based gesture control for drawing sketches by Suriya Dakshina Murthy
// Library - https://github.com/codesavory/MotionDetection-GestureLibrary-Processing

import motiondetection.gesture.*;
import processing.video.*;

BkgdSubtraction motionDetect;

//to create a video capture object based on correct camera parameters
//and update the video capture frame
Capture video;
void captureEvent(Capture video)
{
  video.read();
}

void setup() {
  size(640, 360);
  background(102);
  video = new Capture(this, width, height, "pipeline:autovideosrc", 30);
  video.start();
  motionDetect = new BkgdSubtraction(this);
}

void draw() {
  //show input video in the bottom corner
  pushMatrix();
  image(video, width-100, height-100, 100, 100);
  popMatrix();
  
  //update the parameters based on input frame
  motionDetect.getValues(video);
  
  //available variables
  float x = motionDetect.pgestX; //previous gesture point x
  float y = motionDetect.pgestY; //previous gesture point y
  float px = motionDetect.gestX; //current gesture point x
  float py = motionDetect.gestY; //current gesture point y
}
