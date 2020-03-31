import java.util.Optional;

import javax.sql.DataSource;

import com.example.demo.tracker.model.AuditLog;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class AuditLogService {
    /**
     * this is used to execute a connection with a database.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is used to connect to relational database.
     */
    private final DataSource dataSource;

    /**
     * Creates a service for Menu related operations.
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public AuditLogService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into audit log table.
     * 
     * @param newAuditLog
     * @return audit log
     */
    public AuditLog create(final AuditLog newAuditLog) {
        final String query = null;
        return null;
    }

    /**
     * reads from the table audit log with the given id.
     * 
     * @param id
     * @return audit log
     */
    public Optional<AuditLog> read(final Integer id) {
        final String query = null;
        return null;
    }

    /**
     * update the audit log table.
     * 
     * @param id
     * @param newAuditLog
     * @return audit log table
     */
    public AuditLog update(final Integer id, final AuditLog newAuditLog) {
        final String query = null;
        return null;
    }

    /**
     * deletes a data from audit log table with given id.
     * 
     * @param id
     * @return true if deleted
     */
    public boolean delete(final Integer id) {
        final String query = null;
        return true;
    }

    /**
     * lists the data from table with given page number and size of page.
     * 
     * @param pageNumber
     * @param pageSize
     * @return audit log
     */
    public Optional<AuditLog> list(final Integer pageNumber, final Integer pageSize) {

        final String query = null;
        return null;
    }
}
