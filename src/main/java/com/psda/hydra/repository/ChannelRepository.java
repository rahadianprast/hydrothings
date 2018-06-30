package com.psda.hydra.repository;


import com.psda.hydra.model.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ChannelRepository extends JpaRepository<Channel, Long> {

   @Query(value = "select * from channel c WHERE node_id= ?1 AND created >= NOW() - INTERVAL '24 HOUR'",
         nativeQuery = true)
   List<Channel> getByHours(@Param("nodeId") Long nodeId);
}
