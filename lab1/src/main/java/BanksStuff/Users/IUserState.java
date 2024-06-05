package BanksStuff.Users;

/**
 *  Discriminated union for user state. Depending on state user would be granted access to operating without restrictions
 */
public interface IUserState {
    public class Verified implements IUserState {}
    public class Unverified implements IUserState {}
}
