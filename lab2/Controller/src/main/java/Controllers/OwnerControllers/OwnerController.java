package Controllers.OwnerControllers;

import Service.Owners.IOwnerService;
import Service.Owners.Owner;
import Service.Owners.OwnerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/owners")
public class OwnerController implements IOwnerController {

    @Autowired
    public OwnerController(IOwnerService service) {
        this.service = service;
    }

    IOwnerService service;


    @Override
    @PostMapping("/save")
    public ResponseEntity<OwnerDto> save(@RequestBody Owner owner) {
        return service.save(owner);
    }


    @Override
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        return service.deleteById(id);
    }


    @Override
    @DeleteMapping("/deleteByEntity")
    public ResponseEntity<Void> deleteByEntity(@RequestBody Owner owner) {
        return service.deleteByEntity(owner);
    }


    @Override
    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAll() {
        return service.deleteAll();
    }


    @Override
    @PatchMapping("/patch")
    public ResponseEntity<Void> update(@RequestBody Owner owner) {
        return service.update(owner);
    }



    @Override
    @GetMapping("/getById/{id}")
    public ResponseEntity<OwnerDto> getById(@PathVariable long id) {
        return service.getById(id);
    }



    @Override
    @GetMapping("/getAllOwners")
    public ResponseEntity<List<OwnerDto>> getAll() {
        return service.getAll();
    }
}
