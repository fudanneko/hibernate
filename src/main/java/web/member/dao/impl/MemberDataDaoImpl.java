package web.member.dao.impl;

//import static core.util.CommonUtil.getConnection;

import core.dao.CoreDao;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import web.member.dao.MemberDataDao;
import web.member.pojo.MemberData;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class MemberDataDaoImpl implements MemberDataDao {


    @Override
    public int insert(MemberData memberdata) {//註冊用
//		====JDBC寫法================================================================================
//        final String sql = "insert into MEMBER(membername, memberphone,memberemail ,memberpassword ) " + "values(?, ?, ?, ?)";
//        try (
//                Connection conn = CoreDao.getConnection();
//                PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setString(1, member.getMemberName());
//            pstmt.setString(2, member.getMemberPhone());
//            pstmt.setString(3, member.getMemberEmail();
//            pstmt.setInt(4, member.getMemberPassword();
//            return pstmt.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return -1;
//    ==================================================================================================

//		====Hibernate寫法====
        Session session = getSession();
        try {
            Transaction transaction = session.beginTransaction();

            session.persist(memberdata);

            Hibernate.initialize(MemberData.class);
            transaction.commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return 1;
    }

    @Override
    public int deleteById(Integer memberNo) {
//		====JDBC寫法====
//		final String sql = "delete from MEMBER where ID = ?";

//		try (
//			Connection conn = getConnection();
//			PreparedStatement pstmt = conn.prepareStatement(sql)) {
//			pstmt.setInt(1, id);
//			return pstmt.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return -1;

//		====Hibernate寫法====
        Session session = getSession();
        try {
            Transaction transaction = session.beginTransaction();
//
            MemberData member = session.get(MemberData.class, memberNo);
            session.remove(member);

            Hibernate.initialize(MemberData.class);
            transaction.commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return 1;
    }

    @Override
    public int updateById(MemberData memberdata) {
//         ====Hibernate  update寫法====
        Session session = getSession();
        try {
            Transaction transaction = session.beginTransaction();
//
            session.update(memberdata);

            Hibernate.initialize(MemberData.class);
            transaction.commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return 1;

//        ====Hibernate  Setter寫法====
//        Session session = getSession();
//        MemberData member = session.get(MemberData.class,memberdata.getMemberNo());
//        member.setMemberName(memberdata.getMemberName());
//        member.setMemberGender(memberdata.getMemberGender());
//        member.setMemberPassword(memberdata.getMemberPassword());
//        member.setMemberPhone(memberdata.getMemberPhone());
//        member.setMemberEmail(memberdata.getMemberEmail());
//        member.setMemberAddress(memberdata.getMemberAddress());
//        member.setMemberBirthday(memberdata.getMemberBirthday());
//        member.setMemberNation(memberdata.getMemberNation());
//        member.setMemberPic(memberdata.getMemberPic());
//        member.setMemberCard(memberdata.getMemberCard());
//        return 1;



//		sql寫法
//		final StringBuilder sql = new StringBuilder()
//			.append("update MEMBER set ");
//		int offset = 0;
//		final String password = member.getPassword();
//		if (password != null && !password.isEmpty()) {
//			sql.append("PASSWORD = ?,");
//			offset = 1;
//		}
//		sql.append("NICKNAME = ?,")
//			.append("PASS = ?,")
//			.append("ROLE_ID = ?,")
//			.append("UPDATER = ?,")
//			.append("LAST_UPDATED_DATE = NOW() ")
//			.append("where USERNAME = ?");
//		try (
//			Connection conn = getConnection();
//			PreparedStatement pstmt = conn.prepareStatement(sql.toString())
//		) {
//			if (password != null && !password.isEmpty()) {
//				pstmt.setString(1, member.getPassword());
//			}
//			pstmt.setString(1 + offset, member.getNickname());
//			pstmt.setBoolean(2 + offset, member.getPass());
//			pstmt.setInt(3 + offset, member.getRoleId());
//			pstmt.setString(4 + offset, member.getUpdater());
//			pstmt.setString(5 + offset, member.getUsername());
//			return pstmt.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return -1;

        //hql寫法
//		final StringBuilder hql = new StringBuilder()
//			.append("update MEMBER set ");
//		int offset = 0;
//		final String password = member.getPassword();
//		if (password != null && !password.isEmpty()) {
//			hql.append("PASSWORD = :password,");
//			offset = 1;
//		}
//		hql.append("NICKNAME = :nickname,")
//			.append("PASS =  :pass,")
//			.append("ROLE_ID = :roleId,")
//			.append("UPDATER = updater,")
//			.append("LAST_UPDATED_DATE = NOW() ")
//			.append("where USERNAME = :username");
//
//		Query<?> query= getSession().createQuery(hql.toString());
//		if(password !=null&& !password.isEmpty()){
//			query.setParameter("password",password);
//		}
//		return query
//				.setParameter("nickname",member.getNickname())
//				.setParameter("pass",member.getPass())
//				.setParameter("roleId",member.getRoleId())
//				.setParameter("updater",member.getUpdater())
//				.setParameter("username",member.getUsername())
//				.executeUpdate();
//		
//	}
        //最後一種Query
//        final StringBuilder hql = new StringBuilder()
//                .append("update Member set ");
//        final String password = member.getMemberPassword();
//        if (password != null && !password.isEmpty()) {
//            hql.append("password = :password,");
//        }
//        hql.append("nickname = :nickname,")
//                .append("pass =  :pass,")
//                .append("roleId = :roleId,")
//                .append("updater = :updater,")
//                .append("lastUpdatedDate = NOW() ")
//                .append("where username = :username");
//
//        Query<?> query = getSession().createQuery(hql.toString());
//        if (password != null && !password.isEmpty()) {
//            query.setParameter("password", password);
//        }
//        return query
//                .setParameter("nickname", member.get())
//                .setParameter("pass", member.get())
//                .setParameter("roleId", member.get())
//                .setParameter("updater", member.get())
//                .setParameter("username", member.get())
//                .executeUpdate();

    }

    @Override
    public MemberData selectById(Integer memberNo) {
        //		====Hibernate寫法====


        Session session = getSession();
        try {
            Transaction transaction = session.beginTransaction();
//
            Hibernate.initialize(MemberData.class);
            transaction.commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return session.get(MemberData.class, memberNo);

//		====JDBC寫法====
//		final String sql = "select * from MEMBER where ID = ?";
//		try (
//			Connection conn = getConnection();
//			PreparedStatement pstmt = conn.prepareStatement(sql)) {
//			pstmt.setInt(1, id);
//			try (
//				ResultSet rs = pstmt.executeQuery()) {
//				if (rs.next()) {
//					Member member = new Member();
//					member.setId(rs.getInt("ID"));
//					member.setUsername(rs.getString("USERNAME"));
//					member.setPassword(rs.getString("PASSWORD"));
//					member.setNickname(rs.getString("NICKNAME"));
//					member.setPass(rs.getBoolean("PASS"));
//					member.setRoleId(rs.getInt("ROLE_ID"));
//					member.setCreator(rs.getString("CREATOR"));
//					member.setCreatedDate(rs.getTimestamp("CREATED_DATE"));
//					member.setUpdater(rs.getString("UPDATER"));
//					member.setLastUpdatedDate(rs.getTimestamp("LAST_UPDATED_DATE"));
//					return member;
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;


    }

    @Override
        public List<MemberData> selectAll() {

            //		====Hibernate寫法====
            Session session=getSession();

        List<MemberData> result= null;
        try {
            Transaction transaction = session.beginTransaction();
            String hql = " from MemberData order by memberNo";
            Query<MemberData> query = session.createQuery(hql, MemberData.class);
//            System.out.println(query);
            result = query.list();
//            System.out.println(result);
            Hibernate.initialize(MemberData.class);
            transaction.commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return result;
//		sql寫法
//		final String sql = "select * from MEMBERdata order by ID";
//		try (
//			Connection conn = getConnection();
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//			ResultSet rs = pstmt.executeQuery()) {
//			List<Member> list = new ArrayList<>();
//			while (rs.next()) {
//				Member member = new Member();
//				member.setId(rs.getInt("ID"));
//				member.setUsername(rs.getString("USERNAME"));
//				member.setPassword(rs.getString("PASSWORD"));
//				member.setNickname(rs.getString("NICKNAME"));
//				member.setPass(rs.getBoolean("PASS"));
//				member.setRoleId(rs.getInt("ROLE_ID"));
////				member.setCreator(rs.getString("CREATOR"));
////				member.setCreatedDate(rs.getTimestamp("CREATED_DATE"));
////				member.setUpdater(rs.getString("UPDATER"));
////				member.setLastUpdatedDate(rs.getTimestamp("LAST_UPDATED_DATE"));
//				list.add(member);
//			}
//			return list;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;


//sql寫法

//        final String sql = "FROM MemberData ORDER BY id";
//        return getSession()
//                .createQuery(sql, MemberData.class)
//                .getResultList();
    }



//    @Override
//    public MemberData selectByUsername(String username) {
//		sql語法
//		final String sql = "select * from MEMBER where USERNAME = ?";
//		try (
//			Connection conn = getConnection();
//			PreparedStatement pstmt = conn.prepareStatement(sql)) {
//			pstmt.setString(1, username);
//			try (
//				ResultSet rs = pstmt.executeQuery()) {
//				if (rs.next()) {
//					Member member = new Member();
//					member.setId(rs.getInt("ID"));
//					member.setUsername(rs.getString("USERNAME"));
//					member.setPassword(rs.getString("PASSWORD"));
//					member.setNickname(rs.getString("NICKNAME"));
//					member.setPass(rs.getBoolean("PASS"));
//					member.setRoleId(rs.getInt("ROLE_ID"));
//					member.setCreator(rs.getString("CREATOR"));
//					member.setCreatedDate(rs.getTimestamp("CREATED_DATE"));
//					member.setUpdater(rs.getString("UPDATER"));
//					member.setLastUpdatedDate(rs.getTimestamp("LAST_UPDATED_DATE"));
//					return member;
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;

//		Criteria語法
//        Session session = getSession();
//        CriteriaBuilder cBuilder = session.getCriteriaBuilder();
//        CriteriaQuery<MemberData> cQuery = cBuilder.createQuery(MemberData.class);
//        Root<MemberData> root = cQuery.from(MemberData.class);
//        cQuery.where(cBuilder.equal(root.get("username"), username));
//        return session
//                .createQuery(cQuery)
//                .uniqueResult();
//		final String sql = "select * from MEMBER where USERNAME= ?";

//    }


    @Override
    public MemberData selectForLogin(String username, String password) {
//		sql寫法
//		final String sql = "select * from MEMBER where USERNAME = ? and PASSWORD = ?";
//		try (
//			Connection conn = getConnection();
//			PreparedStatement pstmt = conn.prepareStatement(sql)) {
//			pstmt.setString(1, username);
//			pstmt.setString(2, password);
//			try (
//				ResultSet rs = pstmt.executeQuery()) {
//				if (rs.next()) {
//					Member member = new Member();
//					member.setId(rs.getInt("ID"));
//					member.setUsername(rs.getString("USERNAME"));
//					member.setPassword(rs.getString("PASSWORD"));
//					member.setNickname(rs.getString("NICKNAME"));
//					member.setPass(rs.getBoolean("PASS"));
//					member.setRoleId(rs.getInt("ROLE_ID"));
//					member.setCreator(rs.getString("CREATOR"));
//					member.setCreatedDate(rs.getTimestamp("CREATED_DATE"));
//					member.setUpdater(rs.getString("UPDATER"));
//					member.setLastUpdatedDate(rs.getTimestamp("LAST_UPDATED_DATE"));
//					return member;
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;

//		Native SQL 寫法
        final String sql = "select * from MEMBER where USERNAME = :username and PASSWORD = :password";
        return getSession()
                .createNativeQuery(sql, MemberData.class)
                .setParameter("username", username)
                .setParameter("password", password)
                .uniqueResult();
    }


}