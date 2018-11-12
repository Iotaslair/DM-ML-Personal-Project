static class NodeMaker
{
  ArrayList<String> makeNode(String in)
  {
    ArrayList<String> rtn = new ArrayList<String>();
    int startNode = in.indexOf("(");
    if(in.substring(startNode).contains("("))
    {
      makeNode(in.substring(startNode));
    }
    //handle null
    if(in.indexOf("null") != -1)
    {
      rtn.add("null");
    }
    else //handle children
    {
      print("Children");
    }
    
    
    
    return rtn;
  }
}
