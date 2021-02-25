// Simple Continous Drawing Example using the motion detection based gesture control for drawing sketches by Suriya Dakshina Murthy
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
  stroke(255);
  
  //show input video in the bottom corner
  pushMatrix();
  //translate(video.width/2, video.height/2);
  //scale(-1, 1);
  //translate(-video.width/2, -video.height/2);
  //rotateY(PI/2);
  image(video, width-100, height-100, 100, 100);
  popMatrix();
  
  //update the parameters based on input frame
  motionDetect.updateValues(video);
  //if (mousePressed == true) 
  {
    line(motionDetect.pgestX, motionDetect.pgestY, 
              motionDetect.gestX, motionDetect.gestY);
  }
}

void keyPressed()
{
  if (key=='c')
  {
    clear();
    background(102);
  }
}
