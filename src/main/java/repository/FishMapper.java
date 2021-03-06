package repository;

import domain.FishingHole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FishMapper {
    void initialFishDB(FishingHole fishingHoleDTO);

    List<FishingHole> display();

    List<FishingHole> search(String search);

    FishingHole viewOne(Long id);
}

