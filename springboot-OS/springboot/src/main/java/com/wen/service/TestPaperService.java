package com.wen.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wen.entity.TestPaper;

import java.util.Map;

public interface TestPaperService {
//    Map<String, Object> findArticle();
   void insert(TestPaper testPaper);
   void update(TestPaper testPaper);
   void deleteById(Integer Id);
   Page<TestPaper> getTestPaperList(Integer pageNum,Integer pageSize,String search);
}
