package cl.escalab.crochicat.service;

import cl.escalab.crochicat.model.User;

public interface UserService extends Iservice<User>{
    User saveUser(User obj, int  id);
}
