import processing.pdf.*;
BufferedReader reader;
String line;
ArrayList<Node> nodes = new ArrayList<Node>();


Node SMS = new Node("SMS_Received", 0,1,1,"na");
Node Age1 = new Node("Age", 1,2,1,"No");
Node App1 = new Node("ApptDay", 2,9,1,"50-74");
Node Node4 = new Node("84.47%", 3, 22,1,"Friday");
Node Node5 = new Node("86.98%", 3, 22, 2, "Tuesday");
Node Node6 = new Node("84.76%", 3, 22, 3, "Monday");
Node Node7 = new Node("87.41%", 3, 22, 4, "Wednesday");
Node Node8 = new Node("86.07%", 3, 22, 5, "Thursday");
Node Node9 = new Node("77.78%", 3, 22, 6, "Saturday");
Node App2 = new Node("ApptDay", 2, 9, 4, "0-24");
Node Node11 = new Node("77.72%", 3, 22, 7, "Friday");
Node Node12 = new Node("84.01%", 3, 22, 8, "Tuesday");
Node Node13 = new Node("81.1%", 3, 22, 9, "Monday");
Node Node14 = new Node("81.25%", 3, 22, 10, "Wednesday");
Node Node15 = new Node("82.74%", 3, 22, 11, "Thursday");
Node Node16 = new Node("Yes", 3, 22, 12, "Saturday");
Node Node17 = new Node("85.05%", 2, 9, 3, "75-119");
Node App3 = new Node("ApptDay", 2, 9, 5, "25-49");
Node Node18 =  new Node("79.77%", 3, 22, 13, "Friday");
Node Node19 = new Node("85.02%", 3, 22, 14, "Tuesday");
Node Node20 = new Node("81.27%", 3, 22, 15, "Monday");
Node Node21 = new Node("85.19%", 3, 22, 16, "Wednesday");
Node Node22 = new Node("83.9%", 3, 22, 17, "Thursday");
Node Node23 = new Node("83.33%", 3, 22, 18, "Saturday");
Node Age2 = new Node("Age", 1, 2, 2, "Yes");
Node Gen = new Node("Gender", 2,9,8,"0-24");
Node Node28 = new Node("69.56%", 3, 22, 19, "M");
Node Node29 = new Node("66.39%",3,22,20,"F");
Node Node30 = new Node("70.81%", 2,9,7,"25-49");
Node Node31 = new Node("79.66%",2,9,6,"75-119");
Node Alc = new Node("Alcoholism", 2, 9, 9, "50-74");
Node Node33 = new Node("73.33%", 3, 22, 21, "Yes");
Node Sch = new Node("Scholarship", 3, 22, 22, "No");
Node App4 = new Node("ApptDay", 4, 2, 1, "No");
Node Node36 = new Node("80.88%", 5, 6, 1, "Friday");
Node Node37 = new Node("79.92%", 5, 6, 2, "Tuesday");
Node Node38 = new Node("79.42%", 5, 6, 3, "Monday");
Node Node39 = new Node("77.86%", 5, 6, 4, "Thursday");
Node Node40 = new Node("80.21%", 5,6,5,"Wednesday");
Node Node41 = new Node("74.73%", 5, 6, 6, "Saturday");
Node Node42 = new Node("74.43%", 4, 2, 2, "Yes");

void setup(){
  background(255,255,255);
  size(1400,500,PDF, "ID3Tree.pdf");
  /*
  reader = createReader("Parse Tester.txt");
  try
  {
    while((line = reader.readLine()) != null)
    {
      Node first = makeNode(line);
      
      
    }
  }
  catch(IOException e)
  {
    e.printStackTrace();
    line = null;
  }
  
  Node makeNode(String in)
  {
    
  }
  */
  
  SMS.make();
  Age1.make();
  App1.make();
  SMS.makeLine(Age1);
  Age1.makeLine(App1);
  Node4.make();
  Node5.make();
  Node6.make();
  Node7.make();
  Node8.make();
  Node9.make();
  App1.makeLine(Node4);
  App1.makeLine(Node5);
  App1.makeLine(Node6);
  App1.makeLine(Node7);
  App1.makeLine(Node8);
  App1.makeLine(Node9);
  App2.make();
  Node11.make();
  Node12.make();
  Node13.make();
  Node14.make();
  Node15.make();
  Node16.make();
  App2.makeLine(Node11);
  App2.makeLine(Node12);
  App2.makeLine(Node13);
  App2.makeLine(Node14);
  App2.makeLine(Node15);
  App2.makeLine(Node16);
  Age1.makeLine(App2);
  Node17.make();
  Age1.makeLine(Node17);
  App3.make();
  Node18.make();
  Node19.make();
  Node20.make();
  Node21.make();
  Node22.make();
  Node23.make();
  App3.makeLine(Node18);
  App3.makeLine(Node19);
  App3.makeLine(Node20);
  App3.makeLine(Node21);
  App3.makeLine(Node22);
  App3.makeLine(Node23);
  Age2.make();
  Gen.make();
  Node28.make();
  Node29.make();
  Node30.make();
  Node31.make();
  Alc.make();
  SMS.makeLine(Age2);
  Age2.makeLine(Gen);
  Age2.makeLine(Alc);
  Age2.makeLine(Node31);
  Age2.makeLine(Node30);
  Node33.make();
  Sch.make();
  App4.make();
  Node36.make();
  Node37.make();
  Node38.make();
  Node39.make();
  Node40.make();
  Node41.make();
  Node42.make();
  Alc.makeLine(Node33);
  Alc.makeLine(Sch);
  Sch.makeLine(App4);
  Sch.makeLine(Node42);
  App4.makeLine(Node36);
  App4.makeLine(Node37);
  App4.makeLine(Node38);
  App4.makeLine(Node39);
  App4.makeLine(Node40);
  App4.makeLine(Node41);
  Gen.makeLine(Node28);
  Gen.makeLine(Node29);
  Age1.makeLine(App3);
  
}
