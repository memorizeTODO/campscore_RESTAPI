package com.team5.campscore.model;

import java.util.List;
import lombok.Data;

@Data
public class CampingSearchParamsDTO {
    // 이 필드는 Service에서 Pageable을 받아 설정됩니다.
    private int currentPage; 
    private int maxpage;
}
