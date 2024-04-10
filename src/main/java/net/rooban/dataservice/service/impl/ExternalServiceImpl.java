package net.rooban.dataservice.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import net.rooban.dataservice.dto.request.Shelf;
import net.rooban.dataservice.dto.request.ShopperRequest;
import net.rooban.dataservice.dto.response.ProductResponse;
import net.rooban.dataservice.dto.response.ShopperResponse;
import net.rooban.dataservice.model.Product;
import net.rooban.dataservice.model.ShopperShelf;
import net.rooban.dataservice.repository.ProductRepository;
import net.rooban.dataservice.repository.ShopperRepository;
import net.rooban.dataservice.repository.ShopperShelfRepository;
import net.rooban.dataservice.service.ExternalService;
import net.rooban.dataservice.service.InternalService;
import net.rooban.dataservice.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;

@Service
public class ExternalServiceImpl implements ExternalService {

    @Autowired
    private EntityManager em;

    @Autowired
    private ShopperRepository shopperRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ShopperShelfRepository shopperShelfRepository;
    @Autowired
    private InternalService internalService;

    @Override
    public ShopperResponse getProductsByShopper(String shopperId, String category, String brand, Integer limit) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ShopperShelf> query = cb.createQuery(ShopperShelf.class);
        Root<ShopperShelf> shopperShelfRoot = query.from(ShopperShelf.class);
        List<Predicate> predicates = new ArrayList<>();
        if (Optional.ofNullable(shopperId).isPresent())
            predicates.add(cb.equal(shopperShelfRoot.get("id").get("shopper").get("shopperId"), shopperId));
        if (Optional.ofNullable(category).isPresent())
            predicates.add(cb.equal(shopperShelfRoot.get("id").get("product").get("category"), category));
        if (Optional.ofNullable(brand).isPresent())
            predicates.add(cb.equal(shopperShelfRoot.get("id").get("product").get("brand"), brand));
        query.where(predicates.toArray(new Predicate[predicates.size()]))
                .orderBy(cb.desc(shopperShelfRoot.get("createdAt")));

        List<ShopperShelf> shopperShelfList = em.createQuery(query).setMaxResults(getLimitValue(limit)).getResultList();
        ShopperResponse shopper = new ShopperResponse();
        shopper.setShopperId(shopperId);
        List<ProductResponse> productResponseList = new ArrayList<>();
        shopperShelfList.stream().forEach(shopperShelf -> {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setProductId(shopperShelf.getId().getProduct().getProductId());
            productResponse.setCategory(shopperShelf.getId().getProduct().getCategory());
            productResponse.setBrand(shopperShelf.getId().getProduct().getBrand());
            productResponseList.add(productResponse);
        });
        shopper.setProducts(productResponseList);
        return shopper;
    }

    private static Integer getLimitValue(Integer limit) {
        return limit > Constants.LIMIT_MAXIMUM ? Constants.LIMIT_MAXIMUM : limit;
    }

}
