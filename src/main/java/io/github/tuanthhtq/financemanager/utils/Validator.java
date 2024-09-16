package io.github.tuanthhtq.financemanager.utils;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author io.github.tuanthhtq
 */

@Component
public class Validator {
	public static boolean email(String email) {
		String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
		Pattern pattern = Pattern.compile(emailRegex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public static boolean phone(String phone) {
		String phoneRegex = "^0[1-9]\\d{9,}$";
		Pattern pattern = Pattern.compile(phoneRegex);
		Matcher matcher = pattern.matcher(phone);
		return matcher.matches();
	}

	public static boolean name(String fullName) {
		String fullNameRegex = "^[A-Za-z]+(?:\\s[A-Za-z]+)+$";
		Pattern pattern = Pattern.compile(fullNameRegex);
		Matcher matcher = pattern.matcher(fullName);
		return matcher.matches();
	}


}
