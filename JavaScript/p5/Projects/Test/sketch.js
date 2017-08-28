//1.3: Getting Text from User: Text Input
//var canvas;
var textField;
var output;
var submitButton;

function setup() {
  // We are still calling createCanvas like in the past, but now
  // we are storing the result as a variable. This way we can
  // call methods of the element, to set the position for instance.
  canvas = createCanvas(640, 480);
  //noCanvas();

  //Capture input
  //textField = createInput("Your Favorite Food");
  //textField.changed(newText);
  textField = select('#textfield');
  textField.input(newTyping);
  //textField.changed(newText);
  output = select('#ouput');
  submit = select('#submit');

  //When click mouse, add new text
  submit.mousePressed(newText);

  // Here we call methods of each element to set the position
  // and id, try changing these values.
  // Use the inspector to look at the HTML generated from this
  // code when you load the sketch in your browser.
  canvas.position(300, 50);
  canvas.class("lemon");
}

function draw() {
  // These commands are applied to the graphics canvas as normal.
  background(220, 180, 200);
  ellipse(width/2, height/2, 100, 100);
  ellipse(width/4, height/2, 50, 50);

  //test
  if (mouseIsPressed)
    fill(0)
  else fill (255);

  ellipse (mouseX,mouseY,80,80);

}

function newText() {
  //Console out text
  console.log(textField.value());
  //Show as a new paragrah
  createP(textField.value());
}

function newTyping() {
  //Output to HTML
  output.html(textField.value());
}
