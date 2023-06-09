package core.dao;

import core.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public interface CoreDao<P, I> {
	int insert(P pojo);

	int deleteById(I id);

	int update(P pojo);

	P selectById(I id);

	List<P> selectAll();
	default Session getSession() {
        return HibernateUtil.getSessionFactory().getCurrentSession();
    }
}
