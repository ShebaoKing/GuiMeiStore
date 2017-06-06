package com.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class uploadUtil {
	public static Map<String, String> upload(HttpServletRequest req,File file){
		Map<String, String> map=new HashMap<String, String>();
		
		DiskFileItemFactory ff=new DiskFileItemFactory();
		
		ServletFileUpload fu=new ServletFileUpload(ff);
		
		if(!file.exists()){
			file.mkdirs();
		}
		try {
			List<FileItem> list=fu.parseRequest(req);
			String key=null;
			String value=null;
			for (FileItem f : list) {
				if(f.isFormField()){
					key=f.getFieldName();
					value=f.getString("UTF-8");
					if(map.containsKey(key)){
						value+=":"+map.get(key);
					}
					map.put(key,value);
				}else{
					key=f.getFieldName();
					value=f.getName();
					if(!"".equals(value)){
						String val;
						val=map.get("user_login");
						if(val==null){
							val=map.get("an_title");
						}
						if(val==null){
							val=map.get("good_name");
						}
						if(val==null){
							val=map.get("store_name");
						}
						val=MD5Util.toMD5(val);
						value=val+"-"+value;
						f.write(new File(file, value));
						map.put(key, value);
					}
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
}
