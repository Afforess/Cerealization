/*
 * This file is part of Cerealization.
 *
 * Copyright (c) 2013 Spout LLC <http://www.spout.org/>
 * Cerealization is licensed under the Spout License Version 1.
 *
 * Cerealization is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * In addition, 180 days after any changes are published, you can use the
 * software, incorporating those changes, under the terms of the MIT license,
 * as described in the Spout License Version 1.
 *
 * Cerealization is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Lesser General Public License,
 * the MIT license and the Spout License Version 1 along with this program.
 * If not, see <http://www.gnu.org/licenses/> for the GNU Lesser General Public
 * License and see <http://spout.in/licensev1> for the full license, including
 * the MIT license.
 */
package org.spout.cereal.config.migration;

import org.apache.commons.lang3.StringUtils;

import org.spout.cereal.config.Configuration;

/**
 * This implementation of MigrationAction converts configuration keys based on predefined stringpath patterns where % is replaced by the old key. The stringpath pattern is split by the configuration's
 * path separator
 *
 * @see org.spout.cereal.config.Configuration#getPathSeparator()
 */
public final class NewJoinedKey implements MigrationAction {
	private final String newKeyPattern;
	private final Configuration config;

	public NewJoinedKey(String newKeyPattern, Configuration config) {
		this.newKeyPattern = newKeyPattern;
		this.config = config;
	}

	@Override
	public String[] convertKey(String[] key) {
		String oldKey = StringUtils.join(key, config.getPathSeparator());
		return config.getPathSeparatorPattern().split(newKeyPattern.replace("%", oldKey));
	}

	@Override
	public Object convertValue(Object value) {
		return value;
	}
}
