package cl.escalab.crochicat.controller;

import cl.escalab.crochicat.model.Ranking;
import cl.escalab.crochicat.service.RankingService;
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

    @GetMapping
    public ResponseEntity<List<Ranking>> getRankings(){
        List<Ranking> rankings = rankingService.getAll();
        return new ResponseEntity<>(rankings, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Ranking> saveRanking(@Valid @RequestBody Ranking ranking){
        Ranking ran = rankingService.save(ranking);
        return new ResponseEntity<>(ran, HttpStatus.OK);
    }

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
