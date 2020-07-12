package Service;

import DTO.FishingHoleDTO;
import Repository.FishMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class FishService {

    @Autowired
    FishMapper fishMapper;

    public FishService(){ }
    public FishingHoleDTO viewOne(int id){
        return fishMapper.viewOne(id);
    }
    public List<FishingHoleDTO> search(String search){
        return fishMapper.search(search);
    }
    public List<FishingHoleDTO> display(){
        return fishMapper.display();
    }

    /* do once for data2db
    public void initial(){
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("C:\\Users\\Administrator\\Desktop\\Fish\\src\\main\\resources\\data\\FishData.json"));
            JSONObject jobj = (JSONObject)obj;
            JSONArray jarray = (JSONArray)jobj.get("records");

            for(int i=0; i<jarray.size(); i++){
                FishingHoleDTO fishingHoleDTO = new FishingHoleDTO();
                JSONObject tmp = (JSONObject)jarray.get(i);

                fishingHoleDTO.setName((String)tmp.get("낚시터명"));
                fishingHoleDTO.setCategory((String)tmp.get("낚시터유형"));
                fishingHoleDTO.setAddress((String)tmp.get("소재지지번주소"));
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
     */
}
