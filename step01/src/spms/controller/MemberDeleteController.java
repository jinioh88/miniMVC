package spms.controller;

import java.util.Map;

import com.sun.media.sound.ModelDestination;

import spms.dao.MemberDao;

public class MemberDeleteController implements Controller {

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		MemberDao memberDao = (MemberDao)model.get("memberDao");
		Integer no = (Integer)model.get("no");
		
		memberDao.delete(no);
		
		return "redirect:list.do";
	}

	public Object setMemberDao(MemberDao memberDao) {
		// TODO Auto-generated method stub
		return null;
	}

}
