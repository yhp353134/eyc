package com.fh.util;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeBugUtil {
	private static Logger logger = LoggerFactory.getLogger(DeBugUtil.class);
	
	@SuppressWarnings("unchecked")
	public static void LogPrintLn(PageData pd) {
		Set<String> keySet = pd.keySet();
		 for (String key : keySet) {
			 logger.debug("传入的参数==="+key+"==值为："+pd.get(key));
			 logger.info("传入的参数==="+key+"==值为："+pd.get(key));
		}
	}

}
