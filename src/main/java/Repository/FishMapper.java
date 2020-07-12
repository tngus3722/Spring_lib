package Repository;

import DTO.FishingHoleDTO;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FishMapper {
    void initialFishDB(FishingHoleDTO fishingHoleDTO);

    List<FishingHoleDTO> display();

    List<FishingHoleDTO> search(String search);

    FishingHoleDTO viewOne(int id);
}
