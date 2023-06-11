package core.app;

import core.dao.CoreDao;
import org.hibernate.Session;
import web.member.dao.impl.MemberDataDaoImpl;
import web.member.pojo.MemberData;

import java.util.List;

public class TestApp {
    public static void main(String[] args) {
        CoreDao<MemberData, Integer> memberDao = new MemberDataDaoImpl();
//        List<MemberData> members = memberDao.selectAll();
//        for (MemberData membera:members
//             ) {
//            System.out.println(membera.getMemberName());
//        }
        MemberData test1 = new MemberData();
        test1.setMemberAccount("oooooooo");
        test1.setMemberName("鍾欣宜");
        test1.setMemberPassword("oooooooo");
        test1.setMemberPhone("0988777777");
        test1.setMemberEmail("asd@gmail.com");
        int members = memberDao.insert(test1);
        System.out.println("成功插入");

    }
}
