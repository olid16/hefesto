package infrastructure;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import org.olid16.domain.collections.Users;

public class UserServiceTestModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Users.class).to(InMemoryUsers.class).in(Singleton.class);
    }
}
