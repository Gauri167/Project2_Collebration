package com.niit.FriendsAdda.DAO.Impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.FriendsAdda.DAO.ForumDAO;
import com.niit.FriendsAdda.model.Forum;
import com.niit.FriendsAdda.model.ForumComment;

@Repository
public class ForumDAOImpl implements ForumDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public boolean addForum(Forum forum) {
		
		try {
			sessionFactory.getCurrentSession().save(forum);
			return true;
		}catch(Exception exception) {
			return false;
		}
	}

	@Transactional
	@Override
	public boolean deleteForum(Forum forum) {

		try {
			sessionFactory.getCurrentSession().delete(forum);
			return true;
		}catch(Exception exception) {
			return false;
		}
	}

	@Transactional
	@Override
	public boolean updateForum(Forum forum) {
		
		try {
			sessionFactory.getCurrentSession().update(forum);
			return true;
		}catch(Exception exception) {
			return false;
		}
	}

	@Override
	public Forum getForum(int forumId) {
		
		try {
			Session session = sessionFactory.openSession();
			Forum forum = session.get(Forum.class, forumId);
			return forum;
		}catch(Exception exception) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Forum> listForum(String userName) {
		
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			List<Forum> forumList = new ArrayList<Forum>();
			Query query = session.createQuery("FROM Forum where username=:username").setString("username",userName);
			forumList = query.list();
			return forumList;
		} catch (Exception e) {
			return null;
		}
	}

	@Transactional
	@Override
	public boolean approveForum(Forum forum) {
		
		try {
			
			forum.setStatus("A");
			sessionFactory.getCurrentSession().update(forum);
			return true;
		}catch(Exception exception) {
			return false;
		}
	}

	@Transactional
	@Override
	public boolean rejectForum(Forum forum) {

		try {
			
			forum.setStatus("A");
			sessionFactory.getCurrentSession().update(forum);
			return true;
		}catch(Exception exception) {
			return false;
		}
	}

	@Transactional
	@Override
	public boolean addForumDiscussion(ForumComment forumComment) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(forumComment);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Transactional
	@Override
	public boolean deleteForumDiscussion(ForumComment forumComment) {
		try {
			sessionFactory.getCurrentSession().delete(forumComment);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public ForumComment getForumDiscussion(int discussionId) {
		try {
			Session session = sessionFactory.openSession();
			ForumComment forumComment = session.get(ForumComment.class, discussionId);
			return forumComment;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<ForumComment> listForumDiscussion(int forumid) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from ForumComment where forumId=:forumId");
		query.setParameter("forumId", new Integer(forumid));
		@SuppressWarnings("unchecked")
		List<ForumComment> listForumComments = query.list();
		return listForumComments;
	}

}