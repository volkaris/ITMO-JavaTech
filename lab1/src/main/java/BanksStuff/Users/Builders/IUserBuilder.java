package BanksStuff.Users.Builders;

import BanksStuff.Users.User;

/**
 * Final part of User class builder. Realises interface driven builder pattern. Contains not mandatory parameters
 */
public interface IUserBuilder {
    public IUserBuilder withAddress(String address);
    public IUserBuilder withPassportData(String passportData);
    public User build();
}
