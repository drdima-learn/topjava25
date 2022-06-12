package ru.javawebinar.topjava.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.UserServiceAbstract;

@ActiveProfiles({"postgres", "jpa"})
public class UserServiceJpaTest extends UserServiceAbstract {
}
