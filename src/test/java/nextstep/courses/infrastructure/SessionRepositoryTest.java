package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class SessionRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    @BeforeEach
    void setup() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }

    @Test
    void save() {
        Session session = new Session(2L, "무료강의입니다.", true, SessionType.READY);
        int save = sessionRepository.save(session);
        assertThat(save).isEqualTo(1);
    }

    @Test
    void findById() {
        Session sessionBySessionId = sessionRepository.findById(1L);
        assertThat(sessionBySessionId.getId()).isEqualTo(1L);
    }

    @Test
    void update() {
        Session findSession = sessionRepository.findById(1L);
        findSession.changeToStatus(SessionType.RECRUITING);
        sessionRepository.update(findSession);

        Session session = sessionRepository.findById(1L);
        assertThat(session.getStatus()).isEqualTo(SessionType.RECRUITING);

    }

    @Test
    void delete() {
        int delete = sessionRepository.delete(1L);
        assertThat(delete).isEqualTo(1);
    }
}