package cl.escalab.crochicat.service;

import cl.escalab.crochicat.model.Ranking;

import java.util.UUID;

public interface RankingService extends Iservice<Ranking> {
    Ranking saveRanking(Ranking r, UUID id);
}
