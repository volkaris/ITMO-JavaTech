package Controllers.CatControllers;

import Service.Cats.Cat;
import Service.Cats.CatDto;
import Service.Cats.ICatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/cats")
public class CatController implements ICatController {

    @Autowired
    public CatController(ICatService service) {
        this.service = service;
    }

    ICatService service;

    @PostMapping("/addFriend")
    @Override
    public ResponseEntity<Void> addFriend(@RequestParam long firstFriendId, @RequestParam long secondFriendId) {
        return service.addFriend(firstFriendId,secondFriendId);
    }


    @Override
    @PostMapping("/save")
    public ResponseEntity<CatDto> save(@RequestBody Cat cat) {
        return service.save(cat);
    }


    @Override
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        return service.deleteById(id);
    }


    @Override
    @DeleteMapping("/deleteByEntity")
    public ResponseEntity<Void> deleteByEntity(@RequestBody Cat cat) {
        return service.deleteByEntity(cat);
    }


    @Override
    @DeleteMapping("/deleteAll") //todo remove  verbs
    public ResponseEntity<Void> deleteAll() {
        return service.deleteAll();
    }


    @Override
    @PatchMapping("/patch")
    public ResponseEntity<Void> update(@RequestBody Cat cat) {
        return service.update(cat);
    }


    @Override
    @GetMapping("/getById/{id}")
    public ResponseEntity<CatDto> getById(@PathVariable long id) {
        return service.getById(id);
    }



    @Override
    @GetMapping("/getAllCats")
    public ResponseEntity<List<CatDto>> getAll() {
        return service.getAll();
    }
}
