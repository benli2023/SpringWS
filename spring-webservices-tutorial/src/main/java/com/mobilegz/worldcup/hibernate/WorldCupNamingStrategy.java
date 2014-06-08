package com.mobilegz.worldcup.hibernate;

import org.hibernate.cfg.ImprovedNamingStrategy;

public class WorldCupNamingStrategy extends ImprovedNamingStrategy {

	private static final long serialVersionUID = -1375063718564703323L;

	private static final String PREFIX = "T_WC_";

	@Override
	public String classToTableName(String className) {
		return PREFIX + super.classToTableName(className).toUpperCase();
	}

	@Override
	public String tableName(String tableName) {
		return super.tableName(tableName);
	}

}
