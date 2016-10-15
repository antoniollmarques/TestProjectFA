package general.classes;


import static org.junit.Assert.fail;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;

public class HttpOps extends RestHash
{
	
	public static HttpURLConnection uploadImage() throws Exception 
	{
		String imagePath = new File("resources/image.jpg").getAbsolutePath();
		
	    File image = new File(imagePath);
	    String key = "KEY";
	    URL url;
	    HttpURLConnection conn;
	    ByteArrayOutputStream byteArray;
	    OutputStreamWriter wr;
	    
	    BufferedImage buffImg = new BufferedImage(240, 240, BufferedImage.TYPE_BYTE_BINARY);

	    try 
	    { 
	        buffImg = ImageIO.read(image); 
	    } 
	    catch (Exception ex) 
	    { 
	    	System.out.println("An exception was thrown while reading image -> "+ex);
	    }

	    url = new URL(restHash.get("TestData").uploadPath);
	    conn = (HttpURLConnection) url.openConnection();

        byteArray = new ByteArrayOutputStream();
        ImageIO.write(buffImg, "jpg", byteArray);
        
        String data = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(Base64.encodeBase64String(byteArray.toByteArray()).toString(), "UTF-8");
        data += "&" + URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(key, "UTF-8");
        
	    conn.setDoOutput(true);
	    conn.setDoInput(true);
	    conn.setRequestMethod("POST");
	    conn.setRequestProperty("Authorization", "Client-ID " + restHash.get("TestData").clientId);
	    conn.setRequestMethod("POST");
	    conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

	    conn.connect();

	    wr = new OutputStreamWriter(conn.getOutputStream());
	    wr.write(data);
	    wr.flush();
	    wr.close();

	    return conn;
	}
	
	public static HttpURLConnection getOp(String id) throws IOException 
	{
		
		URL urlFormatted = new URL(restHash.get("TestData").uploadPath+"//"+id);
		HttpURLConnection conn =(HttpURLConnection) urlFormatted.openConnection();
		
	    conn.setRequestProperty("Authorization", "Client-ID " + restHash.get("TestData").clientId);
	    conn.setRequestMethod("GET");

		System.out.println("Get Request -> "+restHash.get("TestData").uploadPath+"//"+id);
		System.out.println("Get Response -> "+conn.getResponseCode());
  
  		return conn;
		  
	}
	
	public static void ReadBuffer(HttpURLConnection connection) throws Exception
	{
		String line;
		StringBuilder sb = null;
		BufferedReader br;
		String result;
		
		try
		{
			br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			sb = new StringBuilder();
			
			while ((line = br.readLine()) != null) 
			{
			    sb.append(line);
			}
			
			br.close();
		    
		    result = sb.toString();
		    
		}
		catch(Exception ex)
		{
			System.out.println("An exception was thrown!");
			
			result = "";
		}
		
		System.out.println("Response Message -> "+result);
		
		restHash.put("TestData", new RestHash(restHash.get("TestData").failSentence, result, restHash.get("TestData").code));
	    
	}
	
	public static void checkConnectionResponse(HttpURLConnection conn, int expectedResponseCode, int i) throws Exception
	{
		long sizeLim;
		
		System.out.println("Code -> "+conn.getResponseCode());
		try
		{
			if(conn.getResponseCode() != expectedResponseCode)
			{
				
				if(conn.getResponseCode() == 429)
				{
					restHash.put("TestData", new RestHash(restHash.get("TestData").failSentence+"\n"+"While sending HTTP POST("+conn.getURL()+"), we were expecting "+expectedResponseCode+" response code and we got "+conn.getResponseCode()+"(Too many resquests)!",restHash.get("TestData").msg,restHash.get("TestData").code));
				}
				else
				{
					sizeLim = Integer.parseInt(restHash.get("TestData").imageSizeKB)*i;
					restHash.put("TestData", new RestHash(restHash.get("TestData").failSentence+"\n"+"While sending HTTP POST("+conn.getURL()+"), we were expecting "+expectedResponseCode+" response code and we got "+conn.getResponseCode()+"("+conn.getResponseMessage()+")!",restHash.get("TestData").msg,restHash.get("TestData").code));
					restHash.put("TestData", new RestHash(restHash.get("TestData").failSentence+"\n"+"API limit size is "+sizeLim+" KB",restHash.get("TestData").msg,restHash.get("TestData").code));
				}
				
				fail(restHash.get("TestData").failSentence);
			}
		}
		catch(Exception ex)
		{
			restHash.put("failSentence", new RestHash(restHash.get("failSentence")+"\n"+"While sending HTTP POST("+conn.getURL()+"), Test failed with -> "+ex+" Exception",restHash.get("TestData").msg,restHash.get("TestData").code));
		}
		
	}

}
