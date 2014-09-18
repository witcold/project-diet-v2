/**
 * 
 */
package com.dataart.spring.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author vmeshcheryakov
 *
 */
@Controller
public class WebPing {

	@Autowired
	private DBStatus dbStatus;

	@RequestMapping(value = "/status")
	@ResponseBody
	public boolean status() {
		return dbStatus.getDBStatus();
	}

}
