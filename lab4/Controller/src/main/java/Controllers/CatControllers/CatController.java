package Controllers.CatControllers;

import Service.Cats.Cat;
import Service.Cats.ICatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/cats")
public class CatController implements ICatController {

    @Autowired
    public CatController(ICatService service) {
        this.service = service;
    }

    ICatService service;

    @PostMapping("/friends")
    @Override
    public ResponseEntity<?> addFriend(@RequestParam long firstFriendId, @RequestParam long secondFriendId) {
        return service.addFriend(firstFriendId,secondFriendId);
    }


    @Override
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Cat cat) {
        return service.save(cat);
    }


    @Override
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable long id) {
        return service.deleteById(id);
    }


    @Override
    @DeleteMapping
    public ResponseEntity<?> deleteByEntity(@RequestBody Cat cat) {
        return service.deleteByEntity(cat);
    }


    @Override
    @DeleteMapping("/all")
    public ResponseEntity<?> deleteAll() {
        return service.deleteAll();
    }


    @Override
    @PatchMapping
    public ResponseEntity<?> update(@RequestBody Cat cat) {
        return service.update(cat);
    }


    @Override
    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        return service.getById(id);
    }



    @Override
    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return service.getAll();
    }
}
