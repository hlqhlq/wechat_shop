package com.hlq.wxshop.dao;

import com.hlq.wxshop.model.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author:HLQ
 * @Date:2019/3/4 17:44
 */
public interface ProductCategoryDao extends JpaRepository<ProductCategory,Integer> {

    /**
     * 根据类目类型查询
     * @param categoryTypeList
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    /**
     * 根据类目类型和类目状态查询
     * @param code
     * @param categoryTypeList
     * @return
     */
    List<ProductCategory> findByCategoryStatusAndCategoryTypeIn(Integer code,List<Integer> categoryTypeList);
    /**
     * 根据类目状态查询
     * @param code
     * @return
     */
    List<ProductCategory> findByCategoryStatus(Integer code);

    /**
     * 类目名模糊查询
     * @param categoryName
     * @param pageable
     * @return
     */
    @Query(nativeQuery = true,value = "select * from product_category where (category_name like CONCAT('%',?1,'%') or ?1 is null)order by ?#{#pageable}")
    Page<ProductCategory> searchByCategoryName(String categoryName, Pageable pageable);
}
