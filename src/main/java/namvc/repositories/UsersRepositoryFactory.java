package namvc.repositories;

public class UsersRepositoryFactory {
    public  <T extends UsersRepository> T create(Class<T> type) throws IllegalAccessException, InstantiationException {
        T usersRepository = type.newInstance();
        usersRepository.create();
        return usersRepository;
    }
}
