package web.member.dao;

import core.dao.CoreDao;
import web.member.pojo.MemberData;

public interface MemberDataDao extends CoreDao<MemberData, Integer> {
	
	MemberData selectBymemberAccount(String memberAccount);
	
	MemberData selectForLogin(String username, String password);
}
