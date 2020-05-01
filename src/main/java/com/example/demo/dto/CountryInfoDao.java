package com.example.demo.dto;

import com.example.demo.models.CountryInfo;
import com.example.demo.models.User;
import com.example.demo.validator.MyAnnot;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;


@Repository
public class CountryInfoDao {
    @PersistenceContext
    private EntityManager entityManager;

    @MyAnnot
    @Transactional
    public CountryInfo saveCountryInfo(CountryInfo countryInfo){
        entityManager.persist(countryInfo);
//        System.out.println(1/0);
        return countryInfo;
    }



    public CountryInfo findCountryInfo(Long id){
        return (CountryInfo) entityManager.find(CountryInfo.class, id);
    }

    public List<CountryInfo> getAllCountries() {
//        entityManager.findAll()
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CountryInfo> cq = cb.createQuery(CountryInfo.class);
        Root<CountryInfo> rootEntry = cq.from(CountryInfo.class);
        CriteriaQuery<CountryInfo> all = cq.select(rootEntry);
        TypedQuery<CountryInfo> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
//        return entityManager.createQuery("Select t from " + CountryInfo.getSimpleName() + " t").getResultList();
    }

    public void deleteCountryInfo(User user){
        entityManager.remove(user);
    }

    public void updateCountryInfo(User user){
        entityManager.merge(user);
    }
}
