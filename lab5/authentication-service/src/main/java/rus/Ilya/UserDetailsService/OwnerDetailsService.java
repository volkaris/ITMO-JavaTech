package rus.Ilya.UserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rus.Ilya.Repos.IOwnerRepository;

@Service
public class OwnerDetailsService implements UserDetailsService {
    private final IOwnerRepository repository;

    @Autowired
    public OwnerDetailsService(IOwnerRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        var owner = repository.getById(Long.parseLong(id));
        if (owner.isPresent()) {
            return owner.get();
        } else {
            throw new UsernameNotFoundException(id);
        }
    }
}
