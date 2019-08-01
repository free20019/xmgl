/*package mvc.controllers;


import helper.DownloadAct;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.service.CommonServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
*//**
 * 公共基础类，通用方法
 *//*
@Controller
@RequestMapping(value = "/common")
public class CommonAction {
	private CommonServer commonService;

	public CommonServer getCommonServer() {
		return commonService;
	}

	@Autowired
	public void setCommonServer(CommonServer commonService) {
		this.commonService = commonService;
	}
	@RequestMapping(value = "/upload")
	@ResponseBody
	public String upload(HttpServletRequest request,HttpServletResponse response) {
		String msg = "";
	  msg = commonService.upload(request, response);
	  return msg;
	}
	@RequestMapping(value = "/download")
	@ResponseBody
	public synchronized String download(HttpServletRequest request,HttpServletResponse response,@RequestParam("id") String id,@RequestParam("type") String type) throws IOException {
		String msg = "";
		commonService.download(request, response,id,type);
	    return msg;
	}
}
*/