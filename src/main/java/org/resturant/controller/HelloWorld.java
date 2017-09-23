package org.resturant.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.resturant.po.User;
import org.resturant.service.UserManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
@RequestMapping("/resturant")
@Controller
public class HelloWorld {
	@Autowired
	private UserManageService userManageService;
	
	private final static Logger log = LoggerFactory.getLogger(HelloWorld.class);
	@RequestMapping("/seehi")
	@ResponseBody
	public String sayHi(){
		return "hello";
		
		
	}
	
	/**
	 * 生成地址二维码
	 * 
	 */
	private void genQrpic(String url, int width, int height, String format, Hashtable hints, String filename)
			throws Exception {
		BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, width, height, hints);

		log.debug("二维码文件保存地址" + filename);
		File outputFile = new File(filename);
		MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);

	}

	@RequestMapping(value = "/qr")
	public void qr(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model rhs)
			throws Exception {
		String url = "http:/" + HardInfo.findNonLocalhostIp() + ":" + request.getLocalPort() + request.getContextPath();
		rhs.addAttribute("url", url);
		Hashtable hints = new Hashtable();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		genQrpic("http://www.baidu.com", 200, 200, "png", hints, System.getProperty("user.home") + "/qrhome.png");

		FileInputStream fs = null;
		try {
			File file = new File(System.getProperty("user.home") + "/qrhome.png");
			fs = new FileInputStream(file);
			int len = (int) file.length();
			byte[] data = new byte[len];
			fs.read(data, 0, len);
			response.setContentType("image/jpeg");
			response.setHeader("Cache-control", "no-cache ");
			response.getOutputStream().write(data);
			response.getOutputStream().flush();

		} catch (Exception e) {
			log.debug("图片不存在");
		} finally {
			if (fs != null) {
				fs.close();
			}
		}

	}
	@ResponseBody
	@RequestMapping("/queryUserInfo")
	public List<User>  queryUserInfo(){
		log.error("##############33hello");
		return userManageService.queryUserInfo();
		
		
	}
}