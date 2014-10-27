package com.dataart.spring.config;

import java.util.Enumeration;
import java.util.Locale;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * @author vmeshcheryakov
 *
 */
public class LocalizationMessageSource extends ReloadableResourceBundleMessageSource {

	public Enumeration<Object> getKeys(Locale locale) {
		return getMergedProperties(locale).getProperties().keys();
	}

}
