package com.springapps.shop.repositories;


import com.springapps.shop.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//creez o interfata care extinde interfata JpaRepository in care voi crea metodele de
//tip CRUD, precum si alte metode pentru clasa Category si ma folosesc de adnotarea @Repository
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
