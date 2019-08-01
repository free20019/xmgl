package mvc.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.JacksonUtil;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


@Service
public class CommonServer {    
	protected JdbcTemplate jdbcTemplate = null;
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	

	private JacksonUtil jacksonUtil = JacksonUtil.buildNormalBinder();
	public int findMaxId(){
		int id = 1;
		String sql = "select max(CONVERT(PRO_ID,SIGNED)) PRO_ID from tw_project";
		System.out.println(sql);
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		if(list.size()>0){
			id = Integer.parseInt(list.get(0).get("PRO_ID").toString())+1;
		}
		return id;
	}
	public String upload(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		int tp = 0;
		if (ServletFileUpload.isMultipartContent(request)) {
			String saveAsFileName = "";
			ServletFileUpload uploadHandler = new ServletFileUpload(new DiskFileItemFactory());
			response.setContentType("application/json");
			try {
				// writer = response.getWriter();
				List<FileItem> items = uploadHandler.parseRequest(request);
				String pro_id = "";
				for (FileItem item : items) {
					 String name = item.getFieldName();
					 java.io.BufferedReader br = new BufferedReader(
                             new InputStreamReader(item.getInputStream()));

					if(name.equals("PRO_ID")){
						pro_id =  br.readLine();
                    }
				}
				if(pro_id!=null&&!String.valueOf(pro_id).equals("null")&&!String.valueOf(pro_id).equals("")){
					request.getSession().setAttribute("PRO_ID", pro_id);
				}else{
					pro_id = findMaxId()+"";
					jdbcTemplate.update("insert into tw_project (PRO_ID) values ('"+pro_id+"')");
					request.getSession().setAttribute("PRO_ID", pro_id);
				}
				for (FileItem item : items) {
					String name = item.getFieldName();
					 java.io.BufferedReader br = new BufferedReader(
                            new InputStreamReader(item.getInputStream()));
					/**
					 * 提交的时候有文件的才上传保存至数据库
					 */
					if (item.getName() != null && item.getName().length() > 0) {
					
						if (!item.isFormField()) {
							String fp = "E:\\upload\\";
							File f = new File(fp);
							if (!f.exists()) {
								f.mkdirs();
							}
							if (item.getName().isEmpty() || item.getSize() == 0) {
								continue;
							}
							saveAsFileName = System.currentTimeMillis() + item.getFieldName()
									+ item.getName().substring(item.getName().lastIndexOf("."));

							File file = new File(fp + saveAsFileName + ".tmp");
							item.write(file);
							file.renameTo(new File(fp + saveAsFileName));
							try {
								String filename=new String(item.getName().getBytes("GBK"), "UTF-8");
								String in = fp+ saveAsFileName;
								in=in.replaceAll("\\\\", "/");
								System.out.println("in="+in);
								if (item.getFieldName().equals("RR_PICT1")) {
									int a = 0;
									String sql = "select * from tw_project where pro_id = ?";
									List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, pro_id);
									if(list.size()>0){
											a = jdbcTemplate.update(
													"update tw_project set PRO_FILE1 ='"+in+"',PRO_FILENAME1='"+filename+"' where PRO_ID = ?",
													new Object[] {pro_id});
											if (a == 0) {
												return "{\"code\":400,\"data\":\"文件提交失败\"}";
											} else {
												tp = 1;
												
											}		
									}else{
										return "{\"code\":400,\"data\":\"文件提交失败\"}";
									}
								}else if (item.getFieldName().equals("RR_PICT2")) {
									int a = 0;
									String sql = "select * from tw_project where pro_id = ?";
									List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, pro_id);
									if(list.size()>0){
											a = jdbcTemplate.update(
													"update tw_project set PRO_FILE2 ='"+in+"',PRO_FILENAME2='"+filename+"' where PRO_ID = ?",
													new Object[] {pro_id});
											if (a == 0) {
												return "{\"code\":400,\"data\":\"文件提交失败\"}";
											} else {
												tp = 1;
											}
										
										
									}else{
										return "{\"code\":400,\"data\":\"文件提交失败\"}";
									}
								}else if (item.getFieldName().equals("RR_PICT3")) {
									int a = 0;
									String sql = "select * from tw_project where pro_id = ?";
									List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, pro_id);
									if(list.size()>0){
											a = jdbcTemplate.update(
													"update tw_project set PRO_FILE3 ='"+in+"',PRO_FILENAME3='"+filename+"' where PRO_ID = ?",
													new Object[] {pro_id});
											if (a == 0) {
												return "{\"code\":400,\"data\":\"文件提交失败\"}";
											} else {
												tp = 1;
											}
										
										
									}else{
										return "{\"code\":400,\"data\":\"文件提交失败\"}";
									}
								} 
							} catch (Exception e) {
								return "{\"code\":400,\"data\":\"文件提交失败\"}";
							}
						
						}
					}
				}
			} catch (FileUploadException e) {
				return "{\"code\":400,\"data\":\"" + e.getMessage() + "\"}";
				
			} catch (Exception e) {
				return "{\"code\":400,\"data\":\"" + e.getMessage() + "\"}";
			} finally {
			}
		}
		if (tp == 0) {
			return "{\"code\":400,\"data\":\"文件提交未提交，请重新提交\"}";
		}
		map.put("data", "OK");
		return jacksonUtil.toJson(map);
	}
	public void download(HttpServletRequest request, HttpServletResponse response,String id,String type) throws IOException{
		Map<String, String> map=getFile(id,type);
		String address=map.get("address").toString();
		String filename=map.get("filename").toString();
		System.out.println(address);
		 //3.设置content-disposition响应头控制浏览器以下载的形式打开文件

		response.setContentType("application/octet-stream;charset=UTF-8");// 设置文件输出类型
        response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(filename, "UTF-8"));
        //4.获取要下载的文件输入流
        InputStream in=null;
        OutputStream out=null;
        try {//获取要下载的文件的绝对路径
            in=new FileInputStream(address);
            int len = 0;
             //5.创建数据缓冲区
             byte[] buffer = new byte[1024];
           //6.通过response对象获取OutputStream流
             out = response.getOutputStream();
             //7.将FileInputStream流写入到buffer缓冲区
             while ((len = in.read(buffer)) > 0) {//in.read(byte[] b)最多读入b.length个字节 在碰到流的结尾时 返回-1
             //8.使用OutputStream将缓冲区的数据输出到客户端浏览器
                 out.write(buffer,0,len);
             }
         }catch (FileNotFoundException e) {
            e.printStackTrace();
         } finally{	            
				in.close();
				out.close();
         }
	}
	public Map<String, String> getFile(String id,String type){
		String sql = "";
		List<Map<String, Object>> list = null;
		String address="";
		String filename="";
		Map<String, String> map=new HashMap<String, String >();
		if(type.equals("1")){
			sql = "select PRO_FILE1,PRO_FILENAME1 from tw_project where pro_id = '"+ id+"'";
			list = jdbcTemplate.queryForList(sql);
			address=list.get(0).get("PRO_FILE1").toString();
			filename=list.get(0).get("PRO_FILENAME1").toString();		
		}else if(type.equals("2")){
			sql = "select PRO_FILE2,PRO_FILENAME2 from tw_project where pro_id = '"+ id+"'";
			list = jdbcTemplate.queryForList(sql);
			address=list.get(0).get("PRO_FILE2").toString();
			filename=list.get(0).get("PRO_FILENAME2").toString();
		}else if(type.equals("3")){
			sql = "select PRO_FILE3,PRO_FILENAME3 from tw_project where pro_id = '"+ id+"'";
			list = jdbcTemplate.queryForList(sql);
			address=list.get(0).get("PRO_FILE3").toString();
			filename=list.get(0).get("PRO_FILENAME3").toString();
		}
		map.put("filename",filename);
		map.put("address", address);
		return map;
	}
}
