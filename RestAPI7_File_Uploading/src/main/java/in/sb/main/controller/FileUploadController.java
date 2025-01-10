package in.sb.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import in.sb.main.helper.FileUploadHelper;

@RestController
public class FileUploadController 
{
	@Autowired
	private FileUploadHelper fileUploadHelper;
	@PostMapping("/upload-file")
	public ResponseEntity<String> uploadFile(@RequestParam("file")MultipartFile file)
	{
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
		System.out.println(file.getContentType());
		System.out.println(file.getName());
		
		
		
					//validation
				try
				{
						if(file.isEmpty())
							{
								return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("no file conatins");
							}
		
		
		
						//file type
						if(file.getContentType().equals("image/jpg,jpeg"))
						{
							return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("only jpg/jpeg files are allowed");
						}
		
						//upload file(streams ani chahiye learn it)
						//upload karne ke liye know path
						boolean f=fileUploadHelper.uploadFile(file);
						if(f)
						{
							return ResponseEntity.ok("File is uploaded successfully");
						}
				}
				catch(Exception e)
					{
					e.printStackTrace();
					}
		
						return ResponseEntity.ok("working");
	}
}
