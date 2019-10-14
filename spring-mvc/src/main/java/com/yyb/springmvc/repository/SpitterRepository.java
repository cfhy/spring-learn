package com.yyb.springmvc.repository;

import com.yyb.springmvc.model.Spitter;

import java.util.List;


/**
 * Repository interface with operations for {@link Spitter} persistence.
 * @author habuma
 */
public interface SpitterRepository {

  long count();
  
  Spitter save(Spitter spitter);
  
  Spitter findOne(long id);

  Spitter findByUsername(String username);

  List<Spitter> findAll();

}
