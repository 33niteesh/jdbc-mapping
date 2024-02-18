package com.jdbc_mapping.repository;


import com.jdbc_mapping.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContactRepository {

	String selectall="select * from cnt";
	String selectone="select * from cnt where cntid = ?";
	String insert = "insert into cnt ( cntid,email, message) values ( ?, ?, ?)";
	String update="update cnt set  email = ?, message = ? where cntid = ?";
	String delete="delete from cnt where cntid = ?";
	
    @Autowired()
    private JdbcTemplate jdbcTemplate;

    public List<Contact> findAll() {
        return jdbcTemplate.query(selectall, (rs, rowNum) ->
                new Contact(
                        rs.getLong("cntid"),
                        rs.getString("email"),
                        rs.getString("message")
                ));
    }

    public Contact findById(Long cntid) {
        return jdbcTemplate.queryForObject(selectone, new Object[]{cntid}, (rs, rowNum) ->
                new Contact(
                        rs.getLong("cntid"),
                        rs.getString("email"),
                        rs.getString("message")
                ));
    }

    public int save(Contact contact) {
        return jdbcTemplate.update(insert,
              contact.getCntcid() ,contact.getEmail(), contact.getMessage());
    }

    public int update(Contact contact) {
        return jdbcTemplate.update(update,
                contact.getEmail(), contact.getMessage(), contact.getCntcid());
    }

    public int deleteById(Long cntid) {
        return jdbcTemplate.update(delete, cntid);
    }
}
