package namvc.repositories;

import namvc.framework.NaMvcPrincipal;

public interface UsersRepository {
    NaMvcPrincipal authenticate(String user, String password) throws Exception;
}
