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
//try {//
//	beginTransaction();//
		if (dao.selectBymemberAccount(memberData.getMemberAccount()) != null) {
			memberData.setMessage("帳號重複");
			memberData.setSuccessful(false);
//			rollback();//
			return memberData;
		}

//		memberData.setMemberStat(0);
		final int resultCount = dao.insert(memberData);//執行insert
		if (resultCount < 1) {
			memberData.setMessage("註冊錯誤，請聯絡管理員!");
			memberData.setSuccessful(false);
//			rollback();//
			return memberData;
		}

		memberData.setMessage("註冊成功");
		memberData.setSuccessful(true);
//		commit();//
		return memberData;
//	}catch(Exception e){//
//		rollback();//
//		e.printStackTrace();//
//		return null;//
//	}//
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
//	public MemberData edit(MemberData memberData) {
//		final MemberData oMember = dao.selectById(memberData.getMemberNo());
//		memberData.setMemberName(oMember.getMemberName());
//		memberData.setMemberGender(oMember.getMemberGender());
//		memberData.setMemberPassword(oMember.getMemberPassword());
//		memberData.setMemberPhone(oMember.getMemberPhone());
//		memberData.setMemberEmail(oMember.getMemberEmail());
//		memberData.setMemberAddress(oMember.getMemberAddress());
//		memberData.setMemberBirthday(oMember.getMemberBirthday());
//		memberData.setMemberNation(oMember.getMemberNation());
//		memberData.setMemberPic(oMember.getMemberPic());
//		memberData.setMemberCard(oMember.getMemberCard());
//		final int resultCount = dao.update(memberData);
//		memberData.setSuccessful(resultCount > 0);
//		memberData.setMessage(resultCount > 0 ? "修改成功" : "修改失敗");
//		return memberData;
//	}
	public MemberData edit(MemberData memberData) {
		final MemberData oMember = dao.selectById(memberData.getMemberNo());

		if (memberData.getMemberName() != null) {
			oMember.setMemberName(memberData.getMemberName());
		}
		if (memberData.getMemberGender() != null) {
			oMember.setMemberGender(memberData.getMemberGender());
		}
		if (memberData.getMemberPassword() != null) {
			oMember.setMemberPassword(memberData.getMemberPassword());
		}
		if (memberData.getMemberPhone() != null) {
			oMember.setMemberPhone(memberData.getMemberPhone());
		}
		if (memberData.getMemberEmail() != null) {
			oMember.setMemberEmail(memberData.getMemberEmail());
		}
		if (memberData.getMemberAddress() != null) {
			oMember.setMemberAddress(memberData.getMemberAddress());
		}
		if (memberData.getMemberBirthday() != null) {
			oMember.setMemberBirthday(memberData.getMemberBirthday());
		}
		if (memberData.getMemberNation() != null) {
			oMember.setMemberNation(memberData.getMemberNation());
		}
		if (memberData.getMemberPic() != null) {
			oMember.setMemberPic(memberData.getMemberPic());
		}
		if (memberData.getMemberCard() != null) {
			oMember.setMemberCard(memberData.getMemberCard());
		}

		final int resultCount = dao.update(oMember);
		oMember.setSuccessful(resultCount > 0);
		oMember.setMessage(resultCount > 0 ? "修改成功" : "修改失敗");
		return oMember;
	}

	public MemberData selectById(MemberData memberData) {
		MemberData resultData = dao.selectById(memberData.getMemberNo());
		resultData.setSuccessful(true);
		resultData.setMessage("查询成功");
		return resultData;
	}

	@Override
	public List<MemberData> findAll() {
		return dao.selectAll();
	}

	@Override
	public boolean remove(Integer id) {

			final int resultCount=dao.deleteById(id);

			return resultCount >0;

//			return dao.deleteById(id)>0;
	}

	@Override
	public boolean save(MemberData memberData) {
		return dao.update(memberData) > 0;
	}
}
