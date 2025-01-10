package in.sb.main.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper 
{
	public final String UPLOAD_DIR="C:\\STS project\\RestAPI7_File_Uploading\\src\\main\\resources\\static\\image"; 
	
	public boolean uploadFile(MultipartFile multipartFile)
	{
		boolean f=false;
		try 
		{
			//---------------use NIO package to convert in 1 line code----------------
			Files.copy(multipartFile.getInputStream(), Paths.get(UPLOAD_DIR +File.separator+multipartFile.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
			
			
			
//---------used Stream Api------------
//			InputStream=multipartFile.getInputStream();												
//			byte data[]=new byte[is.available()];
//			is.read(data);
//			
//			//write
//			FileOutputStream fos=FileOutputStream(UPLOAD_DIR+"\\"+multipartFile.getOriginalFilename());
//			fos.write(data);
//			fos.flush();
//			fos.close();
			f=true;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return f;
	}
}
