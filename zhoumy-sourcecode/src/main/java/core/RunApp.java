package core;

import java.net.URL;

import org.mybatis.generator.api.ShellRunner;

public class RunApp {
	public static void main(String[] args) {
		ShellRunner sr = new ShellRunner();
		URL url = Thread.currentThread().getContextClassLoader().getResource("sqlMapGeneractor.xml");
		args = new String[] { "-configfile", url.getPath().substring(1) };
		sr.main(args);
	}
}
