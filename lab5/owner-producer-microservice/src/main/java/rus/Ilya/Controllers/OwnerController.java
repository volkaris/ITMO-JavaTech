package rus.Ilya.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;
import rus.Ilya.Owners.Owner;
import rus.Ilya.services.IOwnerService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping(value = "/owners")
public class OwnerController implements IOwnerController {

    @Autowired
    public OwnerController(IOwnerService service) {
        this.service = service;
    }

    IOwnerService service;


    @Override
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Owner owner) {
        return service.save(owner);
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable long id) throws ExecutionException, InterruptedException, TimeoutException {
        return service.getById(id);
    }


    @Override
    @GetMapping("/all")
    public ResponseEntity<?> getAll() throws ExecutionException, InterruptedException, TimeoutException {

        return service.getAll();
    }
    @Override
    @PatchMapping
    public ResponseEntity<?> update(@RequestBody Owner owner) {
        return service.update(owner);
    }

    @Override
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable long id) {
        return service.deleteById(id);
    }


    @Override
    @DeleteMapping
    public ResponseEntity<?> deleteByEntity(@RequestBody Owner owner) {
        return service.deleteByEntity(owner);
    }


    @GetMapping("/authentication")
    public Object authentication(@CurrentSecurityContext(expression="authentication")
                                 Authentication authentication) {
        return authentication.getDetails();
    }
    @Override
    @DeleteMapping("/all")
    public ResponseEntity<?> deleteAll() {

        return service.deleteAll();
    }
}
