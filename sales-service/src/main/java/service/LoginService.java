package service;

/**
 * Created by xavier on 2018/1/21.
 */
public interface LoginService {

	public boolean login(String name, String md5Pwd);

	public void quit();
}
