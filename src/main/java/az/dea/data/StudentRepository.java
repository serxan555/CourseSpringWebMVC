package az.dea.data;

import az.dea.model.Student;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class StudentRepository extends DataManager{

    public List<Student> getStudentList(){
        String sql = "select * from students";
        List<Student> students = jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Student.class));
        return students;
    }
    public Student findStudent(int id){
        String query = "select * from students where id = ?";
        Object[] objects = new Object[]{id};
        return jdbcTemplate.queryForObject(query,objects,new BeanPropertyRowMapper<>(Student.class));
    }
    public void addStudent(Student s){
        String query = "insert into students (name,surname,parent,birthDate,phone,gmail,gmailCode,gender,status) values(?,?,?,?,?,?,?,?,1)";
        Object[] objects = {s.getName(),s.getSurname(),s.getParent(),s.getBirthDate(),s.getPhone(),s.getGmail(),s.getGmailCode(),s.getGender()};
        int[] types = {Types.NVARCHAR,Types.NVARCHAR,Types.NVARCHAR,Types.DATE,Types.NVARCHAR,Types.NVARCHAR,Types.NVARCHAR,Types.NVARCHAR,Types.INTEGER};
        jdbcTemplate.update(query,objects,types);
    }
    public Number addStudentLastId(Student s){
        String query = "insert into students (name,surname,parent,birthDate,phone,gmail,gmailCode,gender,status) values(?,?,?,?,?,?,?,?,1)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement statement = connection.prepareStatement(query,new String[]{"id"});
                statement.setString(1,s.getName());
                statement.setString(2,s.getSurname());
                statement.setString(3,s.getParent());
                statement.setDate(4,new Date(s.getBirthDate().getTime()));
                statement.setString(5,s.getPhone());
                statement.setString(6,s.getGmail());
                statement.setString(7,s.getGmailCode());
                statement.setString(8,s.getGender());

                return statement;
            }
        },keyHolder);
        return  keyHolder.getKey();
    }
}
