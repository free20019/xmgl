package mvc.service;

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
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.JacksonUtil;
import helper.SystemConfig;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


@Service
public class userServer {
	protected JdbcTemplate jdbcTemplate = null;
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	private JacksonUtil jacksonUtil = JacksonUtil.buildNormalBinder();
	public String finduser(String info){
		String sql = "select * from twkjywgl  where user_name like '%%'";
		if(info!=null&&info.length()>0){
			sql += " and (user_name like '%"+info+"%' or user_pwd like '%"+info+"%' or"
					+ " real_name like '%"+info+"%')";
		}
		sql += " order by CONVERT(user_id,SIGNED) desc";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return jacksonUtil.toJson(list);
	}
	public String adduser(String postData){
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,new TypeReference<Map<String, Object>>() {});
		String user_name = String.valueOf(paramMap.get("user_name"));
		String user_pwd = String.valueOf(paramMap.get("user_pwd"));
		String real_name = String.valueOf(paramMap.get("real_name"));
		String note = String.valueOf(paramMap.get("note"));
		int user_id = findMaxId("twkjywgl","user_id");
		String sql = "insert into twkjywgl (user_name,user_pwd,real_name,note,user_id) values "
				+ " ('"+user_name+"','"+user_pwd+"','"+real_name+"','"+note+"','"+user_id+"')";
		System.out.println(sql);
		int count = jdbcTemplate.update(sql);
		Map<String, Object> map = new HashMap<String, Object>();
		if(count>0){
			map.put("info", "添加成功");
		}else{
			map.put("info", "添加失败");
		}
		return jacksonUtil.toJson(map);
	}
	public String edituser(String postData){
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,new TypeReference<Map<String, Object>>() {});
		String user_name = String.valueOf(paramMap.get("user_name"));
		String user_pwd = String.valueOf(paramMap.get("user_pwd"));
		String real_name = String.valueOf(paramMap.get("real_name"));
		String note = String.valueOf(paramMap.get("note"));
		String user_id = String.valueOf(paramMap.get("user_id"));
		String sql = "update twkjywgl set user_name='"+user_name+"',user_pwd='"+user_pwd+
				"',real_name='"+real_name+"',note='"+note+"' where user_id='"+user_id+"'";
		int count = jdbcTemplate.update(sql);
		Map<String, Object> map = new HashMap<String, Object>();
		if(count>0){
			map.put("info", "修改成功");
		}else{
			map.put("info", "修改失败");
		}
		return jacksonUtil.toJson(map);
	}
	public String deluser(String postData){
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,new TypeReference<Map<String, Object>>() {});
		String id = String.valueOf(paramMap.get("id"));
		String[] ids = id.split(",");
		String is = "";
		for (int i = 0; i < ids.length; i++) {
			is += "'"+ids[i]+"',";
		}
		String sql = "delete from twkjywgl where user_id in ("+is.substring(0, is.length()-1)+")";
		System.out.println(sql);
		int count = jdbcTemplate.update(sql);
		Map<String, String> map = new HashMap<String, String>();
		if(count>0){
			map.put("info", "删除成功");
		}else{
			map.put("info", "删除失败");
		}
		return jacksonUtil.toJson(map);
	}
	public int findMaxId(String table,String id){
		int id1 = 1;
		String sql = "select "+id+" from "+table+"  order by CONVERT("+id+",SIGNED) desc";
		System.out.println(sql);
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		if(list.size()>0){
			id1 = Integer.parseInt(list.get(0).get(id).toString())+1;
		}
		return id1;
	}
}
	
	