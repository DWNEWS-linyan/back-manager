package entity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;

import com.asprise.ocr.Ocr;

/**
* @ClassName: X
* @Description: 
* @author linyan
* @date 2018年2月1日 下午2:35:31
*
*/
public class X {

	
	public static void main(String[] args) throws IOException {  
        HttpClient httpClient = new HttpClient();  
//        GetMethod getMethod = new GetMethod("http://dz.bjjtgl.gov.cn/service/checkCode.do");  
      GetMethod getMethod = new GetMethod("https://console.oray.com/seccode?name=forgot&r=1517466729465");  
        int statusCode = httpClient.executeMethod(getMethod);  
        if (statusCode != HttpStatus.SC_OK) {  
            System.err.println("Method failed: " + getMethod.getStatusLine());  
            return ;  
        }  
        String picName = "d:\\img\\";  
        File filepic=new File(picName);  
        if(!filepic.exists())  
            filepic.mkdir();  
        File filepicF=new File(picName+new Date().getTime() + ".jpg");  
        InputStream inputStream = getMethod.getResponseBodyAsStream();  
        OutputStream outStream = new FileOutputStream(filepicF);  
        IOUtils.copy(inputStream, outStream);  
        outStream.close();  
   
//		File filepicF= new File("C:\\Users\\linyan\\Desktop\\seccode.png");
		
        Ocr.setUp(); // one time setup  
        Ocr ocr = new Ocr(); // create a new OCR engine  
        ocr.startEngine("eng", Ocr.SPEED_FASTEST); // English  
        String s = ocr.recognize(new File[] {filepicF},Ocr.RECOGNIZE_TYPE_TEXT, Ocr.OUTPUT_FORMAT_PLAINTEXT);  
        System.out.println("Result: " + s);  
        System.out.println("图片文字为:" + s.replace(",", "").replace("i", "1").replace(" ", "").replace("'", "").replace("o", "0").replace("O", "0").replace("g", "6").replace("B", "8").replace("s", "5").replace("z", "2"));  
        // ocr more images here ...  
        ocr.stopEngine();  
    }  
	
}
