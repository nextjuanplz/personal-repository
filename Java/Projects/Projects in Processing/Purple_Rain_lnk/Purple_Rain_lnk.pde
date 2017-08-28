//Purple Rain
//Raindrops: (138,43,226)
//Background: (230, 230,250)

final static int NUMBER_OF_DROPS = 1000;
Droplet[] drops = new Droplet[NUMBER_OF_DROPS];

void setup() {
  size(640,360);
  for (int i = 0; i < drops.length; i++)  {
    drops[i] = new Droplet();
  }  
}
void draw() {
  background(230,230,250);
  for (int i = 0; i < drops.length; i++)  {
    drops[i].fall();
    drops[i].show();
  }
}