package com.domain.services;

import javax.transaction.Transactional;

import com.domain.models.entities.Supplier;
import com.domain.models.repos.SupplierRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SupplierService {

    @Autowired
    private SupplierRepo supplierRepo;

    public Supplier save(Supplier supplier) {
        return supplierRepo.save(supplier);
    }

    public Supplier findOne(Long id) {
        return supplierRepo.findById(id).get();
    }

    public Iterable<Supplier> findAll() {
        return supplierRepo.findAll();
    }

    public void removeOne(Long id) {
        supplierRepo.deleteById(id);
    }

    public Supplier findByEmail(String email) {
        return supplierRepo.findByEmail(email);
    }

    /*
     * public boolean AddSupplier(User user) {
     * boolean t=true;
     * final String User_INSERT1 =
     * "insert into utilisateurs (login, password, nom, prenom,enable) "
     * + "values (?,?,?,?,?)";
     * final String User_INSERT2="insert into roles (login,role) values(?,?)";
     * 
     * MessageDigestPasswordEncoder encoder = new
     * MessageDigestPasswordEncoder("SHA");
     * String hash = encoder.encodePassword(user.getPassword(), "");
     * 
     * final String check ="select count(*) from utilisateurs where login = ?";
     * 
     * int result= getJdbcTemplate().queryForInt(check, new
     * Object[]{String.valueOf(user.getLogin())});
     * if (result==0) {
     * getJdbcTemplate()
     * .update(User_INSERT1,
     * new Object[] {user.getLogin(),
     * hash, user.getNom(),
     * user.getPrenom(), user.getEnable(),
     * });
     * getJdbcTemplate().update(User_INSERT2, new
     * Object[]{user.getLogin(),user.getRole()});
     * return t;
     * }
     * 
     * else { t = false ; return t;}
     * 
     * }
     */

}
