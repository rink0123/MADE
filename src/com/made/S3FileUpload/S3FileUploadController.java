package com.made.S3FileUpload;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 헤더 Controller
 * 
 * @since JDK 1.8
 * @version 1.0.0.RELEASE
 * @author HbeMember_Common
 */
@Controller
public class S3FileUploadController {
	private static Logger log = Logger.getLogger(S3FileUploadController.class);
	
	@RequestMapping(value = "/S3FileUpload")
	public ModelAndView test(ModelAndView modelAndView) throws Exception {
		log.info("S3FileUploadController - s3FileUpload(ModelAndView) request :: 진입(start)");
		
		modelAndView.setViewName("/S3FileUpload");
		
		return modelAndView;
	}
}