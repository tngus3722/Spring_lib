package service;

import domain.FishingHole;

import java.util.List;

public interface FishService {
    public FishingHole viewOne(Long id);
    public List<FishingHole> search(String search);
    public List<FishingHole> display();
    public void initial();
}
