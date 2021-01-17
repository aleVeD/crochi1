package cl.escalab.crochicat.controller;

import cl.escalab.crochicat.model.Ranking;
import cl.escalab.crochicat.service.RankingService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rankings")
public class RankingController {
    @Autowired
    private RankingService rankingService;

    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }


    @ApiOperation(value = "obtener rankings de fotos",
            notes = "",
            response = List.class,
            responseContainer = "Ranking")
    @ApiResponses(value = {@ApiResponse(code= 400, message = "rankings no se obtubieron"),
            @ApiResponse(code = 404, message = "rankings no fueron encontrados"),
            @ApiResponse(code = 200, message = "Rankings encontrados exitosamente")})

    @GetMapping
    public ResponseEntity<List<Ranking>> getRankings(){
        List<Ranking> rankings = rankingService.getAll();
        return new ResponseEntity<>(rankings, HttpStatus.OK);
    }

    @ApiOperation(value = "guardar un ranking de fotos",
            notes = "",
            response = List.class,
            responseContainer = "Ranking")
    @ApiResponses(value = {@ApiResponse(code= 400, message = "rankings no se obtubo"),
            @ApiResponse(code = 404, message = "ranking no fue encontrado"),
            @ApiResponse(code = 200, message = "Ranking guardado exitosamente")})
    @PostMapping
    public ResponseEntity<Ranking> saveRanking(@Valid @RequestBody Ranking ranking, UUID id){
        Ranking ran = rankingService.saveRanking(ranking, id);
        return new ResponseEntity<>(ran, HttpStatus.OK);
    }

    @ApiOperation(value = "obtener un ranking de una foto",
            notes = "",
            response = List.class,
            responseContainer = "Ranking")
    @ApiResponses(value = {@ApiResponse(code= 400, message = "ranking no se obtubo"),
            @ApiResponse(code = 404, message = "ranking no fue encontrado"),
            @ApiResponse(code = 200, message = "Ranking encontrado exitosamente")})
    @GetMapping("/{id}")
    public ResponseEntity<Ranking> getRanking(@PathVariable("id") UUID id){
        Ranking ranking = getRankingById(id);
        return new ResponseEntity<>(ranking, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Ranking> deleteRanking(@PathVariable("id") UUID id){
        Ranking ranking = getRankingById(id);
        rankingService.delete(ranking.getIdRanking());
        return new ResponseEntity<>(ranking, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ranking> updateRanking(@PathVariable("id") UUID id, @RequestBody Ranking ranking){
        Ranking rankingUpdated = rankingService.save(getRankingById(id));
        return new ResponseEntity<>(rankingUpdated, HttpStatus.OK);
    }

    private Ranking getRankingById(UUID id){
        Ranking ranking = rankingService.get(id);
        return  ranking;
    }

}
