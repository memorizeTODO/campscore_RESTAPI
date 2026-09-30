package com.team5.campscore.service;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team5.campscore.dao.CampingDAO;
import com.team5.campscore.model.CampingDTO;
import com.team5.campscore.model.SearchResponse;

@Service
public class CampingDAOImpl  implements CampingDAO {
	@Autowired
    CampingDAO dao; // MyBatis 매퍼 인터페이스 주입
    
    @Override
    public int insertCamping(CampingDTO camping) {
        return dao.insertCamping(camping);
    }
    
    @Override
    public List<CampingDTO> getCampingListByRegion(int start, String region, String sortType, String order) {
        return dao.getCampingListByRegion(start, region, sortType, order);
    }
    
    @Override
    public List<CampingDTO> getCampingListByPlaceName(int start, String region, String sortType, String order, String placeName) {
        return dao.getCampingListByPlaceName(start, region, sortType, order, placeName);
    }
    
    @Override
    public List<CampingDTO> getCampingList(int start, String region, String sortType, String order, String placeName, List<String> category) {
        return dao.getCampingList(start, region, sortType, order, placeName, category);
    }

    // ⭐ [인터페이스 구현체 요구사항 충족용] 카운트 조회 위임 메서드
    @Override
    public long getCampingListCount(String region, String placeName, List<String> category) {
        return dao.getCampingListCount(region, placeName, category);
    }

    // ==========================================
    // ⭐ [핵심] 컨트롤러가 호출할, 페이징 메타데이터가 포함된 응답 생성 메서드
    // ==========================================
    public SearchResponse<CampingDTO> getCampingListWithPaging(
            int page, String region, String sortType, String order, 
            String placeName, List<String> category) {
        
        int size = 10;
        int start = (page - 1) * size; // LIMIT 시작점 계산 (0부터 시작)

        // 1. 기존 DAO 메서드를 이용해 목록 가져오기
        List<CampingDTO> campingList = dao.getCampingList(start, region, sortType, order, placeName, category);
        
        for (CampingDTO camping : campingList) {
        	System.out.println(camping);
            String addr = camping.getAddressName(); // 주소 가져오기
            
            if (addr != null && !addr.isEmpty()) {
                if (addr.startsWith("경기")) {
                    camping.setRegion("경기");
                } else if (addr.startsWith("강원")) {
                    camping.setRegion("강원");
                } else if (addr.startsWith("전북")) {
                    camping.setRegion("전북");
                } else if (addr.startsWith("전남")) {
                    camping.setRegion("전남");
                } else if (addr.startsWith("경북")) {
                    camping.setRegion("경북");
                } else if (addr.startsWith("경남")) {
                    camping.setRegion("경남");
                } else if (addr.startsWith("충북")) {
                    camping.setRegion("충북");
                } else if (addr.startsWith("충남")) {
                    camping.setRegion("충남");
                } else if (addr.startsWith("제주")) {
                    camping.setRegion("제주");
                }
                // 필요에 따라 서울, 부산 등도 추가 가능
            }
        }
        
        // 2. 전체 데이터 개수 가져오기
        long totalCount = dao.getCampingListCount(region, placeName, category);

        // 3. 페이지네이션 메타데이터 계산
        int totalPages = (int) Math.ceil((double) totalCount / size);
        boolean hasNext = page < totalPages;

        SearchResponse.Meta meta = new SearchResponse.Meta(
            totalCount, totalPages , page, size, hasNext
        );

        // 4. Meta와 List를 합쳐서 반환
        return new SearchResponse<>(meta, campingList);
    }
	
	
}
