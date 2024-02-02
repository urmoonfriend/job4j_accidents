package kz.job4j.accidents;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

@SpringBootTest
public class EnvConfigTest {
    @BeforeAll
    public static void setup() {
        System.setProperty("file.encoding", "UTF-8");
    }

    @DynamicPropertySource
    static void registerProperties(DynamicPropertyRegistry registry) {
        registry.add("app.db.url", () -> "jdbc:h2:./testdb;MODE=PostgreSQL;CASE_INSENSITIVE_IDENTIFIERS=TRUE;");
        registry.add("app.db.username", () -> "");
        registry.add("app.db.password", () -> "");
    }
}