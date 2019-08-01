package mvc.service;

import helper.JacksonUtil;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


@Service
public class LoginServer {
	protected JdbcTemplate jdbcTemplate = null;
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	

	private JacksonUtil jacksonUtil = JacksonUtil.buildNormalBinder();


	public String login(String username, String password,HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
//		if(!"wx".equals(username.substring(0,2))){
//			map.put("info", "1");
//			return jacksonUtil.toJson(map);
//		}
		List<Map<String, Object>> list = null;
		String sql= "select * from twkjywgl where user_name = ? and user_pwd = ?";
		System.out.println(sql);		
		list = jdbcTemplate.queryForList(sql,username,password);			
		if(list.size()>0){
			map.put("info", "0");
			request.getSession().setAttribute("username", list.get(0).get("user_name"));
			request.getSession().setAttribute("user_id", list.get(0).get("user_id"));
		}else{
			map.put("info", "1");
		}
		return jacksonUtil.toJson(map);
	}
}
