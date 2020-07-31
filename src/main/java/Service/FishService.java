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
            JSONObject jobj = (JSONObject)obj; //json 객체를 가져옴
            JSONArray jarray = (JSONArray)jobj.get("records"); // record 배열을 json배열로 가져옴

            for(int i=0; i<jarray.size(); i++){
                FishingHole fishingHole = new FishingHole();
                JSONObject tmp = (JSONObject)jarray.get(i);

                fishingHole.setName((String)tmp.get("낚시터명"));
                fishingHole.setCategory((String)tmp.get("낚시터유형"));
                fishingHole.setAddress((String)tmp.get("소재지지번주소"));
                if ( tmp.get("소재지지번주소")==null ){ // 지번주소가 없는 경우
                    fishingHole.setAddress((String)tmp.get("소재지도로명주소")); // 도로명으로 대체
                }
                fishingHole.setLatitude((Float.parseFloat((String)tmp.get("위도"))));
                fishingHole.setLongitude((Float.parseFloat((String)tmp.get("경도"))));
                fishingHole.setFish_species((String)tmp.get("주요어종"));
                fishingHole.setDate( Date.valueOf((String)tmp.get("데이터기준일자")));
                fishMapper.initialFishDB(fishingHole);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
