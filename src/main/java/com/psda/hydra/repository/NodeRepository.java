package com.psda.hydra.repository;

import com.psda.hydra.model.Node;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.Optional;


public interface NodeRepository extends JpaRepository<Node, Long> {

    @Async
    Page<Node> findByKadaster_Id(Long kadasterId, Pageable pageable);

    @Query("SELECT n FROM Node n WHERE LOWER(n.namaNode) = LOWER(:namaNode)")
    Optional<Node> cariNamaNode(@Param("namaNode") String namaNode);


}
