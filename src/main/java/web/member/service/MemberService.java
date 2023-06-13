package web.member.service;

import core.service.CoreService;
import web.member.pojo.MemberData;

import java.util.List;

public interface MemberService extends CoreService {

	MemberData register(MemberData memberData);

	MemberData login(MemberData memberData);

	MemberData edit(MemberData memberData);

	List<MemberData> findAll();

	MemberData selectById(MemberData memberData)    ;

	boolean remove(Integer id);

	boolean save(MemberData member);
}