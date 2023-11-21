package com.company.secondhand.advertisement.elasticsearch.repository;

import com.company.secondhand.advertisement.model.Advertisement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface AdvertisementRepository extends ElasticsearchRepository<Advertisement, String> {

    Page<Advertisement> findByTitleLike(String title, Pageable pageable);

    @Query(" {\"match\": {\"title\": {\"query\": \"?0\",\"fuzziness\":\"AUTO\",\"operator\":\"or\"}}}")
    Page<Advertisement> findByTitleFuzzy(String title, Pageable pageable);

}
