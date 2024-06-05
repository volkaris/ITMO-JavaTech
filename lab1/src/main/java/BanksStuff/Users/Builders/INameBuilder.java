package BanksStuff.Users.Builders;

/**
 * First part of User class builder. Realises interface driven builder pattern
 */
public interface INameBuilder {
    public ISurnameBuilder withName(String name);
}
