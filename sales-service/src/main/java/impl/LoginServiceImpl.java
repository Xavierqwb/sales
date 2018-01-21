package impl;

import org.springframework.stereotype.Service;

import service.LoginService;
import utils.Security;

/**
 * Created by xavier on 2018/1/21.
 */
@Service
public class LoginServiceImpl implements LoginService {

	@Override
	public boolean login(String name, String md5Pwd) {
		return name.equals("seller") && md5Pwd.equals(Security.md5("relles"))
		       || name.equals("buyer") && md5Pwd.equals(Security.md5("reyub"));

	}

	@Override
	public void quit() {

	}
}
