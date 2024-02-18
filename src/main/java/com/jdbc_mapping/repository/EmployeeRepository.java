package com.jdbc_mapping.repository;

import com.jdbc_mapping.model.Contact;
import com.jdbc_mapping.model.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EmployeeRepository {
	
	
	String selectall="SELECT * FROM emp LEFT JOIN cnt ON emp.cntid = cnt.cntid";
	String selectemp="SELECT * FROM emp LEFT JOIN cnt ON emp.cntid = cnt.cntid where emp.empid=?";
	String saveemp="insert into emp (empid,name,cntid) values (?,?,?)";
	String saveempcnt="insert into emp (empid,name) values (?,?)";
	String editemp="update emp set name = ? where empid = ?";
	String dltemp="delete from emp where empid = ?";
	
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private ContactRepository contactRepository;
    
    public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Employee> findAll() {

		RowMapper<Employee> mapper=new RowMapper<Employee>() {
			@Override
			public Employee mapRow(ResultSet r,int rowNum) throws SQLException{
				
				Employee e = new Employee();
				Contact c = new Contact();
 				e.setEmpid(r.getLong(1));
				e.setName(r.getString(2));
				if(r.getLong(3)==r.getLong(4)) {
					c.setCntcid(r.getLong(4));
					c.setEmail(r.getString(5));
					c.setMessage(r.getString(6));
					e.setContact(c);
				}
				return e;
			}
			};
			List<Employee> result = this.jdbcTemplate.query(selectall,mapper);
		    return result;
    }

    public List<Employee> findById(Long empid) {
    	RowMapper<Employee> mapper=new RowMapper<Employee>() {
			@Override
			public Employee mapRow(ResultSet r,int rowNum) throws SQLException{
				
				Employee e = new Employee();
				Contact c = new Contact();
 				e.setEmpid(r.getLong(1));
				e.setName(r.getString(2));
				if(r.getLong(3)==r.getLong(4)) {
					c.setCntcid(r.getLong(4));
					c.setEmail(r.getString(5));
					c.setMessage(r.getString(6));
					e.setContact(c);
				}
				return e;
			}
			};
			List<Employee> result = this.jdbcTemplate.query(selectemp,new Object[] {empid},mapper);
		    return result;
    	
    }

    public int save(Employee employee) {
    	Contact c = employee.getContact();
    	Long s=null;
    	if(c.getCntcid()!=0) {
    		s=c.getCntcid();
    		contactRepository.save(c);
    		return this.jdbcTemplate.update(saveemp,
    	               employee.getEmpid() ,employee.getName(),s);
    	}
    	return this.jdbcTemplate.update(saveempcnt,
                employee.getEmpid() ,employee.getName());
    }

    public int update(Employee employee) {
    	contactRepository.update(employee.getContact());
        return this.jdbcTemplate.update(editemp,
                employee.getName(), employee.getEmpid());
    }

    public int deleteById(Long id) {
        return this.jdbcTemplate.update(dltemp, id);
    }
}