package com.boot.bvserver.repository;

import com.boot.bvserver.bean.EsDemo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author lwy
 */
public interface ChatMessageRepository extends ElasticsearchRepository<EsDemo, Long> {

        List<EsDemo> findByContentLike(String content, Pageable page);
}
