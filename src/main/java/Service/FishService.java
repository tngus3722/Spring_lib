package Service;

import Domain.FishingHole;
import Repository.FishMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.sql.Date;
import java.util.List;

@Service
public class FishService {

    @Autowired
    FishMapper fishMapper;

    public FishService(){ }
    public FishingHole viewOne(Long id){
        return fishMapper.viewOne(id); // 낚시터 하나 조회
    }
    public List<FishingHole> search(String search){
        return fishMapper.search(search);
    } //검색
    public List<FishingHole> display(){
        return fishMapper.display();
    } // 모든 정보 조회


    public void initial(){ // data2db
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("C:\\Users\\Administrator\\Desktop\\Fish\\src\\main\\resources\\data\\FishData.json"));
            JSONObject jobj = (JSONObject)obj;
            JSONArray jarray = (JSONArray)jobj.get("records");

            for(int i=0; i<jarray.size(); i++){
                FishingHole fishingHoleDTO = new FishingHole();
                JSONObject tmp = (JSONObject)jarray.get(i);

                fishingHoleDTO.setName((String)tmp.get("낚시터명"));
                fishingHoleDTO.setCategory((String)tmp.get("낚시터유형"));
                fishingHoleDTO.setAddress((String)tmp.get("소재지지번주소"));
                if ( tmp.get("소재지지번주소")==null ){ // 지번주소가 없는 경우
                    fishingHoleDTO.setAddress((String)tmp.get("소재지도로명주소")); // 도로명으로 대체
                }
                fishingHoleDTO.setLatitude((Float.parseFloat((String)tmp.get("위도"))));
                fishingHoleDTO.setLongitude((Float.parseFloat((String)tmp.get("경도"))));
                fishingHoleDTO.setFish_species((String)tmp.get("주요어종"));
                fishingHoleDTO.setDate( Date.valueOf((String)tmp.get("데이터기준일자")));
                fishMapper.initialFishDB(fishingHoleDTO);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
