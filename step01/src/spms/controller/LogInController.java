package spms.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import spms.dao.MemberDao;
import spms.vo.Member;

public class LogInController implements Controller {

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		if(model.get("loginInfo")==null) {
			return "/auth/LogInForm.jsp";
		} else {
			MemberDao memberDao = (MemberDao)model.get("memberDao");
			Member mtemp = (Member)model.get("loginInfo");
			Member member = memberDao.exist(mtemp.getEmail(), mtemp.getPassword());
			
			if(member==null) {
				HttpSession session = (HttpSession)model.get("session");
				session.setAttribute("member",member);
			} else {
				return "/auth/LogInFail.jsp";
			}
		}
	}

}
