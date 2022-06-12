package ru.javawebinar.topjava.service.datajpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.UserServiceAbstract;

@ActiveProfiles({"postgres", "datajpa"})
public class UserServiceDatajpaTest extends UserServiceAbstract {
}
