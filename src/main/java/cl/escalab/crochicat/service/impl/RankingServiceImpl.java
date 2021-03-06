package cl.escalab.crochicat.service.impl;

import cl.escalab.crochicat.exception.ModelNotFoundException;
import cl.escalab.crochicat.model.Photo;
import cl.escalab.crochicat.model.Ranking;
import cl.escalab.crochicat.repo.RankingRepoInterface;
import cl.escalab.crochicat.service.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class RankingServiceImpl implements RankingService {
    @Autowired
    private RankingRepoInterface rankingRepoInterface;
    @Override
    public List<Ranking> getAll() {
        List<Ranking> rankings = rankingRepoInterface.findAll();
        return rankings;
    }

    @Override
    public Ranking saveRanking(Ranking r, UUID id) {
        List<Photo> photo = new ArrayList<>();
        photo.add(new Photo(id));
        Ranking ranking = new Ranking();
        ranking.setVote(r.getVote());
        ranking.setPhotos(photo);
        Ranking rankingrepo = rankingRepoInterface.save(ranking);
        return rankingrepo;
    }

    @Override
    public Ranking get(UUID id) {
        return getRankingById(id);
    }

    @Override
    public Boolean delete(UUID id) {
        Ranking ranking = getRankingById(id);
        rankingRepoInterface.deleteById(ranking.getIdRanking());
        return true;
    }

    @Override
    public Ranking update(Ranking ranking, UUID id) {
        Ranking rankingUpdated = rankingRepoInterface.save(ranking);
        return rankingUpdated;
    }

    private Ranking getRankingById(UUID id){
        Optional<Ranking> ranking = rankingRepoInterface.findById(id);
        if(ranking.isPresent()){
            return ranking.get();
        }else{
            throw new ModelNotFoundException("el id "+id+" no existe");
        }
    }

    @Override
    public Ranking save(Ranking obj) {
        return null;
    }
}
