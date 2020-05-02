package com.bz.blueauth.model;

import java.util.List;

import com.bz.blueauth.dto.pojo.UserPO;
import com.bz.blueauth.exception.AccessException;
import com.bz.blueauth.exception.BadRequestException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDAO {
        @Autowired
        private UserMysqlDAO userMysqlDao;
        // private EntityManagerFactory emf;

        // private PersistenceJPAConfig emf;
        // @PersistenceContext
        // private EntityManager em;

        public UserDAO() {
                // this.emf = new PersistenceJPAConfig();
                // this.em =
                // this.emf.transactionManager().getEntityManagerFactory().createEntityManager();
        }

        public void create(UserPO obj) {
            try {
                      this.userMysqlDao.save(obj);
            } catch (Exception ex) {
                      throw new BadRequestException(ex.getMessage());
            }
        }

        public void save(UserPO obj) {
                try{
                        this.userMysqlDao.save(obj);
                }catch(Exception ex){
                   throw new BadRequestException(ex.getMessage());
                }                
        }

        public void delete(Long id) {
                try{
                        this.userMysqlDao.deleteById(id);
                }catch(Exception ex){
                   throw new BadRequestException(ex.getMessage());
                }                   
        }

        public UserPO getUser(String criteria) {
                try {
                        List<UserPO> res = this.userMysqlDao.findByUseridOrEmail(criteria, criteria);
                        if(res.size() == 0)
                                throw new AccessException("User "+criteria+" Doesn't Exist"); 
                        
                        return res.get(0);
                   
                } catch (Exception e) {
                        throw new AccessException("User "+criteria+" Doesn't Exist");
                }
        }
}