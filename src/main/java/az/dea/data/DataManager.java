package az.dea.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DataManager {

    @Autowired
    protected JdbcTemplate jdbcTemplate;
}
