package Controllers.OwnerControllers;

import Service.Owners.IOwnerService;
import Service.Owners.Owner;
import Service.Owners.OwnerDto;
import Service.Security.AuthServices.AuthenticationService;
import Service.Security.AuthServices.IAuthenticationService;
import Service.Security.Entities.AuthenticationRequest;
import Service.Security.Entities.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/owners")
public class OwnerController implements IOwnerController {

    @Autowired
    public OwnerController(IOwnerService ownerService,
                           AuthenticationService securityService) {
        this.service = ownerService;
        this.securityService = securityService;
    }

    private final IOwnerService service;
    private final IAuthenticationService securityService;
    @Override
    @PostMapping
    public ResponseEntity<AuthenticationResponse> create(@RequestBody Owner owner) {
        return service.save(owner);
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<OwnerDto> getById(@PathVariable long id) {
        return service.getById(id);
    }


    @Override
    @GetMapping("/all")
    public ResponseEntity<List<OwnerDto>> getAll() {
        return service.getAll();
    }

    @Override
    @PostMapping("/authorize")
    public ResponseEntity<AuthenticationResponse> authorize(
            @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(securityService.authenticate(request));
    }
    @Override
    @PatchMapping
    public ResponseEntity<Void> update(@RequestBody Owner owner) {
        return service.update(owner);
    }

    @Override
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        return service.deleteById(id);
    }


    @Override
    @DeleteMapping
    public ResponseEntity<Void> deleteByEntity(@RequestBody Owner owner) {
        return service.deleteByEntity(owner);
    }


    @Override
    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAll() {

        return service.deleteAll();
    }

}
