package tests;

import general.classes.*;

import org.junit.*;

import java.net.HttpURLConnection;

public class Q4Test extends HttpOps
{
	

  @Before
  public void setUp() throws Exception 
  {

	  restHash.put("TestData", new RestHash("","",""));
		
  }

  @Test
  public void testQ4() throws Exception 
  {
	  HttpURLConnection conn = null;
	  String messageId="";
	  
	  for(int i = 0;;i++)
	  {
		  
		  conn = uploadImage();
	  
		  ReadBuffer(conn);
	  
		  messageId = getResponseMsgId(restHash.get("TestData").msg);
		  
		  checkConnectionResponse(conn, 200, i);
		  
		  conn = getOp(messageId);
		  
		  if(conn.getResponseCode() != 200)
		  {
			  restHash.put("TestData", new RestHash(restHash.get("TestData").failSentence+"\n"+"Uploaded image with id="+messageId+" was not found!",restHash.get("TestData").msg,restHash.get("TestData").code));
		  }
					  
		  Thread.sleep(30000);
		  
	  }

  }


}
