package general.classes;

import java.util.HashMap;

public class RestHash extends ConfigHelp
{
	
	public String failSentence;
	public String uploadPath;
	public String alternativePath;
	public String imageSizeKB;
	public String clientId;
	public String clientId2;
	public String msg;
	public String code;
	public static HashMap<String, RestHash> restHash = new HashMap<String, RestHash>();
	
	public RestHash(String failSentence, String msg, String code) throws Exception 
	{
		
		super();
		this.failSentence = failSentence;
		this.uploadPath = getProperty("uploadPath");
		this.alternativePath = getProperty("alternativePath");
		this.imageSizeKB = getProperty("imageSizeKB");
		this.clientId = getProperty("clientId");
		this.clientId2 = getProperty("clientId2");
		this.msg = msg;
		this.code = code;
		
	}

	public RestHash()
	{

	}
	
	
}
