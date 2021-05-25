package controller;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

public class CheckInjection {
	public static boolean isJsInject(String input) {
		return !input.equals(Jsoup.clean(input, Whitelist.basic()));
	}
}
