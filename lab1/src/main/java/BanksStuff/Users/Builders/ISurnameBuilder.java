package BanksStuff.Users.Builders;

/**
 * Second part of User class builder. Realises interface driven builder pattern
 */
public interface ISurnameBuilder {
    public IUserBuilder withSurname(String surname);
}
