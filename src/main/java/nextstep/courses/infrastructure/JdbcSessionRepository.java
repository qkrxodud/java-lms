package nextstep.courses.infrastructure;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.SessionType;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;

public class JdbcSessionRepository implements SessionRepository {
    private final JdbcOperations jdbcTemplate;
    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session (id, title, free, status) values(?, ?, ?, ?)";
        return jdbcTemplate.update(sql,session.getId(), session.getTitle(), session.getFree(), session.getStatus().toString());
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, title, free, status from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                rs.getString(2),
                rs.getBoolean(3),
                SessionType.valueOf(rs.getString(4)));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public int update(Session session) {
        String sql = "update session set title=?, free=?, status=?";
        return jdbcTemplate.update(sql, session.getTitle(), session.getFree(), session.getStatus().toString());
    }

    @Override
    public int delete(Long id) {
        String sql = "delete from session where id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
