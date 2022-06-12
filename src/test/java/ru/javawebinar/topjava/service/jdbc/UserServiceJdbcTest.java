package ru.javawebinar.topjava.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.UserServiceAbstract;

@ActiveProfiles({"postgres", "jdbc"})
public class UserServiceJdbcTest extends UserServiceAbstract {
}
