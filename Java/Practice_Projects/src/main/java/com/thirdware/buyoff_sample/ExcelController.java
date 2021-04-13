package com.thirdware.buyoff_sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.thirdware.buyoff.vo.FileVo;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ExcelController {
	
	@Autowired
	ExcelService excelService;
	
	
	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	public int fileUpload(@RequestParam("file") MultipartFile file){
		
		if(!file.isEmpty()) {
			String filename = file.getOriginalFilename();
		    String extension = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
		    if (extension.equals("xls") || extension.equals("xlsx")){
		    	try {
					System.out.println(file.getOriginalFilename());
					excelService.excelFile(file);
				    } catch (Exception e) {
				      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
				    }
				return 0;
		    }else {
		    	return 1;
		    }
		}
		return 2;
	}
	
	@RequestMapping(value = "/getExcel/{fileName}", method = RequestMethod.GET)
    public Object getExcel(@PathVariable("fileName") String fileName) throws IOException {
          File file = new File("C:\\Users\\BCP\\Desktop\\Practice Projects\\Excel Manipulation\\New Copied Files\\"+fileName);
          FileInputStream fileInputStream = null;
          try {
               fileInputStream = new FileInputStream(file);
               return IOUtils.toByteArray(fileInputStream);
          } catch (IOException e) {
               e.printStackTrace();
          }
          fileInputStream.close();
          return null;
    }

}


//@RequestMapping(value = "/report/file", produces = "application/vnd.ms-excel;charset=UTF-8")
//public void download(@RequestParam String fileName, final HttpServletResponse response) throws IOException {
//
//
//	   response.setContentType("application/vnd.ms-excel;charset=UTF-8");
//	   String excelFileName = "test.xls";
//	   String headerKey = "Content-Disposition";
//	   String headerValue = String.format("attachment; filename=\"%s\"",
//	           excelFileName);
//	   response.setHeader(headerKey, headerValue);
//	   
//	   String TargetSheetPathAndName = "C:\\Users\\BCP\\Desktop\\Practice Projects\\Excel Manipulation\\New Copied Files\\"+fileName;
//	   File targetFile = new File(TargetSheetPathAndName.trim());
//		FileInputStream inputStream = new FileInputStream(targetFile);
//		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
//
//
//	   //The response is stored in an outputstream
//	   OutputStream out = response.getOutputStream();
//	   response.setContentType("application/vnd.ms-excel");
//	   byte[] byteArray = ((MultipartFile)workbook).getBytes();
//	   out.write(byteArray);
//	   out.flush();
//	   out.close();
//
//
//	   //Wrote this one just for testing if the file is already corrupt here. --> It's fine.
//	   FileOutputStream fos = new FileOutputStream("C:\\Users\\BCP\\Desktop\\Practice Projects\\Excel Manipulation\\New Copied Files\\"+fileName);
//	   fos.write(byteArray);
//	   fos.flush();
//	   fos.close();
//	}
//
//
//@GetMapping("/sample")
//public String ArrayData() {
//	return "HI";
//}
