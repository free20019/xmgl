package mvc.controllers;


import helper.DownloadAct;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.service.ProjectServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/xmgl")
public class ProjectAction {
	private ProjectServer projectserver;
	
	
	public ProjectServer getProjectserver() {
		return projectserver;
	}
	@Autowired
	public void setProjectserver(ProjectServer projectserver) {
		this.projectserver = projectserver;
	}

	@RequestMapping("/findxmnr")
	@ResponseBody
	public String findxmnr(@RequestParam("postData") String postData) {
		String msg = projectserver.findxmnr(postData);	
	    return msg;
	}
	@RequestMapping("/xmdetail")
	@ResponseBody
	public String xmdetail(@RequestParam("id") String id) {
		String msg = projectserver.xmdetail(id);
	    return msg;
	}
	@RequestMapping("/xmglexcle")
	@ResponseBody
	public String xmglexcle(HttpServletRequest request,
			HttpServletResponse response,@RequestParam("postData") String postData) throws IOException {
		String a[] = {"项目ID","项目名称","项目模块","项目地址","项目开发人员","项目登陆名","项目登录密码","最后修改日期","备注信息"
				,"文件地址1","文件名称1","文件地址2","文件名称2","文件地址3","文件名称3"};//导出列明
		String b[] = {"PRO_ID","PRO_NAME","PRO_MODULE","PRO_ADDRESS","PRO_DEVELOPER","PRO_USER","PRO_PASSWORD","JSSJ","PRO_REMARK",
				"PRO_FILE1","PRO_FILENAME1","PRO_FILE2","PRO_FILENAME2","PRO_FILE3","PRO_FILENAME3"};//导出map中的key
		String gzb = "项目管理";//导出sheet名和导出的文件名
		postData = java.net.URLDecoder.decode(postData, "UTF-8");
		String msg = projectserver.xmnr(postData);
		List<Map<String, Object>>list = DownloadAct.parseJSON2List2(msg);
		DownloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
	@RequestMapping("/addxm")
	@ResponseBody
	public String addxm(@RequestParam("postData") String postData,HttpServletRequest request) {
		String msg = projectserver.addxm(postData,request);	
	    return msg;
	}
	
	@RequestMapping("/editxm")
	@ResponseBody
	public String editxm(@RequestParam("postData") String postData) {
		String msg = projectserver.editxm(postData);
	    return msg;
	}
	
	@RequestMapping("/delxm")
	@ResponseBody
	public String delxm(HttpServletRequest request,@RequestParam("postData") String postData) {
		String msg = projectserver.delxm(postData);
	    return msg;
	}
}
