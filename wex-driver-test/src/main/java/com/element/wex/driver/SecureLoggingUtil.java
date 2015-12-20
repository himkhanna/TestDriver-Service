package com.element.wex.driver;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class SecureLoggingUtil {

	private String maskingChar;

	private String secureFields;

	private static final String REGEX_SPLIT = ",";
	
	private static final char SLASH = '/';

	public String maskSensitiveFields(String data) {
		List<String> sensitiveFieldsList = Arrays.asList(secureFields.trim()
				.split(REGEX_SPLIT));

		for (String sensitiveField : sensitiveFieldsList) {
			int beginIndex = 0;
			int lastIndex = -1;
			boolean emptyPass = false;

			while (beginIndex != -1
					&& (beginIndex = StringUtils.indexOfIgnoreCase(data, sensitiveField,
							beginIndex)) > 0) {

				beginIndex = StringUtils.indexOf(data, ">", beginIndex);
				if (beginIndex != -1) {
					char ch = data.charAt(beginIndex - 1);
					if (ch == SLASH) {
						emptyPass = true;
					}
					if (!emptyPass) {
						lastIndex = StringUtils.indexOf(data, "<", beginIndex);
						if (lastIndex != -1) {
							String overlay = maskingChar;
							String str2 = StringUtils.substring(data,
									beginIndex + 1, lastIndex);
							if (str2 != null && str2.length() > 1) {
								overlay = StringUtils.rightPad(overlay,
										str2.length(), maskingChar);

								int temp = beginIndex + 1;
								data = StringUtils.overlay(data, overlay, temp,
										lastIndex);
							}
						}
					}
					if (emptyPass) {
						emptyPass = false;
						lastIndex = beginIndex + 1;
					} else {
						if (lastIndex != -1) {
							lastIndex = StringUtils
									.indexOf(data, ">", lastIndex);
						}
					}
				}
				beginIndex = lastIndex;
			}
		}
		return data;

	}

	public String getMaskingChar() {
		return maskingChar;
	}

	public void setMaskingChar(String maskingChar) {
		this.maskingChar = maskingChar;
	}

	public String getSecureFields() {
		return secureFields;
	}

	public void setSecureFields(String secureFields) {
		this.secureFields = secureFields;
	}

}