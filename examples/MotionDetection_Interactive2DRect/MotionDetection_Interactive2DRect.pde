// 2D Rectangles Interaction using the motion detection based gesture control for drawing sketches by Suriya Dakshina Murthy
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
  noStroke();
  rectMode(CENTER);
  video = new Capture(this, width, height, "pipeline:autovideosrc", 30);
  video.start();
  motionDetect = new BkgdSubtraction(this);
}

void draw() {
  background(51);
  
  //show input video in the bottom corner
  pushMatrix();
  image(video, width-100, height-100, 100, 100);
  popMatrix();
  
  //update the parameters based on input frame
  motionDetect.getValues(video);
  fill(255, 204);
  rect(motionDetect.gestX, height/2, motionDetect.gestY/2+10, motionDetect.gestY/2+10);
  fill(255, 204);
  
  float inverseX = width-motionDetect.gestX;
  float inverseY = height-motionDetect.gestY;
  rect(inverseX, height/2, (inverseY/2)+10, (inverseY/2)+10);
}
