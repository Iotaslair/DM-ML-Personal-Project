class Node { 
    float xpos;
    float ypos;
    float gen;
    float genmax;
    float nodenum;
    String path;
    float bot;
    float top;
    String name;
    float mod;
          
    Node (String nm, float g, float gm, float nn, String pt) {  
    gen = g;
    mod = nn/(gm+1);
    xpos =(mod*1450) - 30;
    float thenew = 90*gen;
    ypos = 30+thenew;
    bot = ypos + 15;
    top = ypos - 15;
    name = nm;
    genmax = gm;
    path = pt;
    }
    
    public void make(){
      if(this.genmax>15){
          fill(180);
          this.xpos -= 10;
          ellipseMode(CENTER);
          ellipse(this.xpos, this.ypos, 50, 30);
          textAlign(CENTER);
          
          fill(20);
          text(this.name, this.xpos, this.ypos+5);
        }
        else{
          fill(180);
          ellipseMode(CENTER);
          ellipse(this.xpos, this.ypos, 75, 30);
          textAlign(CENTER);
          textSize(10);
          fill(20);
          text(this.name, this.xpos, this.ypos+5);
        }
      }
    public void makeLine(Node child){
      line(this.xpos, this.bot, child.xpos, child.top);
      float pat = ((this.xpos + child.xpos) / 2);
      float patom = ((child.xpos + pat)/2);
      float pat2 = ((this.bot + child.top)/2);
      float pat2om = ((child.top + pat2)/2);
      if(child.xpos < this.xpos){
      textAlign(RIGHT);
      }
      else{
      textAlign(LEFT); 
      }
      fill(0);
      text(child.path, patom, pat2om);
    }
}
