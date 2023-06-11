package web.member.service.impl;

import web.member.dao.MemberDataDao;
import web.member.dao.impl.MemberDataDaoImpl;
import web.member.pojo.MemberData;
import web.member.service.MemberService;
import core.pojo.Core;

import java.util.List;

public class MemberServiceImpl implements MemberService {
	private MemberDataDao dao;
	
	public MemberServiceImpl() {
		dao = new MemberDataDaoImpl();
	}
	
	@Override
	public MemberData register(MemberData memberData) {
		if (memberData.getMemberAccount() == null) {
			memberData.setMessage("帳號未輸入");
			memberData.setSuccessful(false);
			return memberData;
		}
		
		if (memberData.getMemberPassword() == null) {
			memberData.setMessage("密碼未輸入");
			memberData.setSuccessful(false);
			return memberData;
		}
		
		if (memberData.getMemberName() == null) {
			memberData.setMessage("姓名未輸入");
			memberData.setSuccessful(false);
			return memberData;
		}
		if (memberData.getMemberPhone() == null) {
			memberData.setMessage("號碼未輸入");
			memberData.setSuccessful(false);
			return memberData;
		}
		if (memberData.getMemberEmail() == null) {
			memberData.setMessage("信箱未輸入");
			memberData.setSuccessful(false);
			return memberData;
		}
try {//
	beginTransaction();//
		if (dao.selectBymemberAccount(memberData.getMemberAccount()) != null) {
			memberData.setMessage("帳號重複");
			memberData.setSuccessful(false);
			rollback();//
			return memberData;
		}

//		memberData.setMemberStat(0);
		final int resultCount = dao.insert(memberData);//執行insert
		if (resultCount < 1) {
			memberData.setMessage("註冊錯誤，請聯絡管理員!");
			memberData.setSuccessful(false);
			rollback();//
			return memberData;
		}

		memberData.setMessage("註冊成功");
		memberData.setSuccessful(true);
		commit();//
		return memberData;
	}catch(Exception e){//
		rollback();//
		e.printStackTrace();//
		return null;//
	}//
	}
	@Override
	public MemberData login(MemberData memberData) {
		final String MemberAccount = memberData.getMemberAccount();
		final String MemberPassword = memberData.getMemberPassword();
		
		if (MemberAccount == null) {
			memberData.setMessage("帳號未輸入");
			memberData.setSuccessful(false);
			return memberData;
		}
		
		if (MemberPassword == null) {
			memberData.setMessage("密碼未輸入");
			memberData.setSuccessful(false);
			return memberData;
		}
		
		memberData = dao.selectForLogin(MemberAccount, MemberPassword);
		if (memberData == null) {
			memberData = new MemberData();
			memberData.setMessage("帳號或密碼錯誤");
			memberData.setSuccessful(false);
			return memberData;
		}
		
		memberData.setMessage("登入成功");
		memberData.setSuccessful(true);
		return memberData;
	}

	@Override
	public MemberData edit(MemberData memberData) {
		final MemberData oMember = dao.selectBymemberAccount(memberData.getMemberAccount());
		memberData.setMemberName(oMember.getMemberName());
		memberData.setMemberGender(oMember.getMemberGender());
		memberData.setMemberPassword(oMember.getMemberPassword());
		memberData.setMemberPhone(oMember.getMemberPhone());
		memberData.setMemberEmail(oMember.getMemberEmail());
		memberData.setMemberAddress(oMember.getMemberAddress());
		memberData.setMemberBirthday(oMember.getMemberBirthday());
		memberData.setMemberNation(oMember.getMemberNation());
		memberData.setMemberPic(oMember.getMemberPic());
		memberData.setMemberCard(oMember.getMemberCard());
		final int resultCount = dao.update(memberData);
		memberData.setSuccessful(resultCount > 0);
		memberData.setMessage(resultCount > 0 ? "修改成功" : "修改失敗");
		return memberData;
	}

	@Override
	public List<MemberData> findAll() {
		return dao.selectAll();
	}

	@Override
	public boolean remove(Integer id) {
		try {
			beginTransaction();
			final int resultCount=dao.deleteById(id);
			commit();
			return resultCount >0;
		} catch (Exception e) {
			rollback();
			e.printStackTrace();
			return false;
		}
//			return dao.deleteById(id)>0;
	}

	@Override
	public boolean save(MemberData memberData) {
		return dao.update(memberData) > 0;
	}
}
