package restapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import restapi.repository.FormulaRepository;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class PublishFormulaController {

    private final FormulaRepository formulaRepository;

    @GetMapping("/formula")
    public ResponseEntity<?> getFormulaByName(@RequestParam String name){
        return ResponseEntity.ok().body(formulaRepository.getFormulaByName(name));
    }

}
