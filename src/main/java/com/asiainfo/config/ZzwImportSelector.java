package com.asiainfo.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import com.asiainfo1.conf.ZzwAutoConfiguration;

public class ZzwImportSelector implements ImportSelector {

	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		// TODO Auto-generated method stub
		return new String[] {ZzwAutoConfiguration.class.getName()};
	}

}
