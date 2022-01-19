package br.com.urlshortener.backendshortener.repository;

import br.com.urlshortener.backendshortener.model.Url;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;



public interface UrlRepository extends JpaRepository<Url, Long> {

    boolean existsByAlias(String alias);
    Url findByAlias(String alias);
    void deleteByAlias(String alias);
    List<Url> findAllByOrderByAliasAsc();

}
