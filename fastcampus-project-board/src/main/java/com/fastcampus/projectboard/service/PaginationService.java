package com.fastcampus.projectboard.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class PaginationService {
    
    // 페이지네이션 바의 길이 (상수값/상태값)
    private static final int BAR_LENGTH = 5;
    
    public List<Integer> getPaginationBarNumbers(int currentPageNumber, int totalPages) {
        int startNumber = Math.max(currentPageNumber - (BAR_LENGTH / 2), 0);
        int endNumber = Math.min(startNumber + BAR_LENGTH, totalPages);
        return IntStream.range(startNumber, endNumber).boxed().toList();
    }
    
    public int currentBarLength() {
        return BAR_LENGTH;
    }
}
