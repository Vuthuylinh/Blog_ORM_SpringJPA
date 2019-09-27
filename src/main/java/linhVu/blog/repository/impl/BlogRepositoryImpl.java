package linhVu.blog.repository.impl;

import linhVu.blog.model.Blog;
import linhVu.blog.repository.BlogRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;


@Transactional
public class BlogRepositoryImpl implements BlogRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Blog> findAll() {
        TypedQuery<Blog> query=em.createQuery("select b from Blog b", Blog.class);
        return query.getResultList();
    }

    @Override
    public Blog findById(int id) {
        TypedQuery<Blog> query= em.createQuery("select b from Blog b where b.id=:id", Blog.class);
        query.setParameter("id",id);
        try{
            return query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }


    }

    @Override
    public List<Blog> findByName(String name) {
        TypedQuery<Blog> query= em.createQuery("select b from Blog b where b.title like concat('%',:name,'%')", Blog.class);
        query.setParameter("name",name);
        try{
            return query.getResultList();
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    public void save(Blog blog) {
        if(blog.getId() != null){
            em.merge(blog);
        }else{
            em.persist(blog);
        }
    }

    @Override
    public void remove(int id) {
    Blog blog = findById(id);
    em.remove(blog);
    }
}
