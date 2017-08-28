class Droplet {
  //Fields
  float x = random(width);
  float y = random(-500,-100);
  float z = random(0,20);
  float ySpeed = map(z,0,20,4,10);
  float dropLength = map(z,0,20,10,20);
  float gravity = map(z,0,20,0,0.2);
   
  //Methods
  void fall() {
    y += ySpeed;
    ySpeed += gravity;
    if (y > height) {
      y = random(-200,100);
      ySpeed = random(4,10);
      z = random(0,20);
    }
  } 
  void show() {
    float thickness = map(z,0,20,1,3);
    strokeWeight(thickness);
    stroke(138,43,226);
    line(x,y,x,y+dropLength);
  } 
}