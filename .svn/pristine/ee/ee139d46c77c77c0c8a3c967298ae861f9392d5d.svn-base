package mvc.service;

import helper.JacksonUtil;










import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProjectServer {
	protected JdbcTemplate jdbcTemplate = null;
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	

	private JacksonUtil jacksonUtil = JacksonUtil.buildNormalBinder();


	public String findxmnr(String postData) {
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,new TypeReference<Map<String, Object>>() {});
		String XMNR = String.valueOf(paramMap.get("XMNR"));
		String sql= "select * from tw_project where PRO_NAME like '%"+XMNR+"%' ORDER BY PRO_TIME_END desc ";
		System.out.println(sql);		
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);			
		return jacksonUtil.toJson(list);
	}
	public String xmdetail(String id) {
		String sql= "select * from tw_project where PRO_ID = "+id ;
		System.out.println(sql);		
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);			
		return jacksonUtil.toJson(list);
	}
	public String xmnr(String postData) {
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,new TypeReference<Map<String, Object>>() {});
		String STIME = String.valueOf(paramMap.get("STIME"));
		String ETIME = String.valueOf(paramMap.get("ETIME"));
		String XMNR = String.valueOf(paramMap.get("XMNR"));
		String KFRY = String.valueOf(paramMap.get("KFRY"));
		String sql= "select * from tw_project where PRO_NAME like '%"+XMNR+"%' and PRO_DEVELOPER like '%"+KFRY+"%' "
				+ "and PRO_TIME_END >=str_to_date('"+STIME+" 00:00:00','%Y-%m-%d %H:%i:%s') "
						+ "and PRO_TIME_END <=str_to_date('"+ETIME+" 23:59:59','%Y-%m-%d %H:%i:%s') ";
		System.out.println(sql);		
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).put("JSSJ", String.valueOf(list.get(i).get("PRO_TIME_END")));
		}
		return jacksonUtil.toJson(list);
	}
	public String addxm(String postData,HttpServletRequest request) {
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,new TypeReference<Map<String, Object>>() {});
		String XMNR = String.valueOf(paramMap.get("projectName"));
		String ETIME = String.valueOf(paramMap.get("endTime"));
		String XMMK = String.valueOf(paramMap.get("module"));
		String KFRY = String.valueOf(paramMap.get("developer"));
		String people = String.valueOf(paramMap.get("people"));
		String database = String.valueOf(paramMap.get("database"));
		String XMDZ = String.valueOf(paramMap.get("address"));
		String BZXX = String.valueOf(paramMap.get("remark"));
		String type = String.valueOf(paramMap.get("type"));
		String user = String.valueOf(paramMap.get("user"));
		String password = String.valueOf(paramMap.get("password"));
		String PRO_ID = "";
		String sql="";
		BZXX=BZXX.replace("<br/ >", "\\r\\n");
		if(type.equals("0")){
			PRO_ID =String.valueOf(request.getSession().getAttribute("PRO_ID"));
			sql = "update tw_project set PRO_NAME='"+XMNR+"',PRO_MODULE='"+XMMK+
					"',PRO_ADDRESS='"+XMDZ+"',PRO_DEVELOPER='"+KFRY+"',PRO_PEOPLE='"+people+"',DATABASE_ADDRESS='"+database+"',PRO_TIME_END='"+ETIME+"',PRO_USER='"+user+"',PRO_PASSWORD='"+password+"',"
					+ "PRO_REMARK='"+BZXX+"' where PRO_ID='"+PRO_ID+"'";
		}else if(type.equals("1")){	
			PRO_ID =findMaxId("tw_project","PRO_ID")+"";
			sql = "insert into tw_project (PRO_ID,PRO_NAME,PRO_MODULE,PRO_ADDRESS,PRO_DEVELOPER,PRO_PEOPLE,DATABASE_ADDRESS,PRO_TIME_END,PRO_USER,PRO_PASSWORD,PRO_REMARK) values "
					+ " ('"+PRO_ID+"','"+XMNR+"','"+XMMK+"','"+XMDZ+"','"+KFRY+"','"+people+"','"+database+"','"+ETIME+"','"+user+"','"+password+"','"+BZXX+"')";
		}
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
	
	public String editxm(String postData){
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,new TypeReference<Map<String, Object>>() {});
		String XMNR = String.valueOf(paramMap.get("projectName"));
		String ETIME = String.valueOf(paramMap.get("endTime"));
		String XMMK = String.valueOf(paramMap.get("module"));
		String KFRY = String.valueOf(paramMap.get("developer"));
		String people = String.valueOf(paramMap.get("people"));
		String database = String.valueOf(paramMap.get("database"));
		String XMDZ = String.valueOf(paramMap.get("address"));
		String BZXX = String.valueOf(paramMap.get("remark"));
		String user = String.valueOf(paramMap.get("user"));
		String password = String.valueOf(paramMap.get("password"));
		String PRO_ID = String.valueOf(paramMap.get("PRO_ID"));
		BZXX=BZXX.replace("<br/ >", "\\r\\n");
		String sql = "update tw_project set PRO_NAME='"+XMNR+"',PRO_MODULE='"+XMMK+
				"',PRO_ADDRESS='"+XMDZ+"',PRO_DEVELOPER='"+KFRY+"',PRO_PEOPLE='"+people+"',DATABASE_ADDRESS='"+database+"',PRO_TIME_END='"+ETIME+"',PRO_USER='"+user+"',PRO_PASSWORD='"+password+"',"
						+ "PRO_REMARK='"+BZXX+"' where PRO_ID='"+PRO_ID+"'";
		int count = jdbcTemplate.update(sql);
		Map<String, Object> map = new HashMap<String, Object>();
		if(count>0){
			map.put("info", "修改成功");
		}else{
			map.put("info", "修改失败");
		}
		return jacksonUtil.toJson(map);
	}
	
	public String delxm(String postData){
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,new TypeReference<Map<String, Object>>() {});
		String id = String.valueOf(paramMap.get("id"));
		String[] ids = id.split(",");
		String is = "";
		for (int i = 0; i < ids.length; i++) {
			is += "'"+ids[i]+"',";
		}
		String sql = "delete from tw_project where PRO_ID in ("+is.substring(0, is.length()-1)+")";
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
